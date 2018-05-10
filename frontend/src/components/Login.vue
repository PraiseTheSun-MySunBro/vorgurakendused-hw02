<template>
  <div class="col-4 offset-4">
    <div class="card">
      <div class="card-header">
        <h1>OurBlog</h1>
      </div>
      <div class="card-body">
        <b-form>
          <div class="form-group">
            <label>Username</label>
            <input type="text" class="form-control" placeholder="Enter username" v-model="credentials.username">
          </div>
          <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" placeholder="Enter password" v-model="credentials.password">
          </div>
        </b-form>
        <small>Don't have an account?</small>
        <router-link to="signup" class="btn btn-primary mt-2">Sign up</router-link>
      </div>
      <div class="card-footer">
        <button class="btn btn-primary float-right" type="submit" @click.prevent="onLogin">Log in</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      credentials: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    onLogin () {
      let app = this
      this.$auth.login({
        data: {
          username: app.credentials.username,
          password: app.credentials.password
        },
        success: (res) => {
          console.log(res)
        },
        error: (err) => {
          console.error(err.response)
        },
        rememberMe: true
      })
    }
  }
}
</script>

<style scoped>
.card {
  width: 100%;
}

.card-header {
  text-align: center;
}

small {
  display: block;
}
</style>
