package duke;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public static final String DEFAULT_FILEPATH = "taskmanager.txt";
    private File file;
    
    /**
     * Constructor for a Storage object.
     * 
     * @param filePath filePath of the file
     */
    public Storage(String filePath) {
        this.file = Path.of(System.getProperty("user.dir")).resolve(filePath).toFile();
    }

    /**
     * Loads the data of the file given and returns it in an ArrayList.
     * 
     * @return An ArrayList of tasks stored in the file.
     * @throws DukeException if file is not found.
     */
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] command = str.split(" \\| ");
                Task task;

                switch (command[0]) {
                case "T":
                    task = new ToDo(command[2]);
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(command[3],
                            DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    task = new Deadline(command[2], by);
                    break;
                case "E":
                    LocalDate at = LocalDate.parse(command[3],
                            DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    task = new Event(command[2], at);
                    break;
                default:
                    throw new DukeException("Failed to load tasks");
                }
                if (command[1].equals("1")) {
                    task.completeTask();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Failed to load tasks");
        }
    }

    /**
     * Updates the data file to store all the Tasks.
     * 
     * @param taskList A TaskList of the tasks.
     * @throws DukeException if IOException occurs.
     */
    public void writeFile(TaskList taskList) throws DukeException {
        try {
            new PrintWriter(file).close();
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getList()) {
                writer.write(task.encode());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks.");
        }
    }
}
