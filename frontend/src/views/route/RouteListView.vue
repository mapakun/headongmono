<template>
  <div class="page">
    <h1>노선 관리</h1>

    <form class="add-form" @submit.prevent="handleCreate">
      <input v-model="form.routeNo" placeholder="노선 번호" />
      <input v-model="form.routeName" placeholder="노선 이름" />
      <input v-model.number="form.totalVehicles" type="number" placeholder="보유 대수" />
      <select v-model="form.status">
        <option value="ACTIVE">운행중</option>
        <option value="INACTIVE">미운행</option>
      </select>
      <button type="submit">등록</button>
    </form>

    <p v-if="errorMsg" class="error">{{ errorMsg }}</p>

    <table>
      <thead>
        <tr>
          <th>노선번호</th>
          <th>노선이름</th>
          <th>보유대수</th>
          <th>상태</th>
          <th>등록일</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="route in routes" :key="route.routeNo">
          <td>{{ route.routeNo }}</td>
          <td>{{ route.routeName }}</td>
          <td>{{ route.totalVehicles }}</td>
          <td>{{ route.status }}</td>
          <td>{{ formatDate(route.createdAt) }}</td>
          <td><button @click="handleDelete(route.routeNo)">삭제</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { getRoutes, createRoute, deleteRoute } from '@/api/route.js'

const routes = ref([])
const errorMsg = ref('')

const form = reactive({
  routeNo: '',
  routeName: '',
  totalVehicles: null,
  status: 'ACTIVE'
})

function formatDate(epoch) {
  if (!epoch) return ''
  return new Date(epoch).toLocaleDateString()
}

async function loadRoutes() {
  const response = await getRoutes()
  routes.value = response.data
}

async function handleCreate() {
  if (!form.routeNo.trim() || !form.routeName.trim()) {
    errorMsg.value = '노선 번호와 이름은 필수입니다.'
    return
  }

  try {
    errorMsg.value = ''
    await createRoute({ ...form })
    form.routeNo = ''
    form.routeName = ''
    form.totalVehicles = null
    form.status = 'ACTIVE'
    await loadRoutes()
  } catch (e) {
    errorMsg.value = '노선 등록에 실패했습니다.'
  }
}

async function handleDelete(routeNo) {
  await deleteRoute(routeNo)
  await loadRoutes()
}

onMounted(loadRoutes)
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
