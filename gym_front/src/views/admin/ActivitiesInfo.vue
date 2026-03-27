<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="活动管理" left-text="返回" left-arrow right-text="上新活动" @click-left="onClickLeft"
                @click-right="addNewActivityWindow" />
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
                                <van-button type="primary" color="red" size="mini"
                                    @click="deleteActivity(activity.id)">下架活动</van-button>
                            </div>
                        </div>
                    </van-swipe-cell>
                </div>
            </div>
        </div>
        <div v-else>
            <van-empty description="近期暂未有活动上线" />
        </div>

        <!-- 上线新活动的弹窗 -->
        <van-popup v-model:show="showAddNewActivityWindow" round :style="{ padding: '1px' }">
            <div class="addNewActivity information">
                <div class="addActivityTitle">上线新活动</div>
                <van-cell-group inset>
                    <van-form @submit="addNewActivity" style="padding-top:0px">
                        <van-field v-model="NewActivityInfo.name" name="活动名称" label="活动名称" input-align="right"
                            error-message-align="right" :placeholder="'请输入活动名称'" :rules="activityFormRule.name" />

                        <!-- 开始时间选择 -->
                        <van-field v-model="NewActivityInfo.startTime" is-link readonly name="picker" label="选择器"
                            placeholder="点击选择活动开始时间" error-message-align="right" @click="showActivitystartTimePicker = true" input-align="right" :rules="activityFormRule.startTime"/>
                        <van-popup v-model:show="showActivitystartTimePicker" position="bottom">
                            <van-date-picker title="选择日期" :min-date="TimePickerLimit.minDate"
                                :max-date="TimePickerLimit.maxDate" @confirm="NewActivityStartTimePicker"
                                @cancel="showActivitystartTimePicker = false" />
                        </van-popup>

                        <!-- 结束时间选择 -->
                        <van-field v-model="NewActivityInfo.endTime" is-link readonly name="picker" label="选择器"
                            placeholder="点击选择活动结束时间" error-message-align="right" @click="showActivityendTimePicker = true" input-align="right" :rules="activityFormRule.endTime"/>
                        <van-popup v-model:show="showActivityendTimePicker" position="bottom">
                            <van-date-picker title="选择日期" :min-date="TimePickerLimit.minDate"
                                :max-date="TimePickerLimit.maxDate" @confirm="NewActivityEndTimePicker"
                                @cancel="showActivityendTimePicker = false" />
                        </van-popup>

                        <!-- 活动场馆选择 -->
                        <van-field v-model="ActivityVenue" is-link readonly name="picker" label="活动场地"
                            placeholder="点击选择活动场地" error-message-align="right" @click="showVenuePicker = true" input-align="right" :rules="activityFormRule.venue"/>
                        <van-popup v-model:show="showVenuePicker" position="bottom">
                            <van-picker :columns="VenueColumns" @confirm="VenuePicked"
                                @cancel="showVenuePicker = false" />
                        </van-popup>





                        <van-field v-model="NewActivityInfo.information" rows="2" autosize label="活动简介" type="textarea"
                            maxlength="50" placeholder="请输入简介" show-word-limit input-align="right" />


                        <!-- 按钮 -->
                        <div style="margin: 16px;">
                            <van-button round block type="primary" native-type="submit">
                                提交上线
                            </van-button>
                        </div>
                    </van-form>
                </van-cell-group>
            </div>

        </van-popup>
    </div>
</template>

