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
                    <md-button>Show comments</md-button>
                </md-card-actions>
            </md-ripple>
        </md-card>
    </div>
</template>


<script>
import {findAllAlerts} from '@/api/alert.js';


export default {
  name: "alerts",
  data() {
    return {
     
      alerts: [],
    }
  },
  mounted() {
    findAllAlerts().then((response) => {
      this.alerts = response;
    })
  }
};
</script>

<style>
.alerts {
    width: 50%;
}
</style>
