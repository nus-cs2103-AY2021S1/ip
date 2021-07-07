package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import duke.exception.FileError;
import duke.exception.InvalidCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Represents storage object to handle the process of storing and loading data.
 *
 */
public class Storage {
    private static final String DATA_FILE_DIRECTORY = System.getProperty("user.dir")
            + (System.getProperty("user.dir").endsWith("text-ui-test") ? "/../data/" : "/data/");
    private File storageFile;

    /**
     * Creates a Storage with given filename to handle storing and loading of data.
     *
     * @param fileName Filename to load and store data.
     */
    public Storage(String fileName) {
        this.storageFile = new File(DATA_FILE_DIRECTORY + fileName);
    }

    /**
     * Loads data from the database when bot starts and update previously saved data if any.
     *
     * @param currTaskList Task list of bot.
     * @return Message after loading data.
     * @throws FileError Unable to read or create data file.
     */
    public String loadData(TaskList currTaskList) throws FileError {
        String loadingDataFileMessage = this.checkHistory();
        checkIfFileAvailable();
        readPastDataFile(currTaskList);
        if (loadingDataFileMessage.length() == 0) {
            loadingDataFileMessage = Ui.allFilesFound();
        }
        return loadingDataFileMessage;
    }

    /**
     * Checks if user has the current directory and data file to store or load data.
     * Creates the relevant directory and data file if user do not have them.
     *
     * @return Message indicating directory and/or datafile found (if applicable).
     * @throws FileError Unable to create directory or data file.
     */
    private String checkHistory() throws FileError {
        String overallHistoryMessage = "";
        overallHistoryMessage += checkDirectoryCreated();
        overallHistoryMessage += checkFileCreated();
        return overallHistoryMessage;
    }

    /**
     * Adds the new task into storage file.
     *
     * @param newTask New Task that has been added.
     * @throws FileError Unable write to file.
     */
    public void addTask(Task newTask) throws InvalidCommand, FileError {
        String retrieveTaskType = checkTaskType(newTask);
        String retrieveStorageName = getStorageName(retrieveTaskType, newTask);
        String wordsToBeWritten = retrieveStorageName + "\n";
        writeToFile(wordsToBeWritten);
    }

    /**
     * Edits any tasks in the current storage file if they have been marked as done.
     *
     * @param editedTask Task to be edited.
     * @param taskIndex Index of task in the task list.
     * @param currentList Current task list used by bot.
     * @throws FileError Unable to remove old data file.
     */
    public void editTask(Task editedTask, int taskIndex, TaskList currentList) throws FileError {
        String taskType = checkTaskType(editedTask);
        String lineToEdit = getStorageName(taskType, editedTask);
        currentList.get(taskIndex).markDone();
        String lineToChangeTo = getStorageName(taskType, editedTask);
        editTaskFromFile(lineToEdit, lineToChangeTo);
    }

    /**
     * Updates the tagged task in the storage file.
     *
     * @param editedTask Task that has been tagged.
     * @param taskIndex Index of task that has been edited in task list.
     * @param currentList Current TaskList in use by bot.
     * @param tagWord Tag word used for tagging.
     * @throws FileError Unable to read or write to storage file.
     */
    public void editTaggedTask(Task editedTask, int taskIndex, TaskList currentList, String tagWord)
            throws FileError {
        String typeOfTask = checkTaskType(editedTask);
        String lineToChangeInStorage = getStorageName(typeOfTask, editedTask);
        currentList.get(taskIndex).tagTask(tagWord);
        String lineToChangeTo = getStorageName(typeOfTask, editedTask);
        editTaskFromFile(lineToChangeInStorage, lineToChangeTo);
    }

    /**
     * Deletes task from storage file when it is removed from task list.
     *
     * @param removedTask Task to be removed.
     * @throws FileError Unable to remove old data file.
     */
    public void deleteTask(Task removedTask) throws FileError {
        String taskType = checkTaskType(removedTask);
        String lineToRemove = getStorageName(taskType, removedTask);
        deleteTaskFromFile(lineToRemove);
    }

    /**
     * Checks task type
     *
     * @param taskToBeChecked
     * @return String corresponding to task type of input Task.
     * @throws FileError If task is missing.
     */
    private String checkTaskType(Task taskToBeChecked) throws FileError {
        String taskType = "";
        if (taskToBeChecked instanceof Deadline) {
            taskType = "Deadline";
        } else if (taskToBeChecked instanceof Event) {
            taskType = "Event";
        } else if (taskToBeChecked instanceof ToDo) {
            taskType = "ToDo";
        } else {
            throw new FileError("Your Task in data file is invalid, please clear all memory.");
        }
        return taskType;
    }

