<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html lang="en">
<tags:header title="Book Appointment">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
</tags:header>

<body>
<tags:citizen citizen="${citizen}">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Book Appointment</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/appointments">Appointments</a>
                </li>
                <li class="active">
                    <strong>Book Appointment</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Appointment Information</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form:form method="post" class="form-horizontal" modelAttribute="newAppointment">

                            <div class="form-group"><label class="col-sm-2 control-label">Patient's Citizen ID:</label>
                                <div class="col-sm-10">
                                    <form:input path="patientCitizenId" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Doctor's Citizen ID:</label>
                                <div class="col-sm-10">
                                    <form:input path="doctorCitizenId" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">End Date:</label>
                                <div class="col-sm-10">
                                    <form:input path="endDate" type="date" class="form-control"/>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">Book Appointment</button>
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