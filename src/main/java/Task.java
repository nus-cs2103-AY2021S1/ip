import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

abstract public class Task {
    public static List<Task> taskList = new ArrayList<>();
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public static void addTask(Task task) {
        taskList.add(task);
        saveTaskListToFile();
        Print.formatPrint(String.format("Got it. I've added this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.size(), taskList.size() > 1 ? "s": ""));
    }

    public static void deleteTask(int num) {
        Task deletedTask = taskList.get(num - 1);
        taskList.remove(num - 1);
        saveTaskListToFile();
        Print.formatPrint(String.format("Got it. I've deleted this task: \n   %s\nNow you have %d task%s in the list.",
                deletedTask, taskList.size(), taskList.size() > 1 ? "s": ""));
    }

    public static String printList() {
        String output = "Here are the tasks in your list:\n";
        int count = taskList.size();
        if (count <= 0) {
            return "There is no task in your list currently. ";
        } else {
            for (int i = 0; i < count; i++) {
                output += (i + 1) + ". " + taskList.get(i) + "\n";
            }
            return output.strip();
        }
    }

    public void markAsDone() {
        this.isDone = true;
        saveTaskListToFile();
        Print.formatPrint("Nice! I've marked this task as done: \n   " + this);
    }

    public static void markTaskAsDone(int idx) {
        taskList.get(idx - 1).markAsDone();
    }

    public static void generateTaskList(List<String> tasks) {
        for (String task : tasks) {
            if (task.length() < 1) {
                continue;
            }
            String[] taskDetail = task.split(" \\| ");
            String type = taskDetail[0];
            boolean isDone = Integer.parseInt(taskDetail[1]) == 1;
            String description = taskDetail[2];
            switch (type) {
                case "T":
                    taskList.add(new ToDo(isDone, description));
                    break;
                case "E":
                    taskList.add(new Event(isDone, description, taskDetail[3]));
                    break;
                case "D":
                    taskList.add(new Deadline(isDone, description, taskDetail[3]));
                    break;
            }
        }
    }

    abstract String getTaskDetailsForSave();

    public static void saveTaskListToFile() {
        try {
            PrintWriter writer = initialiseWriter();
            String allTasks = "";
            for (Task task : taskList) {
                allTasks += task.getTaskDetailsForSave() + "\n";
            }
            writer.print(allTasks);
            writer.close();
        } catch (DukeException e) {
            Print.formatPrint(e.getMessage());
        }
    }

    public static PrintWriter initialiseWriter() throws DukeException {
        try {
            PrintWriter writer = new PrintWriter("data/TaskList.txt");
            return writer;
        } catch (IOException e) {
            throw new DukeException("OOPS! I'm sorry, there is an error during initialisation :-(");
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
