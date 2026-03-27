<template>
    <div class="world">
        <div class="head">
            <van-nav-bar title="我的信息" left-text="返回" left-arrow @click-left="onClickLeft" />
        </div>
    </div>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { onMounted } from 'vue';
import axios from 'axios';
import { showFailToast, showSuccessToast, showToast, showConfirmDialog } from 'vant';
import { onBeforeMount } from 'vue';
export default {
    setup() {
        const router = useRouter();
        const route = useRoute();
        const UserData = ref(null);
        const onClickLeft = () => {
            router.push('/home');
        };
        const goToPage = (path) => {
            console.log(path);
            router.push(path);
        };
        onBeforeMount(() => {
            const UserId = localStorage.getItem('UserId');
            console.log(UserId)
            axios.get('http://localhost:8081/gym/getUserAttributeById/' + UserId)
                .then(response => {
                    if (response.data.code === 20041) {
                        UserData.value = response.data.data;
                        console.log("UserDDDData", UserData.value);
                        console.log(response.data);
                    } else {
                        console.log("error")
                    }
                }).catch(error => {
                    // 处理请求错误
                    console.error('Error while sending the request:', error);
                    showFailToast("系统出错了")
                });
        }
        )

        
        return { onClickLeft, goToPage, UserData };
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