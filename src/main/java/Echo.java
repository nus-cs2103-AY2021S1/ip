import java.util.ArrayList;

public class Echo {
    private ArrayList<Task> tasks;
    private boolean shouldExit;

    Echo() {
        this.tasks = new ArrayList<>();
        this.shouldExit = false;
    }

    public void addTask(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
    }

    public String replyUser() {
        Task task = this.tasks.get(this.tasks.size() - 1);
        String command = task.getDescription();
        if (command.equals("bye")) {
            this.shouldExit = true;
            return Exit.getExitMessage();
        } else if (command.equals("list")) {
            tasks.remove(tasks.size() - 1);
            String response = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                response += String.format("%d. %s%n", i + 1, tasks.get(i).toString());
            }
            return response;
        } else if (command.split(" ")[0].equals("done")) {
            tasks.remove(tasks.size() - 1);
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task t = tasks.get(taskNum);
            return t.markAsDone();
        } else {
            return String.format("added: %s%n", command);
        }
    }

    public boolean toExit() {
        return this.shouldExit;
    }
}
