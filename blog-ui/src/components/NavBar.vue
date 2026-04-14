<template>
    <div class="container">
        <div class="nav">
            <router-link to="/">博客</router-link>
            <router-link to="/about">关于</router-link>
            <router-link to="/admin">后台</router-link>
        </div>
    </div>
</template>

<style scoped>
.container {
    max-width: 42rem;
    margin: 0 auto;
    padding: 2rem 1.5rem 0;
}

.nav {
    display: flex;
    gap: 2rem;
    border-bottom: 1px solid #eaeaea;
    padding-bottom: 1rem;
}

.nav a {
    text-decoration: none;
    color: #666;
    font-size: 0.95rem;
    transition: color 0.2s ease;
}

.nav a:hover,
.nav a.router-link-active {
    color: #111;
    font-weight: 500;
}
</style>