<template>
  <div class="container">
      <div class="row" v-for="(data, index) in posts" :key="index">
        <!-- Blog Entries Column -->
        <div class="col-md-12">
          <!-- Blog Post -->
          <div class="card mb-4">
            <!-- <img class="card-img-top" src="http://placehold.it/750x300" alt="Card image cap"> -->
            <div class="card-body">
              <h2 class="card-title">{{ data.post.title }}</h2>
              <p class="card-text">{{ data.post.content }}</p>
              <a href="#" class="btn btn-primary">Read More &rarr;</a>
            </div>
            <div class="card-footer text-muted">
              Posted on {{ data.post.createdAt }} by {{ data.account.username }}
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      posts: []
    }
  },
  beforeRouteEnter (to, from, next) {
    axios.get('/posts')
      .then(res => {
        next(vm => {
          console.log(res.data)
          vm.posts = res.data
        })
      })
      .catch(err => {
        console.error(err)
      })
  }
}
</script>

<style>
</style>
