# 2017.06.24 10管理员登录前台页面编写
在webapp目录下建立一个pages/back的文件夹
导入相关的js和css文件
需要导入login.html页面
需要在页面中导入basepath
将forward.jsp页面拷贝到pages目录下

1. 修改login.jsp页面  
	```html
	<form role="form" action="<%=basePath%>pages/back/admin_login.action" method="post" id="loginForm">
	<fieldset>
	    <div class="form-group">
	        <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus value="ylcto@163.com">
	    </div>
	    <div class="form-group">
	        <input class="form-control" placeholder="Password" name="password" type="password" value="ylcto">
	    </div>
	    <button type="submit" class="btn btn-success btn-block">登录</button>
	</fieldset>
	</form>
	```
1. 验证登录数据是否存在
	```javascript
	$(function() {  // 在页面加载的时候执行
	    $("#loginForm").validate({ // 定义验证规则
	        debug: true,  // 采用调试模式，表单不会自动提交
	        submitHandler: function (form) {    // 当前表单对象
	            form.submit(); // 手工提交，如果不需要手工提交，可以在此处进行异步处理
	        },
	        rules: {   // 为每一个表单编写验证规则
	
	            "email": {
	                required: true,  // 此字段不允许为空
	                email:true  // 表示为email格式
	            },
	            "password": {
	                required: true,  // 此字段不允许为空
	                minlength:4
	            }
	        }
	    });
	});
	```
	基于jquery验证框架完成的验证操作相对简单，如果不会使用只需要修改admin_login.js文件即可。  