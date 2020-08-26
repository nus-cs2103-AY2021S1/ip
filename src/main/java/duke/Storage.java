package duke;

import duke.exceptions.InvalidTimeException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.nio.file.Files;

/**
 * Class that initiates the Storage. Reads the task from the hardware of the user
 * and saves the task into the hardware the the bye command is given by the user.
 */
public class Storage {

    private final Path PATH;
    private List<String> tasksInString = new ArrayList<>();
    private final static String EVENT_COMMAND = "E";
    private final static String TODO_COMMAND = "T";
    private final static String DEADLINE_COMMAND = "D";

    /**
     * Constructor which creates the file if the file is not present.
     * Throws IOException if the given path is invalid.
     *
     * @param path Path to store and read the file which contains the task list.
     */
    Storage(Path path) {
        this.PATH= path;

        try {
            // create directory if directory does not exist
            Path parentPath = path.getParent();
            Files.createDirectories(parentPath);

            // create file if file do not exist
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            this.tasksInString = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("There is an error with the path you set");
        }
    }

    /**
     * Reads the users hardware to retrieve the list of task.
     * Returns the task list in a ArrayList.
     *
     * @return taskList Task of the user stored in a ArrayList.
     * @throws InvalidTimeException If format of the time given stored in task list is wrong.
     */
    public ArrayList<Task> getCurrentTasks() throws InvalidTimeException {

        ArrayList<Task> taskList = new ArrayList<>();
        for (String line: tasksInString) {
            String[] task = line.split("#");
            String command = task[0];
            boolean isDone = task[1].equals("done");
            String description = task[2];

            if (command.equals(TODO_COMMAND)) {
                taskList.add(new ToDo(description, isDone));
            }

            if (command.equals(EVENT_COMMAND)) {
                LocalDateTime date = Time.getFormattedTime(task[3]);
                taskList.add(new Event(description, isDone, date));
            }

            if (command.equals(DEADLINE_COMMAND)) {
                LocalDateTime date = Time.getFormattedTime(task[3]);
                taskList.add(new Deadline(description, isDone, date));
            }
        }

        return taskList;
    }

    /**
     * Saves the task in a certain format in the users hardware.
     * Returns the task list in a ArrayList.
     */
    public void saveFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(PATH.toString());
            StringBuilder string = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                String currentLine = "";
                String isTaskDone = currentTask.isTaskDone() ? "done" : "notDone";
                String description = currentTask.getDescription();
                if (currentTask instanceof ToDo) {
                    currentLine += "T#" + isTaskDone + "#" + description;
                }

                if (currentTask instanceof Event) {
                    currentLine += "E#" + isTaskDone + "#" + description + "#" + ((Event) currentTask).getBy();
                }

                if (currentTask instanceof Deadline) {
                    currentLine += "D#" + isTaskDone + "#" + description + "#" + ((Deadline) currentTask).getBy();
                }

                string.append(currentLine);

                if (i != taskList.size() - 1) {
                    string.append("\n");
                }
            }
            fw.write(string.toString());
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
