package duke.merchandise;

import java.util.HashMap;

public class Merchandise {
    private final String name;
    private HashMap<String, String> collection = new HashMap<>();

    public Merchandise(String name) {
        this.name = name;
    }

    public void addMerchandise(String merchandise, String description) {
        collection.put(merchandise, description);
    }

    public void removeMerchandise(String merchandise) {
        collection.remove(merchandise);
    }

    public String printCollection() {
        String itemsInCollection = "";
        if (collection.isEmpty()) {
            return "There are no items in your collection";
        }
        for (String item: collection.keySet()) {
            itemsInCollection += item + "\n";
        }
        return itemsInCollection;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
