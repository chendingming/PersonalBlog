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
        background-image: url(img/images/bg4.jpg);    
        background-size:cover;  
     } 
</style>
<script>
	$
			.ajax({
				url : 'ACommentController?action=getAll',
				dataType : 'json',
				method : 'post',
				data : {articleid:${article.id}},
				success : function(result) {
					var list = eval(result);
					var html = "";
					for (var i = 0; i < list.length; i++) {
						var acm = list[i];
						html += "<div class='row MY_divbordertop'><div class='col-lg-1'>"
								+ "<img src='img/apple.png' alt='苹果' height='60px' width='60px' class='img-circle'>"
								+ "</div><div class='col-lg-11'>"
								+ "<span class='MY_span'>"
								+ acm.user_name
								+ "</span><span class='MY_span'>"
								+ "<span class='glyphicon glyphicon-time' aria-hidden='true'>"
								+ acm.date
								+ "</span></span>"
								+ "<br/><span>"
								+ acm.body + "</span><br/></div></div>"
					}
					$("#allcomment").append(html);
				}
			});
	function add() {
		var textbody = document.getElementById("comment1").value;
		if(textbody==" "||textbody.length==0){
			alert("评论内容不能为空");
		}else{
		var data = {
			articleid : $("#comment2").val(),
			username : $("#comment3").val(),
			body : textbody
		}
		$
				.ajax({
					url : 'ACommentController?action=add',
					dataType : 'json',
					method : 'post',
					data : data,
					success : function(result) {
						var acm = eval(result);
						var html = "";
						html += "<div class='row MY_divbordertop'><div class='col-lg-1'>"
								+ "<img src='img/apple.png' alt='苹果' height='60px' width='60px' class='img-circle'>"
								+ "</div><div class='col-lg-11'>"
								+ "<span class='MY_span'>"
								+ acm.user_name
								+ "</span><span class='MY_span'>"
								+ "<span class='glyphicon glyphicon-time' aria-hidden='true'>"
								+ acm.date
								+ "</span></span>"
								+ "<br/><span>"
								+ acm.body + "</span><br/></div></div>"
						$("#allcomment").append(html);
						alert("评论成功");
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
						&nbsp;阅读&nbsp;<small>&nbsp;&nbsp;&nbsp;阅读使人开明</small>
					</h1>
					<hr class="My_hr1" />
					<!-- 遍历的每一行 -->
					<div class="row MY_divbordertop">
						<div class="MY_divpadding2">
							<h2>${article.title }</h2>
							<small> <span class="glyphicon glyphicon-user"
								aria-hidden="true">&nbsp;${article.author }&nbsp;</span> <span
								class="glyphicon glyphicon-time" aria-hidden="true">&nbsp;${article.date }</span>
							</small> <br /> <br />
							<p class="MY_fontSize1">${article.body }</p>
						</div>
					</div>
					<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
					<c:if test="${!empty user }">
						<div class="form-group">
							<textarea id="comment1" cols="100" rows="2" name="body"></textarea>
							<input id="comment2" value="${article.id }" type="hidden"
								name="articleid" /> <input id="comment3" value="${user.name }"
								type="hidden" name="username" />
						</div>
						<button id="button1" type="button" class="btn btn-info"
							onclick="add()">评论</button>
					</c:if>
					<h4>
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true">&nbsp;最新评论</span>
					</h4>



					<div id="allcomment"></div>
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
