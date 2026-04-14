<template>
    <div class="container" v-loading="loading">
        <div v-if="!loading">
            <h1 class="post-title">{{ article.title }}</h1>
            <div class="post-meta">
                <span>{{ formatDate(article.createTime) }}</span>
                <span>·</span>
                <span>{{ article.viewCount }} 次浏览</span>
            </div>
            <div class="post-content" v-html="renderedContent"></div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import request from '@/utils/request';

const route = useRoute();
const article = ref({});
const loading = ref(true);
const renderedContent = ref('');

const fetchDetail = async (id) => {
    try {
        const res = await request.get(`/article/detail/${id}`);
        if (res.data.code === 200) {
            article.value = res.data.data;
            // 极简 Markdown 渲染
            let html = article.value.content || '';
            html = html.replace(/^### (.*$)/gim, '<h3>$1</h3>');
            html = html.replace(/^## (.*$)/gim, '<h2>$1</h2>');
            html = html.replace(/^# (.*$)/gim, '<h1>$1</h1>');
            html = html.replace(/\n/g, '<br/>');
            renderedContent.value = html;
        }
    } catch (err) {
        console.error(err);
    } finally {
        loading.value = false;
    }
};

const formatDate = (dateStr) => {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleDateString('zh-CN', { year: 'numeric', month: 'long', day: 'numeric' });
};

onMounted(() => {
    const id = route.params.id;
    if (id) fetchDetail(id);
});
</script>

<style scoped>
.container {
    max-width: 42rem;
    margin: 0 auto;
    padding: 2rem 1.5rem;
}

.post-title {
    font-size: 2rem;
    font-weight: 700;
    margin-bottom: 0.5rem;
}

.post-meta {
    font-size: 0.85rem;
    color: #666;
    margin-bottom: 2rem;
    display: flex;
    gap: 0.5rem;
}

.post-content {
    font-size: 1rem;
    color: #1a1a1a;
    line-height: 1.7;
}

.post-content h1,
.post-content h2,
.post-content h3 {
    margin-top: 1.5em;
    margin-bottom: 0.5em;
    font-weight: 600;
}

.post-content p {
    margin-bottom: 1.25em;
}

.post-content code {
    font-family: monospace;
    background: #f5f5f5;
    padding: 0.2rem 0.3rem;
    border-radius: 4px;
    font-size: 0.85em;
}

.post-content pre {
    background: #f8f8f8;
    padding: 1rem;
    overflow-x: auto;
    border-radius: 8px;
    margin: 1.25rem 0;
}
</style>