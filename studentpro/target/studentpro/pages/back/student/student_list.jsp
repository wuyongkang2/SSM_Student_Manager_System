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
        <table class="table table-bordered table-hover" id="studentTable">
            <tr>
                <th class="text-center"><input type="checkbox" name="" id="" /></th>
                <th class="text-center">学生编号</th>
                <th class="text-center">学生名字</th>
                <th class="text-center">学生年龄</th>
                <th class="text-center">学生性别</th>
                <th class="text-center">联系地址</th>
                <th class="text-center">操作</th>
            </tr>
        </table>
        <div class="text-right">
            <ul class="pagination pagination-sm" id="pagecontrol">

            </ul>
        </div>
    </div>
</div>
<jsp:include page="/pages/footer.jsp" />
<script src="jquery/jquery.validate.min.js"></script>
<script src="jquery/additional-methods.min.js"></script>
<script src="jquery/jquery.metadata.js"></script>
<script src="jquery/Message_zh_CN.js"></script>
<script src="jquery/student_list.js"></script>
<script src="jquery/util.js"></script>
</body>
</html>