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
<style>
	body{    
        background-image: url(img/images/bg1.jpg);    
        background-size:cover;  
     } 
</style>
<script>
		
				$.ajax({
					url : 'MessageController?action=getAllMessage',
					dataType : 'json',
					method : 'post',
					data : '',
					success : function(result) {
						var list = eval(result);
						var html = '';
						for (var i = 0; i < list.length; i++) {
							var mess = list[i];
							html += "<div class='row MY_divbordertop'><div class='col-lg-1'>"
									+ "<img src='img/apple.png' alt='苹果' height='60px' width='60px' class='img-circle'></div>"
									+ "<div class='col-lg-11'><span class='MY_span'>"+mess.username+"</span><span class='MY_span'>"
									+ "<span class='glyphicon glyphicon-time' aria-hidden='true'></span>"+mess.date+"</span>"
									+ "<br/><span> "+mess.message+"</span><br/>"
									+ "<c:if test='${user.type==1}'>"
									+ "<a class='aclick' href='javascript:void(0);'>回复</a>"
									+ "<div style='display: none;'>"
									+ "<div class='form-group'><textarea id='"+mess.id+"' name='reply' cols='80' rows='2'></textarea></div>"
									+ "<button type='button' onclick='reply("+mess.id+")' class='btn btn-default'>回复</button>"
									+ "</div></c:if><div>"
									+"<h4>博主回复：</h4><p id='' class='"+mess.id+"'> "+mess.reply+"</p>"
									+ "</div></div></div>"
						}
						$("#messagelist").append(html);
					}
				});
				function tologinpage() {
					window.location.href = "login.jsp";
				}
				function reply(id){
					var newreply=document.getElementById(id).value;
					if(newreply.length==0||newreply==" "){
						alert("回复不能为空")
					}else{
					var data={messid:id,reply:newreply};
					$.ajax({
						url:'MessageController?action=addnewreply',
						dataType : 'json',
						method : 'post',
						data : data,
						success:function(result){
							
							if(result!=0){
								window.location.href='message.jsp';
							}
							
							
						}
					});
					}
				}
				function addMessage(id,name){
					var messagetext=document.getElementById("messagetext").value;
					if(messagetext.length==0||messagetext==" "){
						alert("内容不能为空")
					}else{
						var data={userid:id,username:name,message:messagetext};
						$.ajax({
							url:'MessageController?action=addMessage',
							dataType : 'json',
							method : 'post',
							data : data,
							success:function(result){
								var mess=eval(result);
								var html="";
								html += "<div class='row MY_divbordertop'><div class='col-lg-1'>"
									+ "<img src='img/apple.png' alt='苹果' height='60px' width='60px' class='img-circle'></div>"
									+ "<div class='col-lg-11'><span class='MY_span'>"+mess.username+"</span><span class='MY_span'>"
									+ "<span class='glyphicon glyphicon-time' aria-hidden='true'></span>"+mess.date+"</span>"
									+ "<br/><span> "+mess.message+"</span><br/>"
									+ "<c:if test='${user.type==1}'>"
									+ "<a class='aclick' href='javascript:void(0);'>回复</a>"
									+ "<div style='display: none;'>"
									+ "<div class='form-group'><textarea id='reply' name='reply' cols='80' rows='2'></textarea></div>"
									+ "<button type='button' onclick='reply("+mess.id+")' class='btn btn-default'>回复</button>"
									+ "</div></c:if><div>"
									+"<h4>博主回复：</h4><p id=''> "+mess.reply+"</p>"
									+ "</div></div></div>"
								$("#messagelist").append(html);	
							}
						});
					}
					
				}
</script>

<script type="text/javascript">

	


	$(function() {

		$(".aclick").click(function() {
			var str = $(this).next().css("display");
			if (str == "none") {
				$(this).next().css("display", "block");
			} else {
				$(this).next().css("display", "none");
			}
		})

	})
</script>
<script type="text/javascript">
	
</script>

</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-lg-8" id="container">
				<div id="result">
					<h1>
						&nbsp;留言板&nbsp;<small>&nbsp;&nbsp;&nbsp;来都来了，说点什么呗~</small>
					</h1>
					<hr class="My_hr1" />

					<!-- 是要被隐藏的 -->
					<div class="row MY_divbordertop">
						<c:if test="${ empty user }">
							<div class="MY_div1">
								<button type="button" onclick="tologinpage()"
									class="btn btn-primary">留言前先登录一下！</button>

							</div>

						</c:if>

						<c:if test="${ !empty user }">
							<div class="form-group">

								<textarea name="messagetext" class="MY_textarea"
									id="messagetext" cols="100" rows="3"></textarea>

							</div>
							<div class="form-group MY_divright">
								<button type="button"
									onclick="addMessage(${user.id},'${user.name}')"
									class="btn btn-success">留言</button>
							</div>

						</c:if>

					</div>

					<h3>
						<span class="glyphicon glyphicon-list-alt" aria-hidden="true" />&nbsp;最新留言
					</h3>
					<!-- 遍历的每一行 -->
					<div id="messagelist"></div>
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