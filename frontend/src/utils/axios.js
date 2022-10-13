import axios from 'axios';
import BASE_URL from '../services/base-urls';

const AxiosInstance = axios.create({
    baseURL: BASE_URL,
    headers: {
        "Content-Type": "application/json",
      },
  });
  
  AxiosInstance.interceptors.request.use(function (config) {
    const jwt = localStorage.getItem("jwt");
    config.headers["Authorization"] = "Bearer " + jwt;
    return config;
  });
  
  export default AxiosInstance;