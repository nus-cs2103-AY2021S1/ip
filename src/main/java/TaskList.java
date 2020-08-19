import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(String command, UserCommandType userCommandType) throws UserCommands.InvalidCommandException {
        Task task;
        String[] taskComponents = UserCommands.parseTask(command);

        switch (userCommandType) {
            case TODO:
                task = new Todo(taskComponents[0]);
                break;
            case DEADLINE:
                task = new Deadline(taskComponents[0], taskComponents[1]);
                break;
            case EVENT:
                task = new Event(taskComponents[0], taskComponents[1]);
                break;
            default:
                throw new UserCommands.InvalidCommandException();
        }

        addTask(task);
        PrintFunctions.printMessagesBetweenLines(new String[] {
                "Got it. I've added this task: ",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", tasks.size())
        });
    }

    public String[] markTaskDoneAtIndex(int index) throws InvalidIndexException {
        try {
            Task task = tasks.get(index);
            task.markDone();
            return new String[] {
                    StringConstants.DONE_MESSAGE,
                    "  " + task.toString()
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public Task getTaskAtIndex(int index) throws InvalidIndexException {
        try {
            return tasks.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    public void printTaskList() {
        String[] messages = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            messages[i] = String.format("%d.%s", i + 1, task.toString());
        }
        PrintFunctions.printMessagesBetweenLines(messages);
    }

    public static class InvalidIndexException extends Exception {
        public InvalidIndexException() {
            super("Invalid task index");
        }
    }
}
