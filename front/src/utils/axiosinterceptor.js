import axios from 'axios'
// 所有正在请求的资源列表
let reqList = []
/**
 * 阻止重复请求
 * @param {array} reqList - 请求缓存列表
 * @param {string} url - 当前请求地址
 * @param {function} cancel - 请求中断函数
 * @param {string} errorMessage - 请求中断时需要显示的错误信息
 */
const stopRepeatRequest = function (reqList, url, cancel, errorMessage) {
  const errorMsg = errorMessage || ''
  for (let i = 0; i < reqList.length; i++) {
    if (reqList[i] === url) {
      cancel(errorMsg)
      return
    }
  }
  reqList.push(url)
}
/**
 * 允许某个请求可以继续进行
 * @param {array} reqList 全部请求列表
 * @param {string} url 请求地址
 */
const allowRequest = function (reqList, url) {
  for (let i = 0; i < reqList.length; i++) {
    if (reqList[i] === url) {
      reqList.splice(i, 1)
      break
    }
  }
}
const axiosInstance = axios.create()
// 请求拦截器
axiosInstance.interceptors.request.use(
  config => {
    console.log('发送请求')
    let cancel
    // 设置cancelToken对象
    config.cancelToken = new axios.CancelToken(function (c) {
      cancel = c
    })
    // 阻止重复请求。当上个请求未完成时，相同的请求不会进行
    stopRepeatRequest(reqList, config.url, cancel, `${config.url} 请求被中断`)
    return config
  }
)
// 响应拦截器
axiosInstance.interceptors.response.use(
  response => {
    console.log('响应请求')
    // 增加延迟，相同请求不得在短时间内重复发送
    setTimeout(() => {
      allowRequest(reqList, response.config.url)
    }, 200)
    // ...请求成功后的后续操作
    // successHandler(response)
    return response
  },
  error => {
    if (axios.isCancel(error)) {
      console.log(error.message)
    } else {
      // 增加延迟，相同请求不得在短时间内重复发送
      setTimeout(() => {
        console.log(error.message)
        allowRequest(reqList, error.config.url)
      }, 1000)
    }
    // ...请求失败后的后续操作
    // errorHandler(error)
    return Promise.reject(error.message)
  }
)
export default axiosInstance
