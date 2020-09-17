package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import duke.exception.CannotCreateFileException;
import duke.exception.CannotMakeDirectoryException;
import duke.exception.InvalidFormatFromHardDiskException;
import duke.exception.InvalidTaskTypeException;

/**
 * Storage is used to store Tasks to the hard disk from a TaskList. It can
 * also be used to read file to input data to a TaskList.
 */
public class Storage {
    /** Root of the directory. **/
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * Converts a String from hard disk into a Task.
     *
     * @param s String from the hard disk.
     * @return A Task based on the given String.
     * @throws InvalidFormatFromHardDiskException If the input from the file has an invalid format.
     */
    private Task convertFromHardDisk(String s) throws InvalidFormatFromHardDiskException {
        String[] data = s.split(" / ");
        assert data.length > 0;
        String taskType = data[0];
        boolean isDone = data[1].equals("1");
        String description = data[2];

        Task task;
        switch (taskType) {
        case "T":
            assert data.length == 3;
            task = new Todo(description);
            break;
        case "D": {
            assert data.length == 4;
            String date = data[3];
            task = new Deadline(description, date);
            break;
        }
        case "E": {
            assert data.length == 4;
            String date = data[3];
            task = new Event(description, date);
            break;
        }
        default:
            throw new InvalidFormatFromHardDiskException();
        }

        if (isDone) {
            task.markAsDone();
            assert task.isDone();
        }
        return task;
    }

    /**
     * Converts a Task into a String that can be stored in the hard disk.
     *
     * @param task The Task to be converted to String.
     * @return A String representation of the given Task that can be stored in the hard disk.
     * @throws InvalidTaskTypeException If the type of task in the file is invalid.
     */
    String convertToHardDisk(Task task) throws InvalidTaskTypeException {
        String[] info = task.getInfo();
        assert info.length > 0;
        String taskType = info[0];
        String description = info[1];

        String isDone = task.isDone() ? "1" : "0";

        switch(taskType) {
        case "T":
            assert info.length == 2;
            return taskType + " / " + isDone + " / " + description;
        case "D":
        case "E":
            assert info.length == 3;
            return taskType + " / " + isDone + " / " + description + " / " + info[2];
        default:
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Makes directory to store the file if the directory has not been made.
     *
     * @throws CannotMakeDirectoryException If the directory cannot be made.
     */
    private void makeDirectory() throws CannotMakeDirectoryException {
        // Check directory existence.
        Path path = Paths.get(ROOT_DIRECTORY, "data");
        boolean directoryAlreadyExist = Files.exists(path);
        if (directoryAlreadyExist) {
            return;
        }

        // Make directory if needed.
        File temp = new File(path.toString());
        boolean directoryIsNotCreated = !temp.mkdir();
        if (directoryIsNotCreated) {
            throw new CannotMakeDirectoryException();
        }
    }

    /**
     * Creates file based on the path from the file object.
     *
     * @param file File object that represent the file.
     * @throws IOException If the input is invalid.
     * @throws CannotCreateFileException If the file cannot be created.
     */
    private void createFile(File file) throws IOException, CannotCreateFileException {
        // Check if existence of file.
        boolean fileAlreadyExist = file.exists();
        if (fileAlreadyExist) {
            return;
        }

        // Create file if needed.
        boolean fileIsNotCreated = !file.createNewFile();
        if (fileIsNotCreated) {
            throw new CannotCreateFileException();
        }
    }

    /**
     * Returns the file object based on the desired directory.
     *
     * @return A File object.
     */
    private File getFileObject() {
        Path path = Paths.get(ROOT_DIRECTORY, "data", "task.txt");
        return new File(path.toString());
    }

    /**
     * Converts data from file into a TaskList.
     *
     * @param file File object that represent the file.
     * @return A TaskList based on the data from the file.
     * @throws FileNotFoundException If the file does not exist.
     * @throws InvalidFormatFromHardDiskException If the input from the file has an invalid format.
     */
    private TaskList convertFileToTaskList(File file) throws FileNotFoundException,
            InvalidFormatFromHardDiskException {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            Task t = convertFromHardDisk(s);
            taskList.add(t);
        }
        return taskList;
    }

    /**
     * Reads all data from hard disk, then convert all the data into Tasks.
     *
     * @return A TaskList that contains all the Tasks from the hard disk.
     * @throws CannotMakeDirectoryException If the directory cannot be made.
     * @throws IOException If the input is invalid.
     * @throws InvalidFormatFromHardDiskException If the input from the file has an invalid format.
     * @throws CannotCreateFileException If the file cannot be created.
     */
    public TaskList readFromHardDisk() throws CannotMakeDirectoryException, IOException,
            InvalidFormatFromHardDiskException, CannotCreateFileException {
        File file = getFileObject();
        makeDirectory();
        createFile(file);
        return convertFileToTaskList(file);
    }

    /**
     * Converts a TaskList into a single String.
     *
     * @param taskList TaskList that wants to be converted.
     * @return A String that contains tasks from the given TaskList.
     * @throws InvalidTaskTypeException If the type of task in the file is invalid.
     */
    private String convertTaskListToSingleString(TaskList taskList) throws InvalidTaskTypeException {
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task t = taskList.get(i);
            text.append(convertToHardDisk(t)).append("\n");
        }
        return text.toString();
    }

    /**
     * Writes all Tasks from a TaskList to hard disk.
     *
     * @param taskList Tasklist that wants to be stored in the hard disk.
     * @throws InvalidTaskTypeException If the type of task in the file is invalid.
     * @throws IOException If the input is invalid.
     */
    public void writeToHardDisk(TaskList taskList) throws InvalidTaskTypeException, IOException {
        FileWriter fw = new FileWriter(getFileObject().toString());
        fw.write(convertTaskListToSingleString(taskList));
        fw.close();
    }
}
