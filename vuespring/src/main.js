import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import $gridManager,{jTool} from 'gridmanager';
import 'gridmanager/css/gm.css';
export {$gridManager,jTool};
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