    /**
     * Obtains the storage name from data file.
     *
     * @param typeOfInputTask
     * @param taskToRetrieveName
     * @return String representation of a task in storage file.
     * @throws FileError If unable to identify task type from storage file.
     */
    private String getStorageName(String typeOfInputTask, Task taskToRetrieveName) throws FileError {
        String taskStorageName = "";
        switch (typeOfInputTask) {
        case "Deadline":
            taskStorageName = ((Deadline) taskToRetrieveName).getDataStorageName();
            break;
        case "Event":
            taskStorageName = ((Event) taskToRetrieveName).getDataStorageName();
            break;
        case "ToDo":
            taskStorageName = ((ToDo) taskToRetrieveName).getDataStorageName();
            break;
        default:
            throw new FileError("Your Storage File is corrupted. Please delete it.");
        }
        return taskStorageName;
    }

    private void deleteTaskFromFile(String taskNameToBeRemoved) throws FileError {
        try {
            File removed = new File(DATA_FILE_DIRECTORY + "dataList1.txt");
            assert this.storageFile.exists() : "Storage file is lost while application is running!";
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(removed));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                if (currentLine.equals(taskNameToBeRemoved)) {
                    currentLine = reader.readLine();
                    continue;
                }
                writer.write(currentLine + "\n");
                currentLine = reader.readLine();
            }

            writer.close();
            reader.close();

