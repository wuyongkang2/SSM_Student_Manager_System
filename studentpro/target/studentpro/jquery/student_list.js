$(function () {
    loadData();
})

function loadData() { //定义数据读取的操作函数
    $.post("pages/back/student/student_list.action",{"cp":jsCommonCp,"ls":jsCommonLs},function (obj) {
        $("#studentTable tr:gt(0)").remove();
        for(var x = 0; x < obj.allStudent.length; x++){
            addRow(obj.allStudent[x].sid,obj.allStudent[x].name,obj.allStudent[x].age,obj.allStudent[x].sex,obj.allStudent[x].address);
        }
       createSplitBar(obj.allRecorders);
    },"json");
}

function addRow(sid,name,age,sex,address){
    var str = "<tr><td><input type='checkbox' class='text-center' name='sid' id='sid' value='"+sid+"'></td>" +
        "<td class='text-center'>"+sid+"</td>" +
        "<td class='text-center'>"+name+"</td>" +
        "<td class='text-center'>"+age+"</td>" +
        "<td class='text-center'>"+sex+"</td>" +
        "<td class='text-center'>"+address+"</td>" +
        "<td class='text-center'><button type='button' class='btn btn-success btn-sm'>更新</button> </td>" +
        "</tr>";
    $("#studentTable").append($(str));
}
