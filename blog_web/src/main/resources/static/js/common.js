$(document).ajaxSend(function (e, xqr, op) {
    var token = localStorage.getItem("user_token");
    xqr.setRequestHeader("user_token", token)
})

function getUserOrAuthorInfo(url) {
    console.log("url:" + url);
    $.ajax({
        type: "get",
        url: url,
        success: function (result) {
            console.log("result:" +  result);
            if (result != null && result.code == 1 && result.data != null) {
                var userInfo = result.data;
                if (url.indexOf("/user/getUserInfo") >= 0) {
                    $(".container .left .card h3").text("用户名：" + userInfo.userName);
                }
                if (url.indexOf("/user/getAuthorInfo?blogId=") >= 0) {
                    $(".container .left .card h3").text("作者：" + userInfo.userName);
                }
                $(".container .left .card a").attr("href", userInfo.githubUrl);
            } else {
                alert(result.errosMsg);
            }
        }
    });
}

function logout() {
    localStorage.removeItem("user_token");
    location.href = "blog_login.html";
}
