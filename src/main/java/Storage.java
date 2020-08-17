import java.util.ArrayList;

import java.util.ArrayList;

public class Storage {
    private ArrayList<Item> list = new ArrayList<>();

    public void addItem(Item i) {
        list.add(i);
        System.out.println("  added: " + i);
    }

    public void print() {
        int counter = 1;
        for (Item i : list) {
            System.out.println("  " + counter + ". " + i);
            counter++;
        }
    }
}