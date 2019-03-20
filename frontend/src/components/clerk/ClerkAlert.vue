<template>
    <div class="clerk-alert">
        <md-card  class="orange-bk">
            <md-card-header>
                <div class="md-title">Submit an alert</div>
            </md-card-header>

            <md-card-content>
               <form class="md-layout login-form" @submit.prevent="validateAlert">
                    <md-field :class="getValidationClass('title')">
                        <label for="title">Title</label>
                        <md-input type="text" name="title" id="title" v-model="alert.title"/>
                        <span class="md-error" v-if="!$v.alert.title.required">The title is required</span>
                    </md-field>
                    <md-field :class="getValidationClass('address')">
                        <label for="address">Address</label>
                        <md-input type="text" name="address" id="adress" v-model="alert.address"/>
                        <span class="md-error" v-if="!$v.alert.address.required">The address is required</span>
                    </md-field>
                    <md-field :class="getValidationClass('text')">
                        <label for="text">Content</label>
                        <md-textarea type="text" name="text" id="content" v-model="alert.text"/>
                        <span class="md-error" v-if="!$v.alert.text.required">The content is required</span>
                    </md-field>
                    <md-field :class="getValidationClass('date')">
                        <md-datepicker v-model="alert.date" md-immediately />
                        <span class="md-error" v-if="!$v.alert.date.required">The date is required</span>
                    </md-field>
                </form>
            </md-card-content>
            <md-card-actions>
                <md-button type="submit" class="md-primary" @click="validateAlert">Submit</md-button>
            </md-card-actions>
        </md-card>
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showNoCoordinateMessage">
            <span>Click on the map to pick a coordoate</span>
        </md-snackbar>
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showAlertSavedMessage">
            <span>Alert has been saved</span>
        </md-snackbar>
        <div id="mapid" ref="mapid"></div>
    </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {findUserByEmail,registerClerk} from '@/api/user'
import {saveAlert} from '@/api/alert'
import {getCredentials, getUserId} from '@/storage'
import { map } from 'leaflet';
import {
    required,
    email,
    minLength,
    maxLength
} from 'vuelidate/lib/validators'

export default {
    mixins: [validationMixin],

    data() {
        return {
            alert: {
                title: null,
                address: "", 
                text: "",
                date: null,
                lat: null,
                lng:null,
                userId: getUserId(),
            },
            showAlertSavedMessage: false,
            showNoCoordinateMessage:false,
        }
    },
    validations: {

      alert: {
        text: {
          required,
        },
        address: {
          required,
        },
        title: {
            required,
        },
        date: {
            required,
        }
      }
    },
    mounted() {
        var mymap = L.map('mapid').setView([45.78, 24.87], 6.1);
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiYW5kcmVpMjgwNSIsImEiOiJjanMzaDM5M3EyN2wzNDVseG1nZTNyNGZkIn0.nt1jpi_Z1-x0fvTCbNMISA', {
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            maxZoom: 18,
            id: 'mapbox.streets',
            accessToken: 'pk.eyJ1IjoiYW5kcmVpMjgwNSIsImEiOiJjanMzaDM5M3EyN2wzNDVseG1nZTNyNGZkIn0.nt1jpi_Z1-x0fvTCbNMISA'
        }).addTo(mymap);

        var popup = L.popup();
        mymap.on('click', (e) => {
            this.alert.lat = e.latlng.lat
            this.alert.lng = e.latlng.lng

            popup
                .setLatLng(e.latlng)
                .setContent("The Event will take place here: (" + Number(e.latlng.lat).toFixed(2) + ", " + Number(e.latlng.lng).toFixed(2) +")")
                .openOn(mymap);
        });
    },
    methods: {
        getValidationClass (fieldName) {
            const field = this.$v.alert[fieldName]
                if (field) {
                    return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },
        saveAlert() {
            saveAlert(this.alert).then((ok)=>{
                if(ok) {
                    this.showAlertSavedMessage = true;
                    this.alert = {
                        title: null,
                        address: "", 
                        text:'',
                        date: null,
                        lat: null,
                        lng:null,
                        userId: getUserId(),
                    }
                    this.$v.$reset()
                }
            })
        },
        validateAlert() {
            if (!this.alert.lat){
                this.showNoCoordinateMessage = true;
                return
            }
            this.$v.$touch()
            if (!this.$v.$invalid) {
                this.saveAlert()
            }
        }
    }
}
</script>

<style scoped>
#mapid {
    height: 300px;
    margin:auto;
    margin-bottom: 40px;
    width: calc(100%-4px);   
    border: 1px solid black;
    z-index: 0;
}
.clerk-alert{
    width:800px;
}
</style>