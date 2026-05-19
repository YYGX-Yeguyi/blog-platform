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
import { marked } from 'marked';

const route = useRoute();
const article = ref({});
const loading = ref(true);
const renderedContent = ref('');

marked.setOptions({
    breaks: true,
    gfm: true
});

const fetchDetail = async (id) => {
    try {
        const res = await request.get(`/article/detail/${id}`);
        if (res.data.code === 200) {
            article.value = res.data.data;
            renderedContent.value = marked(article.value.content || '');
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

.post-content h1 {
    font-size: 1.8rem;
    margin-top: 1.5em;
    margin-bottom: 0.5em;
    font-weight: 700;
    border-bottom: 1px solid #eee;
    padding-bottom: 0.3em;
}

.post-content h2 {
    font-size: 1.5rem;
    margin-top: 1.5em;
    margin-bottom: 0.5em;
    font-weight: 600;
    border-bottom: 1px solid #eee;
    padding-bottom: 0.3em;
}

.post-content h3 {
    font-size: 1.25rem;
    margin-top: 1.5em;
    margin-bottom: 0.5em;
    font-weight: 600;
}

.post-content h4,
.post-content h5,
.post-content h6 {
    margin-top: 1.5em;
    margin-bottom: 0.5em;
    font-weight: 600;
}

.post-content p {
    margin-bottom: 1.25em;
}

.post-content ul,
.post-content ol {
    margin-bottom: 1.25em;
    padding-left: 2em;
}

.post-content li {
    margin-bottom: 0.5em;
}

.post-content blockquote {
    border-left: 4px solid #ddd;
    padding: 0.5em 1em;
    margin: 1.25rem 0;
    color: #666;
    background-color: #f9f9f9;
    border-radius: 0 4px 4px 0;
}

.post-content code {
    font-family: 'Fira Code', 'Consolas', monospace;
    background: #f5f5f5;
    padding: 0.2rem 0.3rem;
    border-radius: 4px;
    font-size: 0.85em;
}

.post-content pre {
    background: #1e1e1e;
    padding: 1rem;
    overflow-x: auto;
    border-radius: 8px;
    margin: 1.25rem 0;
}

.post-content pre code {
    background: none;
    padding: 0;
    color: #ccc;
}

.post-content a {
    color: #3182ce;
    text-decoration: none;
}

.post-content a:hover {
    text-decoration: underline;
}

.post-content hr {
    border: none;
    border-top: 1px solid #eee;
    margin: 2rem 0;
}

.post-content table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 1.25em;
}

.post-content th,
.post-content td {
    border: 1px solid #ddd;
    padding: 0.5rem 0.75rem;
    text-align: left;
}

.post-content th {
    background-color: #f8f8f8;
    font-weight: 600;
}

.post-content tr:nth-child(even) {
    background-color: #f8f8f8;
}

.post-content img {
    max-width: 100%;
    border-radius: 4px;
}
</style>