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
                        <input type="text" class="form-control" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="Enter password">
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <router-link to="login" class="btn btn-primary">Login</router-link>
                <button class="btn btn-primary float-right">Create account</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
    .card-header {
        text-align: center;
    }
</style>