import java.util.List;
import java.util.ArrayList;

public class ItemList {
    private final List<String> itemList = new ArrayList<>();

    void addItem(String item) {
        itemList.add(item);
    }

    void printList() {
        for (int i = 1; i <= itemList.size(); i++) {
            System.out.println(i + ". " + itemList.get(i - 1) + "\n");
        }
    }
}
