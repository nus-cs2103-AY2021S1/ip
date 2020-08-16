import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String formatReply(String text) {
        String line = "\t_______________________________________________________________";
        return line + "\n\t\t" + text.replaceAll("\\n", "\n\t\t") + "\n" + line;
    }

    private static String listTasks(ArrayList<Task> tasks) {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + (i + 1) + ". " + tasks.get(i).toString()
                    + (i == tasks.size() - 1 ? "" : "\n");
        }
        return listOfTasks;
    }

    private static Task getTask(String command, Scanner input) {
        String[] commandArray = input.nextLine().split(" ");
        String description = "";
        String timing = null;

        for (int i = 1; i < commandArray.length; i++) {
            if (commandArray[i].equals("/by")) {
                timing = getTiming(commandArray, i + 1);
                break;
            } else if (commandArray[i].equals("/at")) {
                timing = getTiming(commandArray, i + 1);
                break;
            }
            if (i == 1) {
                description = commandArray[i];
            } else {
                description = description + " " + commandArray[i];
            }
        }

        switch (command) {
            case "todo":
                return new Todo(description);
            case "deadline":
                return new Deadline(description, timing);
            case "event":
                return new Event(description, timing);
            default:
                return new Task(description);
        }
    }

    private static String getTiming(String[] commandArray, int index) {
        String timing = "";
        for (int i = index; i < commandArray.length; i++) {
            if (i == index) {
                timing = commandArray[i];
            } else {
                timing = timing + " " + commandArray[i];
            }
        }
        return timing;
    }

    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.next();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(formatReply("Here are the tasks in your list:\n" + listTasks(taskList)));
            } else if (command.equals("done")) {
                Task task = taskList.get(input.nextInt() - 1);
                task.completeTask();
                ArrayList<Task> temp = new ArrayList<>();
                temp.add(task);
                System.out.println(formatReply("This task has been marked as done.\n" + listTasks(temp)));
            }
            else {
                Task newTask = getTask(command, input);
                taskList.add(newTask);
                System.out.println(formatReply("Got it. I've added this task:\n" + newTask.toString()
                        + "\nNow you have " + taskList.size() + " tasks in the list."));
            }
        }
        System.out.println(formatReply("Bye. Hope to see you again soon!"));
    }
}

class Task {
    protected String taskDescription;
    protected boolean done;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.done = false;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getDone() {
        return this.done;
    }

    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (getDone() ? "[\u2713] " : "[\u2718] ") + getTaskDescription();
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}