<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/28
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>left</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script type="text/javascript" src="./js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/my_css.css"/>
</head>
<script>
$.ajax({
	url:'ArticleController?action=finHot',
	dataType:'json',
	method:'post',
	data:'',
	success:function(result){
		var list=eval(result);
		var html="";
		for(var i=0;i<list.length;i++){
			var article=list[i];
			html+="<tr><td><span class='glyphicon glyphicon-menu-right' aria-hidden='true'>"
                +"</span><a href='ArticleController?action=getById&id="+article.id+"'>"+article.title+"</a></td></tr>"              
		}
		$("#hot").append(html);
	}
});
$.ajax({
	url:'UserController?action=findMyFriends',
	dataType:'json',
	method:'post',
	data:'',
	success:function(result){
		var list=eval(result);
		var html="";
		for(var i=0;i<list.length;i++){
			var user=list[i];
			html+="<tr><td><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span><a href=''>"+user.name+"的博客</a></td></tr>";             
		}
		$("#myfriend").append(html);
	}
});
</script>
<body>
<div>
    <table class="table MY_fontSize2">
        <tbody>
        <tr>
            <td><span class="glyphicon glyphicon-fire" aria-hidden="true"/>&nbsp;热门文章</td>
        </tr>
        <table class="table my_fontsize3" id="hotarticle">
            <tbody id="hot">
            	
            </tbody>
        </table>
        </tbody>
    </table>
    <br/>
    <table class="table MY_fontSize2">
        <tbody>
        <tr>
            <td><span class="glyphicon glyphicon-send" aria-hidden="true"/>&nbsp;友情连接</td>
        </tr>
        <table class="table MY_fontSize3" id="goodfriend">
            <tbody id="myfriend">
            
            
            </tbody>
        </table>
        </tbody>
    </table>
</div>
</body>
</html>
