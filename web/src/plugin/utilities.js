import {store} from "@/router/index"

export function putOnComma(number) {
    if (!number) return 0
    try {
        return Number(number).toLocaleString()
    } catch (e) {
        return 0
    }
}

export function generateMessage(message, error = false) {
    store.state.message = message
    store.state.snackBar = true
    if (error) {
        store.state.color = "#F44336"
    } else {
        store.state.color = "#36d8f4"
    }
}