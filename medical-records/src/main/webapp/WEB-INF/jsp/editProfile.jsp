<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Edit Profile">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
</tags:header>

<tags:patient name="David Williams" image="/img/david_williams.jpg" citizenId="20" suser="true">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Edit Profile</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/citizens">Citizens</a>
                </li>
                <li>
                    <a href="/citizens/20/view">David Williams</a>
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
                        <h5>Personal Information</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" class="form-horizontal">
                            <div class="form-group"><label class="col-sm-2 control-label">Roles:</label>&nbsp;&nbsp;
                                <div class="checkbox-inline i-checks"><label> <input type="checkbox" disabled="" checked="" value="patient"> <i></i> Patient </label></div>
                                <div class="checkbox-inline i-checks"><label> <input type="checkbox" value="doctor"> <i></i> Doctor </label></div>
                                <div class="checkbox-inline i-checks"><label> <input type="checkbox" value="admin"> <i></i> Administrator </label></div>
                                <div class="checkbox-inline i-checks"><label> <input type="checkbox" value="suser"> <i></i> Super User </label></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Citizen ID:</label>
                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Name:</label>
                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Date of Birth:</label>
                                <div class="col-sm-10"><input type="date" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Gender:</label>
                                <div class="col-sm-10">
                                    <select class="form-control m-b">
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Email:</label>

                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Username:</label>

                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Password:</label>

                                <div class="col-sm-10"><input type="password" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>


                            <div class="form-group"><label class="col-sm-2 control-label">Confirm Password:</label>

                                <div class="col-sm-10"><input type="password" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">Save Changes</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</tags:patient>
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