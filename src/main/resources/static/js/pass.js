$(document).ready(function () {
    var phone = window.sessionStorage.getItem("phone");
    if (phone == null || phone == "") {
        alert("请先登录！");
    } else {
        $.get(
            "/user/getHeadImg",
            {phone: phone},
            function (data) {
                var user = data;
                $("#updateUser").val(user.name);
                $("#passImg").attr("src", user.headImg);
            }, "json"
        )

    }
})

/*上传修改后的头像*/
$("#passImage").click(function () {
    $("#imageFile").trigger('click').on('change', function (event) {
        const imageFile = $("#imageFile");
        const passImg = $("#passImg");
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
                passImg.attr("src", "../images/" + fileName + "");
            }
        })
    });
})

$("#passSub").click(function () {
    const phone = window.sessionStorage.getItem("phone");
    const passImgVal = $("#passImg").attr("src");
    const updateUser = $("#updateUser").val();
    var mpass = $("#mpass").val();
    if (mpass != "") {
        mpass = hex_md5(mpass).toUpperCase();
    }
    const newPwd1 = $("#newPwd1").val();
    const newPwd2 = $("#newPwd2").val();
    if (newPwd1 != newPwd2) {
        alert("两次密码不一致！");
        return;
    }
    // alert(passImgVal + "-----" + updateUser + "-----" + mpass + "-----" + newPwd1 + "-----" + newPwd2);
    if (phone == null || phone == "") {
        alert("请先登录！");
    } else {
        $.get(
            "/user/getHeadImg",
            {phone: phone},
            function (data) {
                var user = data;
                // alert(mpass + "---1");
                if (mpass == "") {
                    alert("请输入原密码！");
                } else if (user.password == mpass) {
                    $.post(
                        "/user/updateUser",
                        {name: updateUser, password: hex_md5(newPwd2).toUpperCase(), headImg: passImgVal, phone: phone},
                        function (data) {
                            var num = data;
                            if (num != 0) {
                                alert("信息修改成功！")
                                location.replace("list");
                            } else {
                                alert("信息修改失败！");
                            }
                        }, "json"
                    )
                } else {
                    alert("原密码不正确,请重新输入！")
                }
            }, "json"
        )
    }
})
// $("#updatePass").click(function () {
//     alert("1");
// });