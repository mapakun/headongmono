import api from '@/api/index.js'

export function getVehicles() {
    return api.get('/vehicles')
}

export function getVehicle(vehicleId) {
    return api.get(`/vehicles/${vehicleId}`)
}

export function createVehicle(vehicle) {
    return api.post('/vehicles', vehicle)
}

export function updateVehicle(vehicleId, vehicle) {
    return api.put(`/vehicles/${vehicleId}`, vehicle)
}

export function deleteVehicle(vehicleId) {
    return api.delete(`/vehicles/${vehicleId}`)
}
