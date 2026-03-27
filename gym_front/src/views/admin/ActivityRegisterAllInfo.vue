<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="活动报名表" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div v-if="exist_data">
            <div class="ActivityList">
                <div class="items" v-for="activity in Activities" :key="activity.id">
                    <van-swipe-cell>
                        <div class="item">
                            <div class="info">
                                <div class="activityName">{{ activity.name }}</div>
                                <div class="activityTime">{{ formatDate(activity.startTime)
                                    }}至{{ formatDate(activity.endTime) }}
                                </div>
                            </div>
                            <div class="btn">
                                <van-button type="primary" color="#FCA55A" size="mini"
                                    @click="lookActivityRegisterInfo(activity.id)">报名表</van-button>
                            </div>
                        </div>
                    </van-swipe-cell>
                </div>
            </div>

            <van-popup v-model:show="showRegisterInfoDetailList" round :style="{ padding: '20px' }" closeable>
                <div v-if="exist_registerList">
                    <div class="RegisterList">
                        <div class="items" v-for="userdata in ActivityRegisterList">
                            <div class="item">
                                <div class="userId">参赛者ID:{{ userdata.user_id ? userdata.user_id :'无'}}&nbsp;&nbsp;&nbsp;&nbsp;参赛者:{{ userdata.u_name? userdata.u_name:'无' }}&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                <div class="phone">联系电话:{{ userdata.u_phone?userdata.u_phone:'无' }}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <van-empty  description="此活动未有参赛者" />
                </div>
            </van-popup>
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
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const onClickLeft = () => {
            router.go(-1);
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        const Activities = ref({})
        const exist_data = ref(false)
        const exist_registerList = ref(false)
        const ActivityRegisterList = ref({})
        const showRegisterInfoDetailList = ref(false)
        onBeforeMount(() => {
            axios.get('http://localhost:8081/gym/getAllActivityInfo')
                .then(response => {
                    if (response.data.code === 20041) {
                        console.log(response.data.data)
                        Activities.value = response.data.data;
                        exist_data.value = true;
                        console.log(Activities.value)
                    } else {
                        console.log("ERROR")
                        exist_data.value = false;
                    }
                }).catch(error => {
                    showFailToast("系统出错了")
                    exist_data.value = false;
                })

        })

        const formatDate = (date) => {
            return `${new Date(date).getFullYear()}/${new Date(date).getMonth() + 1}/${new Date(date).getDate()}`
        }
        
        //根据ActivityId获取报名表
        const lookActivityRegisterInfo = (activityId) => {
            showRegisterInfoDetailList.value = true;

            axios.get('http://localhost:8081/gym/getActivityRegisterInfoByActivityId',{params:{activityId:activityId}})
            .then(response => {
                if(response.data.code === 20041) {
                    ActivityRegisterList.value = response.data.data;
                    exist_registerList.value = true;
                    console.log(ActivityRegisterList.value)
                }else {
                    exist_registerList.value = false;
                    console.log('error')
                }
            }).catch(() => {
                showFailToast('系统出错了')
                console.log('系统出错了')
            })
        }

        return { onClickLeft, goToPage,exist_data,formatDate,Activities,
            lookActivityRegisterInfo,ActivityRegisterList,showRegisterInfoDetailList,
            exist_registerList };
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
        background: linear-gradient(to right, #9CEBF0, #C66D6D);
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


.RegisterList {
    height: 30vh;
    overflow-y: auto;
    .item {
        border-bottom: #9CEBF0 1px solid;
    }
}
</style>