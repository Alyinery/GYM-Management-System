<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="场地详情" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <div class="carousel">
            <div class="core">
                <van-swipe :autoplay="2000" lazy-render>
                    <van-swipe-item v-for="image in images" :key="image">
                        <img :src="image" />
                    </van-swipe-item>
                </van-swipe>
            </div>
        </div>
        <div class="venueinfo">
            <div class="venuehead">
                <div class="pic">
                    <img :src="venue.pic" alt="">
                </div>
                <div class="nameandstar">
                    <div class="name">
                        {{ venue.name }}
                    </div>
                    <div class="star">
                        <van-rate v-model="venue.stars" :size="16" color="#ffd21e" void-icon="star" void-color="#eee" />
                    </div>
                    <div class="address">地址:XXX大学</div>
                </div>
                <div class="share" @click="() => { showShare = true }">
                    <svg t="1710180437414" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="4285" width="20" height="20">
                        <path
                            d="M902.491087 715.076138c-0.045025-0.075725-0.091074-0.152473-0.137123-0.231267-22.296819-37.681204-55.691393-62.485123-99.307773-73.736375-42.452881-10.880815-82.696444-5.764284-119.643937 15.166421l0.127913-0.226151c-3.560082 1.832741-7.068999 3.795443-10.514471 5.919826L429.070754 520.724709c0.227174-2.907213 0.350994-5.612834 0.361227-8.137331 0.106424-29.134551-6.921643-55.875588-20.934798-79.907934l248.514004-144.131655c14.560624-8.43716 22.116717-25.234731 18.805298-41.726334-2.915399-14.497179-3.067872-20.019962-3.067872-20.992103 0-22.909779 7.92653-41.497113 24.87862-58.449203 17.066701-17.028838 35.666314-24.954345 58.589396-24.954345 0.051165 0 0.102331 0 0.152473 0 22.744004 0 41.266869 7.92653 58.21896 24.877597 17.041118 17.054421 24.967648 35.667338 24.967648 58.525951s-7.92653 41.444924-24.967648 58.474786c-16.96437 17.003256-35.461653 24.927739-58.21896 24.927739-0.090051-0.024559-0.165776-0.024559-0.217964 0-21.655206 0-39.234583 17.540492-39.271422 39.222303-0.039909 21.681812 17.526165 39.298028 39.207977 39.323611 0.102331 0 0.179079 0 0.280386 0 43.748387 0 82.047668-16.160051 113.789603-47.966455 31.80845-31.80538 47.941895-70.158896 47.941895-113.981984 0-43.798529-16.121166-82.151022-47.941895-114.034173-31.792077-31.807427-70.067822-47.914266-113.739461-47.914266-0.088004 0-0.178055 0-0.268106 0-43.785226 0-82.137719 16.107863-114.03315 47.914266-31.794124 31.806403-47.914266 70.184479-47.914266 114.034173 0 2.530636 0.11461 5.242398 0.331551 8.15575L352.571453 374.303918c-3.478218-2.199085-7.016811-4.242627-10.605545-6.152117 0.110517 0.103354 0.230244 0.214894 0.341784 0.319272-22.514783-12.144598-47.334052-18.323321-74.229608-18.420535-0.091074-0.001023-0.179079-0.001023-0.268106-0.002047-43.672662-0.159636-82.005712 15.808034-113.915469 47.498804-31.936363 31.765471-48.196699 70.060659-48.357358 113.858164-0.161682 43.822065 15.83157 82.234933 47.52234 114.1539 31.625278 31.924083 69.866231 48.226375 113.614617 48.384987 0.102331 0 0.179079 0 0.280386 0 21.682835 0.055259 39.311331-17.497513 39.352263-39.178301 0.041956-21.682835-17.472953-39.288818-39.127136-39.367613-0.053212-0.022513-0.127913-0.021489-0.217964-0.001023-22.757307-0.082888-41.224914-8.075933-58.128909-25.139563-16.977673-17.09126-24.836665-35.70827-24.753777-58.56586 0.084934-22.856567 8.077979-41.441854 25.183566-58.432831 17.012466-16.889669 35.56603-24.74866 58.307987-24.664749 0.051165 0 0.101307 0 0.152473 0 22.921036 0.083911 41.491996 8.079002 58.496276 25.168216 16.889669 17.014512 24.74866 35.628452 24.664749 58.540278-0.00307 0.972141-0.176009 6.493901-3.143597 20.9788-3.372817 16.479323 4.120854 33.30657 18.651802 41.795918l16.782222 9.816576c-0.00307 0.00307-0.00614 0.005117-0.00921 0.008186l2.26253 1.309832 5.119601 2.995217 0.01535-0.021489 243.563248 141.018757-0.024559 0.053212 3.666506 2.054799 1.930979 1.118474 0.01535-0.026606 18.800181 10.536984c14.676257 8.235568 32.980135 6.183839 45.485961-5.064342 10.991332-9.891278 15.667841-12.83533 16.503882-13.329587 19.717064-11.667737 39.749306-14.310937 62.971194-8.355295 23.345708 6.014994 39.639812 17.986653 51.313689 37.712927 0.025583 0.044002 0.052189 0.088004 0.077771 0.132006 11.58178 19.572778 14.19428 39.550785 8.238638 62.772672-5.997598 23.351847-17.980513 39.652092-37.652552 51.293223-19.672038 11.640108-39.705304 14.284331-63.039755 8.29185-23.27203-5.942339-39.511899-17.824971-51.101865-37.411051-0.025583-0.089028-0.064468-0.153496-0.110517-0.187265-11.028171-18.636452-35.075867-24.832572-53.754275-13.82282-18.680455 11.007705-24.894993 35.096333-13.876032 53.769624 0.052189 0.086981 0.091074 0.153496 0.143263 0.2415 22.278399 37.650505 55.691393 62.381769 99.231025 73.501014 43.569308 11.177574 84.793198 5.529947 122.508171-16.787338 37.692461-22.305005 62.49024-55.709813 73.724095-99.331309C930.360831 793.804201 924.731624 752.661152 902.491087 715.076138z"
                            p-id="4286" fill="#2c2c2c"></path>
                    </svg>
                    <div class="text">分享</div>
                </div>
                <van-share-sheet v-model:show="showShare" title="立即分享给好友" :options="options" />
            </div>
            <div class="worktime">
                <div class="title">
                    营业时间:
                </div>
                <div class="content">
                    <van-text-ellipsis :content="text" expand-text="展开" collapse-text="收起" />
                </div>
            </div>
        </div>
        <div class="booking">
            <div class="bookhead">
                <van-button round type="success" @click="() => { showCalendar = true }">立即预约</van-button>
            </div>
            <van-calendar v-model:show="showCalendar" @confirm="closeCalender" />
            <!-- 底部弹出 -->
            <van-popup position="bottom" v-model:show="showDetailTime">
                <!-- <van-time-picker v-model="currentTime" title="选择时间" :formatter="formatter" /> -->
                <van-floating-panel v-model:height="height" :anchors="anchors">
                    <van-cell-group>
                        <van-time-picker v-model="currentTime" title="选择时间" :formatter="formatter"
                            @confirm="closeDetailTime" />
                    </van-cell-group>
                </van-floating-panel>
            </van-popup>




        </div>
    </div>
