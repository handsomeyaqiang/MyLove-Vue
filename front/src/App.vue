<template>
  <div id="app">
    <keep-alive>
      <audio id="audio" :src="require('./assets/Fpc.mp3')" muted loop autoplay ref="MusicPlay"></audio>
    </keep-alive>
    <keep-alive>
      <img :src="src" alt="" width="45px" height="45px" @click="open" class="img" :class="{start: mymove, pause: !mymove}">
    </keep-alive>
    <transition :name="transitionName">
        <router-view/>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      transitionName: '',
      checked: true, // 默认开始音乐播放
      src: require('@/assets/music.png'), // 注意图片资源的加载方式
      mymove: true // 控制音乐控制按钮样式
    }
  },
  mounted () {
    // 播放音乐
    document.addEventListener('touchstart', this.audioAutoPlay, false)
    document.addEventListener('WeixinJSBridgeReady', this.audioAutoPlay, false)
    document.addEventListener('click', this.audioAutoPlay, false)
    let oAudio = document.querySelector('#audio')
    oAudio.onended = function () {
    // 播放完毕，重新循环播放
      oAudio.load()
      oAudio.play()
    }
  },
  // 监听,当路由发生变化的时候执行
  watch: {
    $route (to, from) {
      if (to.meta.index > from.meta.index) {
        // 实现路由跳转动画
        this.transitionName = 'slide-left'
      }
      if (to.meta.index < from.meta.index) {
        this.transitionName = 'slide-right'
      }
    }
  },
  methods: {
    open () {
      console.log('open')
      var audio = document.getElementById('audio')
      this.checked = !this.checked
      if (this.checked) {
        this.src = require('@/assets/music.png')
        audio.play()
        this.mymove = true
      } else {
        this.src = require('@/assets/music1.png')
        audio.pause()
        this.mymove = false
      }
    },
    audioAutoPlay () {
      let audio = document.getElementById('audio')
      audio.play()
      document.removeEventListener('touchstart', this.audioAutoPlay)
      document.removeEventListener('WeixinJSBridgeReady', this.audioAutoPlay)
      document.removeEventListener('click', this.audioAutoPlay)
    }
  }
}

</script>

<style lang="scss">
  .img {
    position: fixed !important;
    right:18px;
    top:6px;
    z-index: 1000;
  }
  .start {
    animation: mymove 1.5s infinite linear; // 旋转动画部分
  }
  .pause {
    z-index: 1000;
  }
  // css 桢动画
  @keyframes mymove{
    0%{
      transform:rotate(0deg);
    }
    100%{
      transform:rotate(360deg);
    }
  }
  .slide-right-enter-active,
  .slide-right-leave-active,
  .slide-left-enter-active,
  .slide-left-leave-active {
    will-change: transform;
    transition: all 500ms;
    position:absolute;
  }

  .slide-right-enter {
    opacity: 0;
    transform: translate(-100%);
  }

  .slide-right-leave-active {
    opacity: 0;
    transform: translateX(100%);
  }

  .slide-left-enter {
    opacity: 0;
    transform: translateX(100%);
  }

  .slide-left-leave-active {
    opacity: 0;
    transform: translateX(-100%);
  }
</style>
