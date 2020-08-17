import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> itemList = new ArrayList<>();

    void addItem(String item) {
        itemList.add(new Task(item));
    }

    void markAsDone(int itemIndex) {
        itemList.get(itemIndex - 1).markAsDone();
    }

    void printList() {
        for (int i = 1; i <= itemList.size(); i++) {
            System.out.println(i + ". " + itemList.get(i - 1));
        }
    }
}
