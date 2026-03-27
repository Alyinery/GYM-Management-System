<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="登录" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div class="serve shadow">
            <div class="theme">Sign Up</div>
            <van-form @submit="onSubmit">
                <van-cell-group inset>
                    <van-field v-model="username" name="用户名" label="用户名" placeholder="请输入用户名"
                        :rules="userFormRules.userName" left-icon="user" />
                    <van-field v-model="password" type="password" name="密码" label="密码" placeholder="请输入密码"
                        :rules="userFormRules.userPwd" left-icon="lock" />
                </van-cell-group>
                <div style="margin: 18px;">
                    <van-button round block type="primary" native-type="submit" :disabled="canclick">
                        立即登录
                    </van-button>
                </div>
            </van-form>
            <div class="registerandforgotpwd">
                <p class="register" @click="goToPage('/register')">注册</p>
                <p class="forgotpassword" @click="forgotpwd()">忘记密码</p>
            </div>
        </div>
    </div>
</template>
<script>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast } from 'vant';
import { onMounted } from 'vue';
import { nextTick } from 'vue';
import { watch } from 'vue';
import { watchEffect } from 'vue';
export default {
    setup() {
        const router = useRouter();
        const onClickLeft = () => {
            router.go(-1);
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        const username = ref('');
        const password = ref('');
        const canclick = ref('');   //登录按钮是否可点击
        onMounted(async () => {
            await nextTick(); // 等待 DOM 更新
            showToast({
                message: '请先登录',
                position: 'top',
            });
        });

        watchEffect(() => {
            if (username.value === '' || password.value === '') {
                canclick.value = true;
            } else {
                canclick.value = false;
            }
        })

        //校验
        const userFormRules = ref({
            userName: [{ required: true, message: "请输入用户名" }],
            userPwd: [{ required: true, message: "请输入密码" }]
        })

        const onSubmit = (values) => {
            console.log('submit', values);
            const data = {
                username: username.value,
                password: password.value
            };
            axios.post('http://localhost:8081/gym/login', data)
                .then(response => {
                    // 处理后端返回的数据
                    if (response.data.code === 20041) {
                        //验证成功
                        console.log(response)
                        const token = response.data.data.token;
                        console.log("token" + token)
                        console.log(response.data.data.dbUser)
                        localStorage.setItem('token', token); // 将token存储在localStorage中
                        localStorage.setItem('UserId', response.data.data.dbUser.id)
                        showSuccessToast("登录成功")
                        const to_path = localStorage.getItem('redirectPath')
                        // router.push({ name: to_path, query: { userId: response.data.data.dbUser.id } })
                        router.push(to_path)
                        localStorage.removeItem('redirectPath')
                    } else {
                        console.log("error")
                        showFailToast("用户名或密码错误")
                    }
                })
                .catch(error => {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    showFailToast("系统出错了")
                });
        };

        const forgotpwd = () => {
            showFailToast("此功能尚未开通，请注册新的账号")
        }
        return { onClickLeft, goToPage, username, password, onSubmit, userFormRules, canclick, forgotpwd }
    }
}
</script>
<style scoped>
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    padding: 0;
    margin: 0;
    height: 100vh;
    /* 背景图 */
    background: url("../../assets/imgs/bg_home.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    background-attachment: fixed;
}

.serve {
    margin: 20vh auto;
    /* background-color: pink; */
    width: 90vw;
    box-sizing: border-box;
    background-color: rgb(0, 0, 0, 0.3);
    border-radius: 20px 20px 20px 20px;
    padding-bottom: 5px;

    .theme {
        margin-left: 20px;
        font-size: 40px;
        color: #fcecec;
    }


    .registerandforgotpwd {
        display: flex;

        .register {
            font-size: 10px;
            text-align: center;
            color: #fcecec;
            position: relative;
            left: 20px;
        }
        .forgotpassword {
            font-size: 10px;
            text-align: center;
            color: #fcecec;
            position: absolute;
            right: 30px;
        }
    }

}
</style>