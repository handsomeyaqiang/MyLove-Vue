<template>
  <div class="main-container">
    <div class="main-content">
      <!-- 滚动加载 -->
      <div class="scroll-data" v-infinite-scroll="getData" infinite-scroll-disabled="disabled" infinite-scroll-distance="5">
        <Waterfall
          ref="waterfall"
          :list="list"
          :gutter="6"
          :width="400"
          :breakpoints="{
            1200: { //当屏幕宽度小于等于1200
              rowPerView: 3,
            },
            800: { //当屏幕宽度小于等于800
              rowPerView: 1,
            },
            500: { //当屏幕宽度小于等于500
              rowPerView: 1,
            }
          }"
          :animationEffect="effect"
          :animationDuration="`${duration}s`"
          :animationDelay="`${delay}s`"
          :bi="this.bi"
        >
          <template
            slot="item"
            slot-scope="props"
          >
            <div class="card">
              <div
                class="cover"
                :style="initCardStyle(props)"
                @click="clickImg(props.data)"
              >
                <img
                  :src="props.data.src"
                  alt
                  @load="$refs.waterfall.refresh"
                  preview="1"
                  preview-text="大图模式下只能查看加载的图片，更多请返回下拉加载"
                >
              </div>
            </div>
          </template>
        </Waterfall>
      </div>
      <p v-if="loading">加载中...</p>
      <p v-if="noMore">没有更多了</p>
    </div>
  </div>
</template>
<script>
import Waterfall from 'vue-waterfall-plugin'
export default {
  name: 'MyExample',
  components: {
    'Waterfall': Waterfall
  },
  data () {
    return {
      images: [],
      colors: ['#bae3e5'],
      list: [],
      loading: false,
      effect: 'fadeIn',
      duration: 1,
      delay: 0.3,
      isSetInitStyle: true,
      boxWidth: 'auto',
      pageNum: 1,
      pageSize: 7,
      has_not_data: false,
      showImg: false,
      imgSrc: '',
      image_type: '0',
      getDataUrl: '',
      bi: 'http://106.13.43.251:8082/new/performancepictures/background/5.jpg',
      dataUrl: '/pictures/getPicturesByTypeID',
      backUrl: '/pictures/getAllBackPictures'
    }
  },
  computed: {
    noMore () {
      return this.has_not_data
    },
    disabled () {
      return this.loading || this.noMore
    }
  },
  created () {
    this.getBackground()
    if (this.$route.query.image_type !== undefined && this.$route.query.image_type.length !== 0 && this.$route.query.image_type >= 0) {
      this.image_type = this.$route.query.image_type
    }
  },
  methods: {
    getData () {
      this.$axios.get(this.dataUrl, {
        params: {
          pageSize: this.pageSize,
          pageNum: this.pageNum,
          image_type: this.image_type
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.pageNum = this.pageNum + 1
          console.log(this.pageNum)
          if (res.data.data.length === 0) {
            this.has_not_data = true
          }
          this.images = res.data.data.map((item, index) => {
            return {
              src: item.image_url,
              width: item.width,
              height: item.height,
              blankColor: this.colors[index % this.colors.length]
            }
          })
          this.$nextTick(function () {
            this.list = this.list.concat(this.images)
            console.log(this.list)
            this.$previewRefresh()
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
    },
    // 初始化卡片样式
    initCardStyle (props) {
      if (this.isSetInitStyle) {
        return {
          width: `${props.data.itemWidth - 12}px`,
          height: `${((props.data.itemWidth - 12) / props.data.width) *
            props.data.height}px`,
          backgroundColor: props.data.blankColor
        }
      }
    },
    /**
     * 图片点击
     */
    clickImg (item) {
      // this.$message.info(JSON.stringify(item))
      this.showImg = true
      this.imgSrc = item.src
    },
    /**
     * 编辑
     */
    viewImg () {
      this.showImg = false
    }
  }
}
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}
.scroll-data{
  height: 100%;
  overflow-y: auto;
}
.main-container {
  display: flex;
  .main-content {
    flex: 1;
    background: #ffffff;
    height: 100vh;
    overflow: auto;
    p {
      text-align: center;
    }
  }
}
.card {
  background: #fff;
  border-radius: 5px;
  overflow: hidden;
  cursor: pointer;
  position: relative;
  transition: 0.2s;
  top: 0;
  .cover {
    margin: 6px 6px 6px 6px;
    img {
      display: block;
      width: 100%;
    }
  }
  &:hover {
    top: -3px;
  }
  .name {
    background: #fff;
    color: #666;
    font-weight: 600;
    padding: 10px 20px;
    font-size: 14px;
  }
}
</style>
