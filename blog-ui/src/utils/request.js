import axios from 'axios';

// 注意：加上 :8080 端口
const API_BASE_URL = 'http://47.116.30.242:8080/api';

const request = axios.create({
    baseURL: API_BASE_URL,
    timeout: 10000,
});

// 请求拦截器：自动携带 token
request.interceptors.request.use(config => {
    const token = localStorage.getItem('blog_token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default request;