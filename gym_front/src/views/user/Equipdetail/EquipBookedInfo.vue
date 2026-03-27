<template>

    <div v-if="exist_data">
        <div class="bookingList">
            <div class="items" v-for="item in bookingInfoList" :key="item.id">
                <div class="item" v-if="item.state === '1'">
                    <div class="info" @click="showPopup(item)">
                        <div class="venuename">名称:{{ item.e_name }}</div>
                        <div class="bookingtime">预约时间:{{ `${new Date(item.rentStartTime).getFullYear()}/${new
        Date(item.rentStartTime).getMonth() + 1}/${new Date(item.rentStartTime).getDate()}` }}
                        </div>
                    </div>
                    <div class="btn">
                        <van-button v-if="item.state === '1'" type="success" size="mini">评价分享</van-button>
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
            场地名称：{{ currentItem.e_name }}<br>
            租借申请时间：{{ currentItem.rentStartTime }}<br>
            租借状态：
            <span v-if="currentItem.state === '0'">租界中</span>
            <span v-else-if="currentItem.state === '1'">已归还</span>
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
            router.push('/information');
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        const onClickTab = ({ name }) => {
            router.push('/' + name)
        }
        const bookingInfoList = ref({})
        const exist_data = ref(false)
        const showCenter = ref(false);    //展示弹出层
        const currentItem = ref(null);

        //根据venue.id获取球馆名称
        onBeforeMount(() => {
            const UserId = localStorage.getItem('UserId');
            console.log(UserId)
            //获取所有待完成的数据
            axios.get('http://localhost:8081/gym/booking/getEquipBookingInfoById', { params: { user_id: UserId } })
                .then(response => {
                    if (response.data.code === 20041) {
                        bookingInfoList.value = response.data.data;
                        console.log("BookingInfoList", bookingInfoList.value);
                        console.log(response.data);
                        exist_data.value = true
                    } else {
                        console.log("error")
                        exist_data.value = false
                    }
                })
        })
        const showPopup = (item) => {
            currentItem.value = item;
            showCenter.value = true;
        }

        return { onClickLeft, goToPage, onClickTab, exist_data, bookingInfoList, showPopup, currentItem, showCenter }
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