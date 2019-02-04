import VueRouter from 'vue-router'
import App from '@/App.vue'
import Alerts from '@/components/Alerts.vue'
import ClerkAlert from '@/components/clerk/ClerkAlert.vue'
import AdminDeleteUser from '@/components/admin/AdminDeleteUser.vue'
import AdminInsertUser from '@/components/admin/AdminInsertUser.vue'

const router = new VueRouter({
    routes: [
        {
            path: '/', 
            component: Alerts,
        },
        {
            path: '/clerk/alert',
            component: ClerkAlert,
        },
        {
            path: '/admin/user/delete',
            component: AdminDeleteUser,
        },
        {
            path: '/admin/user/insert',
            component: AdminInsertUser,
        }
    ]
})

export default router