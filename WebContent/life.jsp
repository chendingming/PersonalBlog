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
<script>
	function deleteById(id){
		var data={lifeid:id};
		$.ajax({
			url:'LifeController?action=delete',
			dataType : 'json',
			method : 'post',
			data : data,
			success:function(result){
				if(result!=0){
					alert("删除成功");
					window.location.href='LifeController?action=getAllLife';
				}	
			}
		});
	}
	function addLife(){
		var lifetext=document.getElementById("lifetext").value;
		if(lifetext.length==0||lifetext==" "){
			alert("内容不能为空")
		}else{
			var data={lifeBody:lifetext};
			$.ajax({
				url:'LifeController?action=addLife',
				dataType : 'json',
				method : 'post',
				data : data,
				success:function(result){
					if(result!=0){
						alert("添加成功");
						window.location.href='LifeController?action=getAllLife';
					}	
				}
			});
		}
	}
</script>

<body>
	<jsp:include page="head.jsp"></jsp:include>
	<!-- 容器 -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8" id="container">
				<div id="result">
					<h1>
						&nbsp;生活随笔&nbsp;<small>&nbsp;&nbsp;&nbsp;用心记录生活中的点滴。</small>
					</h1>
					<hr class="My_hr1" />
					<!-- 是要被隐藏的 -->
					<c:if test="${user.id==1 }">
						<div class="row MY_divbordertop">
							<h2>个人随笔</h2>
							<div class="form-group">
								<textarea name="lifetext" id="lifetext" class="MY_textarea"
									id="exampleInputFile" cols="100" rows="3"></textarea>
							</div>
							<div class="form-group">
								<button type="button" onclick="addLife()"
									class="btn btn-success">记录一下</button>
							</div>
						</div>
						
						<c:forEach items="${life }" var="l">
						<div class="row MY_divbordertop">
							<div class="MY_divborder">
								<h4>
									<span class="glyphicon glyphicon-calendar" aria-hidden="true">&nbsp;${l.date }</span>
								</h4>
								<p>${l.body }</p>
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								<a href="javascript:void(0);" onclick="deleteById(${l.id})">删除</a>
							</div>
						</div>
					</c:forEach>
					</c:if>
					<c:if test="${user.id!=1 }">
						<c:forEach items="${life }" var="l">
						<div class="row MY_divbordertop">
							<div class="MY_divborder">
								<h4>
									<span class="glyphicon glyphicon-calendar" aria-hidden="true">&nbsp;${l.date }</span>
								</h4>
								<p>${l.body }</p>
								
							</div>
						</div>
					</c:forEach>
					</c:if>
					
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
		var text = $("#input01").val();
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