import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke, your personal assistant!\n"
        + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private static ArrayList<Task> list;

    private static String makeWrappedString(String txt) {
        return LINE + "\n" + txt + "\n" + LINE;
    }

    private static void addToList(Task task) {
        list.add(task);
        System.out.println(LINE
            + "\nSure, I've added this task to your list:\n"
            + task
            + "\nYou now have " + list.size() + " task(s) in the list!\n"
            + LINE);
    }

    private static void makeDataFile() throws IOException {
        File file = Paths.get(".", "data", "duke.data").toFile();
        list = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    @SuppressWarnings("unchecked") //TODO: Don't do this. extract out save functionality into a method and only do that.
    public static void main(String[] args) {
        System.out.println(makeWrappedString(WELCOME_MESSAGE));

        try {
            File file = Paths.get(".", "data", "duke.data").toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<Task>) ois.readObject();
            ois.close();
            //@@author
        } catch (IOException | ClassNotFoundException e1) {
            try {
                makeDataFile();
            } catch (FileNotFoundException e2) {
                try {
                    Path datafolder = Paths.get(".", "data");
                    Files.createDirectories(datafolder);
                    makeDataFile();
                } catch (IOException e) {
                    System.out.println(e);
                }
            } catch (IOException e) {
                System.out.println("There was an error initialising your save file.\n" + e);
            }
        }

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                switch (input.split(" ")[0]) {
                case "todo":
                    if (input.length() <= 5 || input.split(" ").length < 2) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    addToList(new Todo(input.substring(5)));
                    break;
                case "deadline":
                    int indexOfBy = input.indexOf(" /by ");
                    if (input.length() <= 9 || input.split(" ").length < 2) {
                        throw new IllegalArgumentException("The description of a deadline cannot be empty.");
                    }
                    if (indexOfBy < 0 || input.substring(indexOfBy + 5).split(" ").length <= 0) {
                        throw new IllegalArgumentException(
                            "A date needs to be provided with /by when adding a deadline.\n"
                                + "Usage: deadline return book /by 2020-12-30");
                    }
                    addToList(new Deadline(input.substring(9, indexOfBy), input.substring(indexOfBy + 5)));
                    break;
                case "event":
                    int indexOfAt = input.indexOf(" /at ");
                    if (input.length() <= 6 || input.split(" ").length < 2) {
                        throw new IllegalArgumentException("The description of an event cannot be empty.");
                    }
                    if (indexOfAt < 0 || input.substring(indexOfAt + 5).split(" ").length <= 0) {
                        throw new IllegalArgumentException("A date needs to be provided with /at when adding a event.\n"
                            + "Usage: event library renewal /at 2020-12-31");
                    }
                    addToList(new Event(input.substring(6, indexOfAt), input.substring(indexOfAt + 5)));
                    break;
                case "list":
                    System.out.println(LINE + "\nHere are the tasks in your list:");
                    if (list.size() == 0) {
                        System.out.println("Your list is empty. How about adding some tasks?");
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println("" + (i + 1) + ": " + list.get(i));
                        }
                    }
                    System.out.println(LINE);
                    break;
                case "done":
                    if (input.split(" ").length < 2 || !input.split(" ")[1].matches("\\d+")) {
                        throw new IllegalArgumentException("The index of the task to be marked done must be provided.");
                    }
                    if (Integer.parseInt(input.split(" ")[1]) > list.size()) {
                        throw new IllegalArgumentException(
                            "The index provided is too large.\nRun list to see your list of tasks.");
                    }
                    if (Integer.parseInt(input.split(" ")[1]) < 1) {
                        throw new IllegalArgumentException(
                            "The index provided is too small.\nInput an integer that is 1 or greater.");
                    }
                    Task doneTask = list.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    doneTask.setDone(true);
                    System.out.println(
                        makeWrappedString("Great Job! I've marked this task as done:\n" + doneTask));
                    break;
                case "delete":
                    if (input.split(" ").length < 2 || !input.split(" ")[1].matches("\\d+")) {
                        throw new IllegalArgumentException("The index of the task to be deleted must be provided.");
                    }
                    if (Integer.parseInt(input.split(" ")[1]) > list.size()) {
                        throw new IllegalArgumentException(
                            "The index provided is too large.\nRun list to see your list of tasks.");
                    }
                    if (Integer.parseInt(input.split(" ")[1]) < 1) {
                        throw new IllegalArgumentException(
                            "The index provided is too small.\nInput an integer that is 1 or greater.");
                    }
                    Task deleted = list.remove(Integer.parseInt(input.split(" ")[1]) - 1);
                    System.out.println(LINE
                        + "\nSure, I've deleted this task from your list:\n"
                        + deleted
                        + "\nYou now have " + list.size() + " task(s) in the list.\n"
                        + LINE);
                    break;
                default:
                    throw new IllegalArgumentException("Your input must start with one of the following:\n"
                        + "\"todo\", \"deadline\", \"event\", \"list\", \"done\" or \"delete\"."
                    );
                    // break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(makeWrappedString("Error! We had a problem understanding your message.\n"
                    + e.getMessage()));
            }
            input = sc.nextLine();
        }

        try {
            File file = Paths.get(".", "data", "duke.data").toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            //@@author
        } catch (IOException e) {
            System.out.println("There was an error saving your data.\n" + e);
        }

        System.out.println(makeWrappedString(GOODBYE_MESSAGE));
    }
}
