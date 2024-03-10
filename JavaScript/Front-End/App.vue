<template>
  <div class="container">
    <Header />
    <router-view />
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
export default {
  components: {
    Header,
  },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 1080px;
  width: 1920px;
}
body {
  background-color: #F1F0EA;
  margin: 0;
  height: 100%;
  overflow: hidden;
}
main {
  // display: flex;
  justify-content: center;
  margin-top: 5rem;
}
</style>
