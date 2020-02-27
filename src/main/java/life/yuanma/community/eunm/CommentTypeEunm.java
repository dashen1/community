package life.yuanma.community.eunm;

public enum CommentTypeEunm {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    CommentTypeEunm(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExit(Integer type) {
        for(CommentTypeEunm commentTypeEunm:CommentTypeEunm.values()){
            if(commentTypeEunm.COMMENT.getType() == type){
                return true;
            }
        }
        return false;
    }
}
