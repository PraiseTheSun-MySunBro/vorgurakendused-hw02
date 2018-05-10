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
                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="Enter password">
                    </div>
                </form>
                <small>Don't have an account?</small>
                <router-link to="signup" class="btn btn-primary mt-2">Sign up</router-link>
            </div>
            <div class="card-footer">
                <button class="btn btn-primary float-right">Log in</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        
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