package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;



/**
 * Storage class is used to interact with the File System and gather the save data from the last use session.
 */
public class Storage {

    private static final Pattern pattern = Pattern.compile("^\\[(.)]\\s\\[(.)]\\s(.*?)(?:\\s/..\\s(.*))?$");

    private String currentDir = System.getProperty("user.dir");
    private Path dataDir = Paths.get(currentDir, "src", "main", "data");
    private Path fileDir = Paths.get(currentDir, "src", "main", "data", "list.txt");
    /**
     * Checks if the /data directory exists.
     *
     * @return True if it exists, False otherwise.
     */
    private boolean dirExists() {
        return Files.exists(dataDir);
    }

    /**
     * Checks if the /data/list.txt file exists.
     *
     * @return True if it exists, False otherwise.
     */
    private boolean fileExists() {
        return Files.exists(fileDir);
    }

    /**
     * Private method to create the list.txt file in the /data directory.
     *
     * @return list.txt File
     */
    private File createFile() throws DukeException {
        File newFile = new File(dataDir.toString(), "list.txt");
        System.out.println("Creating File...");
        try {
            newFile.createNewFile();
            System.out.println("Save file created at: " + this.fileDir);
            return newFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Something went wrong in creating the file!"
                    + "\nIf restarting the application still fails, in the"
                    + "\ndirectory of the DukeLauncher.jar, please create the"
                    + "\nfollowing directories: src/main/data/"
                    + "\nAfterwards, create a \"list.txt\" file in data."
                    + "\nRestart Duke.");
        }
    }

    /**
     * Private method that gets the list.txt file if present, else a new
     * file is created.
     *
     * @return list.txt File
     */
    private File getFile() throws DukeException {
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
     *
     * @param list The TaskList that is used to update the list.
     * @return String message of the outcome from updating save file.
     */
    public String updateFile(TaskList list) {
        assert (list != null) : "Storage - updateFile: TaskList is null!";
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
            return "Changes saved.";
        } catch (IOException e) {
            System.out.println("Something went wrong during saving!");
            return "Error in writing to the save file!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the TaskList that has been saved in list.txt File.
     *
     * @return TaskList.
     */
    public TaskList getList() {
        try {
            TaskList list = new TaskList();
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                Matcher matcher = pattern.matcher(scanner.nextLine());
                if (!matcher.find()) {
                    continue;
                }
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
                default:
                    System.out.println("Could not parse: " + matcher.group(0));
                }
            }
            return list;
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }
}
