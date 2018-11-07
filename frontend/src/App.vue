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

    <md-list>
      <md-list-item v-for="alert in alerts" :key="alert.id">
        <md-icon>warning</md-icon>
        <span class="md-list-item-text">{{alert.text}}</span>
        
      </md-list-item>
    </md-list>
  </div>
</template>

<script>
import Header from '@/components/Header.vue';
import Login from '@/components/Login.vue';
import {findAllComments} from '@/api/comment.js';
import {findAllAlerts} from '@/api/alert.js';

export default {
  name: 'app',
  components: {
    Header,
    Login,
  },
  data() {
    return {
      comments: [], 
      alerts: [],
    }
  },
  mounted() {
    findAllComments().then((response) => {
      this.comments = response;
    })
    findAllAlerts().then((response) => {
      this.alerts = response;
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
