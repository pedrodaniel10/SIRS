<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        <form:form method="POST" class="m-t" role="form" modelAttribute="login">
            <div class="form-group">
                <form:input type="text" class="form-control" placeholder="email" required="true" path="email"/>
            </div>
            <div class="form-group">
                <form:input type="password" class="form-control" placeholder="password" required="true" path="password"/>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Login</button>
        </form:form>

    </div>
</div>

<!-- Mainly scripts -->
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>

</html>