</template>

<script>
import { useRouter, useRoute } from 'vue-router';
import { createApp, ref, onMounted, onBeforeMount, nextTick, reactive } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';
import Lshow1 from '@/assets/imgs/sporttype/basketball/carousel_1.jpg'
import Lshow2 from '@/assets/imgs/sporttype/basketball/carousel_2.jpg'
import Lshow3 from '@/assets/imgs/sporttype/basketball/carousel_3.jpg'
import Lshow4 from '@/assets/imgs/sporttype/basketball/carousel_4.jpg'
import Lshow5 from '@/assets/imgs/sporttype/basketball/carousel_5.jpg'

export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const onClickLeft = () => {
            router.go(-1);
        }
        const venue = ref('');
        const images = [Lshow1, Lshow2, Lshow3, Lshow4, Lshow5];
        const text = "周一、周二、周三、周四、周五 18:00-21:00;周六、周日 08:00-21:00;";
        const showShare = ref(false);

        const bookdate = ref(new Date);
        const showCalendar = ref(false);
        const showDetailTime = ref(false);
        const currentTime = ref(['12', '00']);
        //自定义锚点
        const anchors = [
            350,
            Math.round(0.4 * window.innerHeight),
            Math.round(0.7 * window.innerHeight),
        ];
        const height = ref(anchors[0]);
        // const formatDate  = (date) => `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
        const bookInfo = ref({
            venueId: Number,
            venueName: Number,
            addtime: Date,
            userId: Number,
            information: String,
            bookStartTime: Date,
            bookEndTime: Date,
            updatetime: Date,
            booktime: Date,
            state: Number
        })
        const closeCalender = (value) => {
            console.log('value', value)
            console.log('初始的bookdate', bookdate.value)
            // bookdate.value = formatDate(value);
            bookdate.value = `${value.getFullYear()}/${value.getMonth() + 1}/${value.getDate()}`
            console.log('设置后的bookdate', bookdate.value)
            // axios.post('http://localhost:8081/gym/booking/addBookingInfo',bookInfo)
            bookInfo.value = {
                ...bookInfo.value,
                venueId: venue.value.id,
                venueName: venue.value.name,
                userId: localStorage.getItem('UserId'),
                addtime: new Date(),
                bookStartTime: value,
                bookEndTime: null,
                updatetime: new Date(),
                state: 0,
                information: "无",
            }
            console.log("bookInfo", bookInfo.value);
            console.log("addtime", bookInfo.value.addtime);
            showCalendar.value = false;
            showDetailTime.value = true;
        };

        //场馆预约业务
        const closeDetailTime = (detailTime) => {
            bookInfo.value.bookStartTime.setHours(detailTime.selectedIndexes[0])
            bookInfo.value.bookStartTime.setMinutes(detailTime.selectedIndexes[1])
            showConfirmDialog({
                title: '预约确认',
                message:'点击确定后即可完成预约',
            }).then(() => {
                    // on confirm
                    showDetailTime.value = false;
                    axios.post('http://localhost:8081/gym/booking/addBookingInfo', bookInfo.value)
                        .then(response => {
                            if (response.data.code === 20011) {
                            } else {console.log("error")}
                        }).catch(error => {
                            // 处理请求错误
                            console.error('Error while sending the request:', error);
                            showFailToast("系统出错了")
                            showDetailTime.value = false;
                        });
                }).catch(() => {
                    // on cancel
                    showCalendar.value = false;
                });
        }



        //分享面板
        const options = [
            [
                { name: '微信', icon: 'wechat' },
                { name: '朋友圈', icon: 'wechat-moments' },
                { name: '微博', icon: 'weibo' },
                { name: 'QQ', icon: 'qq' },
            ],
            [
                { name: '复制链接', icon: 'link' },
                { name: '分享海报', icon: 'poster' },
                { name: '二维码', icon: 'qrcode' },
                { name: '小程序码', icon: 'weapp-qrcode' },
            ],
        ];
        //获取场馆数据
        onBeforeMount(async () => {
            await nextTick(); // 等待 DOM 更新
            const productId = route.params.id;
            console.log("ID:" + productId);
            axios.get('http://localhost:8081/gym/getVenueDetail/' + productId)
                .then(response => {
                    if (response.data.code === 20041) {
                        venue.value = response.data.data;
                        console.log(venue)
                    } else {
                        console.log("error")
                    }
                })
        });

        //时间选择
        const formatter = (type, option) => {
            if (type === 'hour') {
                option.text += '时';
            }
            if (type === 'minute') {
                option.text += '分';
            }
            return option;
        };

        return {
            onClickLeft, images, text, venue, showShare,
            options, showCalendar, closeCalender, bookInfo, formatter,
            showDetailTime, anchors, height, closeDetailTime, currentTime
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
    margin-top: 10px;


    .core {
        height: 100%;
        width: 85%;
        /* background-color: green; */
        border-radius: 10px;
        margin: auto;

        img {
            height: 100%;
            width: 100%;
            border-radius: 10px;
        }
    }


}