<script>
import axios from 'axios';
import { onBeforeMount, ref } from 'vue';
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
        }

        const exist_data = ref(false)
        const Activities = ref({})
        const showAddNewActivityWindow = ref(false)
        const showActivitystartTimePicker = ref(false)
        const showActivityendTimePicker = ref(false)
        const todayDate = ref(new Date())
        const ActivityVenue = ref('')
        const showVenuePicker = ref(false)
        const VenuesData = ref({})
        const VenueColumns = ref([])
        const NewActivityInfo = ref({
            name: String,
            information: String,
            startTime: Date,
            endTime: Date,
            venueId: Number,
            pic: String
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

            NewActivityInfo.value = {
                ...NewActivityInfo,
                name: '',
                information: '',
                pic: null,
                startTime: null,
                endTime: null,
                venueId: null,
                pic: null
            }
        })

        const deleteActivity = (id) => {

            showConfirmDialog({
                title: '温馨提示',
                message: '是否下架此活动'
            }).then(() => {
                console.log('yes')
                axios.post('http://localhost:8081/gym/deleteActivityInfo', null, { params: { activityId: id } })
                    .then(response => {
                        if (response.data.code === 20021) {
                            console.log('delete success')
                            Activities.value = response.data.data;
                        } else {
                            console.log('delete fail')
                            showFailToast('系统出错了')
                        }
                    })

            }).catch(() => {
                console.log('no')
            })
        }


        const addNewActivityWindow = async () => {
            showAddNewActivityWindow.value = true;
            try {
                const response = await axios.get('http://localhost:8081/gym/getAllVenues');
                if (response.data.code === 20041) {
                    const venuesData = response.data.data;

                    if (Array.isArray(venuesData)) {
                        console.log('yes Array')
                        VenuesData.value = venuesData;
                        venuesData.forEach(venue => {
                            VenueColumns.value.push({ text: venue.name, value: String(venue.id) });
                        });
                        console.log('VenueColumns after push:', VenueColumns.value);
                    } else {
                        console.error("venuesData is not an array:", venuesData);
                        showFailToast('系统出错了，返回的场馆数据不是数组');
                    }

                    console.log('VenueColumns', VenueColumns)
                } else {
                    console.log("error")
                    showFailToast('系统出错了')
                }
            } catch {
                console.log("error")
                showFailToast('系统出错了')
            }

        }







        //活动表单校验
        const activityFormRule = ref({
            name: [{ required: true, message: "请输入活动名称" }],
            startTime: [{ required: true, message: "请选择活动开始时间" }],
            endTime: [{ required: true, message: "请选择活动结束时间" }],
            venue: [{ required:true, message:'请选择活动场地'}]
        })


        //时间选择
        const TimePickerLimit = ref({
            // minDate: new Date(),
            // maxDate: new Date()
            minDate: new Date(),
            maxDate: new Date(2025, 5, 1)      //写死了!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    
        })


        const NewActivityStartTimePicker = ({ selectedOptions }) => {
            const year = selectedOptions[0]?.value;
            const month = selectedOptions[1]?.value - 1;
            const day = selectedOptions[2]?.value;
            NewActivityInfo.value.startTime = formatDate(new Date(year, month, day))
            console.log(NewActivityInfo.value.startTime)
            showActivitystartTimePicker.value = false;
        }

        const NewActivityEndTimePicker = ({ selectedOptions }) => {
            const year = selectedOptions[0]?.value;
            const month = selectedOptions[1]?.value - 1;
            const day = selectedOptions[2]?.value;
            NewActivityInfo.value.endTime = formatDate(new Date(year, month, day))
            console.log(NewActivityInfo.value.endTime)
            showActivityendTimePicker.value = false;
        }

        //选择比赛场地
        const VenuePicked = ({ selectedOptions }) => {
            ActivityVenue.value = selectedOptions[0].text;
            NewActivityInfo.value.venueId = selectedOptions[0].value;
            showVenuePicker.value = false;
        }

        //上新活动提交按钮业务
        const addNewActivity = (values) => {
            showConfirmDialog({title: '温馨提示',message: '活动上线后将无法修改'}).then(() => {
                NewActivityInfo.value.startTime = new Date(NewActivityInfo.value.startTime)
                NewActivityInfo.value.endTime = new Date(NewActivityInfo.value.endTime)
                axios.post('http://localhost:8081/gym/addActivityInfo', NewActivityInfo.value)
                    .then(response => {
                        if (response.data.code === 20011) {
                            Activities.value = response.data.data;
                            showSuccessToast('新活动成功上线')
                            console.log('add new activity success')
                            // 复原为空
                            NewActivityInfo.value = {
                                ...NewActivityInfo,name: '',information: '', pic: null,startTime: null,
                                endTime: null,venueId: null,pic: null}
                            // 关闭窗口
                            showAddNewActivityWindow.value = false;
                        } else {
                            showFailToast('系统出错了')
                            console.log('add new activity fail')
                        }
                    })
                console.log("NewActivityInfo", NewActivityInfo)
            }).catch(() => {console.log('no')})
        }

        return {
            onClickLeft, goToPage, Activities, exist_data,
            formatDate, deleteActivity, showAddNewActivityWindow,
            NewActivityInfo, activityFormRule, showActivitystartTimePicker,
            showActivityendTimePicker, addNewActivity,
            NewActivityStartTimePicker, NewActivityEndTimePicker,
            TimePickerLimit, todayDate, ActivityVenue, showVenuePicker,
            VenuePicked, VenueColumns, addNewActivityWindow, VenuesData
        }
    }
}
</script>

<style scoped lang="less">
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
    // background-color: pink;
    margin: auto 25px;
    margin-top: 10px;
    height: 91vh;
    overflow-y: auto;


    .item {
        background: linear-gradient(to right, #DBAFCE, #97FFBA);
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

.addActivityTitle {
    font-size: 18px;
    text-align: center;
    margin-top: 8px;
}
</style>