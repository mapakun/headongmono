import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import api from '@/api/index.js'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') || null)
    const user = ref(JSON.parse(localStorage.getItem('user')) || null)

    const isLoggedIn = computed(() => !!token.value)
    const userRole = computed(() => user.value?.role || null)

    async function login(id, password) {
        const response = await api.post('/auth/login', { id, password })
        token.value = response.data.token
        user.value = {
            id: response.data.id,
            username: response.data.username,
            role: response.data.role
        }
        localStorage.setItem('token', token.value)
        localStorage.setItem('user', JSON.stringify(user.value))
    }

    function logout() {
        token.value = null
        user.value = null
        localStorage.removeItem('token')
        localStorage.removeItem('user')
    }

    return { token, user, isLoggedIn, userRole, login, logout }
})