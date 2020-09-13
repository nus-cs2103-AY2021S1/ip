package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;



/**
 * Saves and stores tasks that user has input.
 */
public class Storage {
    private String database;
    private List<Task> tasks = new ArrayList<>();

    /**
     * Reads the text file 'database.txt' which contains the saved tasks user input previously.
     *
     * @return List of tasks which user had from the last time they used the program.
     * @throws DukeException throw when error occurs.
     */
    public List<Task> readFile() throws DukeException {
        String currentDirectory = System.getProperty("user.dir");
        File dataFolder = new File(currentDirectory + File.separator + "data");
        boolean directoryExists = dataFolder.exists() && dataFolder.isDirectory();
        if (directoryExists) {
            File database = new File(dataFolder.getAbsolutePath() + File.separator + "database.txt");
            try {
                Scanner s = new Scanner(database);
                while (s.hasNext()) {
                    String task = s.nextLine();
                    getSavedTasks(task);
                }
                this.database = database.getAbsolutePath();
                return tasks;
            } catch (FileNotFoundException e) {
                throw new DukeException("No file found sorry please make one");
            }
        } else {
            dataFolder.mkdir();
            File database = new File(dataFolder.getAbsolutePath() + File.separator + "database.txt");
            try {
                database.createNewFile();
                this.database = database.getAbsolutePath();
                return tasks;
            } catch (IOException e) {
                throw new DukeException("File already exists");
            }
        }
    }

    private void getSavedTasks(String s) {
        Task t;
        String[] descriptions = s.split("\\|");
        assert descriptions[0].equals("T")
                || descriptions[0].equals("D")
                || descriptions[0].equals("E")
                : "Task type does not exist.";
        if (descriptions[0].equals("T")) {
            t = new Todo(descriptions[2]);
            if (descriptions[1].equals("T")) {
                t.markAsDone();
            }
            tasks.add(t);
        } else if (descriptions[0].equals("D")) {
            t = new Deadline(descriptions[2], descriptions[3]);
            if (descriptions[1].equals("T")) {
                t.markAsDone();
            }
            tasks.add(t);
        } else if (descriptions[0].equals("E")) {
            t = new Event(descriptions[2], descriptions[3]);
            if (descriptions[1].equals("T")) {
                t.markAsDone();
            }
            tasks.add(t);
        }
    }

    /**
     * Writes the list of tasks into text file 'database.txt' and saves it for future use.
     *
     * @param tasks current list of tasks of user.
     * @throws IOException   throw when FileWriter operations fail.
     * @throws DukeException throw when error occurs and need to print error message.
     */
    public void saveToFile(List<Task> tasks) throws IOException, DukeException {
        assert database != null : "Path to database cannot be null";
        FileWriter fw = new FileWriter(database);
        tasks.forEach((task) -> {
            try {
                if (task instanceof Todo) {
                    if (task.getDone()) {
                        fw.write("T" + "|" + "T" + "|" + task.getDescription() + System.lineSeparator());
                    } else {
                        fw.write("T" + "|" + "F" + "|" + task.getDescription() + System.lineSeparator());
                    }
                } else if (task instanceof Deadline) {
                    if (task.getDone()) {
                        fw.write("D" + "|" + "T" + "|" + task.getDescription()
                                + "|" + ((Deadline) task).getDateorTime() + System.lineSeparator());
                    } else {
                        fw.write("D" + "|" + "F" + "|" + task.getDescription()
                                + "|" + ((Deadline) task).getDateorTime() + System.lineSeparator());
                    }
                } else if (task instanceof Event) {
                    if (task.getDone()) {
                        fw.write("E" + "|" + "T" + "|" + task.getDescription()
                                + "|" + ((Event) task).getDate() + System.lineSeparator());
                    } else {
                        fw.write("E" + "|" + "F" + "|" + task.getDescription()
                                + "|" + ((Event) task).getDate() + System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                try {
                    throw new DukeException("error while saving :(");
                } catch (DukeException dukeException) {
                    dukeException.printStackTrace();
                }
            }
        });
        fw.close();
    }
}

