import VueRouter from 'vue-router'
import App from '@/App.vue'
import Alerts from '@/components/Alerts.vue'

const router = new VueRouter({
    routes: [
        {
            path: '/', 
            component: Alerts,
        }
    ]
})

export default router