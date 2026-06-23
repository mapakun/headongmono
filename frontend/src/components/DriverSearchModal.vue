<template>
  <div v-if="visible" class="overlay" @click.self="$emit('close')">
    <div class="modal">
      <h3>기사 검색</h3>
      <input
        v-model="keyword"
        type="text"
        placeholder="이름 또는 아이디로 검색"
        autofocus
      />
      <ul class="driver-list">
        <li v-if="filteredDrivers.length === 0" class="empty">검색 결과가 없습니다.</li>
        <li
          v-for="driver in filteredDrivers"
          :key="driver.id"
          @click="$emit('select', driver)"
        >
          {{ driver.name }} ({{ driver.id }})
        </li>
      </ul>
      <button class="close-btn" @click="$emit('close')">닫기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
  visible: { type: Boolean, default: false },
  drivers: { type: Array, default: () => [] }
})

defineEmits(['select', 'close'])

const keyword = ref('')

const filteredDrivers = computed(() => {
  if (!keyword.value.trim()) return props.drivers
  const k = keyword.value.trim().toLowerCase()
  return props.drivers.filter(
    d => d.name.toLowerCase().includes(k) || d.id.toLowerCase().includes(k)
  )
})
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 0; left: 0; right: 0; bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 100;
}
.modal {
  background: white;
  padding: 24px;
  border-radius: 8px;
  width: 320px;
  max-height: 70vh;
  display: flex;
  flex-direction: column;
}
h3 {
  margin: 0 0 12px;
}
input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-bottom: 12px;
}
.driver-list {
  list-style: none;
  margin: 0;
  padding: 0;
  overflow-y: auto;
  flex: 1;
  border: 1px solid #eee;
  border-radius: 4px;
}
.driver-list li {
  padding: 10px 12px;
  cursor: pointer;
  border-bottom: 1px solid #f3f3f3;
}
.driver-list li:hover {
  background-color: #f0f7ff;
}
.driver-list li.empty {
  color: #999;
  cursor: default;
}
.close-btn {
  margin-top: 12px;
  padding: 8px;
  background-color: #999;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
