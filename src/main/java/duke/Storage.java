package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for a Storage object.
     * 
     * @param filePath filePath of the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data of the file given and returns it in an ArrayList.
     * 
     * @return An ArrayList of tasks stored in the file.
     * @throws DukeException if file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
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
                    task = new Deadline(command[2], LocalDate.parse(command[3],
                            DateTimeFormatter.ofPattern("MMM dd yyyy")));
                    break;
                case "E":
                    task = new Event(command[2], LocalDate.parse(command[3],
                            DateTimeFormatter.ofPattern("MMM dd yyyy")));
                    break;
                default:
                    throw new DukeException("Failed to load tasks");
                }
                if (command[1].equals("✓")) {
                    task.completeTask();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
    }

    /**
     * Updates the data file to store all the Tasks.
     * 
     * @param taskList A TaskList of the tasks.
     * @throws DukeException if IOException occurs.
     */
    public void write(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList.getList()) {
                writer.write(task.encode());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks");
        }
    }
}
