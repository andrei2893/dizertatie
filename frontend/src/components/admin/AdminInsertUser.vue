<template>
    <div>
        <md-card>
            <md-card-header>
                <div class="md-title">Register Clerk</div>
            </md-card-header>

            <md-card-content>
               <form novalidate class="md-layout login-form" @submit.prevent="validateUserForRegister">
                    <md-field :class="getValidationClassForReg('email')">
                        <label for="email">Email</label>
                        <md-input type="email" name="email" id="email" autocomplete="email" v-model="reg.email"/>
                        <span class="md-error" v-if="!$v.reg.email.required">The email is required</span>
                        <span class="md-error" v-else-if="!$v.reg.email.email">Invalid email</span>
                    </md-field>
                    <md-field :class="getValidationClassForReg('password')">
                        <label for="password">Password</label>
                        <md-input name="password" id="password" v-model="reg.password" />
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
                    
                </form>
            </md-card-content>

            <md-card-actions>
                <md-button type="submit" class="md-primary" @click="register">register</md-button>
            </md-card-actions>
        </md-card>
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showRegisterError">
            <span>Email must be unique</span>
        </md-snackbar>
    </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {required,email} from 'vuelidate/lib/validators'
import {findUserByEmail,registerClerk} from '@/api/user'
import {getCredentials} from '@/storage'

export default { 
    mixins: [validationMixin],
    data() {
        return {
            reg: {
                email: null,
                password: null,
                name: null,
                address: "",
            },
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
        getValidationClassForReg (fieldName) {
            const field = this.$v.reg[fieldName]
                if (field) {
                    return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },
        register() {
            registerClerk(this.reg, getCredentials()).then((ok) => {
                 if (!ok) {
                    this.showRegisterError= true
                } else {
                    ""
                    this.$v.$reset()
                }
            })
        },
    }
}
</script>

<style>

</style>
