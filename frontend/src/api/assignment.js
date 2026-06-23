import api from '@/api/index.js'

export function getAssignments() {
    return api.get('/assignments')
}

export function getSubstitutes() {
    return api.get('/assignments/substitutes')
}

export function upsertAssignment(vehicleId, assignment) {
    return api.put(`/assignments/${vehicleId}`, assignment)
}

export function deleteAssignment(vehicleId) {
    return api.delete(`/assignments/${vehicleId}`)
}
