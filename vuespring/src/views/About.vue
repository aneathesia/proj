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
    <a href="http://202.114.118.138:8181/Pathdownload/Project=Jan&filename=500KV.PRK" download="true">下载文件</a>
   </div>

</template>
<script>

    export  default {
      created() {
        //alert(123);
        axios.get('http://202.114.118.138:8181/Pathdownload/Project=Jan&filename=500KV.PRK').then(function (resp) {
              console.log(resp);
        })
      },
        //
        // //计算结果
        // axios.get('http://202.114.118.138:8181/caculate').then(function (res){
        //     console.log(res.data);
        // })

      // methods:{
      // changeHandle() {
      //   const data = new FormData();
      //   data.append('ProjectName',"Jan");
      //   data.append('DemFile',"DEM.EGX");
      //   data.append('PointFile',"J1-J18.txt");
      //   axios.post('http://202.114.118.138:8181/EGXGEOCaculate', data, {
      //     headers: {
      //       'Content-Type': 'multipart/form-data',
      //     },
      //   }).then(res => {
      //     console.log(res);
      //   }).catch(err => {
      //     console.log(err);
      //   });
      // }
      // },
      methods:{
        changeHandle() {
          const file = this.$refs.fileInt.files[0];
          console.log(file);
          const data = new FormData();
          data.append('file', file);
          data.append('ProjectName',"Jan");
          data.append('FileName',file.name);
          //file upload
          //'http://202.114.118.138:8181/uploadimg'  47.108.218.130:8181
          axios.post('http://202.114.118.138:8181/UploadfileToJson', data, {
            headers: {
              'Content-Type': 'multipart/form-data',
            }
          }).then(res => {
            console.log(res);
          }).catch(err => {
            console.log(err);
          });
        }
      }

    }

</script>


