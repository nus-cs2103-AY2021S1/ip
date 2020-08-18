import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    TaskList() {
        taskArrayList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return taskArrayList.size() == 0;
    }

    public void addToList(Task addTask) {
        taskArrayList.add(addTask);
    }

    public String printTaskList() {
        if (this.taskArrayList.isEmpty()) {
            return "No tasks found";
        } else {
            StringBuilder output = new StringBuilder();
            String textIndentation = "     ";
            int listSize = taskArrayList.size();
            for (int i = 0; i < listSize; i++) {

                String eachTaskString = textIndentation + (i + 1) + ". " + taskArrayList.get(i).printTaskMessage() + "\n";
                output.append(eachTaskString);
            }
            return output.toString();
        }
    }
}
