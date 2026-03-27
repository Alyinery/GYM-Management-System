<template>
    <div class="world">
        <div class="head">
            <van-nav-bar color="#44A248" title="注册" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div class="serve shadow">
            <div class="theme">Register</div>
            <van-form @submit="onSubmit">
                <van-cell-group inset>
                    <van-field v-model="username" name="用户名" label="用户名" placeholder="请输入用户名"
                        :rules="userFormRules.userName" required :error-message="username_exist" />
                    <van-field v-model="password" type="password" name="密码" label="密码" placeholder="请输入密码"
                        :rules="userFormRules.userPwd" required />
                    <van-field v-model="phone" name="手机号" label="手机号" placeholder="请输入手机号"
                        :rules="userFormRules.phone" required />
                </van-cell-group>
                <div style="margin: 18px;">
                    <van-button round block type="primary" native-type="submit" :disabled="canclick">
                        快速注册
                    </van-button>
                </div>
            </van-form>
            <p class="login" @click="goToPage('/login')">已有帐号,去登陆</p>
        </div>
    </div>
</template>
<script>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';
import { showFailToast, showLoadingToast, showSuccessToast } from 'vant';
import { watch } from 'vue';
import { reactive } from 'vue';
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
        const phone = ref('')
        const username_exist = ref('');
        const canclick = ref('');    //按钮是否可按下
        const canregister = ref(false);

        // 监听用户名密码变化
        watch(password, async (newVal) => {
            const { userName, userPwd } = userFormRules.value;//解构
            const isUsernameValid = await userName[1].validator(username.value);
            const isPasswordValid = await userPwd[1].validator(password.value);
            if (isUsernameValid && isPasswordValid) {
                canclick.value = false; //注册按钮可按
                canregister.value = true;
            } else {
                canclick.value = true;  //注册按钮不可按
                canregister.value = false;
            }
        });

        //校验
        const userFormRules = ref({
            userName: [{ required: true, message: "请输入用户名" },
            {
                validator: async (newVal) => {
                    try {
                        const response = await axios.get('http://localhost:8081/gym/checkUsername', {
                            params: { username: newVal }
                        });
                        if (response.data.code === 20041) {
                            username_exist.value = '用户名已存在';
                            return false;
                        } else {
                            username_exist.value = '';
                            return true;
                        }
                    } catch (error) {
                        // 处理请求错误
                        console.error('Error while sending the request:', error);
                        showFailToast("系统出错了");
                        return false;
                    }
                },
                trigger: 'onChange',
                // message: '用户名已存在'
            }],
            userPwd: [{ required: true, message: "密码不能为空", trigger: "onChange" }, {
                validator: (value) => {
                    return /^(?=.*[a-z])(?=.*\d)[^]{8,16}$/.test(value);
                },
                message: "密码必须包含数字,字母,且不少于8位",
                trigger: "onChange"
            }],
            phone:[{required:true,message:"请输入手机号", trigger: "onChange"},{
                validator:(value) => {
                    return /^1[3-9]\d{9}$/.test(value);
                },
                message:"手机号格式错误",
                trigger:"onChange"
            }]
        })


        //请求注册
        const onSubmit = async (values) => {
            console.log('submit', values);

            // 执行用户名和密码的验证
            // const { userName, userPwd } = userFormRules.value;//解构
            // const isUsernameValid = await userName[1].validator(username.value);
            // const isPasswordValid = await userPwd[1].validator(password.value);
            if (canregister.value) {
                const data = {
                    username: username.value,
                    password: password.value,
                    addtime: new Date(),
                    phone: phone.value
                };
                axios.post('http://localhost:8081/gym/register', data)
                    .then(response => {
                        // 处理后端返回的数据
                        if (response.data.code === 20041) {
                            //注册成功
                            showLoadingToast({
                                message: '加载中...',
                                forbidClick: true,
                            })
                            setTimeout(() => {
                                showSuccessToast("注册成功");
                            }, 2500); // 在加载提示框显示1秒后再显示成功提示
                            setTimeout(() => {
                                router.push('/home');
                            }, 4000);
                        } else {
                            showFailToast("注册失败")
                            console.log("error")
                        }
                    })
                    .catch(error => {
                        // 处理请求错误
                        console.error('Error while sending the request:', error);
                        showFailToast("系统出错了")
                    });
            } else {
                showFailToast("注册失败")
            }
        }

        return { onClickLeft, goToPage, username, password,phone, username_exist, onSubmit, userFormRules, canclick }
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

    .login {
        font-size: 10px;
        text-align: center;
        color: #fcecec;
    }
}
</style>