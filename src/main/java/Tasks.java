import java.util.ArrayList;

public class Tasks {
    protected ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    private static Todo addTodo(String input) throws EmptyTaskException {
        if (input.length() == 4) {
            throw new EmptyTaskException("description", TaskType.TODO);
        }

        String description = input.substring(5).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", TaskType.TODO);
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
            throw new EmptyTaskException("description", TaskType.EVENT);
        }

        String date = input.substring(slashIndex + 3).trim();
        if (date.length() < 1) {
            throw new EmptyTaskException("date", TaskType.EVENT);
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
            throw new EmptyTaskException("description", TaskType.DEADLINE);
        }

        String deadline = input.substring(slashIndex + 3).trim();
        if (deadline.length() < 1) {
            throw new EmptyTaskException("date", TaskType.DEADLINE);
        }

        return new Deadline(description, deadline);
    }

    protected void addTask(TaskType type, String input) throws EmptyTaskException, InvalidTaskException, UnknownInputException {
        Task task;
        switch (type) {
            case TODO:
                task = addTodo(input);
                break;
            case EVENT:
                task = addEvent(input);
                break;
            case DEADLINE:
                task = addDeadline(input);
                break;
            default:
                throw new UnknownInputException();
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
            throw new InvalidTaskNumberException("The task to be marked as done does not exist!");
        }
    }

    protected void deleteTask(String input) throws EmptyInputException, InvalidTaskNumberException {
        String taskIndexStr;
        try {
            taskIndexStr = input.substring(7).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be deleted is not specified.");
        }

        if (taskIndexStr.length() < 1) {
            throw new EmptyInputException("The task to be deleted is not specified.");
        }
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;

        try {
            Task task = this.tasks.get(taskIndex);
            this.tasks.remove(taskIndex);
            PrintDuke.printDeleteTask(task, this.tasks.size());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be deleted does not exist!");
        }
    }
}
