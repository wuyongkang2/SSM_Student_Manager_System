$(function () {
    loadData();
    $("#updateForm").validate({ // 定义验证规则
        debug: true,  // 采用调试模式，表单不会自动提交
        submitHandler: function (form) {    // 当前表单对象
            // form.submit(); // 手工提交，如果不需要手工提交，可以在此处进行异步处理
            var sid = $("#ssid").text();
            var name = $("#name").val();
            var age = $("#age").val();
            var address = $("#address").val();
            var sex = $("#sex").val();
            var cid = $("#classes").val();
            $.post("pages/back/student/student_update.action",{"sid":sid,"name":name,"age":age,"address":address,"sex":sex,"classes.cid":cid},function(obj){
                if(obj.trim() == "true"){
                    $("#alertDiv").attr("class","alert alert-success");
                    $("#alertText").text("学生信息修改成功");
                    $("#sid-"+sid).text(sid);
                    $("#name-"+sid).text(name);
                    $("#age-"+sid).text(age);

                    $("#address-"+sid).text(address);

                }else{
                    $("#alertDiv").attr("class","alert alert-danger");
                    $("#alertText").text("学生信息修改失败");
                }
                $("#studentInfo").modal("hide");
                $("#alertDiv").fadeIn(2000,function () {
                    $("#alertDiv").fadeOut(2000);
                });
            },"text");
        },
        rules: {   // 为每一个表单编写验证规则
            "sid": {
                required: true,  // 此字段不允许为空
            },
            "cname": {
                required: true,  // 此字段不允许为空
            },
            "age": {
                required: true,  // 此字段不允许为空
            },
            "address": {
                required: true,  // 此字段不允许为空
            }

        }
    });
})

function loadData() { //定义数据读取的操作函数
    $.post("pages/back/student/student_list.action",{"cp":jsCommonCp,"ls":jsCommonLs},function (obj) {
        $("#studentTable tr:gt(0)").remove();
        for(var x = 0; x < obj.allStudent.length; x++){
            var sex = obj.allStudent[x].sex;
            if(sex == "1"){
                sex = "男";
            }else{
                sex = "女";
            }
            addRow(obj.allStudent[x].sid,obj.allStudent[x].name,obj.allStudent[x].age,sex,obj.allStudent[x].address,obj.allStudent[x].classes.cid);
        }
       createSplitBar(obj.allRecorders);
    },"json");
}

function addRow(sid,name,age,sex,address,cid){
    var str = "<tr><td><input type='checkbox' class='text-center' name='sid' id='sid' value='"+sid+"'></td>" +
        "<td class='text-center' id='sid-"+sid+"'>"+sid+"</td>" +
        "<td class='text-center' id='name-"+sid+"'>"+name+"</td>" +
        "<td class='text-center' id ='age-"+sid+"'>"+age+"</td>" +
        "<td class='text-center' id='sex-"+sid+"'>"+sex+"</td>" +
        "<td class='text-center' id='address-"+sid+"'>"+address+"</td>" +
        "<td class='text-center'><button type='button' class='btn btn-success btn-sm' data-toggle='modal' data-target='#studentInfo' id='"+ sid +"-"+ cid +"'>更新</button> </td>" +
        "</tr>";
    $("#studentTable").append($(str));
    $("#"+sid+"-"+cid).on("click",function () {
        $("#ssid").text(sid);
        $("#name").val(name);
        $("#age").val(age);
        $("#address").val(address);
        loadClasses(cid);
    });
}


function loadClasses(cid) {
    $.post("pages/back/classes/classes_list.action",{},function (obj) {
        $("#classes\\.cid tr:gt(0)").remove();
        alert(obj.allClasses.length);
        for (var x = 0;x <obj.allClasses.length;x++){
            if (obj.allClasses[x].cid== cid){
                $("#classes").append($("<option value='" + obj.allClasses[x].cid + "' selected>" + obj.allClasses[x].cname + "</option>"));
            }else {
                $("#classes").append($("<option value='" + obj.allClasses[x].cid + "'>" + obj.allClasses[x].cname + "</option>"));
            }
        }
    },"json");
}
