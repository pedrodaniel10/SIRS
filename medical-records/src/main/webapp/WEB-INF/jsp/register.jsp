<!DOCTYPE html>

<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>HealthRec | Register</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">HR+</h1>

        </div>
        <h3>Register to HealthRec</h3>
        <p>Create account.</p>
        <form class="m-t" role="form" style="text-align: left" action="/login">
            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" type="text" class="form-control" placeholder="Name" required="true">
            </div>
            <div class="form-group">
                <label for="ccnum">Citizen Card Number:</label>
                <input id="ccnum" type="text" class="form-control" placeholder="Citizen Card Number" required="true">
            </div>
            <div class="form-group">
                <label for="birthDate">Birth Date:</label>
                <input id="birthDate" type="date" class="form-control" placeholder="Birth Date" required="true">
            </div>
            <div class="form-group">
                <label for="email">Name:</label>
                <input id="email" type="email" class="form-control" placeholder="Email" required="true">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input id="password" type="password" class="form-control" placeholder="Password" required="true">
            </div>
            <div class="form-group">
                <div class="checkbox i-checks"><label> <input type="checkbox" required="true"><i></i> Agree the terms and policy </label></div>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Register</button>

            <p class="text-muted text-center"><small>Already have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="/login">Login</a>
        </form>

    </div>
</div>

<!-- Mainly scripts -->
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
</body>

</html>
