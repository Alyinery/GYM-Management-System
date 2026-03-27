import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import { resolve } from "path";
const pathResolve = (dir) => resolve(_dirname, ".", dir);

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],

  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // 手机中调试
  // server: {
  //   host: "192.168.1.8",
  //   port: "8080"
  // }
  server: {
    proxy: {
      '/api': {
        target: "http://localhost:8081/", //跨域地址
        changeOrigin: true, //支持跨域
        rewrite: (path) => path.replace(/^\/api/, "")//重写路径,替换/api
      }
    }
  }
})
