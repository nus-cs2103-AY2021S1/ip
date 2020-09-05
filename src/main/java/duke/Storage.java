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
        if (loadingDataFileMessage.length() == 0) {
            loadingDataFileMessage = "________________________________________________________" + "\n"
                    + "     Found directories and file" + "\n"
                    + "________________________________________________________" + "\n";
        }
        try {
            assert this.storageFile.exists() : "Storage file went missing, please restart your bot!";
            BufferedReader rb = new BufferedReader(new FileReader(this.storageFile));
            String newLine = "";
            newLine = rb.readLine();
            while (newLine != null) {
                String[] taskInput = newLine.split("\\|");
                if (taskInput.length == 1) {
                    break;
                }
                if (taskInput[0].charAt(0) == 'T') {
                    assert taskInput.length == 3 : "Task in storage file is missing information!";
                    assert taskInput[2].trim().length() != 0 : "Task name is corrupted!";
                    assert Integer.parseInt(taskInput[1].trim()) == 1
                            | Integer.parseInt(taskInput[1].trim()) == 0 : "Task status is corrupted!";
                    ToDo pastToDo = new ToDo(taskInput[2].trim());
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastToDo.markDone();
                    }
                    currTaskList.add(pastToDo);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'E') {
                    assert taskInput.length == 4 : "Task name and/or date in storage file is lost!";
                    assert taskInput[2].trim().length() != 0 : "Task name is corrupted!";
                    assert taskInput[3].trim().length() != 0 : "Task date is corrupted!";
                    assert Integer.parseInt(taskInput[1].trim()) == 1
                            | Integer.parseInt(taskInput[1].trim()) == 0 : "Task status is corrupted!";
                    Event pastEvent = new Event(taskInput[2].trim(), LocalDate.parse(taskInput[3].trim()));
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastEvent.markDone();
                    }
                    currTaskList.add(pastEvent);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'D') {
                    assert taskInput.length == 4 : "Task name and/or date in storage file is lost!";
                    assert taskInput[2].trim().length() != 0 : "Task name is corrupted!";
                    assert taskInput[3].trim().length() != 0 : "Task date is corrupted!";
                    assert Integer.parseInt(taskInput[1].trim()) == 1
                            | Integer.parseInt(taskInput[1].trim()) == 0 : "Task status is corrupted!";
                    Deadline pastDeadline = new Deadline(taskInput[2].trim(),
                            LocalDate.parse(taskInput[3].trim()));
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastDeadline.markDone();
                    }
                    currTaskList.add(pastDeadline);
                    newLine = rb.readLine();
                }
            }
            rb.close();
        } catch (IOException e) {
            throw new InvalidCommand("Unable to read file");
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
        try {
            FileReader readFile = new FileReader(DATA_FILE_DIRECTORY);
        } catch (FileNotFoundException e) {
            File newData = new File(DATA_FILE_DIRECTORY);
            if (!newData.exists()) {
                newData.mkdirs();
                overallHistoryMessage += Ui.addDirectory();
                overallHistoryMessage += "\n";
            }
        }

        try {
            if (this.storageFile.createNewFile()) {
                overallHistoryMessage += Ui.addDataFile();
                overallHistoryMessage += "\n";
            }
        } catch (IOException e) {
            throw new InvalidCommand("Unable to create file");
        }
        return overallHistoryMessage;
    }

    /**
     * Adds the new task into storage file.
     *
     * @param newTask New Task that has been added.
     * @throws InvalidCommand Unable write to file.
     */
    public void addTask(Task newTask) throws InvalidCommand {
        try {
            FileWriter fw = new FileWriter(this.storageFile, true);
            if (newTask instanceof ToDo) {
                fw.write(((ToDo) newTask).getDataStorageName() + "\n");
                fw.close();
            } else if (newTask instanceof Deadline) {
                fw.write(((Deadline) newTask).getDataStorageName() + "\n");
                fw.close();
            } else if (newTask instanceof Event) {
                fw.write(((Event) newTask).getDataStorageName() + "\n");
                fw.close();
            }
        } catch (IOException e) {
            throw new InvalidCommand("Unable to write to file");
        }
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
        File toBeDeleted = new File (DATA_FILE_DIRECTORY + "dataList1.txt");
        BufferedReader readerBuffer = null;
        assert this.storageFile.exists() : "Storage file is lost while application is running!";
        try {
            readerBuffer = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writerBuffer = new BufferedWriter(new FileWriter(toBeDeleted));
            String readingLine = readerBuffer.readLine();
            String lineToEdit = "";
            String lineToChangeTo = "";

            if (editedTask instanceof Deadline) {
                lineToEdit = ((Deadline) editedTask).getDataStorageName();
                currentList.get(taskIndex).markDone();
                lineToChangeTo = ((Deadline) editedTask).getDataStorageName();
            } else if (editedTask instanceof Event) {
                lineToEdit = ((Event) editedTask).getDataStorageName();
                currentList.get(taskIndex).markDone();
                lineToChangeTo = ((Event) editedTask).getDataStorageName();
            } else {
                lineToEdit = ((ToDo) editedTask).getDataStorageName();
                currentList.get(taskIndex).markDone();
                lineToChangeTo = ((ToDo) editedTask).getDataStorageName();
            }

            while (readingLine != null) {
                if (readingLine.equals(lineToEdit)) {
                    writerBuffer.write(lineToChangeTo + "\n");
                    readingLine = readerBuffer.readLine();
                    continue;
                }
                writerBuffer.write(readingLine + "\n");
                readingLine = readerBuffer.readLine();
            }

            writerBuffer.close();
            readerBuffer.close();

            if (this.storageFile.delete()) {
                // Rename the output file to the input file
                if (!toBeDeleted.renameTo(this.storageFile)) {
                    throw new IOException("Could not rename to update data file");
                }
            } else {
                throw new IOException("Could not delete old data file");
            }
        } catch (IOException e) {
            throw new InvalidCommand(e.getMessage());
        }

    }

    /**
     * Deletes task from storage file when it is removed from task list.
     *
     * @param removedTask Task to be removed.
     * @throws InvalidCommand Unable to remove old data file.
     */
    public void deleteTask(Task removedTask) throws InvalidCommand {
        try {
            File removed = new File (DATA_FILE_DIRECTORY + "dataList1.txt");
            assert this.storageFile.exists() : "Storage file is lost while application is running!";
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(removed));
            String currentLine = reader.readLine();
            String lineToRemove = "";

            if (removedTask instanceof Deadline) {
                lineToRemove = ((Deadline) removedTask).getDataStorageName();
            } else if (removedTask instanceof Event) {
                lineToRemove = ((Event) removedTask).getDataStorageName();
            } else {
                lineToRemove = ((ToDo) removedTask).getDataStorageName();
            }

            while (currentLine != null) {
                if (currentLine.equals(lineToRemove)) {
                    currentLine = reader.readLine();
                    continue;
                }
                writer.write(currentLine + "\n");
                currentLine = reader.readLine();
            }

            writer.close();
            reader.close();

            if (this.storageFile.delete()) {
                // Rename the output file to the input file
                if (!removed.renameTo(this.storageFile)) {
                    throw new IOException("Could not rename to update data file");
                }
            } else {
                throw new IOException("Could not delete old data file");
            }
        } catch (IOException e) {
            throw new InvalidCommand(e.getMessage());
        }
    }

}
