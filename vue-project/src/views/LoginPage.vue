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
  import axios from 'axios';
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';
  
  export default {
    name: 'LoginPage',
    data() {
      return {
        username: '',
        password: '',
        error: '',
      };
    },
    setup() {
      const store = useStore();
      const router = useRouter();
  
      const handleLogin = async () => {
        try {
          const response = await axios.post('/api/auth/login', {
            username: this.username,
            password: this.password,
          });
  
          const token = response.data.token;
          store.dispatch('saveJwtToken', token);
          router.push('/main');
        } catch (err) {
          this.error = 'Invalid credentials. Please try again.';
        }
      };
  
      const handleGoogleLogin = () => {
        window.location.href = '/api/auth/google';
      };
  
      return {
        handleLogin,
        handleGoogleLogin,
      };
    },
  };
  </script>