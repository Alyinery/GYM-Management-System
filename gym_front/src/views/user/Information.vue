<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="我的信息" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div class="userhead">
            <div class="avator"><img src="@/assets/imgs/user_avator.jpg" alt=""></div>
            <div class="userinfo">
                <div class="name">{{ UserData?.name ? UserData.name : '用户昵称' }}</div>
                <div class="status">
                    <van-tag type="primary" size="medium" @click="showWarning">{{ UserData?.isVip ? '会员用户' : '普通用户' }}</van-tag>
                </div>
            </div>
            <div class="setting" @click="goToPage('/userSetting')">
                <svg t="1710075816104" class="icon" viewBox="0 0 1024 1024" version="1.1"
                    xmlns="http://www.w3.org/2000/svg" p-id="4272" width="30" height="30">
                    <path
                        d="M916.7 424h-99.6c-6.6-23-15.8-45-27.2-65.5l70.4-70.4c16.1-16.1 16.1-42.3 0-58.4l-66.1-66.1c-16.1-16.1-42.3-16.1-58.4 0L665.5 234C645 222.7 623 213.5 600 206.9v-99.6c0-22.7-18.6-41.3-41.3-41.3h-93.4c-22.7 0-41.3 18.6-41.3 41.3v99.6c-23 6.6-45 15.8-65.5 27.2L288 163.6c-16.1-16.1-42.3-16.1-58.4 0l-66.1 66.1c-16.1 16.1-16.1 42.3 0 58.4l70.4 70.4c-11.2 20.5-20.4 42.5-27 65.5h-99.6C84.6 424 66 442.6 66 465.3v93.4c0 22.7 18.6 41.3 41.3 41.3h99.6c6.6 23 15.8 45 27.2 65.5L163.6 736c-16.1 16.1-16.1 42.3 0 58.4l66.1 66.1c16.1 16.1 42.3 16.1 58.4 0l70.4-70.4c20.6 11.4 42.5 20.5 65.5 27.2v99.6c0 22.7 18.6 41.3 41.3 41.3h93.4c22.7 0 41.3-18.6 41.3-41.3v-99.6c23-6.6 45-15.8 65.5-27.2l70.4 70.4c16.1 16.1 42.3 16.1 58.4 0l66.1-66.1c16.1-16.1 16.1-42.3 0-58.4L790 665.5c11.4-20.6 20.5-42.5 27.2-65.5h99.6c22.7 0 41.3-18.6 41.3-41.3v-93.4c-0.1-22.7-18.7-41.3-41.4-41.3zM512.1 650.6c-76.6 0.1-138.8-62.1-138.7-138.7 0.1-75.4 63.2-138.5 138.5-138.5 76.6-0.1 138.8 62.1 138.7 138.7 0 75.4-63.1 138.5-138.5 138.5z"
                        p-id="4273" fill="#2c2c2c"></path>
                </svg>
            </div>
        </div>
        <div class="myorder">
            <div class="head">
                <div class="title">我的记录</div>
                <div class="detail" @click="goToPage('/book')">去运动</div>
            </div>
            <div class="select">
                <div class="prepare item" @click="goToPage('/userbookInfo')">
                    <div class="pic">
                        <svg t="1712981700707" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="2591" width="30" height="30">
                            <path
                                d="M778.24 61.44a122.88 122.88 0 0 1 122.88 122.88v655.36a122.88 122.88 0 0 1-122.88 122.88H245.76a122.88 122.88 0 0 1-122.88-122.88V184.32a122.88 122.88 0 0 1 122.88-122.88h532.48z m0 61.44H245.76a61.44 61.44 0 0 0-61.3376 57.83552L184.32 184.32v655.36a61.44 61.44 0 0 0 57.83552 61.3376L245.76 901.12h532.48a61.44 61.44 0 0 0 61.3376-57.83552L839.68 839.68V184.32a61.44 61.44 0 0 0-57.83552-61.3376L778.24 122.88zM563.2 532.48a30.72 30.72 0 0 1 0 61.44h-266.24a30.72 30.72 0 0 1 0-61.44h266.24z m163.84-225.28a30.72 30.72 0 0 1 0 61.44h-430.08a30.72 30.72 0 0 1 0-61.44h430.08z"
                                fill="#131415" p-id="2592"></path>
                        </svg>
                    </div>
                    <div class="fun">预约记录</div>
                </div>
                <div class="done item" @click="goToPage('/userequipbookInfo')">
                    <div class="pic">

                        <svg t="1712981612191" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="4501" width="30" height="30">
                            <path
                                d="M845.106634 191.70516l-166.553317 0c-63.576991 0-115.306142 51.729152-115.306142 115.306142l0 89.682555-102.494349 0 0-89.682555c0-63.576991-51.729152-115.306142-115.306142-115.306142l-166.553317 0c-63.576991 0-115.306142 51.729152-115.306142 115.306142l0 409.977395c0 63.576991 51.729152 115.306142 115.306142 115.306142l166.553317 0c63.576991 0 115.306142-51.729152 115.306142-115.306142l0-89.682555 102.494349 0 0 89.682555c0 63.576991 51.729152 115.306142 115.306142 115.306142l166.553317 0c63.576991 0 115.306142-51.729152 115.306142-115.306142l0-409.977395C960.412776 243.434312 908.683625 191.70516 845.106634 191.70516zM883.542015 716.988698c0 21.194718-17.240663 38.435381-38.435381 38.435381l-166.553317 0c-21.194718 0-38.435381-17.240663-38.435381-38.435381l0-128.117936c0-21.225417-17.209964-38.435381-38.435381-38.435381l-179.36511 0c-21.225417 0-38.435381 17.209964-38.435381 38.435381l0 128.117936c0 21.194718-17.240663 38.435381-38.435381 38.435381l-166.553317 0c-21.194718 0-38.435381-17.240663-38.435381-38.435381l0-409.977395c0-21.194718 17.240663-38.435381 38.435381-38.435381l166.553317 0c21.194718 0 38.435381 17.240663 38.435381 38.435381l0 128.117936c0 21.225417 17.209964 38.435381 38.435381 38.435381l179.36511 0c21.225417 0 38.435381-17.209964 38.435381-38.435381l0-128.117936c0-21.194718 17.240663-38.435381 38.435381-38.435381l166.553317 0c21.194718 0 38.435381 17.240663 38.435381 38.435381L883.542015 716.988698z"
                                fill="#1A1A1A" p-id="4502"></path>
                        </svg>
                    </div>
                    <div class="fun">器材租借</div>
                </div>
                <div class="lapse item" @click="goToPage('/activityRegisterInfo')">
                    <div class="pic">
                        <svg t="1712981757267" class="icon" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="4826" width="30" height="30">
                            <path
                                d="M513.3 773.1c-161.5 0-292.8-131.4-292.8-292.8V229.7c0-71 57.7-128.7 128.7-128.7h328.3c70.9 0 128.7 57.7 128.7 128.7v250.5c0 161.5-131.4 292.9-292.9 292.9zM349.2 191.2c-21.3 0-38.6 17.3-38.6 38.6v250.5c0 111.7 91 202.7 202.7 202.7s202.8-91 202.8-202.8V229.7c0-21.3-17.3-38.6-38.6-38.6H349.2zM626 928.2H400.7c-24.9 0-45-20.2-45-45 0-24.9 20.2-45 45-45H626c24.9 0 45 20.2 45 45s-20.1 45-45 45z"
                                fill="#333333" p-id="4827"></path>
                            <path
                                d="M827.1 506.1h-66c-24.9 0-45-20.2-45-45V284.6c0-24.9 20.2-45 45-45h66c73.5 0 133.3 59.8 133.3 133.3 0 73.4-59.8 133.2-133.3 133.2zM806.2 416h20.9c23.8 0 43.2-19.4 43.2-43.2 0-23.8-19.4-43.2-43.2-43.2h-20.9V416zM265.6 506.1h-66c-73.5 0-133.3-59.8-133.3-133.3 0-73.5 59.8-133.3 133.3-133.3h66c24.9 0 45 20.2 45 45v176.4c0 25.1-20.1 45.2-45 45.2z m-66-176.4c-23.8 0-43.2 19.4-43.2 43.2 0 23.8 19.4 43.2 43.2 43.2h20.9v-86.3h-20.9zM513.3 928.2c-24.9 0-45-20.2-45-45V728c0-24.9 20.2-45 45-45s45 20.2 45 45v155.2c0.1 24.8-20.1 45-45 45z"
                                fill="#333333" p-id="4828"></path>
                        </svg>
                    </div>
                    <div class="fun">参与活动</div>
                </div>
            </div>
        </div>
        <div class="other">
            <van-cell-group inset>
                <van-cell title="运动记录" is-link to="/mysportlog" />
                <van-cell title="卡券包" is-link />
                <van-cell title="我的活动" is-link />
                <van-cell title="意见反馈" is-link />
                <van-cell title="退出登录" @click="moveuser" />
            </van-cell-group>

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
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const UserData = ref(null);
        const onClickLeft = () => {
            router.push('/home');
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
        }
        )

        //退出登录
        const moveuser = () => {
            showConfirmDialog({
                title: '温馨提示',
                message:
                    '请确认是否退出登录',
            }).then(() => {
                // on confirm
                router.push('/home')
                localStorage.removeItem('UserId')
                localStorage.removeItem('redirectPath')
                localStorage.removeItem('token')
            })
                .catch(() => {
                    // on cancel
                });

        }

        const showWarning = () => {
            showFailToast('目前会员功能尚未开通')
        }

        return { onClickLeft, goToPage, UserData, moveuser,showWarning };
    }
}
</script>

