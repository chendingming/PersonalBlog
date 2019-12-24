<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>个人博客-写文章</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/my_css.css" />
<script type="text/javascript">
	
</script>
</head>
<style>
	body{    
        background-image: url(img/images/bg4.jpg);    
        background-size:cover;  
     } 
</style>
<script>
	$(function() {
		$
				.ajax({
					url : 'ArticleController?action=getAllArticle',
					dataType : 'json',
					method : 'post',
					data : '',
					success : function(result) {
						var list = eval(result);
						var html = "";
						for (var i = 0; i < list.length; i++) {
							var article = list[i];
							html += "<div class='row MY_divbordertop'>"
									+ "<div class='col-lg-5'>"
									+ "<a href='ArticleController?action=getById&id="
									+ article.id
									+ "' class='thumbnail'>"
									+ "<img height='120px' width='180px' src='"
									+ article.image
									+ "' alt='图片未加载'></a></div>"
									+ "<div class='col-lg-7'><h2>"
									+ article.title
									+ "</h2>"
									+ "<p class='MY_fontSize3'>"
									+ article.body.substring(0, 5)
									+ "...<a href='ArticleController?action=getById&id="
									+ article.id
									+ "'>[详情]</a></p>"
									+ "<hr class='My_hr2'><div><span class='glyphicon glyphicon-time' aria-hidden='true'>"
									+ article.date
									+ "&nbsp;"
									+ "<span class='glyphicon glyphicon-pencil' aria-hidden='true'>"
									+ article.num
									+ " 条评论&nbsp;"
									+ "<a href='ArticleController?action=delArticle&articleid="
									+ article.id
									+ "'>"
									+ "<span class='glyphicon glyphicon-trash' aria-hidden='true'>删除&nbsp;"
									+ "</span></a><a href='ArticleController?action=editarticle&articleid="
									+ article.id
									+ "'>"
									+ "<span class='glyphicon glyphicon-file' aria-hidden='true'>修改&nbsp;"
									+ "</span></a></span></span></div></div></div>"
						}
						$("#articlelist").append(html);
					}
				});
	})
</script>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<!-- 容器 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8" id="container">
				<div id="result">
					<h1>
						&nbsp;文章列表&nbsp;<small>&nbsp;&nbsp;&nbsp;点点滴滴都是努力。</small>
					</h1>
					<hr class="My_hr1">
					<div class="row MY_divbordertop">
						<a href="articledetail.jsp?edit=1" class="btn btn-primary btn-lg">写一篇长长的文章</a>
					</div>
					<!-- 遍历的每一行 -->
					<div id="articlelist"></div>
				</div>





			</div>
			<div class="col-lg-4">
				<jsp:include page="right.jsp"></jsp:include>
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
