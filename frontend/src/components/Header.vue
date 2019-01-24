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
            <md-list-item>
            <md-icon>move_to_inbox</md-icon>
            <span class="md-list-item-text">Inbox</span>
            </md-list-item>

            <md-list-item>
            <md-icon>send</md-icon>
            <span class="md-list-item-text">Sent Mail</span>
            </md-list-item>

            <md-list-item>
            <md-icon>delete</md-icon>
            <span class="md-list-item-text">Trash</span>
            </md-list-item>

            <md-list-item>
            <md-icon>error</md-icon>
            <span class="md-list-item-text">Spam</span>
            </md-list-item>
        </md-list>
        </md-drawer>
    </div>
</template>

<script>
export default {
    data(){
        return{
            showNavigation:false,
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
