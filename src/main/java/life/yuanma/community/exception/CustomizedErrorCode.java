package life.yuanma.community.exception;

public enum CustomizedErrorCode implements CustomizedErrorCodeInterface{
    QUESTION_NOT_FOUND(2001,"你找的问题不在，请换个问题试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何评论或回复"),
    NOT_LOGIN(2003,"请登录后重试！"),
    SERVER_ERROR(2004,"服务器异常！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FIND(2006,"你回复的评论不存在"),
    CONTENT_IS_EMPTY(2007,"评论内容不能为空");
    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    CustomizedErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }


//    CustomizedErrorCode(String message) {
//        this.message = message;
//    }
}
