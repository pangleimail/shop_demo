/**页面加载时调用 */
$(document).ready(function run() {
    var phone = window.sessionStorage.getItem("phone");
    $("#indexUserMsg").html("");
    if (phone == null || phone == "") {
        $("#indexUserMsg").html("请登录");
        $("#indexUserMsg").attr("href", "login");
    } else {
        $.get(
            "/user/getHeadImg",
            {phone: phone},
            function (data) {
                var user = data;
                $("#indexUserMsg").html(user.name);
                $("#indexHeadImg").attr("src", user.headImg);
            }, "json"
        )

    }
})

$("#outLogin").click(function () {
    window.sessionStorage.removeItem("phone");
});