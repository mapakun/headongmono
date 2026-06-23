<template>
  <div class="page">
    <h1>차량 관리</h1>

    <form class="add-form" @submit.prevent="handleCreate">
      <input v-model="form.vehicleId" placeholder="차량 번호판" />
      <input v-model="form.routeNo" placeholder="배정 노선 번호" />
      <select v-model="form.vehicleType">
        <option value="일반">일반</option>
        <option value="저상">저상</option>
        <option value="굴절">굴절</option>
      </select>
      <select v-model="form.status">
        <option value="ACTIVE">운행중</option>
        <option value="INACTIVE">미운행</option>
        <option value="REPAIR">정비중</option>
      </select>
      <button type="submit">등록</button>
    </form>

    <p v-if="errorMsg" class="error">{{ errorMsg }}</p>

    <table>
      <thead>
        <tr>
          <th>차량번호</th>
          <th>노선번호</th>
          <th>차종</th>
          <th>상태</th>
          <th>등록일</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="vehicle in vehicles" :key="vehicle.vehicleId">
          <td>{{ vehicle.vehicleId }}</td>
          <td>{{ vehicle.routeNo }}</td>
          <td>{{ vehicle.vehicleType }}</td>
          <td>{{ vehicle.status }}</td>
          <td>{{ formatDate(vehicle.createdAt) }}</td>
          <td><button @click="handleDelete(vehicle.vehicleId)">삭제</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { getVehicles, createVehicle, deleteVehicle } from '@/api/vehicle.js'

const vehicles = ref([])
const errorMsg = ref('')

const form = reactive({
  vehicleId: '',
  routeNo: '',
  vehicleType: '일반',
  status: 'ACTIVE'
})

function formatDate(epoch) {
  if (!epoch) return ''
  return new Date(epoch).toLocaleDateString()
}

async function loadVehicles() {
  const response = await getVehicles()
  vehicles.value = response.data
}

async function handleCreate() {
  if (!form.vehicleId.trim()) {
    errorMsg.value = '차량 번호판은 필수입니다.'
    return
  }

  try {
    errorMsg.value = ''
    await createVehicle({ ...form })
    form.vehicleId = ''
    form.routeNo = ''
    form.vehicleType = '일반'
    form.status = 'ACTIVE'
    await loadVehicles()
  } catch (e) {
    errorMsg.value = '차량 등록에 실패했습니다.'
  }
}

async function handleDelete(vehicleId) {
  await deleteVehicle(vehicleId)
  await loadVehicles()
}

onMounted(loadVehicles)
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
