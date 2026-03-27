<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="运动记录" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
        
    </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
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

<style scoped lang="less">
.world {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    height: 100vh;
    background-color: #f7f7f7;
}
</style>