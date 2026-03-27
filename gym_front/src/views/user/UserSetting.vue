<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="我的信息" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div class="modify information">
            <van-cell-group inset>
                <van-form @submit="saveChanges">
                    <van-field v-model="UserData.name" center="" label="昵称" input-align="right"
                        error-message-align="right" :placeholder="UserData.name ? UserData.name : '请输入昵称'"
                        :rules="userFormRules.userName" />

                    <van-cell v-model="UserData.avator" center title="头像" is-link value="修改头像" @click="showUploader">
                        <van-image round width="1rem" height="1rem" :src="UserData.avator" />
                    </van-cell>
                    <van-cell v-model="UserData.phone" center title="手机号码" :value="UserData.phone" />
                    <van-cell v-model="UserData.gender" center title="性别"
                        :value="UserData.gender === '1' ? '男' : (UserData.gender === '0' ? '女' : '请选择')" is-link
                        @click="showGenderPicker = true" />

                    <van-cell v-model="UserData.birthday" center title="生日"
                        :value="UserData.birthday ? formatDate(UserData.birthday) : '请选择'"
                        @click="showBirthdayPicker = true" is-link />

                    <van-field v-model="UserData.height" center="" label="身高(单位cm)" input-align="right"
                        :placeholder="UserData.height ? UserData.height : '请输入您的身高'" />

                    <van-field v-model="UserData.weight" center="" label="体重(单位kg)" input-align="right"
                        :placeholder="UserData.weight ? UserData.weight : '请输入您的体重'" />



                    <van-field v-model="UserData.email" center label="邮箱" input-align="right"
                        error-message-align="right" validate-trigger="onChange"
                        :placeholder="UserData.email ? UserData.email : '请绑定邮箱'" :rules="userFormRules.email" />

                    <!-- 按钮 -->
                    <div style="margin: 16px;">
                        <van-button round block type="primary" native-type="submit">
                            确认修改
                        </van-button>
                    </div>
                </van-form>
            </van-cell-group>

            <!-- 弹窗 -->
            <van-popup v-model:show="showGenderPicker" position="bottom">
                <van-picker :columns="GenderColumns" title="性别选择" @confirm="pickGender"
                    @cancel="showGenderPicker = false" />
            </van-popup>


            <van-popup v-model:show="showBirthdayPicker" position="bottom">
                <van-date-picker title="生日选择" @confirm="pickBirthday" 
                :min-date="new Date(1990, 0, 1)" :max-date="new Date()"
                @cancel="showBirthdayPicker = false" />
            </van-popup>




        </div>
    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';
import { onBeforeMount } from 'vue';
import { reactive } from 'vue';
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const UserData = ref({
            // avator:String,
            // birthday:Date,
            // email:String,
            // gender:Number,
            // height:Number,
            // id:Number,
            // isVip:Number,
            // name:String,
            // phone:String,
            // weight:String
        });
        const fileList = ref();
        const showGenderPicker = ref(false);    //展示用户性别选择器的开关
        const showBirthdayPicker = ref(false);  //展示用户生日选择器开关
        const onClickLeft = () => {
            router.go(-1);
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        onBeforeMount(() => {
            const UserId = localStorage.getItem('UserId');
            console.log(UserId)
            axios.get('http://localhost:8081/gym/getUserAttributeById/' + UserId)
                .then(response => {
                    if (response.data.code === 20041) {
                        UserData.value = response.data.data;
                        console.log("UserDDDData", UserData.value);
                        console.log(response.data);
                    } else {
                        console.log("error")
                    }
                }).catch(error => {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    showFailToast("系统出错了")
                });
        })
        //格式化日期
        const formatDate = (date) => `${new Date(date).getFullYear()}/${new Date(date).getMonth() + 1}/${new Date(date).getDate()}`;

        const GenderColumns = [
            { text: '男', value: 'male' },
            { text: '女', value: 'female' },
        ];


        const saveChanges = (values) => {
            console.log('UserData', UserData)
            //修改用户信息请求
            axios.post('http://localhost:8081/gym/modifyUserAttributeById',UserData.value)
            .then(response => {
                if(response.data.code === 20031) {
                    console.log("modify success")
                    UserData.value = response.data.data; // 更新响应式引用
                }else {
                    console.log("error")
                }
            })

        }
        const showUploader = () => {
            // 点击头像时触发，显示文件选择框
            fileList.value = [];
        };



        //校验

        const emailpattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const userFormRules = ref({
            email: [{ validator: (value) => emailpattern.test(value), message: "请输入正确的邮箱格式" }],
            userName: [{ required: true, message: "请输入用户名" }],
            userPwd: [{ required: true, message: "请输入密码" }]
        })

        //确定性别
        const pickGender = ({ selectedOptions }) => {
            if (selectedOptions[0]?.text === '男') {
                UserData.value.gender = '1';
            } else {
                UserData.value.gender = '0';
            }
            console.log(selectedOptions[0]?.text)
            console.log(UserData.value.gender)
            showGenderPicker.value = false;
        }

        //确定生日
        const pickBirthday = ({ selectedValues }) => {
            UserData.value.birthday = new Date(`${selectedValues[0]}/${selectedValues[1]}/${selectedValues[2]}`)
            console.log(UserData.value.birthday)
            console.log(selectedValues[0])
            showBirthdayPicker.value = false;
        }



        return {
            onClickLeft, goToPage, UserData, userFormRules,
            saveChanges, showUploader, fileList, showGenderPicker, showBirthdayPicker,
            GenderColumns, pickGender, formatDate, pickBirthday, emailpattern

        };
    }
}
</script>

<style scoped>
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}



.modify {
    padding-top: 20px;



    .van-form {
        padding: 0px;
    }
}
</style>