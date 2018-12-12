<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<tags:header title="Profile"/>

<body>
    <tags:citizen citizen="${citizen}">
        <!--Top panel-->
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>${profile.citizenName}</h2>
                <ol class="breadcrumb">
                    <li class="active">
                        <strong>Profile</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeIn">
            <!-- Profile info -->
            <div class="row">
            <div class="col-lg-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Profile</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="panel-body">
                            <!-- content here -->
                            <div class="widget-head-color-box navy-bg p-lg text-center">
                                <div class="m-b-md">
                                    <h2 class="font-bold no-margins">
                                        ${profile.citizenName}
                                    </h2>
                                    <small>${profile.citizenName}</small>
                                </div>
                                <img src="${profile.profilePic}" class="img-circle circle-border m-b-md" style="width:250px;height:250px;"\>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Profile Information
                                </div>

                                <div class="panel-body">
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Roles:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <c:forEach var="role" items="${profile.roles}">
                                                <c:if test="${role.name().equals(\"SUPERUSER\")}">
                                                    <span class="badge badge-danger">${role.name()}</span>
                                                </c:if>
                                                <c:if test="${role.name().equals(\"ADMIN\")}">
                                                    <span class="badge badge-warning-light">${role.name()}</span>
                                                </c:if>
                                                <c:if test="${role.name().equals(\"DOCTOR\")}">
                                                    <span class="badge badge-success">${role.name()}</span>
                                                </c:if>
                                                <c:if test="${role.name().equals(\"PATIENT\")}">
                                                    <span class="badge badge-secondary">${role.name()}</span>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Citizen ID:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            ${profile.citizenId}
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Name:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                                ${profile.citizenName}
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Born:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                                ${profile.dateOfBirth}
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Gender:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                                ${profile.gender.name()}
                                        </div>
                                    </div>
                                    <div class="row wrapper white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Email:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                                ${profile.email}
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
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
                            <c:if test="${citizen.hasRole(\"DOCTOR\")}">
                            <a href="/citizens/${profile.citizenId}/medrec/create" class="btn btn-info" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">Create Record</span></a>
                            </c:if>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover dataTables-example" style="text-align: center;">
                                    <thead>

                                    <tr>
                                        <th>Date</th>
                                        <th>Doctor</th>
                                        <th>Treatment</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="record" items="${records}">
                                    <tr>
                                        <td>${record.medicalRecord.creationDate}</td>
                                        <td>${record.medicalRecord.doctor.citizenName}</td>
                                        <td>${record.medicalRecord.reportInfo.treatment}</td>
                                        <td><a href="/citizens/${record.medicalRecord.patient.citizenId}/medrec/${record.medicalRecord.recordId}/view" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>

    </tags:citizen>
    <!-- Mainly scripts -->
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <script src="/js/plugins/dataTables/datatables.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="/js/inspinia.js"></script>
    <script src="/js/plugins/pace/pace.min.js"></script>

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function(){
            $('.dataTables-example').DataTable({
                pageLength: 10,
                responsive: true,
                dom: '<"html5buttons"B>lTfgitp',
                buttons: []
            });

        });

    </script>
</body>
</html>