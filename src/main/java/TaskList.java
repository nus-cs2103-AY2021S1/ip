import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> itemList = new ArrayList<>();

    void addItem(String item) {
        String type = getItemType(item);
        switch(type) {
            case "todo":
                itemList.add(new Todo(item.split("todo")[1].trim()));
                break;
            default:
                break;
        }
    }

    String getItemType(String item) {
        return item.split(" ")[0];
    }

    String getItemParameter(String item, String itemType) {
        String delimiter = itemType.equals("deadline") ? "/by" : "/at";
        return item.split(delimiter)[1];
    }

    void markAsDone(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does " +
                                                       "not exist and cannot " +
                                                       "be marked as done.");
        }
        itemList.get(itemIndex - 1).markAsDone();
    }

    void printList() {
        for (int i = 1; i <= itemList.size(); i++) {
            System.out.println(i + ". " + itemList.get(i - 1));
        }
    }
}
