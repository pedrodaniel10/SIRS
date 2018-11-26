<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Citizens"/>

<body>
<tags:patient name="David Williams" image="/img/david_williams.jpg" citizenId="20" suser="true">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Citizens</h2>
            <ol class="breadcrumb">
                <li class="active">
                    <strong>Citizens</strong>
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
                        <h5>Citizens</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <a href="/citizens/add" class="btn btn-success" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">Add Citizen</span></a>

                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover dataTables-example" style="text-align: center;">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>Roles</th>
                                    <th>Citizen ID</th>
                                    <th>Name</th>
                                    <th>Born</th>
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="center"><img src="/img/david_williams.jpg" class="img-circle" style="width:50px;height:50px;"\></td>
                                    <td class="center"><span class="badge badge-success">Patient</span><span class="badge badge-warning">Doctor</span></td>
                                    <td class="center">24786562</td>
                                    <td class="center">David Edward Williams</td>
                                    <td class="center">20/08/1971</td>
                                    <td class="center">Male</td>
                                    <td class="center">davidwilliams@hotmail.com</td>
                                    <td class="center">
                                        <a href="/citizens/20/edit" class="btn btn-success btn-circle" type="button">
                                            <i class="fa fa-pencil"></i>
                                        </a>
                                        <a class="btn btn-danger btn-circle" type="button">
                                            <i class="fa fa-trash-o"></i>
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</tags:patient>
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
