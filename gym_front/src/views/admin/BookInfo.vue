<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="预约列表" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        <van-tabs v-model:active="active" @click-tab="onClickTab" animated>
            <van-tab title="待处理" name="bookingAllInfo"></van-tab>
            <van-tab title="已处理" name="bookedAllInfo"></van-tab>
            <van-tab title="过期预约" name="unbookedAllInfo"></van-tab>
        </van-tabs>
        <router-view></router-view>
    </div>

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
            router.push('/admindetail');
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        const active = ref(0);
        const onClickTab = ({ name }) => {
            router.push('/' + name)
        }
        onBeforeMount(() => {
            router.push('/bookingAllInfo')
        })
        return { onClickLeft, goToPage, active, onClickTab }
    }
}
</script>

<style scoped lang="less">
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}
</style>