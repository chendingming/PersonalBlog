<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/29
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">
        $(function () {
        });
    </script>
</head>
<body>
<nav class="navbar navbar-default MY_fontSize1 navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand active" href="index.jsp">CDM的博客</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="LifeController?action=getAllLife"><span class="glyphicon glyphicon-tree-deciduous"
                                     aria-hidden="true"></span>&nbsp;生活随笔 </a></li>
                <li><a href="message.jsp"><span class="glyphicon glyphicon-bullhorn"
                                     aria-hidden="true">&nbsp;留言板</span></a></li>
                <li><a href="MyInfoController?action=getMyInfo"> <span class="glyphicon glyphicon-hand-right"
                                                 aria-hidden="true">&nbsp;关于我</span></a></li>
                <c:if test="${user.id == 1}">
                    <li><a href="writeArticle.jsp"><span class="glyphicon glyphicon-pencil"
                                                                               aria-hidden="true">&nbsp;写文章</span></a></li>
                    <li><a href="UserController?action=findAllUser"> <span
                            class="glyphicon glyphicon-star-empty" aria-hidden="true">&nbsp;联系人</span></a></li>
                </c:if>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input id="input01" type="text" class="form-control" placeholder="搜索文章" name="title">
                </div>
                <button id="button01" type="button" class="btn btn-default" onclick="search()"><span class="glyphicon glyphicon-search" aria-hidden="true"/></button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty user.name}">
                    <li><a href="login.jsp"> &nbsp;登录</a></li>
                    <li><a href="register.jsp">&nbsp;注册</a></li>
                </c:if>
                <c:if test="${!empty user.name}">
                    <li><a href=""> &nbsp;欢迎${user.name}登陆</a></li>
                    <li><a href="UserController?action=logout">&nbsp;注销</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
