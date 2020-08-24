import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    private static final String GREETING = "Hello, I'm your chatbot!\n"
            + "What can I do for you?\n";
    private static final String FAREWELL = "Sad to see you go!\n";

    List<Task> tasks;

    private void setTasks(String filename) throws IOException {
        File taskFile = new File (filename);
        File dir = new File (taskFile.getParent());
        if (dir.mkdir()) {
            System.out.println(LINES + "Setting up file paths...\n" + LINES);
        }
        if (taskFile.createNewFile()) {
            System.out.println(LINES
                    + "Uh oh, I couldn't find any saved tasks, "
                    + "so I just made a new save for you!\n"
                    + LINES);
        }
        this.tasks = this.readTasks(taskFile);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Duke session = new Duke();
            String filename = "data/tasks.txt";
            session.setTasks(filename);

            System.out.println(LINES + GREETING + LINES);

            while (sc.hasNext()) {
                try {
                    String input = sc.nextLine();
                    if (input.equals("bye")) {
                        System.out.println(LINES + FAREWELL + LINES);
                        break;
                    } else if (input.equals("list")) {
                        session.list();
                    } else if (input.startsWith("done")) {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        session.completeTask(index);
                    } else if (input.startsWith("delete")) {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        session.deleteTask(index);
                    } else {
                        session.addTask(input);
                    }
                } catch (InvalidIndexException | InvalidCommandException
                        | EmptyTaskException | MissingDateException e) {
                    System.out.println(e);
                }
            }
            sc.close();

            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            session.save(bufferedWriter);
            System.out.println(LINES + "All changes saved!\n" + LINES);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private List<Task> readTasks(File taskFile) throws FileNotFoundException {
        Scanner sc = new Scanner(taskFile);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] strings = current.split(" \\| ");
            Task task = null;
            switch (strings[0]) {
            case "T":
                task = new ToDo(strings[2]);
                break;
            case "D":
                task = new Deadline(strings[2], strings[3]);
                break;
            case "E":
                task = new Event(strings[2], strings[3]);
                break;
            default:
                System.out.println(LINES
                        + "Hmm, something's wrong with this task \n"
                        + LINES);
                break;
            }
            if (task != null) {
                if (strings[1].equals("1")) task.setDone();
                tasks.add(task);
            }
        }
        return tasks;
    }

    private void list() {
        System.out.println(LINES + "Here are the tasks in your list:");
        int number = 1;
        for (Task task : tasks) {
            System.out.println(String.format("%d.", number++) + task);
        }
        System.out.println(LINES);
    }

    private void completeTask(int index)
            throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(index);
        task.setDone();
        System.out.println(LINES
                + "Well done! The following task is complete:\n" + task
                + "\n" + LINES);
    }

    private void addTask(String task)
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

    private void deleteTask(int index)
            throws InvalidIndexException {
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

    private void save(Writer writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.print() + System.lineSeparator());
        }
        writer.close();
    }
}