package pl.detectivegame.model;

public enum SuccesorType {
    ITEMS("items"),
    LOCATIONS("locations"),
    ACTIONS("actions"),
    PEOPLE("people");

    private String value;

    SuccesorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
