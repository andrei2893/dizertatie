<template>
    <div class="header">
        <div class="header-item"> 
            <md-button class="md-icon-button" @click="showNavigation = true">
                <md-icon>menu</md-icon>
            </md-button>
        </div>
        <img class="logo" src="pictures/bee-live.png">
        <div class="header-item"> 
        </div>
       
       <md-drawer :md-active.sync="showNavigation" class="drower">
        <md-toolbar class="md-transparent" md-elevation="0">
            <span class="md-title">Menu</span>
        </md-toolbar>

        <md-list>
            <router-link :to="'/'">
                <md-list-item>  
                    <md-icon>send</md-icon>
                    <span class="md-list-item-text">Alerts</span>
                </md-list-item>
            </router-link>
            <router-link :to="'/clerk/alert'" v-if="isClerk"> 
                <md-list-item>  
                    <md-icon>send</md-icon>
                    <span class="md-list-item-text">Create alert</span>
                </md-list-item>
            </router-link>

            <router-link :to="'/admin/user/insert'" v-if="isAdmin">
                <md-list-item>  
                    <md-icon>send</md-icon>
                    <span class="md-list-item-text">Create user</span>
                </md-list-item>
            </router-link>
            <router-link :to="'/admin/user/delete'" v-if="isAdmin">
                <md-list-item>  
                    <md-icon>send</md-icon>
                    <span class="md-list-item-text">Delete user</span>
                </md-list-item>
            </router-link>
        </md-list>
        </md-drawer>
    </div>
</template>

<script>
import {userIsLoggedIn, getRole} from '@/storage'

export default {
    data(){
        return{
            showNavigation: false, 
            userIsLoggedIn: userIsLoggedIn(),
            role: getRole(),
        }
    },
    mounted() {
    },
    computed: {
        isLoggedOut() {
            return !this.userIsLoggedIn
        },
        isBasicUser() {
            return this.userIsLoggedIn && this.role === 'BASIC'
        },
        isClerk() {
            return this.userIsLoggedIn && this.role === 'CLERK'
        },
        isAdmin() {
            return this.userIsLoggedIn && this.role === 'ADMIN'
        }

    }

}
</script>

<style scoped>
.header {
    width: 100%;
    height: 80px;
    /* background-color:black; */
    display: flex;  
    justify-content: space-between;
}
.header-item{
    height:100%;
    width: 100px;
    display:flex;
    align-items: center;
    justify-content: center;
}
.logo {
    height: 100%;
}
.drower{
    width:200px;
}
</style>
