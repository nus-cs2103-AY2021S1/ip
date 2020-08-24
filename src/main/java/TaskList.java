import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> listOfTask;

    public TaskList() {
        listOfTask = new ArrayList<>();
    }

    public void showListOfTask() {
        System.out.println("\n    Here are the task(s) in your list:");
        for (int i = 1; i <= listOfTask.size(); i++) {
            Task task = listOfTask.get(i - 1);
            System.out.println("    " + i + "." + task);
        }
        System.out.println();
    }

    public void doneTask(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        Task taskToBeDone = listOfTask.remove(index);
        taskToBeDone.markAsDone();
        listOfTask.add(index, taskToBeDone);
        System.out.println("\n    Nice! I've marked this task as done:");
        System.out.println("    " + taskToBeDone);
        System.out.println();
    }

    public void deleteTask(String deleteNumber) throws Exception {
        int taskNo = Integer.parseInt(deleteNumber);
        Task taskToBeDone = listOfTask.remove(taskNo - 1);
        System.out.println("\n    Noted. I've removed this task:");
        System.out.println("    " + taskToBeDone);
        System.out.println("    Now you have " + listOfTask.size() + " task(s) in the list.\n");
    }

    public void addDeadline(String input, int dateIndex) throws GelException {
        String by = input.substring(dateIndex + 4);
        LocalDateTime byDateTime = Parser.toDateTime(by);
        String description = input.substring(8, dateIndex);
        Deadline deadline = new Deadline(description, byDateTime);
        listOfTask.add(deadline);
        System.out.println("\n    Got it. I've added this task:");
        System.out.println("      " + deadline);
        System.out.println("    Now you have " + listOfTask.size() + " task(s) in the list.\n");
    }

    public void addEvent(String input, int dateIndex) throws GelException {
        String at = input.substring(dateIndex + 4);
        LocalDateTime atDateTime = Parser.toDateTime(at);
        String description = input.substring(5, dateIndex);
        Event event = new Event(description, atDateTime);
        listOfTask.add(event);
        System.out.println("\n    Got it. I've added this task:");
        System.out.println("      " + event);
        System.out.println("    Now you have " + listOfTask.size() + " task(s) in the list.\n");
    }

    public void addTodo(String input) {
        String description = input.substring(4);
        Todo todo = new Todo(description);
        listOfTask.add(todo);
        System.out.println("\n    Got it. I've added this task:");
        System.out.println("      " + todo);
        System.out.println("    Now you have " + listOfTask.size() + " task(s) in the list.\n");
    }

}
