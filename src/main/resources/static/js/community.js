// 提交回复
function post() {
    var questionId = $("#question_id").val();
    var commentContent =$("#comment_content").val();
    comment2(questionId,1,commentContent);
}


function comment2(targetId,type,commentContent) {
    if(!commentContent){
        alert("评论内容不能为空!");
        return;
    }
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentID":targetId,
            "content":commentContent,
            "type":type
        }),
        success:function (response) {
            if (response.code == 200){
                window.location.reload();
                $("#comment_close").hide();
            }else {
                if(response.code == 2003){
                    var isConfirm = confirm(response.message);
                    if(isConfirm){
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.40716b30e49a5b90&redirect_uri=http://localhost:8886/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(response.message);
                }
            }
            // console.log(response)
        },
        dataType:"json"
    });
}

function comments(e) {
    var commentId = e.getAttribute("data-id");
    var commentContent = $("#reply-"+commentId).val();
    console.log(commentId);
    console.log(commentContent);
    comment2(commentId,2,commentContent);
}

//展开评论
function commentCollapse(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
        //打开二级评论
    var collapse = e.getAttribute("comment-collapse");
    if(collapse){
        comments.removeClass("in");
        e.removeAttribute("comment-collapse");
        e.classList.remove("active");
    }else {
        var commentBody = $("#comment-" + id)
        console.log(commentBody.children().length);
        if(commentBody.children().length != 1){
            comments.addClass("in");
            e.classList.add("active");
            e.setAttribute("comment-collapse","in");
        }else{
            $.getJSON( "/comment/"+id, function( data ) {
                $.each(data.data.reverse(), function (index, comment) {
                    console.log(comment,data.data)
                    var media_lfe = $("<div />",{
                        "class":"media-left",
                    }).append($("<img/>",{
                        "class":"media-object img-rounded",
                        "src":comment.user.avatarUrl,
                    }));
                    var media_body = $("<div />",{
                        "class":"media-body",
                    }).append($("<h5/>",{
                        "class":"media-heading",
                        "html":comment.user.name,
                    })).append($("<div/>",{
                        "html":comment.content,
                    })).append($("<div/>",{
                         "class":"menu",
                    })).append($("<span/>",{
                        "class":"pull-right",
                        "html":moment(comment.gmtCreate).format("YYYY-MM-DD"),
                    }));
                    var media = $("<div/>",{
                        "class":"media",
                    }).append(media_lfe).append(media_body);

                    var com = $("<div />", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-box",
                    }).append(media);
                    commentBody.prepend(com);
                });
            });
            comments.addClass("in");
            e.classList.add("active");
            e.setAttribute("comment-collapse","in");
        }
    }
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var tagName = $("#tag").val();
    if(tagName){
        if(tagName.indexOf(value) == -1){
            $("#tag").val(tagName+","+value);
        }
    }else {
        $("#tag").val(value);
    }
}
function showPop() {
   $("#select-tag").show();
}