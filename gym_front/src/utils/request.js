import axios from 'axios'
const service = axios.create({
    baseURL: "http://localhost:8081/gym/",
    withCredentials: false,    // 跨域请求时是否需要访问凭证
    timeout: 5000
})

//请求拦截器
service.interceptors.request.use(config => {
    // 这里可以进行请求加密等操作。如添加token,cookie，修改数据传输格式等。
    config.headers['token'] = 'xxxx';
    config.headers['Content-type'] = 'application/json';
    return config;
})

//响应拦截器
service.interceptors.response.use(response => {
    // 请求成功进行的操作。// 可以使用switch/if...else对数据先进行处理。
    return response;
}, error => {
    // 请求失败进行的操作
    return error;
})

//参考文档 https://www.kancloud.cn/yunye/axios/234845

const errorHandle = (status, info) => {
    switch (status) {
        case 400:
            console.log("语义有误");
        case 401:
            console.log("服务器认证失败");
        case 403:
            console.log("服务器拒绝访问");
        case 404:
            console.log("地址错误");
        case 500:
            console.log("服务器遇到意外");
        case 502:
            console.log("服务器无响应");
        default:
            console.log(info);
            break
    }
}


//发送数据前
// 创建请求拦截
// service.interceptors.request.use(
//     (config) => {
//       // 如果开启 token 认证
//       if (serverConfig.useTokenAuthorization) {
//         config.headers["Authorization"] = localStorage.getItem("token"); // 请求头携带 token
//       }
//       // 设置请求头
//       if(!config.headers["content-type"]) { // 如果没有设置请求头
//         if(config.method === 'post') {
//           config.headers["content-type"] = "application/x-www-form-urlencoded"; // post 请求
//           config.data = qs.stringify(config.data); // 序列化,比如表单数据
//         } else {
//           config.headers["content-type"] = "application/json"; // 默认类型
//         }
//       }
//       console.log("请求配置", config);
//       return config;
//     },
//     (error) => {
//       Promise.reject(error);
//     }
//   );



//获取数据之前
// service.interceptors.response.use(
//     response => {
//         return response.status === 200 ? Promise.resolve(response) : Promise.reject(response)
//     },
//     error => {
//         const { response } = error;
//         //错误的数据才是我们最关注的
//         errorHandle(response.status, response.info)
//     }
// )


export default service;