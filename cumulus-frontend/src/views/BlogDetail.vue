<template>
  <div>
    <Header></Header>
    <div class="blog">
      <h2>{{blog.title}}</h2>
      <el-link icon="el-icon-edit" v-if="ownblog" class="linklist">
        <router-link :to="{name:'BlogEdit',params:{blogid:blog.id}}" class="edit-btn">
          Edit
        </router-link>
      </el-link>
      <el-link icon="el-icon-delete" v-if="ownblog" class="linklist">
<!--        <el-button type="danger" round @click="delblog">删除</el-button>-->
        <a @click="delblog" class="del-btn">
          Delete
        </a>
      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import 'github-markdown-css'
import Header from "../components/Header";
export default {
  name: "BlogDetail",
  components: { Header },
  data () {
    return {
      blog: {
        id: '',
        title: '',
        content: '',
        description: ''

      },
      ownblog: false
    }
  },
  methods: {
    delblog () {
      const blogId = this.$route.params.blogid
      const _this = this
      if (blogId) {
        this.$confirm('This action will permanently delete the article, do you want to continue?', 'Info', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          _this.$axios.post(`/blogdel/${blogId}`, null, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(res => {
            this.$message({
              type: 'success',
              message: res.data.data
            });
            _this.$router.push("/blogs")
          })

        }).catch(() => {

          this.$message({
            type: 'info',
            message: 'The deletion has been canceled'
          });
        });


      }
    }
  },
  created () {
    // 获取动态路由的 blogid
    const blogId = this.$route.params.blogid
    const _this = this
    if (blogId) {
      this.$axios.get("/blog/" + blogId).then(res => {
        const blog = res.data.data
        _this.blog.id = blog.id
        _this.blog.title = blog.title
        _this.blog.description = blog.description

        // MardownIt 渲染
        var MardownIt = require("markdown-it")
        var md = new MardownIt();
        var result = md.render(blog.content)
        _this.blog.content = result

        // 查看是否是登录人 是则可以编辑和删除
        _this.ownblog = (blog.userId === _this.$store.getters.getUser.id)
      })
    }

  }
}
</script>

<style scoped>
.blog {
  margin-top: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  padding: 10px;
}
.edit-btn {
  margin: 5px;
  text-decoration: none;
  font-size: 17px;
  color: #3182ce;
  transition: background-color 0.3s ease;
}
.del-btn {
  margin: 5px;
  text-decoration: none;
  font-size: 17px;
  color: #f77476;
  transition: background-color 0.3s ease;
}
</style>
