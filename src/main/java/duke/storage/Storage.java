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
    private Path listFileDir = Paths.get(currentDir, "src", "main", "data", "list.txt");
    private Path notesFileDir = Paths.get(currentDir, "src", "main", "data", "notes.txt");
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
    private boolean listFileExists() {
        return Files.exists(listFileDir);
    }

    /**
     * Checks if the /data/list.txt file exists.
     *
     * @return True if it exists, False otherwise.
     */
    private boolean notesFileExists() {
        return Files.exists(notesFileDir);
    }

    /**
     * Private method to create the list.txt file in the /data directory.
     *
     * @return list.txt File
     */
    private File createFile(String filename) throws DukeException {
        File newFile = new File(dataDir.toString(), filename);
        System.out.println("Creating File...");
        try {
            newFile.createNewFile();
            System.out.println("Save file created at: " + this.listFileDir);
            return newFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Something went wrong in creating the file!"
                    + "\nIf restarting the application still fails, in the"
                    + "\ndirectory of the DukeLauncher.jar, please create the"
                    + "\nfollowing directories: src/main/data/"
                    + "\nAfterwards, create a \"list.txt\" file and \"notes.txt\" in data"
                    + "\nif not present. Restart Duke.");
        }
    }

    /**
     * Private method that gets the list.txt file if present, else a new
     * file is created.
     *
     * @return list.txt File
     */
    private File getListFile() throws DukeException {
        if (listFileExists()) {
            return listFileDir.toFile();
        } else if (dirExists()) {
            return createFile("list.txt");
        } else {
            dataDir.toFile().mkdirs();
            return createFile("list.txt");
        }
    }

    /**
     * Private method that gets the notes.txt file if present, else a new
     * file is created.
     *
     * @return notes.txt File
     */
    private File getNotesFile() throws DukeException {
        if (notesFileExists()) {
            return notesFileDir.toFile();
        } else if (dirExists()) {
            return createFile("notes.txt");
        } else {
            dataDir.toFile().mkdirs();
            return createFile("notes.txt");
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
            File listFile = getListFile();
            File notesFile = getNotesFile();
            FileWriter listWriter = new FileWriter(listFile);
            FileWriter notesWriter = new FileWriter(notesFile);
            for (Task t : list.getList()) {
                listWriter.write(t.getSaveString());
                listWriter.write("\n");
                notesWriter.write(t.getNotesSaveString());
                notesWriter.write("\n");
            }
            listWriter.close();
            notesWriter.close();
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
            Scanner listScanner = new Scanner(this.getListFile());
            Scanner notesScanner = new Scanner(this.getNotesFile());
            while (listScanner.hasNextLine() && notesScanner.hasNextLine()) {
                Matcher matcher = pattern.matcher(listScanner.nextLine());
                String note = notesScanner.nextLine();
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
                    list.addItem(new Todo(task, done, note));
                    break;
                case ("D"):
                    list.addItem(new Deadline(task, done, localDate, note));
                    break;
                case ("E"):
                    list.addItem(new Event(task, done, localDate, note));
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
