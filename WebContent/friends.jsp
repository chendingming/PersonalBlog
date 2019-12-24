<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/my_css.css" />
</head>
<style>
	body{    
        background-image: url(img/images/bg1.jpg);    
        background-size:cover;  
     } 
</style>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<!-- 容器 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8" id="container">
				<div id="result">
					<h1>
					&nbsp;注册的小伙伴&nbsp;<small>&nbsp;&nbsp;&nbsp;快来认识一下新朋友</small>
				</h1>
				<hr class="My_hr1">

				<!-- 遍历的每一行 -->
				<div class="row MY_divbordertop">
					<table class="table table-hover  MY_table MY_fontSize4">
						<thead>
							<tr>
								<th class="MY_table">昵称</th>
								<th class="MY_table">电子邮箱</th>
								<th class="MY_table">博客网址</th>
								<th class="MY_table">操作</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach items="${allUser }" var="user">
								<tr>
									<c:if test="${user.stranger!=1 }">
										<td>${user.name }</td>
									</c:if>
									<c:if test="${user.stranger==1 }">
										<td><span class="label label-success">好友</span>
											${user.name }</td>
									</c:if>
									<td>${user.email_address }</td>
									<td>${user.blog_adress }</td>
									<td><span class="glyphicon glyphicon-ok"
										aria-hidden="true"></span> <a href="javascript:void(0);"
										onclick="addFriend(${user.id })">标记好友</a> <span
										class="glyphicon glyphicon-volume-down" aria-hidden="true"></span>
										<a href="javascript:void(0);"
										onclick="deleteFriend(${user.id })">恢复</a> <span
										class="glyphicon glyphicon-trash" aria-hidden="true"></span> <a
										href="javascript:void(0);" onclick="deleteUser(${user.id })">删除</a>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				</div>
				
			</div>
			<div class="col-lg-4">
                <jsp:include page="right.jsp"></jsp:include>
            </div>
		</div>
	</div>
</body>
<script>
	function addFriend(id){
		var data={userid:id};
		$.ajax({
			url:'UserController?action=addFriend',
			dataType:'json',
			method:'post',
			data:data,
			success:function(result){
				if(result>0){
					alert("标记成功");
					window.location.href="UserController?action=findAllUser"
				}
			}
		});
	}
	function deleteFriend(id){
		var data={userid:id};
		$.ajax({
			url:'UserController?action=deleteFriend',
			dataType:'json',
			method:'post',
			data:data,
			success:function(result){
				if(result>0){
					alert("标记成功");
					window.location.href="UserController?action=findAllUser"
				}
			}
		});
	}
	function deleteUser(id){
		var data={userid:id};
		$.ajax({
			url:'UserController?action=deleteUser',
			dataType:'json',
			method:'post',
			data:data,
			success:function(result){
				if(result>0){
					alert("标记成功");
					window.location.href="UserController?action=findAllUser"
				}
			}
		});
	}
</script>
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