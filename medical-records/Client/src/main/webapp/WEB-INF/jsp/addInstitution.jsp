<%@ taglib tagdir="/src/main/webapp/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Add Institution">
    <link href="/css/plugins/iCheck/custom.css" rel="stylesheet">
</tags:header>

<body>
<tags:citizen name="David Williams" image="/img/david_williams.jpg" citizenId="20" suser="true">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>Add Institution</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/institutions">Institutions</a>
                </li>
                <li class="active">
                    <strong>Add Institution</strong>
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
                        <h5>Institution Information</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" class="form-horizontal">

                            <div class="form-group"><label class="col-sm-2 control-label">Name:</label>
                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Address:</label>
                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group"><label class="col-sm-2 control-label">Citizen ID:</label>
                                <div class="col-sm-10"><input type="text" class="form-control"></div>
                            </div>
                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">Add Institution</button>
                                </div>
                            </div>
                        </form>
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