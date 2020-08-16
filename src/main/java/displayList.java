import java.util.ArrayList;

public class DisplayList {
    ArrayList<Task> displayList = new ArrayList<>();

    public void addTask(String input) {
        displayList.add(new Task(input));
    }

    public void completeTask(int index){
         displayList.get(index).markDone();
    }

    public Task getTask(int index) {
        return displayList.get(index);
    }

    public String toString() {
        String output = "";
        if (displayList.size() > 0) {
            output = "Here are the tasks in your list:\n";
            for (int i = 1; i < displayList.size() + 1; i++) {
                output += String.valueOf(i) + ". " + displayList.get(i - 1).toString() + "\n";
            }
        }
        return output;
    }
}
