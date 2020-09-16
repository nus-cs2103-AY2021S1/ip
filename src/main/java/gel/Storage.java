package gel;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import gel.task.Deadline;
import gel.task.Event;
import gel.task.Task;
import gel.task.Todo;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final String filePath;
    private final String directoryPath;

    /**
     * Constructs Storage class at saves data at given filepath.
     *
     * @param filePath ensures that tasks are stored in the file path.
     */
    public Storage(String filePath) {
        int fileIndex = filePath.lastIndexOf("/");
        directoryPath = filePath.substring(0, fileIndex);
        this.filePath = filePath;
    }

    /**
     * Checks for file and directory existence.
     * If they do not exist, create new file/directory.
     *
     * @throws IOException when createNewFile() fails when filePath is invalid.
     */
    public void checkFileExistence() throws IOException {
        File dataDir = new File(directoryPath);
        if (!dataDir.exists()) {
            boolean isDirCreated = dataDir.mkdir();
            if (isDirCreated) {
                System.out.println("data directory successfully created");
            } else {
                System.out.println("Failed to create data directory");
            }
        }
        File file = new File(filePath);
        boolean newFileCreated = file.createNewFile();
        assert file.exists();
    }

    /**
     * Updates the storage file after user has ended the task planner.
     *
     * @param taskList the updated list of tasks after user edit.
     * @throws IOException if filePath is invalid.
     */
    public void updateFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        List<Task> listOfTask = taskList.getListOfTask();
        for (Task task : listOfTask) {
            String isDone;
            if (task.getIsDone()) {
                isDone = "1,";
            } else {
                isDone = "0,";
            }
            if (task instanceof Todo) {
                bw.write("T," + isDone + task.getDescription());
                bw.newLine();
            } else if (task instanceof Event) {
                bw.write("E," + isDone + task.getDescription() + "," + ((Event) task).getAt());
                bw.newLine();
            } else {
                assert task instanceof Deadline : "Task is of type that doesn't exists.";
                bw.write("D," + isDone + task.getDescription() + "," + ((Deadline) task).getBy());
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
    }

    /**
     * Load storage data into a <code>TaskList</code> object.
     *
     * @param ui Required to create a <code>TaskList</code> object.
     * @return <code>TaskList</code> object containing a list of tasks.
     * @throws java.io.FileNotFoundException If scanner could not detect file.
     */

    public TaskList load(Ui ui) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList(ui);
        while (sc.hasNextLine()) {
            String description = sc.nextLine();
            String[] desArr = description.split(",");
            switch (desArr[0]) {
            case "T":
                taskList.addTodoFromFile(desArr[2], Integer.parseInt(desArr[1]));
                break;
            case "D":
                taskList.addDeadlineFromFile(desArr[2], desArr[3], Integer.parseInt(desArr[1]));
                break;
            case "E":
                taskList.addEventFromFile(desArr[2], desArr[3], Integer.parseInt(desArr[1]));
                break;
            default:
                break;
            }
        }
        return taskList;
    }
}