<style scoped>
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}

.userhead {
    height: 80px;
    background-color: white;
    margin: 15px;
    border-radius: 10px;
    display: flex;
    align-items: center;

    .avator {
        height: 50px;
        width: 50px;
        border-radius: 50%;
        /* background-color: green; */
        margin: 20px;

        img {
            height: 50px;
            width: 50px;
            border-radius: 50%;
        }
    }

    .userinfo {

        /* display: block; */
        .name {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 20px;
            line-height: 20px;
            height: 20px;
            margin-bottom: 3px;
        }

        .status {
            display: block;
            height: 20px;
            line-height: 20px;
        }


    }

    .setting {
        width: 30px;
        height: 30px;
        margin-left: auto;
        margin-right: 20px;
    }
}

.myorder {
    height: 100px;
    background-color: white;
    margin: 15px;
    border-radius: 10px;

    .head {
        display: flex;
        /* background-color: green; */
        height: 30%;
        align-items: center;

        .title {
            margin-left: 15px;
            font-size: 16px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .detail {
            margin-left: auto;
            margin-right: 10px;
            font-size: 13px;
            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
            color: orange;
        }
    }

    .select {
        height: 70%;
        /* background-color: yellow; */
        display: flex;
        align-items: center;

        .item {
            width: 33.3%;
            height: 100%;
            /* background-color: gray; */
            text-align: center;

            .pic {
                height: 50%;
                width: 40%;
                /* background-color: palegoldenrod; */
                margin: 10px auto;
                margin-bottom: 0px;
            }

            .fun {
                height: 37%;
                font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
                font-size: 13px;
                /* background-color: tan; */
            }
        }
    }
}
</style>
