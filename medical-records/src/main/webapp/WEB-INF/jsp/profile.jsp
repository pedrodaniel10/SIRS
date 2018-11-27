<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Profile"/>

<body>
    <tags:citizen name="David Williams" image="/img/david_williams.jpg" citizenId="20" suser="true">
        <!--Top panel-->
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>David Williams</h2>
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
                                        David Williams
                                    </h2>
                                    <small>David Edward Williams</small>
                                </div>
                                <img src="/img/david_williams.jpg" class="img-circle circle-border m-b-md" style="width:250px;height:250px;"\>
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
                                            <span class="badge badge-success">Patient</span><span class="badge badge-warning">Doctor</span>
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Citizen ID:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            24786562
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Name:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            David Edward Williams
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Born:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            20 August 1971
                                        </div>
                                    </div>
                                    <div class="row wrapper border-bottom white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Gender:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            Male
                                        </div>
                                    </div>
                                    <div class="row wrapper white-bg">
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            <strong>Email:</strong>
                                        </div>
                                        <div class="col-lg-6" style="height:40px;line-height:40px;">
                                            davidwilliams@hotmail.com
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
                            <a href="/citizens/20/medrec/create" class="btn btn-info" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold">Create Record</span></a>
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
                                    <tr>
                                        <td>11/05/2017</td>
                                        <td>Dr.Rajesh</td>
                                        <td>Check up</td>
                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>13/05/2017</td>
                                        <td>Dr.Rajesh</td>
                                        <td>X-Ray</td>
                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>13/05/2017</td>
                                        <td>Dr.Rajesh</td>
                                        <td>Blood Test</td>
                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>14/05/2017</td>
                                        <td>Dr.Rajesh</td>
                                        <td>Admit</td>
                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>15/05/2017</td>
                                        <td>Dr.Rajesh</td>
                                        <td>Operation</td>
                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>18/05/2017</td>
                                        <td>Dr.Rajesh</td>
                                        <td>Discharge</td>
                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="View">
                                            <i class="fa fa-chevron-circle-right"></i></a>
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