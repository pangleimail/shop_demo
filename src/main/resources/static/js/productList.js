/**页面加载时调用 */
$(document).ready(function run() {

    getTypeList();
    // alert(pageNum+"-------");
    getProductList(1);
})

function getTypeList() {
    $.get(
        "/category/getTypeList",
        {parentId: 0},
        function (data) {
            var parentList = eval(data);
            for (var i = 0; i < parentList.length; i++) {
                $("#parentType").append("<option value=\"" + parentList[i].id + "\">" + parentList[i].name + "</option>\n")
            }
        }, "json"
    )
}

function changesearch() {
    var option = $("#parentType option:selected");
    var parentId = option.val();
    $("#sonType").html("");
    $("#sonType").append("<option value=\"0\">请选择分类</option>");
    $.get(
        "/category/getTypeList",
        {parentId: parentId},
        function (data) {
            var sonList = eval(data);
            for (var i = 0; i < sonList.length; i++) {
                $("#sonType").append("<option value=\"" + sonList[i].id + "\">" + sonList[i].name + "</option>\n")
            }
        }, "json"
    )
}

/**搜索按钮*/
$("#searchPro").click(function () {
    getProductList(1);
})

/**清除下拉框条件*/
$("#delType").click(function () {
    $("#parentType").empty();
    $("#sonType").empty();
    $("#parentType").append("<option value=\"0\">请选择分类</option>");
    $("#sonType").append("<option value=\"0\">请选择分类</option>");
    getTypeList();
})

function getProductList(currentNum) {
    var parentOption = $("#parentType option:selected");
    var parentId = parentOption.val();
    var sonOption = $("#sonType option:selected");
    var cateId = sonOption.val();
    var proName = $("#proName").val();
    $("#tList").html("");
    $("#tList").append("<tr>\n" +
        "                <th width=\"100\" style=\"text-align:left; padding-left:20px;\">商品编码</th>\n" +
        "                <th width=\"10%\">商品名称</th>\n" +
        "                <th>商品类型</th>\n" +
        "                <th>价格</th>\n" +
        "                <th>库存</th>\n" +
        "                <th>状态</th>\n" +
        "                <th width=\"20%\">详情</th>\n" +
        "                <th width=\"10%\">图片</th>\n" +
        "                <th width=\"310\">操作</th>\n" +
        "            </tr>");
    $.get(
        "/product/getProductList",
        {cateId: cateId, name: proName, parentId: parentId, currentNum: currentNum, pageSize: 6},
        function (data) {
            var parentList = eval(data);
            for (var i = 0; i < parentList.length; i++) {
                $("#tList").append("<tr>\n" +
                    "<td style=\"text-align:left; padding-left:20px;\"><input type=\"checkbox\" name=\"id[]\" value=\"" + parentList[i].id + "\"/>" + (i + 1) + "</td>\n" +
                    "<td>" + parentList[i].name + "</td>\n" +
                    "<td>" + parentList[i].cateId + "</td>\n" +
                    "<td>" + parentList[i].price + "元</td>\n" +
                    "<td>" + parentList[i].stock + "</td>\n" +
                    "<td>" + (parentList[i].status == 1 ? '在售' : '下架') + "</td>\n" +
                    "<td><div style=\' width: 240px;height:50px;overflow: hidden;\'>" + parentList[i].detail + "</div></td>\n" +
                    "<td width=\"10%\"><img src=\"" + parentList[i].imgAddress + "\" alt=\"\" width=\"70\" height=\"50\"/></td>\n" +
                    "<td><div class=\"button-group\"><a class=\"button border-main\" href=\"\"><span class=\"icon-check\"></span> 查看</a>\n" +
                    "<a class=\"button border-main\" href=\"add.html\"><span class=\"icon-edit\"></span> 修改</a>\n" +
                    "<a class=\"button border-red\" href=\"javascript:void(0)\" onclick=\"return del(" + parentList[i].id + "," + currentNum + ")\">\n" +
                    "<span class=\"icon-trash-o\"></span> 删除</a></div>\n" +
                    "</td>\n" +
                    "</tr>");
            }
        }, "json"
    )

    $.get(
        "/product/getProductCount",
        {cateId: cateId, name: proName, parentId: parentId, currentNum: currentNum, pageSize: 6},
        function (data) {
            var count = data;
            $("#pageList").html("");
            $("#pageList").append("<a href=\"javascript:void(0)\" onclick=\"getProductList(1);\">首页</a><a href=\"javascript:void(0)\" onclick='getProductList(" + (currentNum == 1 ? 1 : (currentNum - 1)) + ")'>上一页</a>");
            for (var i = 0; i < count; i++) {
                $("#pageList").append("<a href=\"javascript:void(0)\" onclick='getProductList(" + (i + 1) + ")'>" + (i + 1) + "</a>");
            }
            $("#pageList").append("<a href=\"javascript:void(0)\" onclick='getProductList(" + (currentNum == count ? count : (currentNum + 1)) + ")'>下一页</a><a href=\"javascript:void(0)\" onclick=\"getProductList(" + count + ");\">尾页</a>\n");
        }, "json"
    )

}

//单个删除
function del(id, currentNum) {
    var sid = new Array();
    sid[0] = id;
    if (confirm("您确定要删除吗?")) {
        $.get(
            "/product/delProduct?idArray=" + sid,
            function (data) {
                var flag = data;
                if (flag > 0) {
                    alert("删除成功！");
                    getProductList(currentNum);
                } else {
                    alert("删除失败！");
                }
            }, "json"
        )
    }
}

//批量删除
function DelSelect() {
    var Checkbox = false;
    var ck_taskIds = [];
    $("input[name='id[]']").each(function () {
        if (this.checked == true) {
            Checkbox = true;
            ck_taskIds.push($(this).val());
        }
    });
    if (Checkbox) {
        var t = confirm("您确认要删除选中的内容吗？");
        if (t) {
            $.get(
                "/product/delProduct?idArray=" + ck_taskIds,
                function (data) {
                    var flag = data;
                    if (flag > 0) {
                        alert("删除成功！");
                        getProductList(1);
                    } else {
                        alert("删除失败！");
                    }
                }, "json"
            )
        }
    } else {
        alert("请选择您要删除的内容!");
        return false;
    }
}