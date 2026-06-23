<template>
  <div class="page">
    <h1>운전직 전속현황</h1>

    <div v-for="group in groupedAssignments" :key="group.routeNo" class="route-block">
      <h2>{{ group.routeNo }}번 ({{ group.items.length }}대)</h2>
      <table>
        <thead>
          <tr>
            <th>노선번호</th>
            <th>차량번호</th>
            <th>전속기사 1</th>
            <th>전속기사 2</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in group.items" :key="item.vehicleId">
            <td>{{ item.routeNo }}</td>
            <td>{{ item.vehicleId }}</td>
            <td>
              <button class="driver-btn" @click="openModal(item, 'driverId1')">
                {{ item.driverName1 || '미배정' }}
              </button>
              <button
                v-if="item.driverId1"
                class="clear-btn"
                @click="handleClear(item, 'driverId1')"
              >✕</button>
            </td>
            <td>
              <button class="driver-btn" @click="openModal(item, 'driverId2')">
                {{ item.driverName2 || '미배정' }}
              </button>
              <button
                v-if="item.driverId2"
                class="clear-btn"
                @click="handleClear(item, 'driverId2')"
              >✕</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <h2>대무자 목록 (입사순)</h2>
    <table>
      <thead>
        <tr>
          <th>순번</th>
          <th>아이디</th>
          <th>이름</th>
          <th>입사일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(sub, index) in substitutes" :key="sub.id">
          <td>{{ index + 1 }}</td>
          <td>{{ sub.id }}</td>
          <td>{{ sub.name }}</td>
          <td>{{ formatDate(sub.hireDate) }}</td>
        </tr>
      </tbody>
    </table>

    <DriverSearchModal
      :visible="modalVisible"
      :drivers="drivers"
      @select="handleSelectDriver"
      @close="modalVisible = false"
    />
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getAssignments, getSubstitutes, upsertAssignment } from '@/api/assignment.js'
import { getDrivers } from '@/api/driver.js'
import DriverSearchModal from '@/components/DriverSearchModal.vue'

const assignments = ref([])
const substitutes = ref([])
const drivers = ref([])

const modalVisible = ref(false)
const modalTarget = ref(null)

const groupedAssignments = computed(() => {
  const map = new Map()
  for (const item of assignments.value) {
    if (!map.has(item.routeNo)) {
      map.set(item.routeNo, [])
    }
    map.get(item.routeNo).push(item)
  }
  return Array.from(map.entries()).map(([routeNo, items]) => ({ routeNo, items }))
})

function formatDate(epoch) {
  if (!epoch) return ''
  return new Date(epoch).toLocaleDateString()
}

async function loadAssignments() {
  const response = await getAssignments()
  assignments.value = response.data
}

async function loadSubstitutes() {
  const response = await getSubstitutes()
  substitutes.value = response.data
}

async function loadDrivers() {
  const response = await getDrivers()
  drivers.value = response.data
}

function openModal(item, slot) {
  modalTarget.value = { item, slot }
  modalVisible.value = true
}

async function saveAssignment(item) {
  await upsertAssignment(item.vehicleId, {
    driverId1: item.driverId1,
    driverId2: item.driverId2
  })
  await loadSubstitutes()
}

async function handleSelectDriver(driver) {
  const { item, slot } = modalTarget.value
  item[slot] = driver.id
  item[slot === 'driverId1' ? 'driverName1' : 'driverName2'] = driver.name
  modalVisible.value = false
  await saveAssignment(item)
}

async function handleClear(item, slot) {
  item[slot] = null
  item[slot === 'driverId1' ? 'driverName1' : 'driverName2'] = null
  await saveAssignment(item)
}

onMounted(() => {
  loadAssignments()
  loadSubstitutes()
  loadDrivers()
})
</script>

<style scoped>
.page {
  padding: 32px;
}
.route-block {
  margin-bottom: 32px;
}
table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 16px;
}
th, td {
  border: 1px solid #eee;
  padding: 10px;
  text-align: left;
}
.driver-btn {
  padding: 6px 14px;
  background-color: #1890ff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.clear-btn {
  margin-left: 6px;
  padding: 4px 8px;
  background-color: #f5f5f5;
  color: #999;
  border: 1px solid #ddd;
  border-radius: 4px;
  cursor: pointer;
}
</style>
