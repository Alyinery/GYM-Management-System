<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="租借详情" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
    </div>


</template>

<script>
import { useRouter } from 'vue-router';
import axios from 'axios';
import { onMounted, ref, nextTick } from 'vue';

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
        return {onClickLeft,goToPage}
    }
}
</script>

<style scoped lang="less"></style>