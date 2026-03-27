<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="租借记录" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>

        <van-tabs v-model:active="active" @click-tab="onClickTab" animated>
            <van-tab title="待归还" name="equipbookingInfo"></van-tab>
            <van-tab title="已归还" name="equipbookedInfo"></van-tab>
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
            router.push('/information');
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        const onClickTab = ({ name }) => {
            router.push('/' + name)
        }
        //根据venue.id获取球馆名称
        onBeforeMount(() => {
            router.push('/equipbookingInfo')
        })


        return { onClickLeft, goToPage, onClickTab, }
    }
}
</script>

<style scoped lang="less">
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}

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