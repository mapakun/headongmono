import api from '@/api/index.js'

export function getRoutes() {
    return api.get('/routes')
}

export function getRoute(routeNo) {
    return api.get(`/routes/${routeNo}`)
}

export function createRoute(route) {
    return api.post('/routes', route)
}

export function updateRoute(routeNo, route) {
    return api.put(`/routes/${routeNo}`, route)
}

export function deleteRoute(routeNo) {
    return api.delete(`/routes/${routeNo}`)
}
