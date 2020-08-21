import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    List<Task> store;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.run();
    }

    public void saveToDisk() {
        try {
            new File("./data").mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/duke.txt"));
            oos.writeObject(store);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFromDisk() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/duke.txt"));
            store = (List<Task>) ois.readObject();
            listTasks();
            ois.close();
        } catch (Exception e) {
            store = new ArrayList<>();
        }
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int count = 0;
        for (Task task : store) {
            count++;
            System.out.println(String.format("%d. %s", count, task));
        }
    }

    public void doneTask(int num) {
        store.get(num - 1).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(store.get(num - 1));
    }

    public void deleteTask(int num) {
        Task task = store.get(num - 1);
        store.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list", store.size()));
    }

    public void addTaskToList(Task task) {
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list", store.size()));
    }

    public void addTask(String command, String description) throws InvalidEventException, InvalidDeadlineException {
        String splitted[];
        switch (command) {
            case "todo":
                addTaskToList(new Todo(description));
                break;
            case "deadline":
                splitted = description.split(" /by ", 2);
                if (splitted.length == 1) {
                    throw new InvalidDeadlineException();
                }
                addTaskToList(new Deadline(splitted[0], splitted[1]));
                break;
            case "event":
                splitted = description.split(" /at ", 2);
                if (splitted.length == 1) {
                    throw new InvalidEventException();
                }
                addTaskToList(new Event(splitted[0], splitted[1]));
                break;
            default:
                break;
        }

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        loadFromDisk();
        boolean running = true;
        while (running) {
            String input = scanner.nextLine().trim();
            String[] splitString = input.split(" ", 2);
            String command = splitString[0];
            int num;

            try {
                switch (command) {
                    case "bye":
                        running = false;
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (splitString.length == 1) {
                            throw new InvalidDescriptionException();
                        }
                        addTask(command, splitString[1].trim());
                        break;
                    case "done":
                        num = Integer.parseInt(splitString[1]);
                        doneTask(num);
                        break;
                    case "delete":
                        num = Integer.parseInt(splitString[1]);
                        deleteTask(num);
                        break;
                    default:
                        throw new InvalidCommandException();
                }
                saveToDisk();
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
