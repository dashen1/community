package life.yuanma.community.eunm;

public enum NotificationStatusEunm {
    UNREAD(0),
    READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEunm(int status) {
        this.status = status;
    }
}
