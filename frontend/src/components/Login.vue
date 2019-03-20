<template>
    <div class="login">
        <md-button class="md-dense md-primary" v-if="!userLoggedIn" @click="showLoginDialog">Login</md-button>
        <md-button class="md-dense md-primary" v-if="!userLoggedIn" @click="showRegisterDialog">Register</md-button>
        <md-button class="md-dense md-primary" v-if="userLoggedIn" @click="logout">Logout</md-button>

        <md-dialog :md-active.sync="loginDialogVisible"  class="orange-bk">
            <md-dialog-title>Login</md-dialog-title>

            <form novalidate class="md-layout login-form" @submit.prevent="validateUser">
                <md-field :class="getValidationClass('email')">
                    <label for="email">Email</label>
                    <md-input type="email" name="email" id="email" autocomplete="email" v-model="form.email"/>
                    <span class="md-error" v-if="!$v.form.email.required">The email is required</span>
                    <span class="md-error" v-else-if="!$v.form.email.email">Invalid email</span>
                </md-field>
                <md-field :class="getValidationClass('password')">
                    <label for="password">Password</label>
                    <md-input name="password" id="password" type="password" v-model="form.password" />
                    <span class="md-error" v-if="!$v.form.password.required">The password is required</span>
                </md-field>  
                <md-button type="submit" class="md-primary" @click="login">login</md-button>
            </form>
        </md-dialog>

        <md-dialog :md-active.sync="registerDialogVisible" class="orange-bk">
            <md-dialog-title>Register</md-dialog-title>

            <form novalidate class="md-layout login-form" @submit.prevent="validateUserForRegister">
                <md-field :class="getValidationClassForReg('email')">
                    <label for="email">Email</label>
                    <md-input type="email" name="email" id="email" autocomplete="email" v-model="reg.email"/>
                    <span class="md-error" v-if="!$v.reg.email.required">The email is required</span>
                    <span class="md-error" v-else-if="!$v.reg.email.email">Invalid email</span>
                </md-field>
                <md-field :class="getValidationClassForReg('password')">
                    <label for="password">Password</label>
                    <md-input name="password" id="password" type="password" v-model="reg.password" />
                    <span class="md-error" v-if="!$v.reg.password.required">The password is required</span>
                </md-field>
                <md-field :class="getValidationClassForReg('name')">
                    <label for="name">Name</label>
                    <md-input type="text" name="name" id="name" v-model="reg.name"/>
                    <span class="md-error" v-if="!$v.reg.name.required">The name is required</span>
                </md-field>
                <md-field>
                    <label for="address">Address</label>
                    <md-input type="text" name="address" id="address" v-model="reg.address"/>
                </md-field>
                <md-button type="submit" class="md-primary" @click="register">register</md-button>
            </form>
        </md-dialog>

        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showLoginError">
            <span>Invalid credentials!</span>
        </md-snackbar>

        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showRegisterError">
            <span>Email must be unique</span>
        </md-snackbar>
    </div>
</template>


<script>

import { validationMixin } from 'vuelidate'
import {required,email} from 'vuelidate/lib/validators'
import {findUserByEmail,register} from '@/api/user'
import {saveCredentials,userIsLoggedIn,deleteCredentials,setRole,setUserId, deleteUserId} from '@/storage'


export default {
    name: 'Login',
    mixins: [validationMixin],
    data(){
        return {
            loginDialogVisible: false,
            form: {
                password: null,
                email: null,
            },
            reg:{
                email: null,
                password: null,
                name: null,
                address: "",

            },
            userLoggedIn: userIsLoggedIn(),
            showLoginError: false,
            registerDialogVisible:false,
            showRegisterError: false,
        }
    },
    validations: {
      form: {
        password: {
          required,
        },
        email: {
          required,
          email
        }
      },
      reg: {
        password: {
          required,
        },
        email: {
          required,
          email
        },
        name: {
            required,
        }
      }
    },
    methods: {
        showLoginDialog() {
            this.loginDialogVisible = true;
        },
        showRegisterDialog() {
            this.registerDialogVisible = true
        },
        getValidationClass (fieldName) {
        const field = this.$v.form[fieldName]
            if (field) {
                return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },
        getValidationClassForReg (fieldName) {
            const field = this.$v.reg[fieldName]
                if (field) {
                    return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },

        login() {
            const credentials = 'Basic '+btoa(this.form.email+':'+this.form.password)
            findUserByEmail(this.form.email, credentials).then((response) => {
                if (response === false) {
                    this.showLoginError = true

                } else {
                    saveCredentials(credentials)
                    this.loginDialogVisible = false
                    this.userLoggedIn = true
                    setRole(response.role) 
                    setUserId(response.id)
                }

            })
        },
        logout() {
            deleteCredentials()
            this.userLoggedIn = false
            deleteUserId()
        },
        validateUser() {
            this.$v.$touch()
            if (!this.$v.$invalid) {
                this.login()
            }
        },
        validateUserForRegister() {
            this.$v.$touch()
            if (!this.$v.$invalid) {
                this.register()
            }
        },
        register() {
            register(this.reg).then(ok=>{
                if (!ok) {
                    this.showRegisterError= true
                } else {
                    const credentials = 'Basic ' + btoa(this.reg.email+':'+this.reg.password)
                    findUserByEmail(this.reg.email, credentials).then((response) => {
                        if (response === false) {
                            this.showLoginError = true

                        } else {
                            saveCredentials(credentials)
                            this.loginDialogVisible = false
                            this.userLoggedIn = true
                            setRole(response.role) 
                            setUserId(response.id)

                            this.registerDialogVisible= false
                            this.$v.$reset()
                        }

                    })
                }
            })
        },
    }
}
</script>

<style scoped>
.login {
    width: 100%;
    height: 50px;
    display: flex;
    justify-content: flex-end;
    align-items:center;
}

.login-form {
    padding: 24px;
    padding-top: 0;
    justify-content: flex-end;
}
</style>

