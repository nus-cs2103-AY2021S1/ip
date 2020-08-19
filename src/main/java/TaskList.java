import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(String taskName) {
        Task task = new Task(taskName);
        addTask(task);
        PrintFunctions.printMessageBetweenLines("added: " + task.getName());
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
