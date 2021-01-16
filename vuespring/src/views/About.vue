<template>
  <div class="about">
    <h1>This is an about page</h1>
    <table>
      <tr>
        <td>id</td>
        <td>name</td>
        <td>password</td>
      </tr>

    </table>
   <input type="file" ref="fileInt" @change="changeHandle">
   </div>
</template>

<script>
    export  default {
      created() {
        //alert(123);
        axios.get('http://202.114.118.138:8181/prkfile').then(function (resp) {
              console.log(resp.data);
        })
        //
        // //计算结果
        // axios.get('http://202.114.118.138:8181/caculate').then(function (res){
        //     console.log(res.data);
        // })

      },
      methods:{
      changeHandle() {
        const file = this.$refs.fileInt.files[0];
        console.log(file);
        const data = new FormData();
        data.append('file', file);
        //file upload
        //'http://202.114.118.138:8181/uploadimg'
        axios.post('http://202.114.118.138:8181/uploadimg', data, {
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


