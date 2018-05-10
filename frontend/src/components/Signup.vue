<template>
  <div class="col-4 offset-4">
    <div class="card">
      <div class="card-header">
        <h1>OurBlog</h1>
      </div>
      <div class="card-body">
        <form>
          <div class="form-group">
            <label>Username</label>
            <input type="text" class="form-control" placeholder="Enter username" v-model="credentials.username">
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" placeholder="Enter email" v-model="credentials.email">
          </div>
          <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" placeholder="Enter password" v-model="credentials.password">
          </div>
        </form>
      </div>
      <div class="card-footer">
        <router-link to="login" class="btn btn-primary">Login</router-link>
        <button class="btn btn-primary float-right" @click.prevent="onSignUp">Create account</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      credentials: {
        'username': '',
        'password': '',
        'email': ''
      }
    }
  },
  methods: {
    onSignUp () {
      let app = this
      this.$auth.register({
        data: {
          username: app.credentials.username,
          password: app.credentials.password,
          email: app.credentials.email
        },
        success: (res) => {
          console.log(res)
        },
        error: (err) => {
          console.error(err.response)
        }
      })
    }
  }
}
</script>

<style scoped>
.card-header {
  text-align: center;
}
</style>
