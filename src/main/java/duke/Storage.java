package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import exceptions.InvalidFileException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

/**
 * Handles the reading and writing of the data.txt file on the user's hard-disk
 */
public class Storage {
    private TaskList tasks;
    private File file;

    /**
     * Creates a new Storage object from a pre-existing file.
     * @param file The file to be read from.
     * @param tasks The TaskList object to be built.
     */
    public Storage(File file, TaskList tasks) {
        this.file = file;
        this.tasks = tasks;
    }

    /**
     * Used if the file cannot be found in the working directory
     */
    public Storage() {
        this.file = new File("data.txt");
    }

    /**
     * Reads data from a pre-existing .txt file
     * @throws FileNotFoundException If required file is not found
     */
    public void readData() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(" # ");
            String type = line[0];
            boolean done = line[1].equals("1");
            String name = line[2];
            if (type.equals("T")) {
                tasks.addItem(new Todo(name, done));
            } else {
                String time = line[3];
                LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                if (type.equals("D")) {
                    tasks.addItem(new Deadline(name, ldt, done));
                } else {
                    tasks.addItem(new Event(name, ldt, done));
                }
            }
        }
    }

    /**
     * Writes data into a .txt file
     * @param path Path to the file
     * @param text Text to be written into the file
     * @throws InvalidFileException File was not found at the end of the input path
     */
    public void writeToFile(String path, String text) throws InvalidFileException {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new InvalidFileException("File was not found");
        }
    }
}
