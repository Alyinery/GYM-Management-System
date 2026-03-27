<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="场地详情" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>

        <div class="modify information">
            <van-cell-group inset>
                <van-form @submit="saveChanges">
                    <van-field name="uploader" label="场地图片">
                        <template #input>
                            <van-uploader v-model="picValue" />
                        </template>
                    </van-field>
                    <van-field v-model="venue.name" name="场地名称" label="场馆名称" input-align="right"
                        error-message-align="right" :placeholder="venue.name ? venue.name : '请输入场馆名'"
                        :rules="venueFormRule.name" />

                    <van-field v-model="venue.price" label="收费(元/小时)" input-align="right"
                        :placeholder="venue.price ? venue.price : '请输入价格'" error-message-align="right"
                        :rules="venueFormRule.price" />


                    <van-field name="switch" label="开放/封闭" input-align="right">
                        <template #input>
                            <van-switch v-model="checked" @click="lookchecked" />
                        </template>
                    </van-field>


                    <van-field v-model="venue.information" rows="2" autosize label="场地简介" type="textarea" maxlength="50"
                        placeholder="请输入简介" show-word-limit />

                    <!-- 按钮 -->
                    <div style="margin: 16px;">
                        <van-button round block type="primary" native-type="submit">
                            确认修改
                        </van-button>
                    </div>
                </van-form>


            </van-cell-group>
        </div>



    </div>
</template>

<script>
import { useRouter, useRoute } from 'vue-router';
import { createApp, ref, onMounted, onBeforeMount, nextTick, reactive } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';


export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const onClickLeft = () => {
            router.go(-1);
        }
        const venue = ref({});
        const text = "周一、周二、周三、周四、周五 18:00-21:00;周六、周日 08:00-21:00;";
        const checked = ref(true)
        // const formatDate  = (date) => `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;


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
                        console.log('pic', venue.value.pic)
                        if (venue.value.state === 1) {
                            checked.value = true;
                        } else {
                            checked.value = false;
                        }
                        picValue.value[0].url = venue.value.pic;
                    } else {
                        console.log("error")
                    }
                })
        });

        //校验

        const pricepattern = /^(?!0$)(?!0\d)[0-9]{1,3}$/;
        const venueFormRule = ref({
            name: [{ required: true, message: "请输入场馆名称" }],
            price: [{ validator: (value) => pricepattern.test(value), message: "价格限制0~999" }]
        })

        const picValue = ref([
            { url: '' }
        ]);

        // 开关
        const lookchecked = () => {
            if (checked.value) {
                venue.value.state = 1;
                showToast("已开放")
            } else {
                venue.value.state = 0;
                showToast("已封闭")
            }
        }

        const saveChanges = (values) => {
            console.log(venue.value)
            axios.post("http://localhost:8081/gym/modifyVenueById", venue.value)
                .then(response => {
                    if (response.data.code === 20031) {
                        console.log("modify success")
                    } else {
                        console.log("modify failure")
                    }
                }).catch(error => {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    showFailToast("系统出错了")
                })
        }

        return {
            onClickLeft, text, venue, picValue, venueFormRule,
            saveChanges, lookchecked, checked, pricepattern

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
</style>