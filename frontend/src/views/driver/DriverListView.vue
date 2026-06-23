<template>
  <div class="page">
    <h1>기사 관리</h1>

    <form class="add-form" @submit.prevent="handleCreate">
      <input v-model="form.id" placeholder="아이디" />
      <input v-model="form.name" placeholder="이름" />
      <input v-model="form.phone" placeholder="휴대폰" />
      <input v-model="form.licenseNumber" placeholder="면허번호" />
      <select v-model="form.status">
        <option value="ACTIVE">재직</option>
        <option value="INACTIVE">퇴사</option>
      </select>
      <button type="submit">등록</button>
    </form>

    <p v-if="errorMsg" class="error">{{ errorMsg }}</p>

    <table>
      <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>휴대폰</th>
          <th>면허번호</th>
          <th>상태</th>
          <th>등록일</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="driver in drivers" :key="driver.id">
          <td>{{ driver.id }}</td>
          <td>{{ driver.name }}</td>
          <td>{{ driver.phone }}</td>
          <td>{{ driver.licenseNumber }}</td>
          <td>{{ driver.status }}</td>
          <td>{{ formatDate(driver.createdAt) }}</td>
          <td><button @click="handleDelete(driver.id)">삭제</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { getDrivers, createDriver, deleteDriver } from '@/api/driver.js'

const drivers = ref([])
const errorMsg = ref('')

const form = reactive({
  id: '',
  name: '',
  phone: '',
  licenseNumber: '',
  status: 'ACTIVE'
})

function formatDate(epoch) {
  if (!epoch) return ''
  return new Date(epoch).toLocaleDateString()
}

async function loadDrivers() {
  const response = await getDrivers()
  drivers.value = response.data
}

async function handleCreate() {
  if (!form.id.trim() || !form.name.trim()) {
    errorMsg.value = '아이디와 이름은 필수입니다.'
    return
  }

  try {
    errorMsg.value = ''
    await createDriver({ ...form })
    form.id = ''
    form.name = ''
    form.phone = ''
    form.licenseNumber = ''
    form.status = 'ACTIVE'
    await loadDrivers()
  } catch (e) {
    errorMsg.value = '기사 등록에 실패했습니다.'
  }
}

async function handleDelete(id) {
  await deleteDriver(id)
  await loadDrivers()
}

onMounted(loadDrivers)
</script>

<style scoped>
.page {
  padding: 32px;
}
.add-form {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}
input, select {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
button {
  padding: 8px 16px;
  background-color: #1890ff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #eee;
  padding: 10px;
  text-align: left;
}
.error {
  color: red;
  font-size: 13px;
}
</style>
