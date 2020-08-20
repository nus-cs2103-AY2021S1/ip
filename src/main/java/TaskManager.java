import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<Task>();
    }

    public void parseCommand(String command) {
        if (command.startsWith("list")) {
            printList();
        } else if (command.startsWith("done ")) {
            String[] temp = command.split(" ");
            int doneIndex = Integer.parseInt(temp[1]) - 1;
            markTaskDone(doneIndex);;
        } else {
            addNewTask(command);
        }
    }

    public void addNewTask(String description) {
        Task task = new Task(description);
        taskList.add(task);
        System.out.println("\n Task added: " + description);
    }

    public void markTaskDone(int index) {
        Task completedTask = taskList.get(index);
        completedTask.markAsDone();
        System.out.println("The following task has been marked as done: \n" + completedTask);
    }

    public void printList() {
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            String s = "";
            for (int i = 0; i < taskList.size(); i++) {
                s = s + (i + 1) + ". " + taskList.get(i) + "\n";
            }
            System.out.println(s);
        }
    }
}
