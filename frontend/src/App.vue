<template>
  <div id="app">
    <Header/>
    <Login/>
    <md-table>
      <md-table-row>
        <md-table-head md-numeric>ID</md-table-head>
        <md-table-head>text</md-table-head>
      </md-table-row>

      <md-table-row v-for="comment in comments" :key="comment.id">
        <md-table-cell md-numeric>{{comment.id}}</md-table-cell>
        <md-table-cell>{{comment.text}}</md-table-cell>
      </md-table-row>
    </md-table>
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
      comments: []
    }
  },
  mounted() {
    findAllComments().then((response) => {
      this.comments = response;
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
}

body {
  margin: 0;
}
</style>
