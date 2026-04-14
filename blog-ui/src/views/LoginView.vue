<template>
    <div class="container">
        <div class="login-box">
            <h2>登录后台</h2>
            <el-form :model="form">
                <el-form-item>
                    <el-input v-model="form.username" placeholder="用户名" />
                </el-form-item>
                <el-form-item>
                    <el-input v-model="form.password" type="password" placeholder="密码" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleLogin" style="width: 100%;">登录</el-button>
                </el-form-item>
            </el-form>
            <div v-if="errorMsg" style="color: red; font-size: 0.85rem;">{{ errorMsg }}</div>
        </div>
    </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import request from '@/utils/request';

const router = useRouter();
const form = reactive({ username: '', password: '' });
const errorMsg = ref('');

const handleLogin = async () => {
    try {
        const res = await request.post('/auth/login', form);
        if (res.data.code === 200) {
            localStorage.setItem('blog_token', res.data.data.token);
            router.push('/admin');
        } else {
            errorMsg.value = res.data.message || '登录失败';
        }
    } catch (err) {
        errorMsg.value = '请求失败，请检查网络或后端服务';
    }
};
</script>

<style scoped>
.container {
    max-width: 28rem;
    margin: 0 auto;
    padding: 4rem 1.5rem;
}

.login-box {
    padding: 2rem;
    border: 1px solid #eaeaea;
    border-radius: 8px;
}

h2 {
    font-weight: 600;
    margin-bottom: 1.5rem;
    text-align: center;
}
</style>