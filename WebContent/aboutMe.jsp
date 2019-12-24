<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/29
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人信息</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
    <script type="text/javascript">
    </script>
</head>
<style>
	body{    
        background-image: url(img/images/bg3.jpg);    
        background-size:cover;  
     } 
</style>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div id="userInfo">
    <div class="container">
        <div class="row">
            <div class="col-lg-8" id="container">
                <div id="result">
                	<h1>&nbsp;关于我&nbsp;<small>&nbsp;&nbsp;&nbsp;只能用三个字概括自己---我真帅</small>
                </h1>
                <hr class="My_hr1"/>
                <div class="row MY_divbordertop">
                    <div class="MY_divpadding" id="div01">
                        <br/>
                    </div>
                    <c:if test="${user.id == 1}">
                        <div class="MY_divpadding" id="div02">
                            <form method="post" action="MyInfoController?action=updateMyInfo">
                                <div class="form-group">
                                    <label class="MY_fontSize4 "><span class="glyphicon glyphicon-info-sign"
                                                                       aria-hidden="true">&nbsp;个人资料&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="info" value="${me.info }">
                                    <input type="hidden" name="userID" value="${user.id }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人爱好&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="hobby" value="${me.hobby }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人QQ&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="qq" value="${me.qq }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人邮箱&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="email" value="${me.email }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人简介&nbsp;</span></label>
                                    <textarea class="MY_textarea" id="exampleInputFile" cols="100" rows="3"
                                              name="description">${me.description }</textarea>
                                </div>
                                <button type="submit" class="btn btn-success">保存</button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${user.id != 1}">
                        <div class="MY_divpadding" id="div02">
                            <form method="post" action="MyInfoController?action=updateMyInfo">
                                <div class="form-group">
                                    <label class="MY_fontSize4 "><span class="glyphicon glyphicon-info-sign"
                                                                       aria-hidden="true">&nbsp;个人资料&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="info" value="${me.info }" readonly>
                                    <input type="hidden" name="userID" value="${user.id }">
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人爱好&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="hobby" value="${me.hobby }" readonly>
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人QQ&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="qq" value="${me.qq }" readonly>
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人邮箱&nbsp;</span></label>
                                    <input type="text" class="form-control MY_input" name="email" value="${me.email }" readonly>
                                </div>
                                <div class="form-group">
                                    <label class="MY_fontSize4"><span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true">&nbsp;个人简介&nbsp;</span></label>
                                    <textarea class="MY_textarea" id="exampleInputFile" cols="100" rows="3"
                                              name="description" readonly>${me.description }</textarea>
                                </div>                        
                            </form>
                        </div>
                    </c:if>
                </div>
                </div>
            </div>
            <div class="col-lg-4">
                <jsp:include page="right.jsp"></jsp:include>
            </div>
        </div>
    </div>
</div>
</body>
<script>
	function search() {
		var text = $("#input01").val().trim().toString();
		if (text.length == 0 || text == " ") {
			alert("搜索内容不能为空");
		} else {
			$
					.ajax({
						url : "ArticleController?action=search",
						dataType : "JSON",
						method : "post",
						data : {
							search : text
						},
						success : function(result) {
							$("#result").remove();
							$("#container").append("<div id='result'></div>");
							var list = eval(result);
							var html = "";
							for (var i = 0; i < list.length; i++) {
								var article = list[i];
								html += "<div class='row MY_divbordertop'>"
										+ "<div class='col-lg-5'><a href='ArticleController?action=getById&id="
										+ article.id
										+ "' class='thumbnail'>"
										+ "<img height='120px' width='180px' src='"
										+ article.image
										+ "' alt='图片未加载'> </a></div>"
										+ "<div class='col-lg-7'><h2>"
										+ article.title
										+ "</h2>"
										+ "<p class='MY_fontSize3'>"
										+ article.body.substring(0, 5)
										+ "...<a href='ArticleController?action=getById&id="
										+ article.id
										+ " '>[详情]</a></p>"
										+ "<hr class='My_hr2'>"
										+ "<div><span class='glyphicon glyphicon-time' aria-hidden='true'>&nbsp;"
										+ article.date
										+ "&nbsp;"
										+ "<span class='glyphicon glyphicon-pencil' aria-hidden='true'>&nbsp;"
										+ article.num
										+ "条评论</span></span></div></div></div>"
							}
							$("#result").append(html);
						}
					});
		}
	}
</script>
</html>
