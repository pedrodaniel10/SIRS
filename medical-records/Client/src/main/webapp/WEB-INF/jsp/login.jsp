<%@ taglib tagdir="/src/main/webapp/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Login"/>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">MR+</h1>

        </div>
        <h3>Welcome to MedRec</h3>
        <p>Login in.</p>
        <form class="m-t" role="form" action="/src/main/webapp">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="Username" required="true">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="true">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

            <a href="#"><small>Forgot password?</small></a>
            <p class="text-muted text-center"><small>Do not have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="/register">Create an account</a>
        </form>

    </div>
</div>

<!-- Mainly scripts -->
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>

</html>
