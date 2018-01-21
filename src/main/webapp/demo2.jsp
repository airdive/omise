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
	function c() {
		$.post("http://192.168.50.119:8080/omise/charges/accountInfo", {
			name : 'jack'
		}, function(date) {
			console.log(date);
		});
	}
</script>
</head>
<body>
	<button onclick="c()">按钮</button>
	<form name="checkoutForm" method="post"
		action="https://api.baoming.in/omise/charges/accountInfo">
		<input type="text" value="e52aa86921" name="para">
		<div class="field half first">
			<label for="name">姓名</label> <input type="text" name="name" id="name"
				required value="cc" />
		</div>
		<div class="field half ">
			<label for="country">国家</label> <input type="text" name="country"
				id="country" required value="cc" />
		</div>
		<div class="field half first">
			<label for="company">公司</label> <input type="text" name="company"
				id="company" required value="cc" />
		</div>
		<div class="field half">
			<label for="position">职位</label> <input type="text" name="position"
				id="position" required value="cc" />
		</div>
		<div class="field half first">
			<label for="telephone">电话</label> <input type="text" name="telephone"
				id="telephone" required value="cc" />
		</div>
		<div class="field half">
			<label for="email">邮箱</label> <input type="text" name="email"
				id="email" required value="571740367@qq.com" />
		</div>
		<div class="field">
			<label for="note">备注(选填)</label>
			<textarea name="note" id="note" rows="4" maxlength="500"></textarea>
		</div>
		<input type="submit" value="提交">
	</form>
</body>
</html>