//封装用户请求的模块
import request from "@/utils/request";
export const login = (data) => {
    return request({
        method: "POST",
        url: "/user/login",
        data,
    });
};