package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

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
     * @throws InvalidCommand Unable to read or create data file.
     */
    public String loadData(TaskList currTaskList) throws InvalidCommand {
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
     */
    private String checkHistory() throws InvalidCommand {
        String overallHistoryMessage = "";
        overallHistoryMessage += checkDirectoryCreated();
        overallHistoryMessage += checkFileCreated();
        return overallHistoryMessage;
    }

    /**
     * Adds the new task into storage file.
     *
     * @param newTask New Task that has been added.
     * @throws InvalidCommand Unable write to file.
     */
    public void addTask(Task newTask) throws InvalidCommand {
        String retrieveTaskType = checkTaskType(newTask);
        String retrieveStorageName = getStorageName(retrieveTaskType, newTask);
        String wordsToBeWritten = retrieveStorageName + "\n";
        writeToFile(wordsToBeWritten);
    }

    /**
     * Edits any tasks in the current stroage file if they have been marked as done.
     *
     * @param editedTask Task to be edited.
     * @param taskIndex Index of task in the task list.
     * @param currentList Current task list used by bot.
     * @throws InvalidCommand Unable to remove old data file.
     */
    public void editTask(Task editedTask, int taskIndex, TaskList currentList) throws InvalidCommand {
        String taskType = checkTaskType(editedTask);
        String lineToEdit = getStorageName(taskType, editedTask);
        currentList.get(taskIndex).markDone();
        String lineToChangeTo = getStorageName(taskType, editedTask);
        editTaskFromFile(lineToEdit, lineToChangeTo);
    }

    /**
     * Deletes task from storage file when it is removed from task list.
     *
     * @param removedTask Task to be removed.
     * @throws InvalidCommand Unable to remove old data file.
     */
    public void deleteTask(Task removedTask) throws InvalidCommand {
            String taskType = checkTaskType(removedTask);
            String lineToRemove = getStorageName(taskType, removedTask);
            deleteTaskFromFile(lineToRemove);
    }

    /**
     *
     * @param taskToBeChecked
     * @return
     * @throws InvalidCommand
     */
    private String checkTaskType(Task taskToBeChecked) throws InvalidCommand {
        String taskType = "";
        if (taskToBeChecked instanceof Deadline) {
            taskType = "Deadline";
        } else if (taskToBeChecked instanceof Event) {
            taskType = "Event";
        } else if (taskToBeChecked instanceof ToDo) {
            taskType = "ToDo";
        } else {
            throw new InvalidCommand("Your Task is invalid, please clear all memory.");
        }
        return taskType;
    }

    /**
     *
     * @param typeOfInputTask
     * @param taskToRetrieveName
     * @return
     * @throws InvalidCommand
     */
    private String getStorageName(String typeOfInputTask, Task taskToRetrieveName) throws InvalidCommand{
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
                throw new InvalidCommand("Your Storage File is corrupted. Please delete it.");
        }
        return taskStorageName;
    }

    private void deleteTaskFromFile(String taskNameToBeRemoved) throws InvalidCommand {
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
            throw new InvalidCommand(ex.getMessage());
        }
    }

    private void filesDeletion (File newFile, File overWrittenFile) throws IOException{
        if (overWrittenFile.delete()) {
            // Rename output file to input file
            if (!newFile.renameTo(overWrittenFile)) {
                throw new IOException("Could not rename to update data file");
            }
        } else {
            throw new IOException("Could not delete old data file");
        }
    }

    private void editTaskFromFile (String removeTaskString, String editedTaskString) throws InvalidCommand {
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
            throw new InvalidCommand(ex.getMessage());
        }
    }

    private void writeToFile(String stringToBeWritten) throws InvalidCommand {
        try {
            FileWriter fw = new FileWriter(this.storageFile, true);
            fw.write(stringToBeWritten);
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommand("Cannot write to file!");
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

    private String checkFileCreated() throws InvalidCommand {
        String fileCreationMessage = "";
        try {
            if (this.storageFile.createNewFile()) {
                fileCreationMessage += Ui.addDataFile();
                fileCreationMessage += "\n";
            }
        } catch (IOException e) {
            throw new InvalidCommand("Unable to create file");
        }
        return fileCreationMessage;
    }

    private void checkIfFileAvailable() throws InvalidCommand {
        try {
            BufferedReader rb = new BufferedReader(new FileReader(this.storageFile));
        } catch (FileNotFoundException e) {
            throw new InvalidCommand("File cannot be found");
        }
    }

    private void readPastDataFile(TaskList toBeUpdatedTaskList) throws InvalidCommand{
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
            throw new InvalidCommand("Your storage file cannot be read");
        }
    }

    private String trimTaskName(String[] storageFileString) {
        return storageFileString[2].trim();
    }

    private boolean checkTaskStatus(String[] storageFileString) {
        int statusValue = Integer.parseInt(storageFileString[1].trim());
        assert statusValue == 1 || statusValue == 0 : "Your storage file is corrupted.";
        if (statusValue == 1) {
            return true;
        }
        return false;
    }

    private LocalDate getDate(String[] storageFileString) {
        assert storageFileString[3].trim().length() != 0 : "Task date is corrupted!";
        return LocalDate.parse(storageFileString[3].trim());
    }

    private void processTasks(String[] taskStorageString, TaskList toBeUpdatedTaskList) {
        String trimmedTaskName = trimTaskName(taskStorageString);
        boolean isTaskDone = checkTaskStatus(taskStorageString);
        assert taskStorageString.length == 3 : "Task in storage file is missing information!";
        assert taskStorageString[2].trim().length() != 0 : "Task name is corrupted!";
        assert Integer.parseInt(taskStorageString[1].trim()) == 1
                | Integer.parseInt(taskStorageString[1].trim()) == 0 : "Task status is corrupted!";
        if (taskStorageString[0].charAt(0) == 'T') {
            ToDo pastToDo = new ToDo(trimmedTaskName);
            processTaskStatus(pastToDo, isTaskDone);
            addTaskToList(pastToDo, toBeUpdatedTaskList);
        } else if (taskStorageString[0].charAt(0) == 'E') {
            LocalDate eventDate = getDate(taskStorageString);
            Event pastEvent = new Event(trimmedTaskName, eventDate);
            processTaskStatus(pastEvent, isTaskDone);
            addTaskToList(pastEvent, toBeUpdatedTaskList);
        } else if (taskStorageString[0].charAt(0) == 'D') {
            LocalDate deadlineDate = getDate(taskStorageString);
            Deadline pastDeadline = new Deadline(trimmedTaskName,
                    deadlineDate);
            processTaskStatus(pastDeadline, isTaskDone);
            addTaskToList(pastDeadline, toBeUpdatedTaskList);
        }
    }

    private void addTaskToList(Task pastTaskToAdd, TaskList currentTaskList) {
        currentTaskList.add(pastTaskToAdd);
    }

    private void processTaskStatus(Task pastTask, boolean taskStatus) {
        if (taskStatus) {
            pastTask.markDone();
        }
    }
}
