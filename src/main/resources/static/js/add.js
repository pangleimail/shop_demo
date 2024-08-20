/**页面加载时调用 */
$(document).ready(function run() {
    getAddTypeList();
})

/**父菜单列表*/
function getAddTypeList() {
    $.get(
        "/category/getTypeList",
        {parentId: 0},
        function (data) {
            var parentList = eval(data);
            for (var i = 0; i < parentList.length; i++) {
                $("#addParentType").append("<option value=\"" + parentList[i].id + "\">" + parentList[i].name + "</option>\n")
            }
        }, "json"
    )
}

/**子菜单列表*/
function AddChangesearch() {
    var option = $("#addParentType option:selected");
    var parentId = option.val();
    // $("#addSonType");
    $("#addSonType").html("").append("<option value=\"0\">请选择分类</option>");
    $.get(
        "/category/getTypeList",
        {parentId: parentId},
        function (data) {
            var sonList = eval(data);
            for (var i = 0; i < sonList.length; i++) {
                $("#addSonType").append("<option value=\"" + sonList[i].id + "\">" + sonList[i].name + "</option>\n")
            }
        }, "json"
    )
}

/**清除下拉框条件*/
$("#delType").click(function () {
    // $("#addParentType");
    // $("#addSonType").empty();
    $("#addParentType").empty().append("<option value=\"0\">请选择分类</option>");
    $("#addSonType").empty().append("<option value=\"0\">请选择分类</option>");
    getAddTypeList();
})

/** 单选框点击事件*/
$("#forSale").click(function () {
    $("#forSale").attr('checked', true);
    $("#soldOut").attr('checked', false);
});
$("#soldOut").click(function () {
    $("#soldOut").attr('checked', true);
    $("#forSale").attr('checked', false);
});

/**添加商品*/
function addProduct() {
    var cateId = $("#addSonType").val();
    var productName = $("#productName").val();
    var price = $("#price").val();
    var stock = $("#stock").val();
    var status;
    if ($("#forSale").attr('checked') == ('checked')) {
        status = $("#forSale").val();
    } else {
        status = $("#soldOut").val();
    }
    var detail = $("#detail").val();
    var imgAddress = "../images/" + $("#imgAddress").val();
    $.post(
        "/product/addProduct",
        {
            cateId: cateId,
            name: productName,
            price: price,
            stock: stock,
            status: status,
            detail: detail,
            imgAddress: imgAddress,
        },
        function (data) {
            var flag = data;
            if (flag == 1) {
                location.replace("list");
            } else {
                alert("添加失败！");
                location.replace("list");
            }
        }, "json"
    )
}

/**图片上传点击事件*/
$("#image1").click(function () {
    $("#imageFile").trigger('click').on('change', function (event) {
        const imageFile = $("#imageFile");
        const imgAddress = $("#imgAddress");
        const file = event.target.files[0];
        const fileName = file.name;
        var formData = new FormData();
        formData.append("file", imageFile[0].files[0]);
        formData.append("fileName", fileName);
        $.ajax({
            url: "/product/fileFormData",
            data: formData,
            type: 'post',
            dataType: "json",
            contentType: false,
            processData: false,
            success: function (flag) {
                // alert(flag);
                imgAddress.val(fileName);
                imgAddress.attr("data-image", "../images/" + fileName + "");
            }
        })
    });
})
