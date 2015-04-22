function populateListTweet() {

    var progressBar = "<div class='progress-bar progress-bar-striped active'"+
    "role='progressbar' style='width: 100%'>Loading tweets";
    $(".progress").html(progressBar);

    var jqxhr = $
        .ajax({
            url: "tweeting",
            type: 'GET',
            data : {query : $("#searchInTweet").val()}
        })
        .done(
        function (msg) {
            console.log(msg);
            var html = "";

            msg = msg.substring(1, msg.length-1);
            var tweets = msg.split(",");

            for (var tweet in tweets) {
                html += "<div class='row tweet'>"+ tweets[tweet]
                +"</div>";
            }

            $("#listTweet").html(html);
        });
    return jqxhr;
}


function getStats() {
    var jqxhr = $
        .ajax({
            url: "stats",
            type: 'GET'
        })
        .done(
        function (msg) {
            console.log(msg);
            var html = "";

            msg = msg.substring(1, msg.length-1);
            var stats = msg.split(",");

            $("#nbTweets").html(stats[0]);
            $("#nbTweetsDisplayed").html(stats[1]);
            $("#nbFollower").html(stats[2]);
            $("#nbFollowing").html(stats[3]);

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


function populateListUsers() {
    var progressBar = "<div class='progress-bar progress-bar-striped active'"+
        "role='progressbar' style='width: 100%'>Loading tweets";
    $("#userList").html(progressBar);

    var jqxhr = $
        .ajax({
            url: "userList",
            type: 'GET',
            data : {query : $("#searchInUsers").val()}
        })
        .done(
        function (msg) {
            console.log(msg);
            var html = "";

            msg = msg.substring(1, msg.length-1);
            var users = msg.split(",");

            for (var user in users) {
                html += "<div class='col-xs-4 divUser'><a href='#' class='user'>"+ users[user]+"</a> <span class=\"glyphicon glyphicon-plus\" onclick=\"gonnaFollow(this, '"+users[user]+"')\"></span></div>";
            }
            $("#userList").html(html);
        });
    return jqxhr;
}

function gonnaFollow(elem, userToFollow) {

    $(elem).removeClass("glyphicon-plus");
    $(elem).addClass("glyphicon-refresh");
    console.log("gonna follow ()"+userToFollow);

    var jqxhr = $
        .ajax({
            url: "follow",
            type: 'POST',
            data : {user : userToFollow}
        })
        .done(
        function (msg) {
            $(elem).removeClass("glyphicon-refresh");
            $(elem).addClass("glyphicon-plus");
            $("#notif").html("Vous suivez maintenant " + userToFollow);
        });
    return jqxhr;
}


function displayFollower() {
    var progressBar = "<div class='progress-bar progress-bar-striped active'"+
        "role='progressbar' style='width: 100%'>Loading tweets";
    $(".progress").html(progressBar);

    var jqxhr = $
        .ajax({
            url: "follow",
            type: 'GET',
            data : {query : "follower"}
        })
        .done(
        function (msg) {
            console.log(msg);
            var html = "";

            msg = msg.substring(1, msg.length-1);
            var follower = msg.split(",");
            console.log(msg);

            if(msg === "") {
                html = "Pas de follower"
            } else {
                for (var f in follower) {
                    html += "<div class='row'>" + follower[f] + "</div>";
                }
            }

            $("#listTweet").html(html);
        });
    return jqxhr;
}

function displayFollowing() {
    var progressBar = "<div class='progress-bar progress-bar-striped active'"+
        "role='progressbar' style='width: 100%'>Loading tweets";
    $(".progress").html(progressBar);

    var jqxhr = $
        .ajax({
            url: "follow",
            type: 'GET',
            data : {query : "following"}
        })
        .done(
        function (msg) {
            console.log(msg);
            var html = "";

            msg = msg.substring(1, msg.length-1);
            var following = msg.split(",");
            console.log(msg);

            if(msg === "") {
                html = "Pas de follow"
            } else {
                for (var f in following) {
                    html += "<div class='row'>"+ following[f]+"</div>";
                }
            }

            $("#listTweet").html(html);
        });
    return jqxhr;
}
