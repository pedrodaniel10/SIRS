<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<tags:header title="Medical Record">
    <link rel="stylesheet" href="css/blueimp-gallery.min.css">
</tags:header>

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
                    <a href="/citizens/${record.medicalRecord.patient.citizenId}/profile">${record.medicalRecord.patient.citizenName}</a>
                </li>
                <li class="active">
                    <strong>#${record.medicalRecord.recordId}</strong>
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
                            <div>
                                <b><span>Number:</span></b><br>
                                <span><c:out value="${record.medicalRecord.recordId}"/></span><br>
                            </div>

                            <div>
                                <b><span>Creation Date:</span></b><br>
                                <span><c:out value="${record.medicalRecord.creationDate}"/></span><br>
                            </div>

                            <div>
                                <b><span>Treatment:</span></b><br>
                                <span><c:out value="${record.medicalRecord.reportInfo.treatment}"/></span><br>
                            </div>

                            <div>
                                <b><span>Signature Doctor:</span></b><br>
                                <c:if test="${record.verified}">
                                    <span class="text-info" style="color: greenyellow">
                                    Verified
                                    </span>
                                </c:if>
                                <c:if test="${!record.verified}">
                                    <span class="text-info" style="color: red">
                                    Not Verified
                                    </span>
                                </c:if>
                            </div>
                            <br/><br/><br/>
                        </div>
                    </div>
                </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box navy-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Patient
                        </h2>
                        <small><c:out value="${record.medicalRecord.patient.citizenName}"/></small>
                    </div>
                    <img src="${record.medicalRecord.patient.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box yellow-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Doctor
                        </h2>
                        <small><c:out value="${record.medicalRecord.doctor.citizenName}"/></small>
                    </div>
                    <img src="${record.medicalRecord.doctor.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box blue-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Institution
                        </h2>
                        <small><c:out value="${record.medicalRecord.institution.institutionName}"/></small>
                    </div>
                    <img src="${record.medicalRecord.institution.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
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
                            <div class="body">
                                <div class="mypost-list">
                                    <div class="post-box">
                                        <p><c:out value="${record.medicalRecord.reportInfo.generalReport}"/></p>
                                    </div>
                                    <hr>
                                    <div class="post-box">
                                        <h4 class="font-bold">General Report</h4>
                                        <hr>
                                        <h5>Heart Beat <span class="pull-right"><c:out value="${record.medicalRecord.reportInfo.heartBeat}"/></span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow=<c:out value="${record.medicalRecord.reportInfo.heartBeat}"/> aria-valuemin="0" aria-valuemax="100" style="width: ${record.medicalRecord.reportInfo.heartBeat}%"> <span class="sr-only">40% Complete (success)</span> </div>
                                        </div>
                                        <h5>Blood Pressure<span class="pull-right"><c:out value="${record.medicalRecord.reportInfo.bloodPressure}"/></span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" aria-valuenow=<c:out value="${record.medicalRecord.reportInfo.bloodPressure}"/> aria-valuemin="0" aria-valuemax="100" style="width: ${record.medicalRecord.reportInfo.bloodPressure}%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                        <h5>Sugar<span class="pull-right"><c:out value="${record.medicalRecord.reportInfo.sugar}"/></span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" aria-valuenow=<c:out value="${record.medicalRecord.reportInfo.sugar}"/> aria-valuemin="0" aria-valuemax="100" style="width: ${record.medicalRecord.reportInfo.sugar}%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                        <h5>Haemoglobin<span class="pull-right"><c:out value="${record.medicalRecord.reportInfo.haemoglobin}"/></span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow=<c:out value="${record.medicalRecord.reportInfo.haemoglobin}"/> aria-valuemin="0" aria-valuemax="100" style="width: ${record.medicalRecord.reportInfo.haemoglobin}%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>





</tags:citizen>

</body>
</html>