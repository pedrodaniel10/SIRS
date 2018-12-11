<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
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
                    <a href="/citizens">Citizens</a>
                </li>
                <li>
                    <a href="/citizens/${citizen.citizenId}/view">${citizen.citizenName}</a>
                </li>
                <li class="active">
                    <strong>#${record.recordId}</strong>
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
                                <span>Number:</span>
                                <span class="pull-right">${record.recordId}</span>
                            </div>

                            <div>
                                <span>Creation Date:</span>
                                <span class="pull-right">${record.creationDate}</span>
                            </div>

                            <div>
                                <span>Treatment:</span>
                                <span class="pull-right">${record.reportInfo.treatment}</span>
                            </div>

                            <div>
                                <span>Signature Patient:</span>
                                <span class="pull-right text-info">Verified</span>
                            </div>

                            <div>
                                <span>Signature Doctor:</span>
                                <span class="pull-right text-info">Verified</span>
                            </div>

                            <div>
                                <span>Signature Institution:</span>
                                <span class="pull-right text-info">Verified</span>
                            </div>
                            <br/><br/><br/><br/><br/>
                        </div>
                    </div>
                </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box navy-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Patient
                        </h2>
                        <small>${citizen.citizenName}</small>
                    </div>
                    <img src="${citizen.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box yellow-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Doctor
                        </h2>
                        <small>${doctor.citizenName}</small>
                    </div>
                    <img src="${doctor.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box blue-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Institution
                        </h2>
                        <small>${institution.institutionName}</small>
                    </div>
                    <img src="${institution.profilePic}" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
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
                                        <p>${record.reportInfo.generalReport}</p>
                                    </div>
                                    <hr>
                                    <div class="post-box">
                                        <h4 class="font-bold">General Report</h4>
                                        <hr>
                                        <h5>Heart Beat <span class="pull-right">${record.reportInfo.heartBeat}</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow=${record.reportInfo.heartBeat} aria-valuemin="0" aria-valuemax="100" style="width: 40%"> <span class="sr-only">40% Complete (success)</span> </div>
                                        </div>
                                        <h5>Blood Pressure<span class="pull-right">${record.reportInfo.bloodPressure}</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" aria-valuenow=${record.reportInfo.bloodPressure} aria-valuemin="0" aria-valuemax="100" style="width:90%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                        <h5>Sugar<span class="pull-right">${record.reportInfo.sugar}</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" aria-valuenow=${record.reportInfo.sugar} aria-valuemin="0" aria-valuemax="100" style="width:50%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                        <h5>Haemoglobin<span class="pull-right">${record.reportInfo.haemoglobin}</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow=${record.reportInfo.haemoglobin} aria-valuemin="0" aria-valuemax="100" style="width:70%;"> <span class="sr-only">50% Complete</span> </div>
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