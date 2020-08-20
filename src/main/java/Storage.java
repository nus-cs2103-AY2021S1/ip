import java.util.ArrayList;

public class Storage {

    private ArrayList<Task> storage = new ArrayList<>();

    public void addTodo(String name) {
        storage.add(new Task("[T]", name, ""));
    }

//    public void addDeadline(String name, String date) {
//        storage.add(new Task(type, name, date));
//    }

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
            Text.normalPrint("beri gude, finish that thing liao\n" +
                    storage.get(position - 1).toString());

        } catch (IndexOutOfBoundsException e) {
            Text.printTaskNotFoundError();
        }
    }
}
