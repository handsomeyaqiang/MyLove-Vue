<template>
  <div class="nav" :style="{backgroundImage:'url('+ this.bi +')'}">
    <div class="introdution-title" >
      <p>走进幸福时刻</p>
    </div>
    <div class="nav-item">
<!--      <keep-alive>-->
      <ul >
        <li :key="index" v-for='(item,index) in this.images' @click="toForwardMenu(item.type_id)">
          <img :src="item.att.src" alt="">
          <span>{{item.type_name}}</span>
        </li>
      </ul>
<!--      </keep-alive>-->
    </div>
  </div>
</template>

<script>
export default {
  name: 'PicturesMenu',
  data () {
    return {
      images: [],
      lists: [],
      dataUrl: '/pictures/getDistinctPicturesByType',
      backUrl: '/pictures/getAllBackPictures',
      bi: 'http://106.13.43.251:8082/new/performancepictures/background/5.jpg'
    }
  },
  created () {
    this.getBackground()
    this.getData()
  },
  methods: {
    toForwardMenu (id) {
      this.$router.push({
        name: 'myexample',
        query: {
          image_type: id
        }
      })
    },
    getData () {
      this.$axios.get(this.dataUrl).then(res => {
        if (res.data.code === 0) {
          this.images = res.data.data.map((item, index) => {
            return {
              att: {
                src: item.image_url
              },
              type_id: item.typeId,
              type_name: item.typeName
            }
          })
          console.log(this.images)
        }
      }).catch((error) => {
        console.log(error)
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

<style lang="scss">
* {
  margin: 0;
  padding: 0;
}
.nav{
  width: 100%;
  height: 100%;
  margin: 0 auto;
  background-color: #ffffff;
  background-position: 50% 50%;
  border-top:0px;
  border-bottom:0px;
  /*background-size: cover;*/
  /*background-repeat: no-repeat;*/
  align-items: center;
  align-content: center;
  .nav-item {
    width: 92%;
    margin: 0 auto;
    padding-top: 10px;
    padding-bottom: 20px;
    align-items: center;
    align-content: center;
    ul{
      width: 100%;
      margin:0 auto;
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      margin-left: 4px;
      li {
        background: #ffffff;
        border-radius: 10px;
        margin-right: 4px;
        margin-bottom: 4px;
        overflow: hidden;
        cursor: pointer;
        position: relative;
        transition: 0.1s;
        box-sizing: border-box;
        width: calc(50% - 6px);
        height: 280px;
        text-align: center;
        align-items: center;
        align-content: center;
        display: block;
        /*padding-right: 50px;*/
        &:hover {
          top: -3px;
        }
        img{
          /*margin: 5px 5px 5px 5px;*/
          display: block;
          position: relative;
          z-index: 11;
          width: 100%;
          height: 100%;
          overflow: hidden;
          align-content: center;
          align-items: center;
          object-fit: cover;
        }
        span{
          position:absolute;
          left:0;
          right:0;
          bottom: 0;
          text-align: center;
          z-index: 12;
          height: 80px;
          line-height: 80px ;
          font-size: 21px;
          font-weight: bold;
          color: #ff123d;
        }

      }
    }
  }
}
.introdution-title{
  width: 92% ;
  margin: 0 auto;
  text-align: center;
  font-size: 24px;
  /*font-weight: bold;*/
  height: 60px;
  color: #ff746f;
  line-height:46px;
  letter-spacing: 1px;
  padding-left: 30px;
  /*border-bottom: 1px solid #E1E1E1;*/
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  img{
    width: 150px;
    height: 100px;
    position: absolute;
  }
}
</style>
