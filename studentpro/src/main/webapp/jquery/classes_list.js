$(function () {
    loadDate();
})

function loadDate() {
    $.post("pages/back/classes/classes_list.action",{},function (obj) {
        $("#classesTable tr:gt(0)").remove();
        for (var x = 0;x <obj.allClasses.length;x++){
            addRow(obj.allClasses[x].cid,obj.allClasses[x].cname,obj.allClasses[x].note);
        }
    },"json");
}

function addRow(cid,cname,note) {
    var str = "<tr>" +
        "<td>"+cid+"</td>"+
        "<td>"+cname+"</td>"+
        "<td>"+note+"</td>"
        "</tr>";
    $("#classesTable").append($(str));
}