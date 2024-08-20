function insertUser(){
    var rName = $("#rName").val();
    var rPassword = $("#rPassword").val();
    var rPhone = $("#rPhone").val();
    if (rName == "" || rPassword == "" || rPhone == "") {
        alert("请完整填写信息！");
    } else {
        $.post(
            "/user/register",
            {name: rName, password: rPassword,phone:rPhone},
            function (data) {
                var num = data;
                if (num != 0) {
                    alert("注册成功！")
                    $(".input").val("");
                }else{
                    alert("用户注册失败！");
                }
            }, "json"
        )
    }
}