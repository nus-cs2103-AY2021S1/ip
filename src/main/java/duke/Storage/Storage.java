package duke.Storage;
import duke.Task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Storage class is used to interact with the File System and gather the save data from the last use session.
 */
public class Storage {
    private String currentDir = System.getProperty("user.dir");
    private Path dataDir = Paths.get(currentDir, "src", "main", "data");
    private Path fileDir = Paths.get(currentDir, "src", "main", "data", "list.txt");

    private static final Pattern pattern = Pattern.compile("^\\[(.)]\\s\\[(.)]\\s(.*?)(?:\\s/..\\s(.*))?$");

    /**
     * Checks if the /data directory exists.
     * @return True if it exists, False otherwise.
     */
    private boolean dirExists() {
        return Files.exists(dataDir);
    }

    /**
     * Checks if the /data/list.txt file exists.
     * @return True if it exists, False otherwise.
     */
    private boolean fileExists() {
        return Files.exists(fileDir);
    }

    /**
     * Private method to create the list.txt file in the /data directory.
     * @return list.txt File
     */
    private File createFile() {
        File newFile = new File(dataDir.toString(), "list.txt");
        System.out.println("Creating File...");
        try {
            newFile.createNewFile();
            return newFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Private method that gets the list.txt file if present, else a new
     * file is created.
     * @return list.txt File
     */
    private File getFile() {
        if (fileExists()) {
            return fileDir.toFile();
        } else if (dirExists()) {
            return createFile();
        } else {
            dataDir.toFile().mkdirs();
            return createFile();
        }
    }

    /**
     * Updates the list.txt File with the current list. If list.txt file is not
     * present, a new file is created.
     * @param list The TaskList that is used to update the list.
     * @return True if update is successful, False otherwise.
     */
    public boolean updateFile(TaskList list) {
        try {
            System.out.println("Saving changes...");
            File file = getFile();
            FileWriter writer = new FileWriter(file);
            for (Task t : list.getList()) {
                writer.write(t.getSaveString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Changes saved.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong during saving!");
            return false;
        }
    }

    /**
     * Gets the TaskList that has been saved in list.txt File.
     * @return TaskList.
     */
    public TaskList getList() {
        try {
            TaskList list = new TaskList();
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                Matcher matcher = pattern.matcher(scanner.nextLine());
                if (matcher.find()) {
                    boolean done = matcher.group(2).equals("1");
                    String task = matcher.group(3);
                    String date = matcher.group(4);
                    LocalDate localDate = null;

                    if (date != null && !date.equals("null")) {
                        localDate = LocalDate.parse(date);
                    }
                    switch (matcher.group(1)) {
                        case ("T"):
                            list.addItem(new Todo(task, done));
                            break;
                        case ("D"):
                            list.addItem(new Deadline(task, done, localDate));
                            break;
                        case ("E"):
                            list.addItem(new Event(task, done, localDate));
                            break;
                    }
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return new TaskList();
        }
    }
}
