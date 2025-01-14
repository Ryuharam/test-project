// src/store/index.js
import { createStore } from 'vuex';

export default createStore({
  state: {
    jwtToken: null,
  },
  mutations: {
    setJwtToken(state, token) {
      state.jwtToken = token;
    },
  },
  actions: {
    saveJwtToken({ commit }, token) {
      commit('setJwtToken', token);
    },
  },
  getters: {
    getJwtToken(state) {
      return state.jwtToken;
    },
  },
});
