import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../views/LoginPage.vue';
import MainPage from '../views/MainPage.vue';

const routes = [
  { path: '/', name: 'Login', component: LoginPage },
  { path: '/main', name: 'Main', component: MainPage },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 네비게이션 가드 추가
router.beforeEach((to, from, next) => {
  // 보호된 라우트인지 확인 (여기서는 '/main'만 보호)
  if (to.name === 'Main') {
    const token = store.getters.getJwtToken;
    if (!token) {
      // 인증되지 않은 경우 로그인 페이지로 리다이렉트
      return next({ name: 'Login' });
    }
  }
  next(); // 접근 허용
});

export default router;
