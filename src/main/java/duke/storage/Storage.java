package duke.storage;

import duke.commands.Command;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class the simulates the storing and retrieving of information into the hard-disk
 */
public class Storage {
    
    // might use it at the end
    private static final String CREATE_FILEPATH = "Creating file path ... ... ... ... ... ... ... ... ... ...";
    private static final String CREATE_CSV = "Creating file todoList.csv ... ... ... ... ... ... ... ...";
    private static final String SAVE_INFO = "Saving information ... ... ... ... ... ... ... ... ... ...";
    private static final String SAVED = "Saved  ... ... ... ... ... ... ... ... ... ... ... ... ... ";

    /**
     * Checking if a particular task is completed.
     * 
     * @param s A string encoding whether a task is completed.
     * @return Returns true if the task is completed, false otherwise.
     */
    private boolean isTaskDone(String s) {
        return !s.equals("0");
    }

    /**
     * Creates a file path.
     * 
     * @param path The directory that to be created.
     * @throws IOException
     */
    private void createFilePath(Path path) throws IOException {
        //System.out.println(String.format("%s does not exist...", path.toString()));
        Files.createDirectories(path);
        //System.out.println(CREATE_FILEPATH);
    }

    /**
     * Creates a CSV file for the user.
     * 
     * @param file The CSV file to be created.
     * @throws IOException
     */
    private void createCSV(File file) throws IOException {
        //System.out.println(CREATE_CSV);
        file.createNewFile();
    }

    /**
     * Recording down the list of tasks that the user have during this session.
     * 
     * @param file The CSV file to record down the information.
     * @param tasks Object contains the task list.
     * @throws IOException
     */
    private void savingFileInfo(File file, TaskList tasks) throws IOException {
        // System.out.println(SAVE_INFO);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < tasks.size(); i++) {
            bufferedWriter.write(tasks.get(i).formatStyling());
        }
        //System.out.println(SAVED);
        bufferedWriter.close();
    }

    /**
     * When the user exits, records the data back into the user's file.
     * 
     * @param tasks Object contains the task list.
     */

    public void record(TaskList tasks) {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "duke.Duke");
        try {
            //System.out.println(String.format("Saving file information into %s", path.toString()));
            // checking if path exist
            if (!Files.exists(path)) {
                createFilePath(path);
            }
            Path filePath = Paths.get(dir, "duke.Duke", "todoList.csv");
            File file = filePath.toFile();
            // checking if file exist
            if (!file.exists()) {
                createCSV(file);
            }
            // saving file
            savingFileInfo(file, tasks);
        } catch (IOException e) {
            Command.printErr();
        }
    }

    /**
     * Retrieves the user's data and load into the system.
     * 
     * @param tasks Object contains the task list.
     */
    public void retrieve(TaskList tasks) {
        String dir = System.getProperty("user.dir");
        Path path = Paths.get(dir, "duke.Duke", "todoList.csv");
        if (path.toFile().exists()) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);
                String line = bufferedReader.readLine();
                while (line != null) {
                    String[] info = line.split(",", 4);
                    // todo format type description done
                    // event format type at description done
                    // deadline format type by description done
                    if (Parser.isTODO(info[0])) {
                        tasks.add(new ToDo(info[1], isTaskDone(info[2])));
                    } else if (Parser.isEvent(info[0])) {
                        tasks.add(new Event(info[2], info[1], isTaskDone(info[3])));
                    } else if (Parser.isDeadline(info[0])) {
                        tasks.add(new Deadline(info[2], info[1], isTaskDone(info[3])));
                    }
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                Command.printErr();
            }
        }
    }

}
