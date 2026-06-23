import api from '@/api/index.js'

export function getDrivers() {
    return api.get('/drivers')
}

export function getDriver(id) {
    return api.get(`/drivers/${id}`)
}

export function createDriver(driver) {
    return api.post('/drivers', driver)
}

export function updateDriver(id, driver) {
    return api.put(`/drivers/${id}`, driver)
}

export function deleteDriver(id) {
    return api.delete(`/drivers/${id}`)
}
