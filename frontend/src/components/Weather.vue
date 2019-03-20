<template>
    <md-content class="weather" v-if="temp && lat && icon">
        <img :src="niceIcon"> 
        <div>{{niceWeather}}</div>
    </md-content>
</template>

<script>
import {getWeather} from '@/api/weather'

export default {
    data() {
        return {
            temp: null,
            lat: null,
            lng: null,
            icon: null,
        }
    },
    mounted() {
        if ("geolocation" in navigator) {
            const me=this
            navigator.geolocation.getCurrentPosition((position) => {
                this.lat = position.coords.latitude
                this.lng = position.coords.longitude
                getWeather(this.lat, this.lng).then((ra)=>{
                    this.temp = Math.trunc(ra.main.temp-273.15)
                    this.icon = ra.weather[0].icon
                })
            });
        }
        
    },
    computed: {
        niceWeather() {
            return `${this.temp}°C`
        },
        niceIcon() {
            return `http://openweathermap.org/img/w/${this.icon}.png`
        }
    }
}
</script>

<style scoped>
.weather{
    /* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#fefcea+0,f1da36+99,f4ef64+100 */
background: rgb(254,252,234); /* Old browsers */
background: -moz-radial-gradient(center, ellipse cover, rgba(254,252,234,1) 0%, rgba(241,218,54,1) 99%, rgba(244,239,100,1) 100%); /* FF3.6-15 */
background: -webkit-radial-gradient(center, ellipse cover, rgba(254,252,234,1) 0%,rgba(241,218,54,1) 99%,rgba(244,239,100,1) 100%); /* Chrome10-25,Safari5.1-6 */
background: radial-gradient(ellipse at center, rgba(254,252,234,1) 0%,rgba(241,218,54,1) 99%,rgba(244,239,100,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fefcea', endColorstr='#f4ef64',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
    color: black;
    width: 100px;
    height: 100px;
    border-radius: 100px;
     
    display: flex;
    flex-direction:column; 
    justify-content: center;
    align-items: center;
}
</style>
