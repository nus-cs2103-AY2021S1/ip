import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String HOME = System.getProperty("user.home");
    private static Path SAVED_FILE_PATH = Paths.get(HOME, "ip", "data", "wish.txt");
    private static Path DATABASE_DIRECTORY_PATH = Paths.get(HOME, "ip", "data");

    public ArrayList<Task> loadFromDatabase() throws WishException {
        boolean directoryExists = Files.exists(DATABASE_DIRECTORY_PATH);
        ArrayList<Task> database = new ArrayList<>(100);

        if (!directoryExists) {
            System.out.println("Oops! Folder where data is saved does not exists");
            File newFolder = new File(DATABASE_DIRECTORY_PATH.toString());
            boolean createdNewFolder = newFolder.mkdir();

            if (createdNewFolder) {
                System.out.println("I have created a new folder to store saved data");
            } else {
                throw new WishException("Could not create new directory to store saved data");
            }
        } else {
            if (Files.exists(SAVED_FILE_PATH)) {
                try {
                    System.out.println("I have loaded data from the database");
                    File f = new File(SAVED_FILE_PATH.toString());
                    Scanner s = new Scanner(f);

                    while (s.hasNext()) {
                        String currentLine = s.nextLine();
                        String[] parsed = currentLine.split(" \\| ");

                        switch (parsed[0]) {
                        case "T":
                            database.add(new ToDo(parsed[2], parsed[1].equals("1")));
                            break;

                        case "D":
                            database.add(new Deadline(parsed[2], parsed[1].equals("1"), parsed[3]));
                            break;

                        default:
                            database.add(new Event(parsed[2], parsed[1].equals("1"), parsed[3]));
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
            }
        }

        return database;
    }

    public void saveToDatabase(ArrayList<Task> database) {
        try {
            FileWriter fw = new FileWriter(SAVED_FILE_PATH.toString());

            for (int i = 0; i < database.size(); i++) {
                Task currentTask = database.get(i);

                if (currentTask instanceof ToDo) {
                    fw.write("T | " + (currentTask.getDoneStatus() ? "1" : "0") + " | " +
                            currentTask.getDescription() + "\n" );
                } else if (currentTask instanceof Deadline) {
                    fw.write("D | " + (currentTask.getDoneStatus() ? "1" : "0") + " | " +
                            currentTask.getDescription() + " | " + ((Deadline)currentTask).getDeadline() + "\n" );
                } else {
                    fw.write("E | " + (currentTask.getDoneStatus() ? "1" : "0") + " | " +
                            currentTask.getDescription() + " | " + ((Event)currentTask).getStartDate() + "\n" );
                }
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
