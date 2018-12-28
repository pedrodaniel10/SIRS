<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<tags:header title="Doctors"/>

<body>
<tags:citizen citizen="${citizen}">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Doctors</h2>
            <ol class="breadcrumb">
                <li class="active">
                    <strong>Doctors</strong>
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
                        <h5>Doctors</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <a href="/doctors/add" class="btn btn-success" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">Add Doctor</span></a>

                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover dataTables-example" style="text-align: center;">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>Citizen ID</th>
                                    <th>Name</th>
                                    <th>Born</th>
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="doctor" items="${doctors}">
                                <tr>
                                    <td class="center"><img src="${doctor.citizen.profilePic}" class="img-circle" style="width:50px;height:50px;"\></td>
                                    <td class="center"><c:out value="${doctor.citizen.citizenId}"/></td>
                                    <td class="center"><c:out value="${doctor.citizen.citizenName}"/></td>
                                    <td class="center"><c:out value="${doctor.citizen.dateOfBirth}"/></td>
                                    <td class="center"><c:out value="${doctor.citizen.gender}"/></td>
                                    <td class="center"><c:out value="${doctor.citizen.email}"/></td>
                                    <td class="center">
                                        <a href="/doctors/${doctor.citizenId}/delete" class="btn btn-danger btn-circle" type="button">
                                            <i class="fa fa-trash-o"></i>
                                        </a>
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
