import java.util.ArrayList;

public class Echo {
    private ArrayList<Task> tasks;
    private boolean shouldExit;

    static final String GREET = "greet";
    static final String EXIT = "bye";
    static final String LIST = "list";
    static final String DONE = "done";
    static final String TODO = "todo";
    static final String EVENT = "event";
    static final String DEADLINE = "deadline";
    static final String DELETE = "delete";

    Echo() {
        this.tasks = new ArrayList<>();
        this.shouldExit = false;
    }

    private void saveToFile() {
        try {
            SaveFile.save(this.tasks);
        } catch (DukeException e) {
            System.err.println(e);
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String replyUser() {
        int curr = this.tasks.size() - 1;
        Task task = this.tasks.get(curr);
        String type = task.getType();

        if (type.equals(GREET)) {
            this.tasks.remove(curr);
            return task.toString();
        } else if (type.equals(EXIT)) {
            this.shouldExit = true;
            this.tasks.remove(curr);
            return task.toString();
        } else if (type.equals(LIST)) {
            this.tasks.remove(curr);
            String response = curr <= 0
                ? "Here is the task in your list:\n"
                : "Here are the tasks in your list:\n";
            for (int i = 0; i < curr; i++) {
                response += String.format(
                    "%d. %s%n",
                    i + 1,
                    this.tasks.get(i).toString()
                );
            }
            return response;
        } else if (type.equals(DONE)) {
            this.tasks.remove(curr);
            Done d = (Done) task;
            Task t = tasks.get(d.getTaskNum());
            String response = t.markAsDone();
            this.saveToFile();
            return response;
        } else if (type.equals(DELETE)) {
            this.tasks.remove(curr);
            Delete del = (Delete) task;
            Task t = tasks.remove(del.getTaskNum());
            String message = "Noted. I've removed this task:\n";
            this.saveToFile();
            return message + "   " + t.toString() + "\n" + getNumTasks();
        } else {
            String message = "Got it. I've added this task:\n";
            this.saveToFile();
            return message + "   " + task.toString() + "\n" + getNumTasks();
        }
    }

    public boolean toExit() {
        return this.shouldExit;
    }

    protected String getNumTasks() {
        int numTasks = tasks.size();
        return String.format("Now you have %d %s in your list.%n", numTasks, numTasks <= 1 ? "task" : "tasks");
    }
}
