import java.util.ArrayList;

public class Tasks {
    protected ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    private static Todo addTodo(String input) throws EmptyTaskException {
        if (input.length() == 4) {
            throw new EmptyTaskException("description", "todo");
        }

        String description = input.substring(5).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", "todo");
        }

        return new Todo(description);
    }

    private static Event addEvent(String input) throws InvalidTaskException, EmptyTaskException {
        int slashIndex = input.indexOf("/at");
        if (slashIndex == -1) {
            throw new InvalidTaskException("The /at command cannot be found.");
        }

        String description = input.substring(6, slashIndex).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", "event");
        }

        String date = input.substring(slashIndex + 3).trim();
        if (date.length() < 1) {
            throw new EmptyTaskException("date", "event");
        }

        return new Event(description, date);
    }

    private static Deadline addDeadline(String input) throws InvalidTaskException, EmptyTaskException {
        int slashIndex = input.indexOf("/by");
        if (slashIndex == -1) {
            throw new InvalidTaskException("The /by command cannot be found.");
        }

        String description = input.substring(9, slashIndex).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", "deadline");
        }

        String deadline = input.substring(slashIndex + 3).trim();
        if (deadline.length() < 1) {
            throw new EmptyTaskException("date", "deadline");
        }

        return new Deadline(description, deadline);
    }

    protected void addTask(String command, String input) throws EmptyTaskException, InvalidTaskException {
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

    protected void markTaskAsDone(String input) throws EmptyInputException, InvalidTaskNumberException {
        String taskIndexStr;
        try {
            taskIndexStr = input.substring(5).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be marked as done is not specified.");
        }

        if (taskIndexStr.length() < 1) {
            throw new EmptyInputException("The task to be marked as done is not specified.");
        }
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;

        try {
            this.tasks.get(taskIndex).markAsDone();
            PrintDuke.printMarkTaskAsDone(this.tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException();
        }
    }
}
