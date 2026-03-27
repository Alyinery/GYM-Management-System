<template>
    <div v-if="exist_data">
        <div class="unbookedList">
            <div class="items" v-for="item in UnBookedInfoList" :key="item.id">
                <div class="item">
                    <div class="info" @click="showPopup(item)">
                        <div class="venuename">名称:{{ item.v_name }}</div>
                        <div class="bookingtime">预约时间:{{ `${new Date(item.bookStartTime).getFullYear()}/${new
        Date(item.bookStartTime).getMonth() + 1}/${new Date(item.bookStartTime).getDate()}` }}</div>
                    </div>
                    <div class="btn">
                        <van-button type="primary" color="#42b983" size="mini">已取消</van-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-else>
        <van-empty description="暂无数据" />
    </div>

    <!-- 弹出层组件 -->
    <van-popup v-model:show="showCenter" round :style="{ padding: '10px' }">
        <div v-if="currentItem">
            场地名称：{{ currentItem.v_name }}<br>
            价格:{{ currentItem.v_price }}元/小时<br>
            创建时间：{{ currentItem.addtime }}<br>
            预约开始时间：{{ currentItem.bookStartTime }}<br>
            预约结束时间：{{ currentItem.bookEndTime }}<br>
            预约状态：
            <span v-if="currentItem.state === '0'">待审核</span>
            <span v-else-if="currentItem.state === '1'">已审核</span>
            <span v-else-if="currentItem.state === '2'">已失效</span>
        </div>
    </van-popup>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast } from 'vant';
export default {
    setup() {
        const router = useRouter();
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        const onClickLeft = () => {
            router.go(-1);
        };
        const UnBookedInfoList = ref();
        const exist_data = ref(false);
        const showCenter = ref(false);    //展示弹出层
        const currentItem = ref(null);
        onMounted(() => {
            const UserId = localStorage.getItem('UserId');
            console.log(UserId)
            axios.get('http://localhost:8081/gym/booking/getUnBookedListByUserId', { params: { user_id: UserId } })
                .then(response => {
                    if (response.data.code === 20041) {
                        UnBookedInfoList.value = response.data.data;
                        console.log("UnBookingInfoList", UnBookedInfoList.value);
                        console.log(response.data);
                        exist_data.value = true;
                    } else {
                        console.log("error")
                        exist_data.value = false;
                    }
                })
        })

        const showPopup = (item) => {
            currentItem.value = item;
            showCenter.value = true;
        }
        return { goToPage, onClickLeft, UnBookedInfoList, exist_data,showCenter,showPopup,currentItem }
    }
}
</script>

<style scoped lang="less">
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}


.unbookedList {
    border-radius: 10px;
    width: 85vw;
    margin: 10px auto;
    background-color: white;
    box-sizing: border-box;
    padding: 5px;

    .item {
        display: flex;
        font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        font-size: 18px;
        height: 50px;

        .info {
            flex: 8;
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
