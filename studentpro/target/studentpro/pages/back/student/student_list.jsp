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
            <ul class="pagination pagination-sm" id="pagecontrol"></ul>
        </div>
        <div class="alert alert-success" id="alertDiv">
            <button class="close">&times;</button>
            <span id="alertText"></span>
        </div>
    </div>

    <div class="modal" id="studentInfo">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">修改学生信息</h3>
                </div>
                <div class="modal-body">
                    <form method="post" class="form-horizontal" id="updateForm">
                        <!-- 学生编号 -->
                        <div class="form-group">
                            <label class="control-label col-md-3">学生编号</label>
                            <div class=col-md-5>
                                <span id="ssid"></span>
                            </div>
                        </div>

                        <!-- 班级编号 -->
                        <div class="form-group">
                            <label class="control-label col-md-3">班级编号</label>
                            <div class=col-md-5>
                                <select name="classes.cid" id="classes" class="form-control" ></select>
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

                        <!-- 联系地址 -->
                        <div class="form-group">
                            <label for="address" class="control-label col-md-3">联系地址</label>
                            <div class=col-md-5>
                                <input type="text" class="form-control" name="address" id="address" placeholder="请输入联系地址">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-6">
                                <input type="hidden" name="sex" id="sex" />
                                <button type="submit" class="btn btn-success btn-sm">修改</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success btn-sm" data-dismiss="modal">关闭编辑窗口</button>
                </div>
            </div>
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