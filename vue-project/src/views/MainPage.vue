<template>
    <div>
      <h1>Main Page</h1>
      <p>Your JWT Token:</p>
      <pre>{{ token }}</pre>
    </div>
  </template>
  
  <script>
  import { useStore } from 'vuex';
  
  export default {
    name: 'MainPage',
    setup() {
      const store = useStore();
      return {
        token: store.getters.getJwtToken,
      };
    },
  };
  </script>