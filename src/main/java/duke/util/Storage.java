package duke.util;

import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the file storage for TaskList.
 */
public class Storage {
    private final String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /** Helper: creates file if it is not present in filePath.
     * @throws IOException If file dosen't exist.
     */
    public void createFileIfNotPresent() throws IOException{
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
    }

    /** Helper: Converts text line to a Task object.
     * @oaram fileString A single line string from file text
     * @return Task that corresponds to that fileString
     */
    public Task generateTaskFromFileString(String fileString) throws DukeException {
        String[] strings = fileString.split("[|]");
        String type = strings[0].trim();
        boolean isCompleted = strings[1].trim().equals("1");
        String priorityLevel = strings[2].trim();
        String desc = strings[3].trim();
        switch (type) {
            case "T":
                return new TodoTask(desc, priorityLevel, isCompleted);
            case "E":
                return new EventTask(desc, priorityLevel, strings[4].trim(), isCompleted);
            case "D":
                return new DeadlineTask(desc, priorityLevel, strings[4].trim(), isCompleted);
            default:
                throw new DukeException("unknown fileString");
        }
    }

    /** Helper: generate the Tasks based on the text file.
     * @return TaskList that contains the collection of Tasks in the storage.
     */
    public TaskList generateTaskList () throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = generateTaskFromFileString(scanner.nextLine());
                tasks.add(task);
            }
            return new TaskList(tasks);


        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }

    }

    /** Initialise the storage for duke.Duke.
     * @return TaskList that contains the collection of Tasks in the storage.
     */
    public TaskList init() throws DukeException {
        try {
            createFileIfNotPresent();
            return generateTaskList();

        } catch (IOException e) {
            throw new DukeException("Cannot update file.");
        }
    }

    /**
     * Updates the file in storage based on new task list.
     * @param taskList the list of new tasks
     * @throws IOException If file don't exist.
     */
    public void updateFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int index = 0; index < taskList.size(); index++) {
            Task task = taskList.get(index);
            fileWriter.write(task.fileString() + "\n");
        }
        fileWriter.close();
    }



}
