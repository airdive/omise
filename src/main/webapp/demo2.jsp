<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="css/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
	function c(){
		$.post("http://192.168.50.119:8080/omise/charges/accountInfo",{name:'jack'},function(date){
			console.log(date);
		});
	}
</script>
</head>
<body>
<button onclick="c()">按钮</button>
</body>
</html>