/**页面加载时调用 */
$(document).ready(function run() {
    code_login();
})

function code_login() {
    $.post(
        "/user/verifyCode",
        function (data) {
            var num = data;
            document.getElementById("passcode").innerText = num;
        }, "json"
    )
}

function dLogin() {
    var phone = $("#tName").val();
    var tPassword = $("#tPassword").val();
    var tcode = $("#code").val();
    if (phone == "" || tPassword == "" || code == "") {
        alert("请完整填写信息！");
    } else {
        $.post(
            "/user/getUserLogin",
            {phone: phone, password: tPassword, code: tcode},
            function (data) {
                var num = data;
                if (num == 2) {
                    alert("验证码错误，请重新输入！");
                } else if (num == 1) {
                    //跳转路径
                    location.replace("index");
                    //设置sessionStorage：-----------主要代码-------------------------------
                    window.sessionStorage.setItem('phone', phone);
                } else if (num == 0) {
                    alert("用户名或密码错误！");
                }
            }, "json"
        )
    }
}