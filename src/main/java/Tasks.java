import java.util.ArrayList;

public class Tasks {
    protected ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    private static Todo addTodo(String input) {
        String description = input.substring(5).trim();
        return new Todo(description);
    }

    private static Event addEvent(String input) {
        int slashIndex = input.indexOf("/at");
        String description = input.substring(6, slashIndex).trim();
        String date = input.substring(slashIndex + 3).trim();
        return new Event(description, date);
    }

    private static Deadline addDeadline(String input) {
        int slashIndex = input.indexOf("/by");
        String description = input.substring(9, slashIndex).trim();
        String deadline = input.substring(slashIndex + 3).trim();
        return new Deadline(description, deadline);
    }

    protected void addTask(String command, String input) {
        Task task = new Task("");
        switch (command) {
            case "todo":
                task = addTodo(input);
                break;
            case "event":
                task = addEvent(input);
                break;
            case "deadline":
                task = addDeadline(input);
                break;
        }
        this.tasks.add(task);
        PrintDuke.printAddTask(task, this.tasks.size());
    }

    protected void markTaskAsDone(String input) {
        String taskIndexStr = input.substring(5).trim();
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        this.tasks.get(taskIndex).markAsDone();
        PrintDuke.printMarkTaskAsDone(this.tasks.get(taskIndex));
    }
}
