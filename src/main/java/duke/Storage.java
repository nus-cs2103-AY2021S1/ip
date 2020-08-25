package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates data storage functionality
 */
public class Storage {
    File file;

    /**
     * Instantiates a storage item that can read and write data.
     * @param filePath the directory to store the data
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Runs upon program start-up to load data onto program.
     * @return An arrayList of existing tasks
     * @throws DukeException if file is not found
     */
    ArrayList<Task> load() throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return readContentToProgram(file);
        }  catch (IOException e) {
            throw new DukeException("File not Found");
        }
    }

    /**
     *
     * @param file path pointing to data storage file
     * @return An arrayList of existing tasks
     * @throws FileNotFoundException if file is not found
     */
    ArrayList<Task> readContentToProgram(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file); // create a Scanner using file as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while(sc.hasNext()) {
            // take the content and make an arraylist of tasks
            String dataEntry = sc.nextLine();
            String[] parsedData = dataEntry.split("\\|");

            String command = parsedData[0].toLowerCase();
            boolean isDone = Integer.parseInt(parsedData[1]) == 1;
            String description = parsedData[2];

            if (command.equals(UserCommand.TODO.getCommand())) {
                tasks.add(new ToDo(description, isDone));
            } else if (command.equals(UserCommand.DEADLINE.getCommand())) {
                String timing = parsedData[3];
                tasks.add(new Deadline(description, isDone, timing));
            } else if (command.equals(UserCommand.EVENT.getCommand())) {
                String timing = parsedData[3];
                tasks.add(new Deadline(description, isDone, timing));
            }
        }
        return tasks;
    }

    /**
     * Iterates through taskList and writes tasks to file storage
     * @param tasks the taskList containing the tasks
     */
    void writeToFile(TaskList<Task> tasks) {
        try {
            ArrayList<Task> taskArrayList = tasks.exportList();
            FileWriter fw = new FileWriter(file);
            for (Task task : taskArrayList) {
                fw.write(task.toStorageRepresentation() + System.lineSeparator()); // overwrite file
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error with writing tasks to storage");
        }
    }
}
