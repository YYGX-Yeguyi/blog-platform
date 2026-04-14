<template>
    <div class="container">
        <div v-for="article in articles" :key="article.id" class="article-item">
            <h2 class="article-title">
                <router-link :to="`/post/${article.id}`">{{ article.title }}</router-link>
            </h2>
            <div class="article-meta">
                <span>{{ formatDate(article.createTime) }}</span>
                <span>·</span>
                <span>{{ article.viewCount || 0 }} 次浏览</span>
            </div>
            <div class="article-summary">{{ article.summary || (article.content?.substring(0, 150) + '...') }}</div>
        </div>

        <div style="margin-top: 2rem; text-align: center;">
            <el-pagination
                background
                layout="prev, pager, next"
                :total="total"
                :page-size="pageSize"
                :current-page="currentPage"
                @current-change="handlePageChange"
            />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import request from '@/utils/request';

const articles = ref([]);
const total = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);

const fetchArticles = async () => {
    try {
        const res = await request.get('/article/list', { params: { page: currentPage.value, size: pageSize.value } });
        if (res.data.code === 200) {
            articles.value = res.data.data.records || [];
            total.value = res.data.data.total || 0;
        }
    } catch (err) {
        console.error(err);
    }
};

const handlePageChange = (page) => {
    currentPage.value = page;
    fetchArticles();
};

const formatDate = (dateStr) => {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleDateString('zh-CN', { year: 'numeric', month: 'long', day: 'numeric' });
};

onMounted(() => {
    fetchArticles();
});
</script>

<style scoped>
.container {
    max-width: 42rem;
    margin: 0 auto;
    padding: 2rem 1.5rem;
}

.article-item {
    margin-bottom: 2.5rem;
}

.article-title {
    font-size: 1.5rem;
    font-weight: 600;
    line-height: 1.3;
    margin-bottom: 0.5rem;
}

.article-title a {
    text-decoration: none;
    color: #111;
}

.article-title a:hover {
    text-decoration: underline;
}

.article-meta {
    font-size: 0.85rem;
    color: #666;
    margin-bottom: 0.75rem;
    display: flex;
    gap: 0.5rem;
}

.article-summary {
    color: #444;
    font-size: 0.95rem;
    line-height: 1.5;
}
</style>