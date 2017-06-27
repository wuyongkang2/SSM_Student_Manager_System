# 2017.06.26 17班级增加（编写页面）  

1. header.jsp

   ```html
   <html lang="zh">
   <head>
       <base href="<%=basePath%>">
       <meta charset="utf-8">
       <meta http-equiv="X-UA-Compatible" content="IE=edge">
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <meta name="description" content="">
       <meta name="author" content="">
       <title>优乐在线教育学生管理系统（WWW.YLCTO.CN）</title>
       <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
       <link href="js/metisMenu/metisMenu.min.css" rel="stylesheet">
       <link href="js/dist/css/sb-admin-2.css" rel="stylesheet">
       <link href="js/morrisjs/morris.css" rel="stylesheet">
       <link href="js/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
       <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
       <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
   </head>
   <body>

   <div id="wrapper">
       <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
           <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="index.html">学生管理系统</a>
           </div>
           </li>
           <ul class="nav navbar-top-links navbar-right">
               <li class="dropdown">
                   <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                       <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                   </a>
                   <ul class="dropdown-menu dropdown-user">
                       <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户信息</a>
                       </li>
                       <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a>
                       </li>
                       <li class="divider"></li>
                       <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                       </li>
                   </ul>
               </li>
           </ul>
           <div class="navbar-default sidebar" role="navigation">
               <div class="sidebar-nav navbar-collapse">
                   <ul class="nav" id="side-menu">
                       <li>
                           <a href="/pages/back/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 优乐在线教育</a>
                       </li>
                       <li>
                           <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 班级管理<span class="fa arrow"></span></a>
                           <ul class="nav nav-second-level">
                               <li>
                                   <a href="/pages/back/classes/classes_insert.jsp">增加班级</a>
                               </li>
                               <li>
                                   <a href="/pages/back/classes/classes_list.action">班级列表</a>
                               </li>
                           </ul>
                       </li>
                       <li>
                           <a href="#"><i class="fa fa-edit fa-fw"></i> 学生列表<span class="fa arrow"></span></a>
                           <ul class="nav nav-second-level">
                               <li>
                                   <a href="panels-wells.html">增加学生</a>
                               </li>
                               <li>
                                   <a href="buttons.html">学生列表</a>
                               </li>
                           </ul>
                       </li>
                   </ul>
               </div>
           </div>
       </nav>
   ```

2. footer.jsp

   ```html
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <%
       String path = request.getContextPath();
       String basePath = request.getScheme() + "://"
               + request.getServerName() + ":" + request.getServerPort()
               + path +"/";
   %>
   <base href="<%=basePath%>">
   </div>
   <script src="js/jquery/jquery.min.js"></script>
   <script src="js/bootstrap/js/bootstrap.min.js"></script>
   <script src="js/metisMenu/metisMenu.min.js"></script>
   <script src="js/raphael/raphael.min.js"></script>
   <script src="js/morrisjs/morris.min.js"></script>
   <script src="js/data/morris-data.js"></script>
   <script src="js/dist/js/sb-admin-2.js"></script>
   ```

3. 编写表单

   ```html
   <form action="<%=basePath%>pages/back/classes/classes_insert.action" method="post" class="form-horizontal" id="insertForm">
           <div class="form-group">
               <label for="cname" class="control-label col-md-3">班级名称</label>
               <div class=col-md-5>
                   <input type="text" class="form-control" name="cname" id="cname" placeholder="请输入与班级名称">
               </div>
           </div>
           <div class="form-group">
               <label for="cname" class="control-label col-md-3">班级名称</label>
               <div class=col-md-5>
                   <textarea class="form-control" name="note" id="note" placeholder="请输入班级简介" ></textarea>
               </div>
           </div>
           <div class="form-group">
               <div class="col-md-4 col-md-offset-6">
                   <button type="submit" class="btn btn-success btn-sm">增加</button>
                   <button type="reset" class="btn btn-danger btn-sm">重置</button>
               </div>
           </div>
       </form>
   ```

4. 编写验证

   ```javascript
   $(function() {  // 在页面加载的时候执行
       $("#insertForm").validate({ // 定义验证规则
           debug: true,  // 采用调试模式，表单不会自动提交
           submitHandler: function (form) {    // 当前表单对象
               form.submit(); // 手工提交，如果不需要手工提交，可以在此处进行异步处理
           },
           rules: {   // 为每一个表单编写验证规则

               "cname": {
                   required: true,  // 此字段不允许为空
               },
               "note": {
                   required: true,  // 此字段不允许为空
               }
           }
       });
   });
   ```

   ​


