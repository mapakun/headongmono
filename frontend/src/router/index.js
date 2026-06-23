import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth.js'

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/auth/LoginView.vue'),
        meta: { requiresAuth: false }
    },
    {
        path: '/',
        name: 'Home',
        component: () => import('@/views/home/HomeView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/drivers',
        name: 'DriverList',
        component: () => import('@/views/driver/DriverListView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/vehicles',
        name: 'VehicleList',
        component: () => import('@/views/vehicle/VehicleListView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/routes',
        name: 'RouteList',
        component: () => import('@/views/route/RouteListView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/dispatches',
        name: 'DispatchList',
        component: () => import('@/views/dispatch/DispatchListView.vue'),
        meta: { requiresAuth: true }
    },
    {
        path: '/assignments',
        name: 'AssignmentView',
        component: () => import('@/views/assignment/AssignmentView.vue'),
        meta: { requiresAuth: true }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to) => {
    const authStore = useAuthStore()
    if (to.meta.requiresAuth && !authStore.isLoggedIn) {
        return { name: 'Login' }
    }
})

export default router