import java.util.ArrayList;

public class Storage {

    private ArrayList<Task> storage = new ArrayList<>();

    public void addItem(String string) {
        storage.add(new Task(string));
    }

    public void printOut() {
        String temp = "";
        int counter = 1;
        for (Task item: storage) {
            if (counter != 1) {
                temp += "\n";
            }
            temp += counter + ". " + item.toString();
            counter++;
        }
        Text.normalPrint(temp);
    }

    public void markDone(int position) {
        try {

            storage.get(position - 1).markDone();
            Text.normalPrint("The following item has been marked as done\n" +
                    storage.get(position - 1).toString());

        } catch (IndexOutOfBoundsException e) {
            Text.printTaskNotFoundError();
        }
    }
}
