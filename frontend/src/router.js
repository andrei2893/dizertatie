import VueRouter from 'vue-router'
import App from '@/App.vue'
import Alerts from '@/components/Alerts.vue'
import Announcements from '@/components/Announcements.vue'
import CreateAnnouncement from '@/components/basic/CreateAnnouncement.vue'
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
            path: '/announcements', 
            component: Announcements,
        },
        {
            path: '/announcements/create', 
            component: CreateAnnouncement,
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