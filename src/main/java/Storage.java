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
    private final Path DATABASE_DIRECTORY_PATH;
    private final Path DATABASE_FILE_PATH;

    Storage(String databaseDirectoryPath) {
        this.DATABASE_DIRECTORY_PATH = Paths.get(databaseDirectoryPath);
        this.DATABASE_FILE_PATH = Paths.get(databaseDirectoryPath + "/tasks.txt");
    }

    /**
     * Loads the saved tasks of the user from /data/tasks.txt.
     * If the directory does not exist, a new folder will be created.
     *
     * @return an ArrayList containing all the tasks (if any) of the user
     * @throws DukeException if the folder to store tasks.txt cannot be created
     * @throws FileNotFoundException if tasks.txt cannot be found
     */
    public ArrayList<Task> load() throws DukeException {
        boolean directoryExists = Files.exists(this.DATABASE_DIRECTORY_PATH);
        ArrayList<Task> database = new ArrayList<>(100);

        if (!directoryExists) {
            // Create new directory
            File newFolder = new File(this.DATABASE_DIRECTORY_PATH.toString());
            boolean createdNewFolder = newFolder.mkdir();

            // If directory could not be created
            if (!createdNewFolder) {
                throw new DukeException("Could not create new directory to store saved data");
            }
        } else {
            // If the directory exists, check if tasks.txt exists
            if (Files.exists(this.DATABASE_FILE_PATH)) {
                try {
                    File f = new File(this.DATABASE_FILE_PATH.toString());
                    Scanner s = new Scanner(f);

                    while (s.hasNext()) {
                        String currentLine = s.nextLine();
                        String[] parsed = currentLine.split(" \\| ");
                        String description = parsed[2];
                        boolean taskCompletionStatus = parsed[1].equals("1");

                        switch (parsed[0]) {
                        case "T":
                            database.add(new ToDo(description, taskCompletionStatus));
                            break;

                        case "D":
                            String deadline = parsed[3];
                            database.add(new Deadline(description, taskCompletionStatus, deadline));
                            break;

                        default:
                            String eventDateTimeStart = parsed[3];
                            database.add(new Event(description, taskCompletionStatus, eventDateTimeStart));
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new DukeException("Could not find tasks.txt");
                }
            }
        }

        return database;
    }

    /**
     * Saves all the tasks of the user to data/tasks.txt.
     *
     * @throws DukeException if tasks cannot be saved to tasks.txt
     */
    public void save(ArrayList<Task> database) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.DATABASE_FILE_PATH.toString());

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
                            currentTask.getDescription() + " | " + ((Event)currentTask).getEventDateTimeStart() + "\n" );
                }
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not save tasks to database");
        }
    }
}
