<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html lang="en">
<tags:header title="Edit Institution">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
</tags:header>

<tags:citizen citizen="${citizen}">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Edit Institution</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/institutions">Institutions</a>
                </li>
                <li>
                    <a href="/institutions">${institutionToEdit.institutionName}</a>
                </li>
                <li class="active">
                    <strong>Edit</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"> </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight">


        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Institution Information</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form:form method="POST" class="form-horizontal" modelAttribute="institutionToEdit">

                            <div class="form-group"><label class="col-sm-2 control-label">Profile Pic:</label>
                                <div class="col-sm-10">
                                    <form:input path="profilePic" type="text" class="form-control"/>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Name:</label>
                                <div class="col-sm-10">
                                    <form:input path="institutionName" type="text" class="form-control"/>
                                </div>
                            </div>

                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Address:</label>
                                <div class="col-sm-10">
                                    <form:input path="institutionAddress" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Admin Citizen ID:</label>
                                <div class="col-sm-10">
                                    <form:input path="adminCitizenId" type="text" class="form-control"/>
                                </div>
                            </div>
                            <form:hidden path="superuserCitizenId"/>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">Save Changes</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</tags:citizen>
<script src="/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
</html>