.venueinfo {
    width: 85vw;
    margin: 10px auto;
    border-radius: 10px;
    background-color: white;
    border-radius: 10px;
    padding-bottom: 8px;

    .venuehead {
        height: 80px;
        /* background-color: skyblue; */
        display: flex;

        .pic {
            height: 70px;
            width: 60px;
            margin: 5px;
            border-radius: 5px;
            /* background-color: green;  */

            img {
                border-radius: 5px;
                width: 100%;
                height: 100%;
            }
        }

        .nameandstar {
            .name {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                font-size: 18px;
                margin: 5px;
            }

            .star {
                margin-left: 5px;
                /* background-color: green; */
                line-height: 10px;
            }

            .address {
                font-size: 15px;
                margin-left: 5px;
            }
        }

        .share {
            height: 20px;
            width: 20px;
            /* background-color: gray; */
            margin: auto;
            margin-right: 20px;
            font-size: 10px;
        }
    }

    .worktime {
        .title {
            font-size: 16px;
            margin-left: 5px;
        }

        .content {
            font-size: small;
            margin-left: 3px;
        }
    }
}

.booking {
    width: 85vw;
    /* background-color: pink; */
    margin: 10px auto;
    border-radius: 10px;

    .bookhead {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        font-size: 16px;
        margin-left: 5px;

        .van-button {
            width: 100%;
        }
    }
}
</style>