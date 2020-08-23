import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class TaskList implements Serializable {
    private final List<Task> itemList = new ArrayList<>();

    Task addItem(String item) throws DukeException {
        String type = getItemType(item);
        Task taskToAdd = null;
        switch(type) {
            case "todo":
                if (item.trim().equals("todo")) {
                    throw new DukeException("Todos must have non-empty " +
                                                    "descriptions!");
                }

                String todoDescription = item.split("todo")[1].trim();
                taskToAdd = new Todo(todoDescription);
                break;
            case "deadline":
                taskToAdd = new Deadline(
                        getItemDescription(item, type),
                        getItemParameter(item, type)
                );
                break;
            case "event":
                taskToAdd = new Event(
                        getItemDescription(item, type),
                        getItemParameter(item, type)
                );
                break;
            default:
                throw new DukeException("Unrecognized task/command.");
        }


        itemList.add(taskToAdd);
        return taskToAdd;
    }

    //--------start of item literal parsers---------//
    String getItemType(String item) {
        return item.split(" ")[0].trim();
    }

    String getItemDescription(String item, String itemType) {
        // remove itemtype, which is the first word of the item literal
        return splitItemLiteral(item, itemType)[0]
                .split(itemType)[1]
                .trim();
    }

    String getItemParameter(String item, String itemType) {
        return splitItemLiteral(item, itemType)[1].trim();
    }

    String[] splitItemLiteral(String item, String itemType) {
        String delimiter = itemType.equals("deadline") ? "/by" : "/at";
        return item.split(delimiter);
    }
    //--------end of item literal parsers---------//

    void markAsDone(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does " +
                                                       "not exist and cannot " +
                                                       "be marked as done.");
        }
        itemList.get(itemIndex - 1).markAsDone();
    }

    void removeItem(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does " +
                                                       "not exist and cannot " +
                                                       "be removed.");
        }
        Task removed = itemList.remove(itemIndex - 1);
        System.out.println("This task has been removed: " + removed);
    }

    void printList() {
        for (int i = 1; i <= itemList.size(); i++) {
            System.out.println(i + ". " + itemList.get(i - 1));
        }
    }
}
