package pl.detectivegame.model;

public enum ItemType {
    ITEM('I'),
    PERSON('P');

    private Character type;

    ItemType(Character type) {
        this.type = type;
    }

    public Character getType() {
        return type;
    }
}
