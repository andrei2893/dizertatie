<template>
  <div id="app">
    <Header/>
    <Login/>
    <router-view></router-view> 
  </div>
</template>

<script>
import Header from '@/components/Header.vue';
import Login from '@/components/Login.vue';
import {findAllComments} from '@/api/comment.js';


export default {
  name: 'app',
  components: {
    Header,
    Login,
  },
  data() {
    return {
      
    }
  },
  mounted() {
    
  }
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
}
</style>
