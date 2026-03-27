<template>
    <div v-if="exist_data">
        <div class="bookingList">
            <div class="items" v-for="item in bookingInfoList" :key="item.id">
                <div class="item">
                    
                    <div class="info" @click="showPopup(item)">
                        <div class="venuename">名称:{{ item.venueName }}</div>
                        <div class="bookingtime">预约时间:{{ `${new Date(item.booktime).getFullYear()}/${new
        Date(item.booktime).getMonth() + 1}/${new Date(item.booktime).getDate()}` }}</div>
                    </div>
                    <div class="btn">
                        <van-button type="primary" color="#42b983" size="mini">待处理</van-button>
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
            预约用户：{{ currentItem.u_name }}<br>
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
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';
import { onBeforeMount } from 'vue';
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
        const bookingInfoList = ref();
        const exist_data = ref(false);
        const showCenter = ref(false);    //展示弹出层
        const currentItem = ref(null);
        onBeforeMount(() => {
            axios.get('http://localhost:8081/gym/booking/getAllUnBookedInfo')
                .then(response => {
                    if (response.data.code === 20041) {
                        console.log('YYYYYY');
                        bookingInfoList.value = response.data.data;
                        console.log(bookingInfoList);
                        exist_data.value = true;
                    } else {
                        console.log('NNNNN');
                        exist_data.value = false;
                    }
                }).catch(error => {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    exist_data.value = false;
                    showFailToast("系统出错了")
                });
        })
        
        const showPopup = (item) => {
            currentItem.value = item;
            showCenter.value = true;
        }
        return { onClickLeft, goToPage, bookingInfoList,exist_data,showPopup,currentItem,showCenter }
    }
}
</script>

<style scoped lang="less">
.bookingList {
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