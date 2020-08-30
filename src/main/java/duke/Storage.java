package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws DukeException {
        File taskListFile = new File(filePath);
        try {
            if (!taskListFile.exists() && !taskListFile.createNewFile()) {
                throw new DukeException("Failed to create new text file.");
            }
        } catch (IOException e) {
            throw new DukeException("Exception while opening task list file: " + e);
        }

        ArrayList<Task> outputTaskList = new ArrayList<>();
        Scanner taskReader;
        try {
            taskReader = new Scanner(taskListFile);
            while (taskReader.hasNextLine()) {
                String taskFromFile = taskReader.nextLine();
                // Note, this is assuming that format of
                // task.Task.getDescriptionForDatabase() remains the same.
                String[] formattedTaskString = taskFromFile.split(" - ");
                Parser.Command taskCommand = Parser.parseCommand(formattedTaskString[0]);
                boolean isTaskDone = formattedTaskString[1].equals("1");
                switch (taskCommand) {
                    case TODO:
                        outputTaskList.add(new Todo(formattedTaskString[2], isTaskDone));
                        break;
                    case EVENT:
                        outputTaskList.add(
                                new Event(
                                        formattedTaskString[2],
                                        formattedTaskString[3],
                                        isTaskDone
                                )
                        );
                        break;
                    case DEADLINE:
                        outputTaskList.add(
                                new Deadline(
                                        formattedTaskString[2],
                                        formattedTaskString[3],
                                        isTaskDone
                                )
                        );
                        break;
                }
            }
            taskReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Exception while scanning task list file: " + e);
        }
        return outputTaskList;
    }

    /**
     * Save the current list into a file and closes the program
     */
    boolean store(TaskList taskList) throws DukeException {
        try {
            File taskListFile = new File(filePath);
            // Clear the pre-existing file if there is one.
            if (taskListFile.exists()) {
                taskListFile.delete();
                taskListFile.createNewFile();
            }
            if (taskList.getSize() > 0) {
                FileWriter taskWriter = new FileWriter(taskListFile);
                for (Task t : taskList.getTaskList()) {
                    taskWriter.write(t.getDescriptionForDatabase());
                    taskWriter.write("\n");
                }
                taskWriter.close();
            }
            return true;
        } catch (IOException e) {
            throw new DukeException("Exception occurred while storing into file: " + e);
        }
    }
}
