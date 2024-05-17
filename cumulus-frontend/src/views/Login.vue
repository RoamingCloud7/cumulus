<template>
  <div>
    <el-container>
      <el-header>
        Login
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="Username" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="homepage">Home</el-button>
            <el-button type="primary" @click="submitForm('ruleForm')">Login</el-button>
            <el-button @click="resetForm('ruleForm')">Reset</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: "Login",
  data () {
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: 'Please enter the username', trigger: 'blur' },
          { min: 3, max: 15, message: 'Keep the length between 3 and 15 characters', trigger: 'blur' }

        ],
        password: [
          { required: true, message: 'Please enter the password', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;//获取整个vue的this
          console.log("Verification Success")
          //  alert('submit!');
          _this.$axios.post("/login", _this.ruleForm).then(res => {

            const jwt = res.headers['authorization'];
            const userInfo = res.data.data
            //存储(共享)全局变量jwt和userInfo
            _this.$store.commit("SET_TOKEN", jwt);
            _this.$store.commit("SET_USERINFO", userInfo);

            //获取token和getUser
            // console.log(_this.$store.getters.getToken)
            // console.log(_this.$store.getters.getUser)
            //跳转blogs页面
            _this.$router.push("/blogs")
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm (formName) {
      this.$refs[formName].resetFields();
    },
    homepage () {
      this.$router.push("/blogs")
    }
  }
}
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: white;
  color: black;
  margin: 20px 0px;
  font-size: 40px;
  text-align: center;
  line-height: 70px;
}

.el-main {
  /* background-color: #e9eef3; */
  color: #333;
  text-align: center;
  line-height: 160px;
}
.mlogo {
  height: 80%;
}

.demo-ruleForm {
  max-width: 500px;
  margin: 0 auto;
  font-size: 30px;
}
</style>
