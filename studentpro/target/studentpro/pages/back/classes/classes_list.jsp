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
        <table class="table table-bordered table-hover" id="classesTable">
            <tr>
                <th>班级编号</th>
                <th>班级名称</th>
                <th>班级简介</th>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="/pages/footer.jsp" />
<script src="jquery/jquery.validate.min.js"></script>
<script src="jquery/additional-methods.min.js"></script>
<script src="jquery/jquery.metadata.js"></script>
<script src="jquery/Message_zh_CN.js"></script>
<script src="jquery/classes_list.js"></script>
</body>
</html>