<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<!DOCTYPE html>

<html lang="en">
<tags:header title="Medical Record">
    <link rel="stylesheet" href="css/blueimp-gallery.min.css">
</tags:header>

<body>
<tags:patient name="David Williams" image="/img/david_williams.jpg">
    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-9">
            <h2>Medical Record</h2>
            <ol class="breadcrumb">
                <li>
                    <a href="/profile">David Williams</a>
                </li>
                <li class="active">
                    <strong>#23</strong>
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
                                <span class="pull-right">23</span>
                            </div>

                            <div>
                                <span>Creation Date:</span>
                                <span class="pull-right">2018-11-19 11:26:53</span>
                            </div>

                            <div>
                                <span>Last Modified:</span>
                                <span class="pull-right">2018-11-19 11:26:53</span>
                            </div>

                            <div>
                                <span>Treatment:</span>
                                <span class="pull-right">X-Ray</span>
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
                            <br/><br/><br/>
                            <div class="text-center"><button class="btn btn-success" type="button"><i class="fa fa-paste"></i> Edit Record</button></div>
                        </div>
                    </div>
                </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box navy-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Patient
                        </h2>
                        <small>David Williams</small>
                    </div>
                    <img src="/img/david_williams.jpg" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box yellow-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Doctor
                        </h2>
                        <small>John Terry</small>
                    </div>
                    <img src="/img/alex_smith.jpg" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
                </div>
            </div>

            <div class="col-lg-3">
                <div class="widget-head-color-box blue-bg p-lg text-center">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins">
                            Institution
                        </h2>
                        <small>Royal London Hospital</small>
                    </div>
                    <img src="/img/royal_london_hospital.jpg" class="img-circle circle-border m-b-md" style="width:150px;height:150px;">
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
                                        <p>It is also used to identify any abnormal tissue in the uterine cavity, such as uterine fibroids, endometrial polyps, scar tissue, or retained pregnancy tissue, the presence of which may be suggested by history or previous tests such as a hysterosalpingogram (x-ray of the uterus and tubes). This procedure is done in the office here at IVF New England, and is done by one of the physicians.  </p>
                                        <p>Approximately an hour before the exam we suggest that you take Ibuprofen 600 mg (Motrin/Advil) or a similar medication to minimize some mild to moderate cramping that you may experience during the procedure. </p>
                                    </div>
                                    <hr>
                                    <div class="post-box">
                                        <h4 class="font-bold">General Report</h4>
                                        <hr>
                                        <h5>Heart Beat <span class="pull-right">85</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"> <span class="sr-only">40% Complete (success)</span> </div>
                                        </div>
                                        <h5>Blood Pressure<span class="pull-right">93</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width:90%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                        <h5>Sugar<span class="pull-right">55</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                        <h5>Haemoglobin<span class="pull-right">78%</span></h5>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%;"> <span class="sr-only">50% Complete</span> </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">

                    <div class="ibox-content">

                        <h2>Attachments</h2>
                            <a href="/img/xray1.jpg" title="X-Ray 1" data-gallery=""><img src="/img/xray1.jpg" style="width:auto;height:100px;"></a>
                            <a href="/img/xray2.jpg" title="X-Ray 2" data-gallery=""><img src="/img/xray2.jpg"style="width:auto;height:100px;"></a>
                            <a href="/img/electrocardiogram.png" title="Electrocardiogram" data-gallery=""><img src="/img/electrocardiogram.png"style="width:auto;height:100px;"></a>
                    </div>
                </div>
            </div>

        </div>
    </div>





</tags:patient>

</body>
</html>