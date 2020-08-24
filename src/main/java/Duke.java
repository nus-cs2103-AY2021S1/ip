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

import exception.InvalidDateException;
import exception.EmptyTaskException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;
import exception.MissingDateException;

public class Duke {
    private static final String LINE1 = "____________________\n";
    private static final String LINE2 = "\n____________________";
    private static final String GREETING = "Hello, I'm your chatbot!\n"
            + "What can I do for you?";
    private static final String FAREWELL = "Sad to see you go!";

    private List<Task> tasks;

    private void setTasks(String filename) throws IOException {
        File taskFile = new File (filename);
        File dir = new File (taskFile.getParent());
        if (dir.mkdir()) {
            System.out.println(LINE1 + "Setting up file paths..." + LINE2);
        }
        if (taskFile.createNewFile()) {
            System.out.println(LINE1
                    + "Uh oh, I couldn't find any saved tasks, "
                    + "so I just made a new save for you!"
                    + LINE2);
        }
        this.tasks = this.readTasks(taskFile);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Duke session = new Duke();
            String filename = "data/tasks.txt";
            session.setTasks(filename);

            System.out.println(LINE1 + GREETING + LINE2);

            while (sc.hasNext()) {
                try {
                    String input = sc.nextLine();
                    if (input.equals("bye")) {
                        System.out.println(LINE1 + FAREWELL + LINE2);
                        sc.close();
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
                } catch (InvalidIndexException | InvalidCommandException | EmptyTaskException
                        | MissingDateException | InvalidDateException e) {
                    System.out.println(e);
                }
            }

            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            session.save(bufferedWriter);
            System.out.println(LINE1 + "All changes saved!" + LINE2);
        } catch (IOException e) {
            e.printStackTrace();
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
                task = Deadline.create(strings[2], strings[3]);
                break;
            case "E":
                task = Event.create(strings[2], strings[3]);
                break;
            default:
                System.out.println(LINE1
                        + "Hmm, something's wrong with this task"
                        + LINE2);
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
        System.out.println(LINE1 + "Here are the tasks in your list:");
        int number = 1;
        for (Task task : tasks) {
            System.out.println(String.format("%d.", number++) + task);
        }
        System.out.println("____________________");
    }

    private void completeTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(index);
        task.setDone();
        System.out.println(LINE1
                + "Well done! The following task is complete:\n" + task
                + LINE2);
    }

    private void addTask(String task) throws InvalidCommandException,
            EmptyTaskException, MissingDateException, InvalidDateException {
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
        System.out.println(LINE1 +
                "Got it, I have added this task:\n"
                + newTask
                + "\nNow you have " + tasks.size() + " task(s) in this list"
                + LINE2);
    }

    private void deleteTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task removed = tasks.remove(index);
        System.out.println(LINE1
                + "Noted, I have removed the below task:\n"
                + removed
                + "\nNow you have " + tasks.size() + " task(s) left"
                + LINE2);
    }

    private void save(Writer writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.print() + System.lineSeparator());
        }
        writer.close();
    }
}