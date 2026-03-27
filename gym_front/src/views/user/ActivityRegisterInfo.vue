<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="活动报名记录" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div v-if="exist_data">
            <div class="ActivityList">
                <div class="items" v-for="activity in Activities" :key="activity.id">
                    <van-swipe-cell>
                        <div class="item">
                            <div class="info">
                                <div class="activityName">{{ activity.act_name }}</div>
                                <div class="activityTime">{{ formatDate(activity.act_startTime)
                                    }}至{{ formatDate(activity.act_endTime) }}
                                </div>
                            </div>
                            <div class="btn">
                                <van-button type="primary" color="#B092F7" size="mini">{{
                checkDate(new Date(activity.act_startTime), new Date(activity.act_endTime))}}</van-button>
                            </div>
                        </div>
                    </van-swipe-cell>
                </div>
            </div>
        </div>
        <div v-else>
            <van-empty description="近期暂未有活动上线" />
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
import { computed } from 'vue';
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
        const Activities = ref({})
        const exist_data = ref(false)

        const formatDate = (date) => {
            return `${new Date(date).getFullYear()}/${new Date(date).getMonth() + 1}/${new Date(date).getDate()}`
        }

        onBeforeMount(() => {
            const UserId = localStorage.getItem('UserId');
            console.log(UserId)
            axios.get('http://localhost:8081/gym/getActivityRegisterInfoByUserId', { params: { userId: UserId } })
                .then(response => {
                    if (response.data.code === 20041) {
                        Activities.value = response.data.data;
                        console.log("UserDDDData", Activities.value);
                        console.log(response.data);
                        exist_data.value = true;
                    } else {
                        console.log("error")
                        showFailToast('系统出错了')
                        exist_data.value = false;
                    }
                }).catch(error => {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    showFailToast("系统出错了")
                    exist_data.value = false;
                });
        }
        )

        const checkDate = (startTime, endTime) => {
            const today = new Date()
            console.log('startTime:', startTime);
            console.log('endTime:', endTime);
            console.log('today:', today);
            if (today < startTime) {
                return '未开始';
            } else if (today >= startTime && today <= endTime) {
                return '进行中';
            } else {
                return '已结束'
            }
        }

        return { onClickLeft, goToPage, Activities, exist_data, formatDate, checkDate };
    }
}
</script>
<style scoped>
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}

/* 不显示滚动条 */
.ActivityList::-webkit-scrollbar {
    display: none;
}

.ActivityList {
    /* background-color: pink; */
    margin: auto 25px;
    margin-top: 10px;
    height: 91vh;
    overflow-y: auto;


    .item {
        background: linear-gradient(to right, #D9DFB1, #ACC7F6);
        margin-bottom: 10px;
        border-radius: 10px 10px 10px 10px;
        padding: 8px;
        display: flex;
        font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        font-size: 18px;
        height: 50px;

        .info {
            flex: 8;

            .activityName {
                font-size: 20px;
                margin-bottom: 5px;
            }

            .activityTime {
                font-size: 15px;
            }
        }

        .btn {
            flex: 2;
            height: 100%;
            display: flex;

            .van-button {
                margin: auto;
            }
        }
    }


}
</style>