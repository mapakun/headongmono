<template>
  <div class="login-wrapper">
    <div class="login-box">
      <h1>해동여객 학습용</h1>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label>아이디</label>
          <input v-model="id" type="text" placeholder="아이디를 입력하세요" />
        </div>
        <div class="form-group">
          <label>비밀번호</label>
          <input v-model="password" type="password" placeholder="비밀번호를 입력하세요" />
        </div>
        <p v-if="errorMsg" class="error">{{ errorMsg }}</p>
        <button type="submit">로그인</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth.js'

const id = ref('')
const password = ref('')
const errorMsg = ref('')
const router = useRouter()
const authStore = useAuthStore()

async function handleLogin() {
  try {
    errorMsg.value = ''
    await authStore.login(id.value, password.value)
    router.push('/')
  } catch (e) {
    errorMsg.value = '아이디 또는 비밀번호가 올바르지 않습니다.'
  }
}
</script>

<style scoped>
.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}
.login-box {
  background: white;
  padding: 48px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
  width: 360px;
}
h1 {
  font-size: 20px;
  text-align: center;
  margin-bottom: 32px;
  color: #333;
}
.form-group {
  margin-bottom: 16px;
}
label {
  display: block;
  font-size: 14px;
  margin-bottom: 6px;
  color: #555;
}
input {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  box-sizing: border-box;
}
button {
  width: 100%;
  padding: 12px;
  background-color: #1890ff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 8px;
}
.error {
  color: red;
  font-size: 13px;
  margin-bottom: 8px;
}
</style>