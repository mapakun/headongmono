<template>
  <div class="page">
    <h1>배차 현황</h1>

    <form class="add-form" @submit.prevent="handleCreate">
      <select v-model="form.driverId">
        <option value="" disabled>기사 선택</option>
        <option v-for="driver in drivers" :key="driver.id" :value="driver.id">
          {{ driver.name }} ({{ driver.id }})
        </option>
      </select>
      <select v-model="form.vehicleId">
        <option value="" disabled>차량 선택</option>
        <option v-for="vehicle in vehicles" :key="vehicle.vehicleId" :value="vehicle.vehicleId">
          {{ vehicle.vehicleId }}
        </option>
      </select>
      <input v-model="form.dispatchDateInput" type="date" />
      <select v-model="form.shift">
        <option value="AM">오전</option>
        <option value="PM">오후</option>
      </select>
      <select v-model="form.status">
        <option value="SCHEDULED">배차예정</option>
        <option value="COMPLETED">운행완료</option>
        <option value="CANCELLED">취소</option>
      </select>
      <button type="submit">등록</button>
    </form>

    <p v-if="errorMsg" class="error">{{ errorMsg }}</p>

    <table>
      <thead>
        <tr>
          <th>기사</th>
          <th>차량</th>
          <th>배차일</th>
          <th>교대</th>
          <th>상태</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="dispatch in dispatches" :key="dispatch.dispatchId">
          <td>{{ driverName(dispatch.driverId) }}</td>
          <td>{{ dispatch.vehicleId }}</td>
          <td>{{ formatDate(dispatch.dispatchDate) }}</td>
          <td>{{ dispatch.shift }}</td>
          <td>{{ dispatch.status }}</td>
          <td><button @click="handleDelete(dispatch.dispatchId)">삭제</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { getDispatches, createDispatch, deleteDispatch } from '@/api/dispatch.js'
import { getDrivers } from '@/api/driver.js'
import { getVehicles } from '@/api/vehicle.js'

const dispatches = ref([])
const drivers = ref([])
const vehicles = ref([])
const errorMsg = ref('')

const form = reactive({
  driverId: '',
  vehicleId: '',
  dispatchDateInput: '',
  shift: 'AM',
  status: 'SCHEDULED'
})

function formatDate(epoch) {
  if (!epoch) return ''
  return new Date(epoch).toLocaleDateString()
}

function driverName(driverId) {
  const driver = drivers.value.find(d => d.id === driverId)
  return driver ? driver.name : driverId
}

async function loadDispatches() {
  const response = await getDispatches()
  dispatches.value = response.data
}

async function loadDrivers() {
  const response = await getDrivers()
  drivers.value = response.data
}

async function loadVehicles() {
  const response = await getVehicles()
  vehicles.value = response.data
}

async function handleCreate() {
  if (!form.driverId || !form.vehicleId || !form.dispatchDateInput) {
    errorMsg.value = '기사, 차량, 배차일은 필수입니다.'
    return
  }

  try {
    errorMsg.value = ''
    await createDispatch({
      driverId: form.driverId,
      vehicleId: form.vehicleId,
      dispatchDate: new Date(form.dispatchDateInput).getTime(),
      shift: form.shift,
      status: form.status
    })
    form.driverId = ''
    form.vehicleId = ''
    form.dispatchDateInput = ''
    form.shift = 'AM'
    form.status = 'SCHEDULED'
    await loadDispatches()
  } catch (e) {
    errorMsg.value = '배차 등록에 실패했습니다.'
  }
}

async function handleDelete(dispatchId) {
  await deleteDispatch(dispatchId)
  await loadDispatches()
}

onMounted(() => {
  loadDispatches()
  loadDrivers()
  loadVehicles()
})
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
