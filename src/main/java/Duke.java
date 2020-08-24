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
import java.util.Scanner;

public class Duke {
    public static final String LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke, your personal assistant!\n"
        + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private static TaskList list;

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
        list = new TaskList();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    public static void main(String[] args) {
        System.out.println(makeWrappedString(WELCOME_MESSAGE));

        try {
            File file = Paths.get(".", "data", "duke.data").toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (TaskList) ois.readObject();
            ois.close();
            //@@author
        } catch (IOException | ClassNotFoundException e1) {
            try {
                makeDataFile();
            } catch (FileNotFoundException e2) {
                try {
                    Path dataFolder = Paths.get(".", "data");
                    Files.createDirectories(dataFolder);
                    makeDataFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("There was an error initialising your save file.\n" + e);
            }
        }

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        Command command = Parser.parse(input);
        while (command.getTaskType() != TaskType.BYE) {
            command.execute(list);

            input = sc.nextLine();
            command = Parser.parse(input);
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
