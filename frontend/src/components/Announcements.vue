<template>
    <div class="announcements">
        <md-card md-with-hover v-for="announcement in announcements" :key="announcement.id"
                 class="announcement-item red-bk">
            <md-ripple>
                <md-card-header>
                    <div class="md-title">
                        <md-icon style="color:brown;">supervisor_account</md-icon>
                        {{new Date(announcement.date).toLocaleString()}}
                    </div>
                    
                </md-card-header>

                <md-card-content>
                    {{announcement.text}}
                </md-card-content>

                <md-card-actions>
                    
                    <md-button @click="toggleAnnouncementComments(announcement)">
                        {{ announcementButtonText(announcement.showComments) }}
                    </md-button>
                </md-card-actions>
            </md-ripple>
            <div class="announcement-comments" v-if="announcement.showComments">
                <md-field v-if="userLoggedIn" class="announcement-text">
                    <label>write a comment</label>
                    <md-textarea v-model="announcement.newComment"></md-textarea>
                </md-field>
                <md-button v-if="userLoggedIn" :disabled="!announcement.newComment" @click="submitComment(announcement)">
                    submit
                </md-button>

                <md-card md-with-hover class="comment" v-for="comment in announcement.comments" :key="comment.id">
                    <md-card-content>
                        {{comment.text}}
                    </md-card-content>
                </md-card>
            </div>
        </md-card>


    </div>
</template>


<script>
import {findAllAnnouncements} from '@/api/announcement';
import {findCommentsForAnnouncement,submitAnnouncementComment} from '@/api/comment';
import {getUserId} from '@/storage'
import {userIsLoggedIn} from '@/storage'


export default {
  name: "announcements",
  data() {
    return {
      announcements: [],
      userLoggedIn: userIsLoggedIn(),
      showMapDialog: false,
      currentAnnouncement: null,
    }
  },
  mounted() {
    findAllAnnouncements().then((response) => {
      this.announcements = response;
      this.announcements.forEach((announcement)=> {
          this.$set(announcement, 'showComments', false)
          this.$set(announcement, 'newComment', '')
          findCommentsForAnnouncement(announcement.id).then((comments)=>{
              this.$set(announcement, 'comments', comments.reverse())
          })
      })
    })
  },
  updated() {
  },
  methods: {
    announcementButtonText(showComments) {
        return (showComments) ? 'Hide comments' : 'Show comments'
    },
    toggleAnnouncementComments(announcement){
        announcement.showComments = !announcement.showComments;
    },
    submitComment(announcement) {

        submitAnnouncementComment({
            entityId:announcement.id,
            text:announcement.newComment,
            userId:getUserId(),
        }).then((com)=>{
            announcement.comments.splice(0, 0, com)
            announcement.newComment=''
        })
    },
    toggleMap(announcement) {
        this.showMapDialog = true;
        this.currentAnnouncement = announcement;
    }
  },
};
</script>

<style scoped>
.announcements {
    width: 75%;
}
.announcements > * {
    margin-bottom: 30px;
}
.announcement-comments{
    margin: 10px;
    display:flex;
    flex-direction:column;
    align-items: flex-end;
}
.comment{
    width:100%;
    /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#d2ff52+0,91e842+100;Neon */
background: #d2ff52; /* Old browsers */
background: -moz-radial-gradient(center, ellipse cover, #d2ff52 0%, #91e842 100%); /* FF3.6-15 */
background: -webkit-radial-gradient(center, ellipse cover, #d2ff52 0%,#91e842 100%); /* Chrome10-25,Safari5.1-6 */
background: radial-gradient(ellipse at center, #d2ff52 0%,#91e842 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#d2ff52', endColorstr='#91e842',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
}
.announcement-text{
    margin-bottom:0;

}
.map {
    all: unset;
}
#mapid{
    width:500px;
    height: 500px;
}
.announcement-item{
    width: 800px;
    opacity: .9;
}
.announcement-item-visible{
    opacity: 1;
}
.announcement-item:hover{
    transition: linear 150ms;
    opacity: 1;
}
</style>
