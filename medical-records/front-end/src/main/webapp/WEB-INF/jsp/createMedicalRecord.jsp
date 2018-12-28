<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Edit Medical Record"/>

<body>
<tags:citizen citizen="${citizen}">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-9">
            <h2>Medical Record</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/patients">Patients</a>
                </li>
                <li>
                    <a href="/citizens/<c:out value="${newRecord.patient.citizenId}"/>/profile">${newRecord.patient.citizenId}</a>
                </li>
                <li class="active">
                    <strong>Create Medical Record</strong>
                </li>
            </ol>
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">

            <div class="col-lg-3">
                <div class="widget-head-color-box white-bg p-lg">
                    <br class="m-b-md">
                    <div>
                        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                    </div>
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box navy-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Patient
                        </h2>
                        <small><c:out value="${newRecord.patient.citizenName}"/></small>
                    </div>
                    <img src="${newRecord.patient.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box yellow-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Doctor
                        </h2>
                        <small><c:out value="${newRecord.doctor.citizenName}"/></small>
                    </div>
                    <img src="${newRecord.doctor.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box blue-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Institution
                        </h2>
                        <small><c:out value="${newRecord.institution.institutionName}"/></small>
                    </div>
                    <img src="${newRecord.institution.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Medical Records</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="panel-body">
                            <!-- content here -->
                                <form:form method="post" class="form-horizontal" modelAttribute="newReportInfo">
                                    <div class="mypost-list">
                                        <h4 class="font-bold">Description:</h4>
                                            <form:textarea path="generalReport" class="form-control" rows="6"/>
                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group"><label class="col-sm-2 control-label">Treatment:</label>
                                            <div class="col-sm-10">
                                                <form:input path="treatment" type="text" class="form-control"/></div>
                                        </div>
                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group"><label class="col-sm-2 control-label">Heart Beat:</label>
                                            <div class="col-sm-10">
                                                <form:input path="heartBeat" type="number" class="form-control"/></div>
                                        </div>
                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group"><label class="col-sm-2 control-label">Blood Pressure:</label>
                                            <div class="col-sm-10">
                                                <form:input path="bloodPressure" type="number" class="form-control"/></div>
                                        </div>
                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group"><label class="col-sm-2 control-label">Sugar:</label>
                                            <div class="col-sm-10">
                                                <form:input path="sugar" type="number" class="form-control"/></div>
                                        </div>
                                        <div class="hr-line-dashed"></div>

                                        <div class="form-group"><label class="col-sm-2 control-label">Haemoglobin:</label>
                                            <div class="col-sm-10">
                                                <form:input path="haemoglobin" type="number" class="form-control"/></div>
                                        </div>
                                        <div class="hr-line-dashed"></div>
                                    </div>
                                    <button class="btn btn-primary pull-right" type="submit">Create</button>
                            </form:form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>





</tags:citizen>

</body>
</html>