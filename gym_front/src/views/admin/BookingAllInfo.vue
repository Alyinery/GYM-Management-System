<template>
    <div v-if="exist_data">
        <div class="bookingList">
            <div class="items" v-for="item in bookingInfoList" :key="item.id">
                <div class="item" >
                    <div class="info" @click="showPopup(item)">
                        <div class="venuename">名称:{{ item.v_name }}</div>
                        <div class="bookingtime">预约时间:{{ `${new Date(item.bookStartTime).getFullYear()}/${new
        Date(item.bookStartTime).getMonth() + 1}/${new Date(item.bookStartTime).getDate()}` }}</div>
                    </div>
                    <div class="btn">
                        <van-button type="primary" color="#42b983" size="mini" @click="solveBookingInfo(item)">立即处理</van-button>
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
            价格:{{ currentItem.v_price }}元/小时<br>
            场地名称：{{ currentItem.v_name }}<br>
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
import { onMounted,onBeforeMount, ref } from 'vue';
import { useRouter } from 'vue-router';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';
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
        const showCenter = ref(false);
        const currentItem = ref(null);
        const newitem = ref({
            id: Number,
            venueId: Number,
            userId:Number,
            addtime: Date,
            information: String,
            bookStartTime: Date,
            bookEndTime: Date,
            updatetime: Date,
            state: Number
        })
        onBeforeMount(() => {
            axios.get('http://localhost:8081/gym/booking/getAllBookingInfo')
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

        const solveBookingInfo = (item) => {
            showConfirmDialog({
                title: '处理确认',
                message: '是否通过预约申请'
            }).then(() => {
                // on confirm
                console.log('olditem',item)
                newitem.value = item;
                newitem.value.userId = item.user_id;
                newitem.value.venueId = item.venue_id;
                newitem.value.state = '1';
                console.log('newitem',newitem.value)
                axios.post('http://localhost:8081/gym/booking/modifyBookInfo', newitem.value)
                    .then(response => {
                        if (response.data.code === 20031) {
                            console.log("modify success");
                            
                            location.reload();
                        } else {
                            console.log("modify failure")
                        }
                    }).catch(error => {
                        // 处理请求错误
                        console.error('Error while sending the request:', error);
                        showFailToast("系统出错了")
                    });
            }).catch(() => {
                // on cancel
            })
        }

        const showPopup = (item) => {
            console.log('被点击的item',item)
            currentItem.value = item;
            showCenter.value = true;
        }

        return { onClickLeft, goToPage, bookingInfoList, solveBookingInfo,
            exist_data,currentItem,showPopup,showCenter,newitem }
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