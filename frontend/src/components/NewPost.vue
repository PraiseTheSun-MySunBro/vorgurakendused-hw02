<template>
  <div>
    <div class="container">
      <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-12">
          <!-- Blog Post -->
          <div class="card mb-4">
            <!-- <img class="card-img-top" src="http://placehold.it/750x300" alt="Card image cap"> -->
            <div class="card-header">
              <h1>New post</h1>
            </div>
            <div class="card-body">
                <div class="form-group">
                  <label><h1>Title:</h1></label>
                  <input type="text" class="form-control col-4" v-model="postData.title">
                </div>
                <div class="form-group">
                  <label><h4>Content:</h4></label>
                  <textarea class="form-control" rows="3" v-model="postData.content"></textarea>
                </div>
            </div>
            <div class="card-footer">
              <button class="btn btn-primary float-right" @click="addPost">Publish</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      postData: {
        title: '',
        content: ''
      }
    }
  },
  methods: {
    addPost () {
      axios.put('/posts', {
        title: this.postData.title,
        content: this.postData.content
      })
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.error(err.response)
        })
    }
  }
}
</script>

<style scoped>

</style>
