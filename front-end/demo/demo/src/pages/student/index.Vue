<template>
  <div>
  <router-view/>
  </div>
</template>
<script>
  // import __ from '__' // __是需要手动引入的文件
  export default {
    name: '',
    components: {},
    data() {
      return {msg: '这个是登入页面'}
    },
    methods: {}
  }
</script>
<style lang="postcss" scoped>
  body {
    background-color: #ff0000;
  }
</style>
