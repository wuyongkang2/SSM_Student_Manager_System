<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path +"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>优乐在线教育图书管理系统</title>
</head>
<body>
<jsp:include page="/pages/header.jsp" />
<!-- 主体内容编写 -->
<div id="page-wrapper">
    <div class="col-md-12">
        <h2 class="h2" style="text-align: center">增加学生</h2>
    </div>
    <form action="<%=basePath%>pages/back/student/student_insert.action" method="post" class="form-horizontal" id="insertForm">
        <!-- 学生编号 -->
        <div class="form-group">
            <label for="sid" class="control-label col-md-3">学生编号</label>
            <div class=col-md-5>
                <input type="text" class="form-control" name="sid" id="sid" placeholder="请输入学生编号">
            </div>
        </div>

        <!-- 班级编号 -->
        <div class="form-group">
            <label for="sid" class="control-label col-md-3">班级编号</label>
            <div class=col-md-5>
                <select name="classes.cid" id="classes.cid" class="form-control" />
            </div>
        </div>

        <!-- 学生姓名 -->
        <div class="form-group">
            <label for="name" class="control-label col-md-3">学生姓名</label>
            <div class=col-md-5>
                <input type="text" class="form-control" name="name" id="name" placeholder="请输入学生姓名">
            </div>
        </div>

        <!-- 学生年龄 -->
        <div class="form-group">
            <label for="age" class="control-label col-md-3">学生年龄</label>
            <div class=col-md-5>
                <input type="text" class="form-control" name="age" id="age" placeholder="请输入学生年龄">
            </div>
        </div>

        <!-- 学生性别 -->
        <div class="form-group">
            <label class="control-label col-md-3">学生性别</label>
            <div class=col-md-5>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex1" value="0" checked>女
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="sex2" value="1">男
                </label>
            </div>
        </div>

        <!-- 联系地址 -->
        <div class="form-group">
            <label for="address" class="control-label col-md-3">联系地址</label>
            <div class=col-md-5>
                <input type="text" class="form-control" name="address" id="address" placeholder="请输入联系地址">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4 col-md-offset-6">
                <button type="submit" class="btn btn-success btn-sm">增加</button>
                <button type="reset" class="btn btn-danger btn-sm">重置</button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/pages/footer.jsp" />
<script src="jquery/jquery.validate.min.js"></script>
<script src="jquery/additional-methods.min.js"></script>
<script src="jquery/jquery.metadata.js"></script>
<script src="jquery/Message_zh_CN.js"></script>
<script src="jquery/student_insert.js"></script>
</body>
</html>