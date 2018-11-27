<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="image" required="true"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="citizenId" required="true"%>
<%@ attribute name="patient" required="false" type="java.lang.Boolean"%>
<%@ attribute name="doctor" required="false" type="java.lang.Boolean"%>
<%@ attribute name="admin" required="false" type="java.lang.Boolean"%>
<%@ attribute name="suser" required="false" type="java.lang.Boolean"%>

<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="/css/animate.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">

<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="${image}" style="width:90px;height:90px;"/>
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${name}</strong>
                             </span> <span class="text-muted text-xs block">Patient <b class="caret"></b></span> </span> </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="/login">Logout</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        MR+
                    </div>
                </li>
                <li>
                    <a href="/citizens/${citizenId}/profile"><i class="fa fa-user"></i> <span class="nav-label">Profile</span></a>
                </li>

                <c:if test="${admin}">
                    <li>
                        <a href="#"><i class="fa fa-user-md"></i> <span class="nav-label">Doctors</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="/doctors">All doctors</a></li>
                            <li><a href="/doctors/add">Add doctor</a></li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${suser}">
                    <li>
                        <a href="#"><i class="fa fa-group"></i> <span class="nav-label">Citizens</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="/citizens">All citizens</a></li>
                            <li><a href="/citizens/add">Add citizen</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-hospital-o"></i> <span class="nav-label">Institutions</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="/institutions">All institutions</a></li>
                            <li><a href="/institutions/add">Add institution</a></li>
                        </ul>
                    </li>
                </c:if>

            </ul>

        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>

                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">Welcome to MedRec.</span>
                    </li>
                    <li>
                        <a href="/login">
                            <i class="fa fa-sign-out"></i> Log out
                        </a>
                    </li>

                </ul>

            </nav>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight">
            <jsp:doBody/>
        </div>

        <div class="footer">
            <div class="pull-right">
                Made by <strong>Mafalda Ferreira</strong>, <strong>Leonardo Epif&acirc;nio</strong> and <strong>Pedro Lopes</strong>
            </div>
            <div>
                <strong>Copyright</strong> MedRec - Network and Computer Security &copy; 2018-2019
            </div>
        </div>

    </div>
</div>

<!-- Mainly scripts -->
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="/js/inspinia.js"></script>
<script src="/js/plugins/pace/pace.min.js"></script>