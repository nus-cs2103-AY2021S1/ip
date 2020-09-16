import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class serves to save users' task lists to local machines' disk
 * so that the previous run's list will be available to users for reference.
 */
public class Storage {
;
    private static final String home = "data";

    private static java.nio.file.Path path = java.nio.file.Paths.get(home, "duke.txt");

    private static boolean directoryExists = java.nio.file.Files.exists(path);

    /**
     * Retrieves stored tasks data from storage and print it for the user.
     */
    public static String read() {

        try {

            File storage = new File(home);

            if (!directoryExists) {

                String warning = ("I am sorry, but the folder does not exist yet.\n" +
                        "Let me create one for you now :).\n");

                if (storage.mkdir()) {
                    return (warning + "Folder is created");
                } else {
                    return (warning + "Folder cannot be created");
                }
            }

            File myFile = path.toFile();
            String output = "";
            int i = 0;

            if (!myFile.createNewFile()) {
                Scanner sc = new Scanner(myFile);

                while (sc.hasNextLine()) {
                    String item = sc.nextLine();
                    i ++;
                    String sequence = (i) + ".";
                    output = output + sequence + item + "\n";

                }
                sc.close();
                return (output + "\n" + "All items successfully shown!");

            } else {
                return ("I am sorry, but the file does not exist yet.\n" +
                        "Please create one.");
            }

        } catch (IOException e) {

            e.printStackTrace();
            return e.getMessage();

        }
    }

    /**
     * Writes(stores) user data to hard disk.
     *
     * @param newTaskStorage the array list to store all tasks of the user
     */
    public static void write(ArrayList<Task> newTaskStorage) {
        try {

            FileWriter fw = new FileWriter(path.toFile());
            for (Task task : newTaskStorage) {
                String[] formatter = task.toString().split(" ");

                fw.write(formatter[0] + " " +
                        formatter[1] + " " +
                        (formatter.length > 2 ? formatter[2] + " " +
                                formatter[3] + " " +
                                formatter[4]
                                : "") + "\n");

            }

            fw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
