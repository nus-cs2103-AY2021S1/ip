package main.java;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<String> allItems;
    private final String line = "____________________________________________________________\n";

    Store() {
        this.allItems = new ArrayList<>();
    }

    public void addToStore(String item) {
        String actual_item = item.strip();
        System.out.println("Okay I have added " + actual_item + " to your list of tasks.\n" + line);
        this.allItems.add(actual_item);
    }

    public boolean printStore() {
        String printList;
        if (this.allItems.size() == 0) {
            printList = "There are no tasks added till now.\nAdd one by just typing its name.\n" + line;
        } else {
            printList = "Please finish these tasks ASAP!\n";
            for (String item: this.allItems) {
                printList = printList.concat(item + "\n");
            }
            printList = printList.concat("If you're brave enough to start,\n" + "You're strong enough to finish it!\n" + line);
        }
        System.out.println(printList);
        return true;
    }
}
