package duke.merchandise;

public class Merchandise {
    private final String itemName;

    public Merchandise(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String toString() {
        return this.itemName;
    }
}