            filesDeletion(removed, this.storageFile);
        } catch (IOException ex) {
            throw new FileError("Unable to read or write to file!");
        }
    }

    private void filesDeletion (File newFile, File overWrittenFile) throws FileError {
        if (overWrittenFile.delete()) {
            // Rename output file to input file
            if (!newFile.renameTo(overWrittenFile)) {
                throw new FileError("Could not rename to update data file");
            }
        } else {
            throw new FileError("Could not delete old data file");
        }
    }

    private void editTaskFromFile (String removeTaskString, String editedTaskString) throws FileError {
        try {
            File toBeDeleted = new File(DATA_FILE_DIRECTORY + "dataList1.txt");
            assert this.storageFile.exists() : "Storage file is lost while application is running!";
            BufferedReader readerBuffer = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writerBuffer = new BufferedWriter(new FileWriter(toBeDeleted));
            String readingLine = readerBuffer.readLine();
            while (readingLine != null) {
                if (readingLine.equals(removeTaskString)) {
                    writerBuffer.write(editedTaskString + "\n");
                    readingLine = readerBuffer.readLine();
                    continue;
                }
                writerBuffer.write(readingLine + "\n");
                readingLine = readerBuffer.readLine();
            }

            writerBuffer.close();
            readerBuffer.close();

            filesDeletion(toBeDeleted, this.storageFile);
        } catch (IOException ex) {
            throw new FileError("Cannot read or write to file!");
        }
    }

    private void writeToFile(String stringToBeWritten) throws FileError {
        try {
            FileWriter fw = new FileWriter(this.storageFile, true);
            fw.write(stringToBeWritten);
            fw.close();
        } catch (IOException e) {
            throw new FileError("Cannot write to file!");
        }
    }

    private String checkDirectoryCreated() {
        String messageObtained = "";
        try {
            FileReader readFile = new FileReader(DATA_FILE_DIRECTORY);
        } catch (FileNotFoundException e) {
            File newData = new File(DATA_FILE_DIRECTORY);
            if (!newData.exists()) {
                newData.mkdirs();
                messageObtained += Ui.addDirectory();
                messageObtained += "\n";
            }
        }
        return messageObtained;
    }

    private String checkFileCreated() throws FileError {
        String fileCreationMessage = "";
        try {
            if (this.storageFile.createNewFile()) {
                fileCreationMessage += Ui.addDataFile();
                fileCreationMessage += "\n";
            }
        } catch (IOException e) {
            throw new FileError("Unable to create file");
        }
        return fileCreationMessage;
    }

    private void checkIfFileAvailable() throws FileError {
        try {
            BufferedReader rb = new BufferedReader(new FileReader(this.storageFile));
        } catch (FileNotFoundException e) {
            throw new FileError("File cannot be found");
        }
    }

    private void readPastDataFile(TaskList toBeUpdatedTaskList) throws FileError {
        String newLine = "";
        try {
            assert this.storageFile.exists() : "Storage file went missing, please restart your bot!";
            BufferedReader rb = new BufferedReader(new FileReader(this.storageFile));
            newLine = rb.readLine();
            while (newLine != null) {
                String[] taskInput = newLine.split("\\|");
                if (taskInput.length == 1) {
                    break;
                }
                processTasks(taskInput, toBeUpdatedTaskList);
                newLine = rb.readLine();
            }
            rb.close();
        } catch (IOException e) {
            throw new FileError("Your storage file cannot be read");
        }
    }

    private String trimTaskName(String[] storageFileString) {
        String taskName = storageFileString[2].trim();
        if (taskName.contains("#")) {
            int indexOfTaggedWord = taskName.indexOf('#');
            return taskName.substring(0, indexOfTaggedWord - 1);
        }
        return taskName;
    }

    private boolean checkTaskStatus(String[] storageFileString) {
        int statusValue = Integer.parseInt(storageFileString[1].trim());

        assert statusValue == 1 || statusValue == 0 : "Your storage file is corrupted.";

        if (statusValue == 1) {
            return true;
        }
        return false;
    }

    private boolean checkTaskTaggedStatus(String[] storageFileString) {
        String taskName = storageFileString[2].trim();
        if (taskName.contains("#")) {
            return true;
        }
        return false;
    }

    private LocalDate getDate(String[] storageFileString) {
        assert storageFileString[3].trim().length() != 0 : "Task date is corrupted!";
        return LocalDate.parse(storageFileString[3].trim());
    }

    private void processTasks(String[] taskStorageString, TaskList toBeUpdatedTaskList) {
        assert taskStorageString.length >= 3 : "Task in storage file is missing information!";
        assert taskStorageString[2].trim().length() != 0 : "Task name is corrupted!";
        assert Integer.parseInt(taskStorageString[1].trim()) == 1
                || Integer.parseInt(taskStorageString[1].trim()) == 0 : "Task status is corrupted!";

        String trimmedTaskName = trimTaskName(taskStorageString);
        boolean isTaskDone = checkTaskStatus(taskStorageString);
        boolean isTaskTagged = checkTaskTaggedStatus(taskStorageString);
        String tagWord = getTagWord(taskStorageString);
        String taskType = checkTaskTypeFromDataFile(taskStorageString);
        if (taskType.equals("ToDo")) {
            ToDo pastToDo = new ToDo(trimmedTaskName);
            processTaskStatus(pastToDo, isTaskDone, isTaskTagged, tagWord);
            addTaskToList(pastToDo, toBeUpdatedTaskList);
        } else if (taskType.equals("Event")) {
            assert taskStorageString.length == 4 : "Task in storage file is missing information!";

            LocalDate eventDate = getDate(taskStorageString);
            Event pastEvent = new Event(trimmedTaskName, eventDate);
            processTaskStatus(pastEvent, isTaskDone, isTaskTagged, tagWord);
            addTaskToList(pastEvent, toBeUpdatedTaskList);
        } else if (taskType.equals("Deadline")) {
            assert taskStorageString.length == 4 : "Task in storage file is missing information!";

            LocalDate deadlineDate = getDate(taskStorageString);
            Deadline pastDeadline = new Deadline(trimmedTaskName,
                    deadlineDate);
            processTaskStatus(pastDeadline, isTaskDone, isTaskTagged, tagWord);
            addTaskToList(pastDeadline, toBeUpdatedTaskList);
        }
    }

    private String checkTaskTypeFromDataFile(String[] taskStorageString) {
        String taskTypeInStorage = "";
        if (taskStorageString[0].charAt(0) == 'T') {
            taskTypeInStorage = "ToDo";
        } else if (taskStorageString[0].charAt(0) == 'D') {
            taskTypeInStorage = "Deadline";
        } else if (taskStorageString[0].charAt(0) == 'E') {
            taskTypeInStorage = "Event";
        }
        return taskTypeInStorage;
    }

    private String getTagWord(String[] taskStorageString) {
        String taskName = taskStorageString[2].trim();
        if (taskName.contains("#")) {
            int taggedIndex = taskName.indexOf('#');
            String taggedWord = taskName.substring(taggedIndex + 1);
            return taggedWord;
        }
        return "";
    }

    private void addTaskToList(Task pastTaskToAdd, TaskList currentTaskList) {
        currentTaskList.add(pastTaskToAdd);
    }

    private void processTaskStatus(Task pastTask, boolean taskStatus, boolean taskTaggedStatus, String tagWord) {
        if (taskStatus) {
            pastTask.markDone();
        }

        if (taskTaggedStatus) {
            pastTask.tagTask(tagWord);
        }
    }
}
