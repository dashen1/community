package life.yuanma.community.eunm;

public enum NotificationTypeEunm {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(1,"回复了评论");
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEunm(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String nameOfType(int type){
        for (NotificationTypeEunm notificationTypeEunm : NotificationTypeEunm.values()) {
            if(notificationTypeEunm.getType() == type){
                return notificationTypeEunm.getName();
            }
        }
        return "";
    }
}
