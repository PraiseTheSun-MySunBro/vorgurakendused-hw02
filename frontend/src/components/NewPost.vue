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
                  <input type="text" class="form-control col-4">
                </div>
                <div class="form-group">
                  <label><h4>Content:</h4></label>
                  <textarea class="form-control" rows="3"></textarea>
                </div>
            </div>
            <div class="card-footer">
              <button class="btn btn-primary float-right">Publish</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {

  }
</script>

<style scoped>

</style>
