<template>
    <div class="alerts">
        <md-card md-with-hover v-for="alert in alerts" :key="alert.id">
            <md-ripple>
                <md-card-header>
                    <div class="md-title">
                        <md-icon>warning</md-icon>
                        {{alert.title}}
                    </div>
                    <div class="md-subhead">{{new Date(alert.date)}}</div>
                </md-card-header>

                <md-card-content>
                    {{alert.text}}
                </md-card-content>

                <md-card-actions>
                    <md-button @click="toggleAlertComments(alert)">
                        {{ alertButtonText(alert.showComments) }}
                    </md-button>
                </md-card-actions>
            </md-ripple>
            <div class="alert-comments" v-if="alert.showComments">
                <md-field v-if="userLoggedIn" class="alert-text">
                    <label>write a comment</label>
                    <md-textarea v-model="alert.newComment"></md-textarea>
                </md-field>
                <md-button v-if="userLoggedIn" :disabled="!alert.newComment" @click="submitComment(alert)">
                    submit
                </md-button>

                <md-card md-with-hover class="comment" v-for="comment in alert.comments" :key="comment.id">
                    <md-card-content>
                        {{comment.text}}
                    </md-card-content>
                </md-card>
            </div>
        </md-card>
    </div>
</template>


<script>
import {findAllAlerts} from '@/api/alert';
import {findCommentsForAlert,submitComment} from '@/api/comment';
import {getUserId} from '@/storage'
import {userIsLoggedIn} from '@/storage'


export default {
  name: "alerts",
  data() {
    return {
      alerts: [],
      userLoggedIn: userIsLoggedIn()
    }
  },
  mounted() {
    findAllAlerts().then((response) => {
      this.alerts = response;
      this.alerts.forEach((alert)=> {
          this.$set(alert, 'showComments', false)
          this.$set(alert, 'newComment', '')
          findCommentsForAlert(alert.id).then((comments)=>{
              this.$set(alert, 'comments', comments.reverse())
          })
      })
    })
  },
  methods: {
      alertButtonText(showComments) {
          return (showComments) ? 'Hide comments' : 'Show comments'
      },
      toggleAlertComments(alert){
          alert.showComments = !alert.showComments;
      },
      submitComment(alert) {
          submitComment({
              alertId:alert.id,
              text:alert.newComment,
              userId:getUserId(),
          }).then((com)=>{
              alert.comments.splice(0, 0, com)
              alert.newComment=''
          })
      }
  },
};
</script>

<style>
.alerts {
    width: 75%;
}
.alerts > * {
    margin-bottom: 30px;
}
.alert-comments{
    margin: 10px;
    display:flex;
    flex-direction:column;
    align-items: flex-end;
}
.comment{
    width:100%;
}
.alert-text{
    margin-bottom:0;

}
</style>
