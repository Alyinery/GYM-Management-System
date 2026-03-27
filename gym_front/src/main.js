//import './assets/main.css'
//引入createApp用于创建应用
import { createApp } from 'vue'
import App from './App.vue' //引入App根组件 
import router from './router'   //引入路由器
import 'amfe-flexible/index'   //引入移动端适配插件
import Vant from 'vant' //引入Vant组件
import 'vant/lib/index.css' //引入Vant组件样式
import '@vant/touch-emulator'
import '@/style/vant.less'


// createApp(App).mount('#app')

//创建一个应用
const app = createApp(App)
//使用路由器
app.use(router)
//使用Vant
app.use(Vant)

//关闭页面的token消除
window.onload = function () {
    let lastTime = localStorage.getItem("lastTime");
    const interval = 3 * 1000;
    // 如果没有上一次离开的时间或者时间间隔大于3s，就清除token
    if (!lastTime || new Date().getTime() - lastTime > interval) {
        localStorage.removeItem("token");
        localStorage.removeItem("Admintoken");
        localStorage.removeItem('redirectPath'); // 清除重定向路径
        localStorage.removeItem('UserId');
        console.log("清除token")
    }else{
        console.log("时间过短不清除token")
    }
};
window.onunload = function () {
    localStorage.setItem("lastTime", new Date().getTime());
};

//挂载整个应用到App容器中
app.mount('#app')