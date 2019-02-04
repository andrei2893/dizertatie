<template>
  <div id="app">
    <Header/>
    <Login class="app-login"/>
    
    <router-view></router-view> 
  </div>
</template>

<script>
import Header from '@/components/Header.vue';
import Menu from '@/components/Menu.vue';
import Login from '@/components/Login.vue';
import {findAllComments} from '@/api/comment.js';

export default {
  name: 'app',
  components: {
    Header,
    Login,
    Menu,
  },
  data() {
    return {
      
    }
  },
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  display:  flex;
  flex-direction: column;
  align-items: center;
}

body {
  margin: 0;
  background: no-repeat center/150% url("./assets/poza.jpg");
}

.md-button-content{
  text-transform: capitalize;
}
.app-login{
  margin-bottom: 20px;
}
</style>
