<template>
  <div class="about">
    <h1>file upload page</h1>
    <input type="file" ref="fileInt" @change="changeHandle">
  </div>

</template>

<script>
export default {
  name: "upload",
  methods: {
    changeHandle() {
      const file = this.$refs.fileInt.files[0];
      console.log(file);
      const data = new FormData();
      data.append('file', file);
      axios.post('http://202.114.118.138/upload', data, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }).then(res => {
        console.log(res);
      }).catch(err => {
        console.log(err);
      });
    }
  }


}
</script>

<style scoped>

</style>