function populateListTweet() {

    var progressBar = "<div class='progress-bar progress-bar-striped active'"+
    "role='progressbar' style='width: 100%'>Loading tweets";
    $(".progress").html(progressBar);

    var jqxhr = $
        .ajax({
            url: "tweeting",
            type: 'GET'
            //data : {query : $("#search-tweet").val()}
        })
        .done(
        function (msg) {
            console.log(msg);
            var html = "";

            msg = msg.substring(1, msg.length-1);
            var tweets = msg.split(",");
            console.log(msg);

            for (var tweet in tweets) {
                console.log(tweets[tweet]);

                html += "<div class='row tweet'>"+ tweets[tweet]
                +"</div>";
            }
            $("#listTweet").html(html);
        });
    return jqxhr;
}


function pwdCheck() {
    var spanPwdCheck = $("#identicalPwd");
    var submitNewUser = $("#submitNewUser");

    if ($("#pwd2").val() != $("#pwd1").val()) {
        spanPwdCheck.removeClass("text-success").addClass("text-danger");
        spanPwdCheck.text("Mot de passe different");
        submitNewUser.addClass("disabled");
    } else {
        spanPwdCheck.removeClass("text-danger").addClass("text-success");
        spanPwdCheck.text("Mot de passe identique");
        submitNewUser.removeClass("disabled");
    }
}