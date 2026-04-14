<template>
    <div class="container">
        <div class="admin-header">
            <h2>文章管理</h2>
            <div>
                <el-button text @click="createNew">新建文章</el-button>
                <el-button text @click="logout">退出登录</el-button>
            </div>
        </div>

        <div v-for="article in articles" :key="article.id" class="article-row">
            <div>
                <strong>{{ article.title }}</strong>
                <span class="status-badge">{{ article.status === 1 ? '已发布' : '草稿' }}</span>
            </div>
            <div>
                <el-button text size="small" @click="editArticle(article.id)">编辑</el-button>
                <el-button text size="small" @click="deleteArticle(article.id)">删除</el-button>
            </div>
        </div>

        <!-- 编辑弹窗 -->
        <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑文章' : '新建文章'" width="70%">
            <el-form :model="currentArticle" label-width="80px">
                <el-form-item label="标题">
                    <el-input v-model="currentArticle.title" />
                </el-form-item>
                <el-form-item label="分类ID">
                    <el-input-number v-model="currentArticle.categoryId" :min="1" />
                </el-form-item>
                <el-form-item label="状态">
                    <el-radio-group v-model="currentArticle.status">
                        <el-radio :label="1">发布</el-radio>
                        <el-radio :label="0">草稿</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="内容">
                    <MdEditor v-model="currentArticle.content" />
                </el-form-item>
            </el-form>
            <template #footer>
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="saveArticle">保存</el-button>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
import { MdEditor } from 'md-editor-v3';  // 注意：花括号
import 'md-editor-v3/lib/style.css';
import request from '@/utils/request';

const router = useRouter();
const articles = ref([]);
const dialogVisible = ref(false);
const isEdit = ref(false);
const currentArticle = reactive({
    id: null,
    title: '',
    categoryId: 1,
    status: 1,
    content: ''
});

const fetchArticles = async () => {
    try {
        const res = await request.get('/article/list', { params: { page: 1, size: 100 } });
        if (res.data.code === 200) {
            articles.value = res.data.data.records || [];
        }
    } catch (err) {
        console.error(err);
    }
};

const createNew = () => {
    isEdit.value = false;
    currentArticle.id = null;
    currentArticle.title = '';
    currentArticle.categoryId = 1;
    currentArticle.status = 1;
    currentArticle.content = '';
    dialogVisible.value = true;
};

const editArticle = async (id) => {
    try {
        const res = await request.get(`/article/detail/${id}`);
        if (res.data.code === 200) {
            const data = res.data.data;
            currentArticle.id = data.id;
            currentArticle.title = data.title;
            currentArticle.categoryId = data.categoryId;
            currentArticle.status = data.status;
            currentArticle.content = data.content || '';
            isEdit.value = true;
            dialogVisible.value = true;
        }
    } catch (err) {
        console.error(err);
    }
};

const saveArticle = async () => {
    try {
        const res = await request.post('/article/save', currentArticle);
        if (res.data.code === 200) {
            ElMessage.success('保存成功');
            dialogVisible.value = false;
            fetchArticles();
        } else {
            ElMessage.error(res.data.message || '保存失败');
        }
    } catch (err) {
        ElMessage.error('请求失败');
    }
};

const deleteArticle = async (id) => {
    ElMessageBox.confirm('确定删除这篇文章吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
    }).then(async () => {
        try {
            await request.delete(`/article/delete/${id}`);
            ElMessage.success('删除成功');
            fetchArticles();
        } catch (err) {
            ElMessage.error('删除失败');
        }
    }).catch(() => {});
};

const logout = () => {
    localStorage.removeItem('blog_token');
    router.push('/login');
};

onMounted(() => {
    fetchArticles();
});
</script>

<style scoped>
.container {
    max-width: 60rem;
    margin: 0 auto;
    padding: 2rem 1.5rem;
}

.admin-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 2rem;
    border-bottom: 1px solid #eaeaea;
    padding-bottom: 1rem;
}

.article-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #f0f0f0;
    padding: 0.75rem 0;
}

.status-badge {
    font-size: 0.75rem;
    color: #888;
    margin-left: 0.75rem;
}
</style>