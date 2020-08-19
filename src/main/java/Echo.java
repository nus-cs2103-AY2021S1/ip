import java.util.ArrayList;

public class Echo {
    private ArrayList<Task> tasks;
    private boolean shouldExit;

    Echo() {
        this.tasks = new ArrayList<>();
        this.shouldExit = false;
    }

    public void addTask(String description) {
        Task task;
        String[] command = description.split(" ", 2);
        String type;
        String descOfTask;

        if (command.length > 1) {
            type = command[0];
            descOfTask = command[1];
        } else {
            type = "";
            descOfTask = "";
        }

        if (type.equals("todo")) {
            task = new ToDo(descOfTask);
        } else if (type.equals("event")) {
            String[] s = descOfTask.split(" /at ");
            String desc = s[0];
            String at = s[1];
            task = new Event(desc, at);
        } else if (type.equals("deadline")) {
            String[] s = descOfTask.split(" /by ");
            String desc = s[0];
            String by = s[1];
            task = new Deadline(desc, by);
        } else {
            task = new Task(description);
        }

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
            String response = tasks.size() <= 1
                ? "Here is the task in your list:\n"
                : "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                response += String.format("%d. %s%n", i + 1, tasks.get(i).toString());
            }
            return response;
        } else if (command.split(" ", 2)[0].equals("done")) {
            tasks.remove(tasks.size() - 1);
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task t = tasks.get(taskNum);
            return t.markAsDone();
        } else {
            String message1 = "Got it. I've added this task:\n";
            String taskWord = tasks.size() <= 1 ? "task" : "tasks";
            String message2 = String.format("Now you have %d %s in your list.%n", tasks.size(), taskWord);
            return message1 + "   " + task.toString() + "\n" + message2;
        }
    }

    public boolean toExit() {
        return this.shouldExit;
    }
}
