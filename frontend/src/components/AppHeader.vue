<template>
  <header v-if="authStore.isLoggedIn" class="app-header">
    <RouterLink to="/" class="home-link">해동여객</RouterLink>
    <nav>
      <RouterLink to="/drivers">기사 관리</RouterLink>
      <RouterLink to="/vehicles">차량 관리</RouterLink>
      <RouterLink to="/routes">노선 관리</RouterLink>
      <RouterLink to="/dispatches">배차 현황</RouterLink>
      <RouterLink to="/assignments">전속현황</RouterLink>
    </nav>
    <button @click="handleLogout">로그아웃</button>
  </header>
</template>

<script setup>
import { RouterLink, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth.js'

const router = useRouter()
const authStore = useAuthStore()

function handleLogout() {
  authStore.logout()
  router.push('/login')
}
</script>

<style scoped>
.app-header {
  display: flex;
  align-items: center;
  gap: 24px;
  padding: 16px 32px;
  background-color: #1f2937;
  color: white;
}
.home-link {
  font-weight: bold;
  color: white;
  text-decoration: none;
}
nav {
  display: flex;
  gap: 16px;
  flex: 1;
}
nav a {
  color: #d1d5db;
  text-decoration: none;
}
nav a.router-link-active {
  color: white;
  font-weight: bold;
}
button {
  padding: 6px 14px;
  background-color: #374151;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
