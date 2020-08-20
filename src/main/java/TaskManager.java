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
        } else if (command.startsWith("deadline ")) {
            addNewDeadline(command.substring(8));
        } else if (command.startsWith("event ")) {
            addNewEvent(command.substring(6));
        } else if (command.startsWith("todo ")) {
            addNewTodo(command.substring(5));
        }
    }

    public void addNewDeadline(String task) {
        String[] temp = task.split(" /by ");
        Deadline deadline = new Deadline(temp[0], temp[1]);
        taskList.add(deadline);
        System.out.println("\n Task added: " + deadline);
    }

    public void addNewEvent(String task) {
        String[] temp = task.split(" /at ");;
        Event event = new Event(temp[0], temp[1]);
        taskList.add(event);
        System.out.println("\n Task added: " + event);
    }

    public void addNewTodo(String task) {
        ToDo todo = new ToDo(task);
        taskList.add(todo);
        System.out.println("\n Task added: " + todo);
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
