import api from '@/api/index.js'

export function getDispatches() {
    return api.get('/dispatches')
}

export function getDispatch(dispatchId) {
    return api.get(`/dispatches/${dispatchId}`)
}

export function createDispatch(dispatch) {
    return api.post('/dispatches', dispatch)
}

export function updateDispatch(dispatchId, dispatch) {
    return api.put(`/dispatches/${dispatchId}`, dispatch)
}

export function deleteDispatch(dispatchId) {
    return api.delete(`/dispatches/${dispatchId}`)
}
