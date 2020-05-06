package pl.detectivegame.model;

public enum VerficiationStatus {
    VALID(0),
    NOTVALID(1);

    private int status;

    VerficiationStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
