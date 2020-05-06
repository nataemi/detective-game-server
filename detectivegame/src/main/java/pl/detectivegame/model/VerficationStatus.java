package pl.detectivegame.model;

public enum VerficationStatus {
    VALID(0),
    NOTVALID(1);

    private int status;

    VerficationStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
