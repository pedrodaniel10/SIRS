<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Add Citizen">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
</tags:header>

<body>
<tags:citizen citizen="${citizen}">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Add Citizen</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/citizens">Citizens</a>
                </li>
                <li class="active">
                    <strong>Add Citizen</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2">

        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Personal Information</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div style="color: red">
                            <b><p>${error}</p></b>
                        </div>
                        <form:form method="POST" class="form-horizontal" modelAttribute="newCitizen">
                            <div class="form-group">
                                <label path="roles" class="col-sm-2 control-label">Roles:</label>&nbsp;&nbsp;
                                <div class="checkbox-inline i-checks">
                                    <label> <form:checkbox disabled="true" path="roles" value="PATIENT"/> <i></i> Patient </label></div>
                                <div class="checkbox-inline i-checks">
                                    <label> <form:checkbox path="roles" value="DOCTOR"/> <i></i> Doctor </label></div>
                                <div class="checkbox-inline i-checks">
                                    <label> <form:checkbox path="roles"  value="ADMIN"/> <i></i> Administrator </label></div>
                                <div class="checkbox-inline i-checks">
                                    <label> <form:checkbox path="roles" value="SUPERUSER"/> <i></i> Super User </label></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Profile Pic:</label>
                                <div class="col-sm-10">
                                    <form:input path="profilePic" type="text" class="form-control"/></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Citizen ID:</label>
                                <div class="col-sm-10">
                                    <form:input path="citizenId" type="text" class="form-control"/></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Name:</label>
                                <div class="col-sm-10">
                                    <form:input path="citizenName" type="text" class="form-control"/></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Date of Birth:</label>
                                <div class="col-sm-10">
                                    <form:input path="dateOfBirth" type="date" class="form-control"/></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Gender:</label>
                                <div class="col-sm-10">
                                    <form:select  path="gender" class="form-control m-b">
                                        <form:option value="MALE">Male</form:option>
                                        <form:option value="FEMALE">Female</form:option>
                                    </form:select>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Email:</label>
                                <div class="col-sm-10">
                                    <form:input path="email" type="text" class="form-control"/></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Password:</label>
                                <div class="col-sm-10"><form:input path="password" type="password" class="form-control"/></div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">Confirm Password:</label>
                                <div class="col-sm-10"><input type="password" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <form:hidden path="superuserCitizenId"/>

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">Add Citizen</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</tags:citizen>
<!-- iCheck -->
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
</body>
</html>