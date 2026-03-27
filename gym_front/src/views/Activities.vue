<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="近期活动" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div class="carousel">
            <div class="core">
                <van-swipe :autoplay="3000" lazy-render>
                    <van-swipe-item v-for="image in images" :key="image">
                        <img :src="image" />
                    </van-swipe-item>
                </van-swipe>
            </div>
        </div>
        <div class="adv">
            <van-notice-bar left-icon="volume-o" scrollable text="月度挑战!当月预约进馆8次,赢￥50无门槛红包券!" />
        </div>

        <div v-if="exist_data">
            <div class="ActivitiesList">
                <div class="items" v-for="activity in Activities" :key="activity.id">
                    <div class="item" @click="joinActivity(activity)">
                        <div class="activityName">{{ activity.name }}</div>
                        <div class="activityTime">{{ formatDate(activity.startTime) }}至{{ formatDate(activity.endTime)
                            }}
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <data v-else>
            <van-empty description="近期暂未有活动" />
        </data>

    </div>
</template>

<script>
import { useRouter } from 'vue-router';
import Lshow1 from '@/assets/imgs/carousels/Lshow1.jpg'
import Lshow2 from '@/assets/imgs/carousels/Lshow2.jpg'
import { onBeforeMount, ref } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';
export default {
    setup() {
        const router = useRouter();
        const onClickLeft = () => {
            router.go(-1);
        }
        const images = [
            Lshow1,
            Lshow2
        ];
        const Activities = ref({})
        const exist_data = ref(false)
        const userAttribute = ref({})
        const ActivityRegisterInfo = ref({
            userId: Number,
            venueId: Number,
            state: Number,
            activityId:Number
        })

        const formatDate = (date) => {
            return `${new Date(date).getFullYear()}/${new Date(date).getMonth() + 1}/${new Date(date).getDate()}`
        }


        onBeforeMount(() => {
            axios.get('http://localhost:8081/gym/getAllActivityInfo')
                .then(response => {
                    if (response.data.code === 20041) {
                        console.log(response.data.data)
                        Activities.value = response.data.data;
                        exist_data.value = true;
                        console.log(Activities)
                    } else {
                        console.log("ERROR")
                        exist_data.value = false;
                    }
                }).catch(error => {
                    showFailToast("系统出错了")
                    exist_data.value = false;
                })
        })



        


        //报名请求
        const joinActivity = async (activity) => {
            console.log(activity)
            //参加活动业务请求
            const userId = localStorage.getItem("UserId");
            if (userId) {
                try {
                    const response = await axios.get('http://localhost:8081/gym/getUserAttributeById/' + userId)
                    if (response.data.code === 20041) {
                        userAttribute.value = response.data.data;
                        console.log('userAttribute', userAttribute.value)
                    } else {
                        console.log("error")
                    }
                } catch (error) {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    showFailToast("系统出错了")
                }

                console.log('111', userAttribute.value)

                if (!userAttribute.value.name) {
                    showConfirmDialog({
                        title: '温馨提示',
                        message: '您未填写个人信息,是否前往个人中心填补'
                    }).then(() => {
                        router.push('/information')
                    }).catch(() => {
                        // on cancel
                    })
                } else (
                    showConfirmDialog({
                        title: '温馨提示',
                        message: '请再次确认是否报名该活动'
                    }).then(() => {
                        ActivityRegisterInfo.value = {
                            ...ActivityRegisterInfo,
                            userId: localStorage.getItem("UserId"),
                            venueId: activity.venueId,
                            state: 0,
                            activityId:activity.id
                        }
                        console.log(ActivityRegisterInfo.value)
                        axios.post('http://localhost:8081/gym/addActivityRegisterInfo', ActivityRegisterInfo.value)
                            .then(response => {
                                if (response.data.code === 20011) {
                                    console.log("报名成功")
                                    showSuccessToast("报名成功")
                                } else {
                                    showFailToast("报名失败")
                                }
                            }).catch(error => {
                                showFailToast("系统出错了")
                            })
                    }).catch(() => {
                        // on cancel
                    })
                )
            } else {
            showConfirmDialog({
                title:'温馨提示',
                message:'您尚未登录，是否前往登录'
            }).then(() => {
                localStorage.setItem('redirectPath', '/activities')
                router.push('/login')
            }).catch(() => {
                // no
            })
                
            }


        }

        
        return {
            onClickLeft, images, Activities, exist_data, formatDate,
            joinActivity, ActivityRegisterInfo, userAttribute
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

.carousel {
    /* background-color: pink; */
    height: 180px;
    box-sizing: border-box;
    padding: 15px 20px;
    margin-bottom: 20px;

    .core {
        /* background-color: green; */
        height: 150px;
        border-radius: 10px;

        img {
            height: 180px;
            width: 100%;
            border-radius: 10px;
        }
    }
}

.adv {
    margin-bottom: 5px;
}

/* 不显示滚动条 */
.ActivitiesList::-webkit-scrollbar {
    display: none;
}

.ActivitiesList {
    /* background-color: pink; */
    margin: auto 20px;
    border-radius: 20px 20px 20px 20px;
    height: 53vh;
    overflow-y: auto;

    .item {
        background: linear-gradient(to right, #D9DFB1, #ACC7F6);
        border-radius: 20px 20px 20px 20px;
        margin: 5px auto;
        height: 50px;
        padding: 8px;

        .activityName {
            font-size: 20px;
        }

        .activityTime {
            font-size: 15px;
        }

    }
}
</style>