//创建一个路由器，并暴露出去

//第一步：引入createRouter
import { createRouter, createWebHashHistory } from 'vue-router'
//引入一个个可能要呈现的组件
import Home from '@/views/home.vue'
import Login from '@/views/user/Login.vue'
import AppVue from '@/App.vue'
import Book from '@/views/Book.vue'
import Equip from '@/views/Equip.vue'
import Information from '@/views/Equip.vue'
import RegisterVue from '@/views/user/Register.vue'
import AdminLogin from '../views/admin/AdminLogin.vue'
import { all } from 'axios'
import EquipDetail from '@/views/EquipDetail.vue'
//第二步：创建路由器
const router = createRouter({
    history: createWebHashHistory(), //路由器的工作模式
    routes: [//一个一个的路由规则
        { path: '/', component: Home },
        { path: '/home', component: Home },
        { path: '/login', component: Login },
        { path: '/book', component: () => import('../views/Book.vue') },
        { path: '/equip', component: () => import('../views/Equip.vue') },
        { path: '/information', name: 'information', component: () => import('../views/user/Information.vue') },
        { path: '/activities', component: () => import('../views/Activities.vue') },
        { path: '/admin', component: () => import('../views/admin/AdminLogin.vue') },
        { path: '/register', component: RegisterVue },
        { path: '/adminregister', component: () => import('../views/admin/AdminRegister.vue') },
        { path: '/venuedetail/:id', component: () => import('../views/VenueDetail.vue') },
        { path: '/admindetail', component: () => import('../views/admin/AdminDetail.vue') },
        { path: '/adminlogin', component: AdminLogin },
        { path: '/equipdetail/:id', component: () => import('../views/EquipDetail.vue') },
        { path: '/mysportlog', component: () => import('../views/user/mysportlog.vue') },
        { path: '/equipInfo', component: () => import('../views/admin/EquipInfo.vue') },
        { path: '/activitiesInfo', component: () => import('../views/admin/ActivitiesInfo.vue') },
        { path: '/venueInfo', component: () => import('../views/admin/VenueInfo.vue') },
        { path: '/userSetting', component: () => import('../views/user/UserSetting.vue') },
        { path: '/adminVenueDetail/:id', component: () => import('../views/admin/AdminVenuedetail.vue') },
        { path: '/activityRegisterAllInfo', component: () => import('../views/admin/ActivityRegisterAllInfo.vue') },
        { path: '/activityRegisterInfo', component: () => import('../views/user/ActivityRegisterInfo.vue')},
        {
            path: '/userbookInfo', component: () => import('../views/user/Bookdetail/UserBookInfo.vue'),
            children: [
                { path: '/bookedInfo', component: () => import('../views/user/Bookdetail/BookedInfo.vue') },
                { path: '/bookingInfo', component: () => import('../views/user/Bookdetail/BookingInfo.vue') },
                { path: '/unbookedInfo', component: () => import('../views/user/Bookdetail/UnBookedInfo.vue') },
            ]
        },
        {
            path: '/userequipbookInfo', component: () => import('../views/user/Equipdetail/UserEquipBookInfo.vue'),
            children: [
                { path: '/equipbookingInfo', component: () => import('../views/user/Equipdetail/EquipBookingInfo.vue') },
                { path: '/equipbookedInfo', component: () => import('../views/user/Equipdetail/EquipBookedInfo.vue') }
            ]
        },
        {
            path: '/adminbookInfo', component: () => import('../views/admin/BookInfo.vue'),
            children: [
                { path: '/bookingAllInfo', component: () => import('../views/admin/BookingAllInfo.vue') },
                { path: '/bookedAllInfo', component: () => import('../views/admin/BookedAllInfo.vue') },
                { path: '/unbookedAllInfo', component: () => import('../views/admin/UnBookedAllInfo.vue') }
            ]
        },

    ]
})

//拦截
router.beforeEach((to, from, next) => {
    //验证token,只有存在token的时候,才能跳转到内容页
    console.log('要去' + to.path + '页面');
    let token = localStorage.getItem("token");
    let Admintoken = localStorage.getItem("Admintoken");
    const allowedPaths = ["/", "/register", "/home", "/adminregister", "/book", "/login", "/equip", "/activities", "/Information", "/adminlogin", "/venuedetail"]; // 包含"/login", "/home"和"/about"这几个路径
    const adminallowedPaths = ["/adminbookInfo", "/activitiesInfo", "/venueInfo", "/equipInfo", "/bookingAllInfo", "/bookedAllInfo", "/unbookedAllInfo", "/adminVenueDetail","/activityRegisterAllInfo"];
    if (to.path === '/admindetail') {
        if (Admintoken) {
            console.log('有 Admintoken')
            next();
        } else {
            if (from.path !== '/adminlogin') {
                localStorage.setItem('redirectPath', to.path)
                next('/adminlogin')
            } else {
                next(false);
            }
        }
    } else if (adminallowedPaths.some(path => to.path.startsWith(path)) && Admintoken) {
        console.log('adminallowedPath && Admintoken')
        next();
    } else {
        if (token || allowedPaths.includes(to.path)) {
            if (to.path === '/login' && token) {
                next('/home');
            } else {
                next();
            }
        } else {
            if (from.path !== '/login') {
                console.log('from.path !== /login')
                localStorage.setItem('redirectPath', to.path)
                next('/login')
            } else {
                next(false);
            }
        }
    }
})


//暴露出去router
export default router