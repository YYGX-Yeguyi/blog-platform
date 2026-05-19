import axios from 'axios';
// 配置访问是远端地址还是本地地址的端口设置 远端'http://47.116.30.242:8080/api'
// 注意：加上 :8080 端口
const API_BASE_URL = 'http://localhost:8080/api';
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