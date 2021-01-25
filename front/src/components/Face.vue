<template>
  <div class="all" :style="{backgroundImage:'url('+ this.bi +')'}">
    <div class="introdution-title" >
        <p>我们结婚了</p>
    </div>
  <div class="swiper-container" >
    <div class="swiper-wrapper">
      <div>
        <div v-intro="'图片可以左右滑动哦~'" v-intro-position="'top'" v-intro-step="1" style="z-index: 20"></div>
        <div v-intro="'点击图片可以查看更多哦~'" v-intro-position="'top'" v-intro-step="2" style="z-index: 20"></div>
      </div>
      <div class="swiper-slide" v-for="(item, index) in this.ss" :key="index">
        <img :data-src="item.src" class="swiper-lazy" @click="toMenu">
        <!-- Preloader image -->
        <div class="swiper-lazy-preloader swiper-lazy-preloader-black"></div>
      </div>
    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination swiper-pagination-white"></div>
    <!-- Navigation -->
    <div class="swiper-button-next swiper-button-white"></div>
    <div class="swiper-button-prev swiper-button-white"></div>
  </div>
  </div>
</template>
<script>
import Swiper from 'swiper'
import 'swiper/dist/css/swiper.min.css'

export default {
  name: 'face',
  data () {
    return {
      ss: [],
      pageSize: 25,
      pageNum: 1,
      imageType: 13,
      getDataUrl: '/pictures/getPicturesByTypeID',
      backUrl: '/pictures/getAllBackPictures',
      bi: 'http://106.13.43.251:8082/new/performancepictures/background/5.jpg',
      modalStep: 1
    }
  },
  mounted () {
    this.getData()
    /* eslint-disable no-new */
    new Swiper('.swiper-container', {
      autoplay: 5000,
      speed: 860,
      nextButton: '.swiper-button-next',
      prevButton: '.swiper-button-prev',
      // 如果需要分页器
      pagination: '.swiper-pagination',
      paginationClickable: true,
      observer: true, // 修改swiper自己或子元素时，自动初始化swiper
      observeParents: true, // 修改swiper的父元素时，自动初始化swiper
      // Disable preloading of all images
      preloadImages: false,
      // Enable lazy loading
      lazyLoading: true,
      lazyLoadingInPrevNext: true,
      // loop: true,
      centeredSlides: true,
      effect: 'coverflow',
      overflow: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows: true
      }
    })
  },
  created () {
    this.getBackground()
    this.$nextTick(() => {
      var introJS = require('intro.js')
      introJS().setOptions({
        prevLabel: '上一步',
        nextLabel: '下一步',
        skipLabel: '跳过',
        doneLabel: '完成',
        /* 是否允许点击空白处退出 */
        exitOnOverlayClick: true,
        /* 默认提示位置 */
        hintPosition: 'top-middle',
        /* 是否滑动到高亮的区域 */
        scrollToElement: true,
        /* 遮罩层的透明度 */
        overlayOpacity: 0.8
      }).oncomplete(function () {
        // 点击跳过按钮后执行的事件
      }).onexit(function () {
        // 点击结束按钮后， 执行的事件
      }).start()
    })
  },
  methods: {
    toMenu () {
      this.$router.push({
        name: 'menu',
        query: {
        }
      })
    },
    getData () {
      this.$axios.get(this.getDataUrl, {
        params: {
          pageSize: this.pageSize,
          pageNum: this.pageNum,
          image_type: this.imageType
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.pageNum = this.pageNum + 1
          console.log(this.pageNum)
          if (res.data.data.length === 0) {
          }
          this.ss = res.data.data.map((item, index) => {
            return {
              src: item.image_url
            }
          })
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    getBackground () {
      var randNum = Math.round(Math.random() * 13)
      console.log(randNum)
      this.$axios.get(this.backUrl, {
        params: {
          pageSize: 14,
          pageNum: 1,
          image_type: 14
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.bi = res.data.data[randNum].image_url
        }
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>
<style scoped>
  .all {
    margin: 0 auto;
    background-color: #ffffff;
    border-top:0px solid #e5dc40;
    border-bottom:0px solid #9cf344;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: 50% 50%;
    align-items: center;
    align-content: center;
    position: relative;
    padding: 0;
  }
  .swiper-container {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    align-items: center;
    align-content: center;
  }

  .swiper-wrapper {
    height: 100%;
    align-items: center;
    align-content: center;
  }

  .swiper-slide img {
    width: 100%;
    height: 100%;
    align-items: center;
    align-content: center;
  }

  .swiper-slide {
    text-align: center;
    align-items: center;
    align-content: center;
    /* Center slide text vertically */
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    width: 100%;
    height: 100%;
  }
  .introdution-title{
    width: 92% ;
    margin: 0 auto;
    text-align: center;
    font-size: 25px;
    z-index: 12;
    height: 66px;
    color: #ff746f;
    line-height:46px;
    letter-spacing: 1px;
    padding-left: 30px;
    /*border-bottom: 1px solid #E1E1E1;*/
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    align-content: center;
  }
</style>
