<template>
  <div id="app">
    <div class="background-picture"></div>
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

<style scoped>

</style>


<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  display:  flex;
  flex-direction: column;
  align-items: center;
}

body {
  margin: 0;
  position:relative;
}
.background-picture{
  background: no-repeat center/150% url("./assets/poza.jpg");
  width: 100vw;
  height: 100vh;
  position: fixed;
  z-index: -1000;
}

.md-button-content{
  text-transform: capitalize;
}
.app-login{
  margin-bottom: 20px;
}

.orange-bk{
  /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#fceabb+0,fccd4d+50,f8b500+98,fbdf93+100 */
background: #fceabb; /* Old browsers */
background: -moz-radial-gradient(center, ellipse cover, #fceabb 0%, #fccd4d 50%, #f8b500 98%, #fbdf93 100%); /* FF3.6-15 */
background: -webkit-radial-gradient(center, ellipse cover, #fceabb 0%,#fccd4d 50%,#f8b500 98%,#fbdf93 100%); /* Chrome10-25,Safari5.1-6 */
background: radial-gradient(ellipse at center, #fceabb 0%,#fccd4d 50%,#f8b500 98%,#fbdf93 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fceabb', endColorstr='#fbdf93',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
}

.red-bk{
  /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#fceabb+0,fccd4d+50,f8b500+98,fbdf93+100 */
/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#b7deed+0,71ceef+53,21b4e2+92,b7deed+100 */
background: rgb(183,222,237); /* Old browsers */
background: -moz-radial-gradient(center, ellipse cover, rgba(183,222,237,1) 0%, rgba(113,206,239,1) 53%, rgba(33,180,226,1) 92%, rgba(183,222,237,1) 100%); /* FF3.6-15 */
background: -webkit-radial-gradient(center, ellipse cover, rgba(183,222,237,1) 0%,rgba(113,206,239,1) 53%,rgba(33,180,226,1) 92%,rgba(183,222,237,1) 100%); /* Chrome10-25,Safari5.1-6 */
background: radial-gradient(ellipse at center, rgba(183,222,237,1) 0%,rgba(113,206,239,1) 53%,rgba(33,180,226,1) 92%,rgba(183,222,237,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#b7deed', endColorstr='#b7deed',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
}
</style>
