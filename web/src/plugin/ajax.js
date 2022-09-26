import axios from "axios";
import {generateMessage} from "@/plugin/utilities";

export default {
    async post(url, params, callback) {
        const res = await axios.post(url, params).catch(this.catchFun)
        if (!res) return
        if (callback) {
            callback(res)
        }
        return res
    },
    async get(url, params, callback) {
        const res = await axios.get(url, params).catch(this.catchFun)
        if (!res) return
        if (callback) {
            callback(res)
        }
        return res
    },
    async catchFun(error) {
        console.log(error)
        generateMessage("エラーが発生しました", true)
    }
}