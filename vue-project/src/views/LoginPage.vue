<template>
  <div>
    <h1>Login</h1>
    <input v-model="username" type="text" placeholder="Enter your username" />
    <input v-model="password" type="password" placeholder="Enter your password" />
    <button @click="handleLogin">Login</button>
    <button @click="handleGoogleLogin">Google Login</button>
    <p>{{ error }}</p>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  name: 'LoginPage',
  setup() {
    // vuex, router 가져오기
    const store = useStore();
    const router = useRouter();

    // state 정의
    const username = ref('');
    const password = ref('');
    const error = ref('');

    // 함수 정의
    const handleLogin = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/login', {
          username: username.value, // ref이므로 .value
          password: password.value,
        });
        const token = response.data.token;
        store.dispatch('saveJwtToken', token);
        router.push('/main');
      } catch (err) {
        error.value = 'Invalid credentials. Please try again.';
      }
    };

    const handleGoogleLogin = () => {
      window.location.href = '/api/auth/google';
    };

    return {
      username,
      password,
      error,
      handleLogin,
      handleGoogleLogin,
    };
  },
};
</script>
