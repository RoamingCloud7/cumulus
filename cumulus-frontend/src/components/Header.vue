<template>
  <header class="header">
    <div class="blog">
      <div class="blog-name">Cumulus</div>
      <div class="blog-description">Roaming in the sky</div>
    </div>
    <div class="user-nav">
      <div class="user-info">
        <div class="user-language">
          <el-avatar :size="30" :src="user.avatar"></el-avatar>
<!--          <span class="language-icon">CN</span>-->
          <div class="user-name">{{user.username}}</div>
        </div>
        <nav class="user-menu">
          <a class="menu-item" @click="homepage">Home</a>
          <a class="menu-item" v-show="hasLogin" @click="write">Write Blog</a>
          <a class="menu-item" v-show="!hasLogin" @click="login">Login</a>
          <a class="menu-item" v-show="hasLogin" @click="logout">Logout</a>
        </nav>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header"
  , data () {
    return {
      user: {
        username: 'Guest',
        avatar: '../assets/logo.png'
      },
      hasLogin: false
    }
  },
  methods: {
    //退出操作
    logout () {
      const _this = this
      //首先调用后端logout接口(因该接口需要认证权限,所以需要传入token)
      //其次调用$store清除用户信息及token
      _this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO")
        _this.$router.push("/login")
      })
    },
    login () { //跳转登录页面进行登录
      this.$router.push("/login")
    },
    write () {
      this.$router.push("/blog/add")
    },
    homepage () {
      this.$router.push("/blogs")
    }
  },
  //页面创建时即会调用,进而获取用户信息
  created () {
    if (this.$store.getters.getUser.username) {//如果username不为空
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
      //判断是登录状态还是非登录显示 退出按钮或者登录按钮
      this.hasLogin = true;
    }
  }
}
</script>

<style>
.m-content {
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}
.maction {
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem 1rem;
}

.blog {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.blog-name {
  font-size: 3rem;
  font-weight: bold;
  color: #3182ce;
}

.blog-description {
  font-size: 1.5rem;
  margin: 0.3rem 0.1rem;
  color: #74b8f7;
}

.user-nav {
  display: flex;
  align-items: center;
}

.user-info {
  display: flex;
  align-items: center;
  margin-right: -0.5rem;
}

.user-language {
  display: flex;
  align-items: center;
  margin-right: 1rem;
}

el-avatar {
  overflow: hidden;
  border-radius: 50%;
  height: 2rem;
  width: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #3182ce;
  color: #3182ce;
}

.user-name {
  font-size: 1rem;
  font-weight: bold;
  margin: 0 1rem;
}

.user-menu {
  display: flex;
  align-items: center;
}

.menu-item {
  font-size: 1rem;
  font-weight: bold;
  color: black;
  text-decoration: none;
  margin-right: 1rem;
}

.menu-item:hover {
  text-decoration: underline;
  color: #3182ce;
  cursor: pointer;
}
</style>
