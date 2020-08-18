import java.util.ArrayList;

class TaskList {

    private ArrayList<Task> taskArrayList;

    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    private boolean isEmpty() {
        return taskArrayList.size() == 0;
    }

    void addToList(Task addTask) {
        this.taskArrayList.add(addTask);
    }

    Task getTaskAt(int taskIndex) {
        return this.taskArrayList.get(taskIndex);
    }

    public String toString() {
        String textIndentation = "     ";
        if (this.isEmpty()) {
            return textIndentation + "No tasks found";
        } else {
            StringBuilder output = new StringBuilder();
            int listSize = this.taskArrayList.size();
            for (int i = 0; i < listSize; i++) {
                String eachTaskString = textIndentation + (i + 1) + ". " + this.taskArrayList.get(i) + "\n";
                output.append(eachTaskString);
            }
            return output.deleteCharAt(output.length()-1).toString();
        }
    }
}
