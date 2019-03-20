<template>
    <div class="clerk-announcement">
        <md-card  class="orange-bk">
            <md-card-header>
                <div class="md-title">Submit an announcement</div>
            </md-card-header>

            <md-card-content>
               <form class="md-layout login-form" @submit.prevent="validateAnnouncement">
                    
                    <md-field :class="getValidationClass('text')">
                        <label for="text">Content</label>
                        <md-textarea type="text" name="text" id="content" v-model="announcement.text"/>
                        <span class="md-error" v-if="!$v.announcement.text.required">The content is required</span>
                    </md-field>

                </form>
            </md-card-content>
            <md-card-actions>
                <md-button type="submit" class="md-primary" @click="validateAnnouncement">Submit</md-button>
            </md-card-actions>
        </md-card>
        
        <md-snackbar md-position="center" :md-duration="2000" :md-active.sync="showAnnouncementSavedMessage">
            <span>Announcement has been saved</span>
        </md-snackbar>
    </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {findUserByEmail,registerClerk} from '@/api/user'
import {saveAnnouncement} from '@/api/announcement'
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
            announcement: {
                text: "",
                date: Date.now(),
                userId: getUserId(),
            },
            showAnnouncementSavedMessage: false,
        }
    },
    validations: {
      announcement: {
        text: {
          required,
        },
      }
    },
    mounted() {
    },
    methods: {
        getValidationClass (fieldName) {
            const field = this.$v.announcement[fieldName]
                if (field) {
                    return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },
        saveAnnouncement() {
            saveAnnouncement(this.announcement).then((ok)=>{
                if(ok) {
                    this.showAnnouncementSavedMessage = true;
                    this.announcement = {
                        text:'',
                        date: Date.now(),
                        userId: getUserId(),
                    }
                    this.$v.$reset()
                }
            })
        },
        validateAnnouncement() {
            this.$v.$touch()
            if (!this.$v.$invalid) {
                this.saveAnnouncement()
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
.clerk-announcement{
    width:800px;
}
</style>