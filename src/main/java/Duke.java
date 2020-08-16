import javax.swing.*;
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

    public static void main(String[] args) {
        System.out.println(formatReply("Hello! I'm Duke\nWhat can I do for you?"));
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(formatReply(listTasks(taskList)));
            } else if (command.split( " ")[0].equals("done")) {
                Task task = taskList.get(Integer.parseInt(command.split(" ")[1]) - 1);
                task.completeTask();
                ArrayList<Task> temp = new ArrayList<>();
                temp.add(task);
                System.out.println(formatReply("This task has been marked as done.\n" + listTasks(temp)));
            }
            else {
                taskList.add(new Task(command));
                System.out.println(formatReply("added: " + command));
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