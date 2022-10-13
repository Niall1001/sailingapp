import axios from 'axios';
import AxiosInstance from '../../utils/axios';
import BASE_URL from '../base-urls';

const boats = {
    async getOne(id) {
        return await AxiosInstance(`${BASE_URL.sailingApi}/boats/${id}`)
    },
    async getMany() {
        return await axios.get(`${BASE_URL.sailingApi}/boats/`)
    },
    async createOne(body) {
        return await axios.post(`${BASE_URL.sailingApi}/boats/create`)
    },
    async updateOne(id, body) {
        return await axios.put(`${BASE_URL.sailingApi}/boats/${id}`)
    },
    async deleteOne(id) {
        return await axios.delete(`${BASE_URL.sailingApi}/boats/${id}`)
    },
}
const appUsers = {
    async login(...body) {
        return await axios.post(`${BASE_URL.sailingApiVersion}/login`, ...body)
    },
    async regsiter(...body){
        return await axios.post(`${BASE_URL.sailingApi}/app-users/create`, ...body)
    },
    async getMany() {
        return await AxiosInstance(`${BASE_URL.sailingApi}/app-users/`)
    }
}
const events = {}


const sailingApi = {
    appUsers,
    boats,
    events
}

export default sailingApi;
