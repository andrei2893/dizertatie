<template>
    <div class="alerts">
        <md-card md-with-hover v-for="alert in alerts" :key="alert.id"
                 class="alert-item orange-bk">
            <md-ripple>
                <md-card-header>
                    <div class="md-title">
                        <md-icon style="color:red;">warning</md-icon>
                        {{alert.title}}
                    </div>
                    <div class="md-subhead">{{new Date(alert.date).toLocaleString()}}</div>
                </md-card-header>

                <md-card-content>
                    {{alert.text}}
                </md-card-content>

                <md-card-actions>
                    <md-button @click="toggleMap(alert)">
                        show map
                    </md-button>
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


        <div class="map">
            <md-dialog :md-active.sync="showMapDialog">
                <div class="map">
                <div id="mapid" ref="mapid"></div>
                </div>
            </md-dialog>
        </div>
        
        <Weather class="Weather"/>

    </div>
</template>


<script>
import Weather from '@/components/Weather.vue';
import {findAllAlerts} from '@/api/alert';
import {findCommentsForAlert,submitComment} from '@/api/comment';
import {getUserId} from '@/storage'
import {userIsLoggedIn} from '@/storage'


export default {
  name: "alerts",
  components:{
      Weather,
  },
  data() {
    return {
      alerts: [],
      userLoggedIn: userIsLoggedIn(),
      showMapDialog: false,
      currentAlert: null,
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
  updated() {
    if (this.showMapDialog) {
        var mymap = L.map('mapid').setView([45.78, 24.87], 6.1);
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiYW5kcmVpMjgwNSIsImEiOiJjanMzaDM5M3EyN2wzNDVseG1nZTNyNGZkIn0.nt1jpi_Z1-x0fvTCbNMISA', {
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            maxZoom: 18,
            id: 'mapbox.streets',
            accessToken: 'pk.eyJ1IjoiYW5kcmVpMjgwNSIsImEiOiJjanMzaDM5M3EyN2wzNDVseG1nZTNyNGZkIn0.nt1jpi_Z1-x0fvTCbNMISA'
        }).addTo(mymap);

        var popup = L.popup();
        popup
            .setLatLng(L.latLng(this.currentAlert.lat, this.currentAlert.lng))
            .setContent("The Event will take place here: (" + Number(this.currentAlert.lat).toFixed(2) + ", " + Number(this.currentAlert.lng).toFixed(2) +")")
            .openOn(mymap);
    }
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
            entityId:alert.id,
            text:alert.newComment,
            userId:getUserId(),
        }).then((com)=>{
            alert.comments.splice(0, 0, com)
            alert.newComment=''
        })
    },
    toggleMap(alert) {
        this.showMapDialog = true;
        this.currentAlert = alert;
    }
  },
};
</script>

<style scoped>
.alerts {
    width: 60%;
    position: relative;
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
    /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#d2ff52+0,91e842+100;Neon */
background: #d2ff52; /* Old browsers */
background: -moz-radial-gradient(center, ellipse cover, #d2ff52 0%, #91e842 100%); /* FF3.6-15 */
background: -webkit-radial-gradient(center, ellipse cover, #d2ff52 0%,#91e842 100%); /* Chrome10-25,Safari5.1-6 */
background: radial-gradient(ellipse at center, #d2ff52 0%,#91e842 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#d2ff52', endColorstr='#91e842',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
}
.alert-text{
    margin-bottom:0;

}
.map {
    all: unset;
}
#mapid{
    width:500px;
    height: 500px;
}
.alert-item{
    width: 100%;
    opacity: .9;
}
.alert-item-visible{
    opacity: 1;
}
.alert-item:hover{
    transition: linear 150ms;
    opacity: 1;
}
.Weather{
  position: absolute;
  top: 20px;
  right: -180px;
}
</style>
