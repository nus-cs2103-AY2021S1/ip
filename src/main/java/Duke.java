import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;

import exception.EmptyTaskException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;
import exception.MissingDateException;

public class Duke {
    private static final String LINES = "____________________\n";
    private static List<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String farewell = "Sad to see you go!\n";

        System.out.println(LINES
                + "Hello, I'm your chatbot!\n"
                + "What can I do for you?\n"
                + LINES);

        while (sc.hasNext()) {
            try{
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println(LINES + farewell + LINES);
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("done")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    completeTask(index);
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    deleteTask(index);
                } else {
                    addTask(input);
                }
            } catch (InvalidCommandException | MissingDateException | InvalidIndexException | EmptyTaskException e) {
                System.out.println(e);
            }
        }
    }

    private static void list() {
        System.out.println(LINES + "Here are the tasks in your list:");
        int number = 1;
        for (Task task : tasks) {
            System.out.println(String.format("%d.", number++) + task);
        }
        System.out.println(LINES);
    }

    private static void completeTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(index);
        task.setDone();
        System.out.println(LINES
                + "Well done! The following task is complete:\n" + task
                + "\n" + LINES);
    }

    private static void addTask(String task)
            throws InvalidCommandException, EmptyTaskException, MissingDateException {
        Task newTask;

        if (task.startsWith("todo")) {
            newTask = ToDo.create(task);
        } else if (task.startsWith("deadline")) {
            newTask = Deadline.create(task);
        } else if (task.startsWith("event")) {
            newTask = Event.create(task);
        } else {
            throw new InvalidCommandException();
        }

        tasks.add(newTask);
        System.out.println(LINES +
                "Got it, I have added this task:\n"
                + newTask
                + "\nNow you have " + tasks.size() + " task(s) in this list\n"
                + LINES);
    }

    private static void deleteTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task removed = tasks.remove(index);
        System.out.println(LINES
                + "Noted, I have removed the below task:\n"
                + removed
                + "\nNow you have " + tasks.size() + " task(s) left\n"
                + LINES);
    }
}