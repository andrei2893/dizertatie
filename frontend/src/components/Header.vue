<template>
    <div class="header">
        <div class="header-item"> 
            <md-button class="md-icon-button" @click="showNavigation = true" v-show="!showNavigation">
                <md-icon>menu</md-icon>
            </md-button>
        </div>
        <img class="logo" src="pictures/bee-live.png">
        <div class="header-item"> 
        </div>
       
       <md-drawer :md-active.sync="showNavigation" class="drower">
        <md-toolbar class="md-transparent" md-elevation="0">
            <span class="md-title color-white">Menu</span>
        </md-toolbar>

        <md-list class="drower-bk">
            <router-link :to="'/'">
                <md-list-item>  
                    <md-icon class="color-white">send</md-icon>
                    <span class="md-list-item-text color-white">Alerts</span>
                </md-list-item>
            </router-link>
             <router-link :to="'/announcements'">
                <md-list-item>  
                    <md-icon class="color-white">send</md-icon>
                    <span class="md-list-item-text color-white">Announcements</span>
                </md-list-item>
            </router-link>
            <router-link :to="'/clerk/alert'" v-if="isClerk || isAdmin"> 
                <md-list-item>  
                    <md-icon class="color-white">send</md-icon>
                    <span class="md-list-item-text color-white">Create alert</span>
                </md-list-item>
            </router-link>
            <router-link :to="'/announcements/create'" v-if="isBasicUser || isClerk || isAdmin"> 
                <md-list-item>  
                    <md-icon class="color-white">send</md-icon>
                    <span class="md-list-item-text color-white">Create announcement</span>
                </md-list-item>
            </router-link>

            <router-link :to="'/admin/user/insert'" v-if="isAdmin">
                <md-list-item>  
                    <md-icon class="color-white">send</md-icon>
                    <span class="md-list-item-text color-white">Create user</span>
                </md-list-item>
            </router-link>
            <router-link :to="'/admin/user/delete'" v-if="isAdmin">
                <md-list-item>  
                    <md-icon class="color-white">send</md-icon>
                    <span class="md-list-item-text color-white">Delete user</span>
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
    width:250px;

    background: transparent;
}
.drower-bk{
    color: white !important;

    background: transparent;
}
.color-white{
    color: white !important;
}
</style>
