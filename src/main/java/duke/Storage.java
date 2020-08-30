package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

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
     */
    public void loadData(TaskList currTaskList) {
        this.checkHistory();
        BufferedReader rb = null;
        try {
            rb = new BufferedReader(new FileReader(this.storageFile));
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        }
        String newLine = "";
        try {
            newLine = rb.readLine();
            while (newLine != null) {
                String[] taskInput = newLine.split("\\|");
                if (taskInput.length == 1) {
                    break;
                }
                if (taskInput[0].charAt(0) == 'T') {
                    ToDo pastToDo = new ToDo(taskInput[2].trim());
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastToDo.markDone();
                    }
                    currTaskList.add(pastToDo);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'E') {
                    Event pastEvent = new Event(taskInput[2].trim(), LocalDate.parse(taskInput[3].trim()));
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastEvent.markDone();
                    }
                    currTaskList.add(pastEvent);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'D') {
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
            e.printStackTrace();
        }
    }

    /**
     * Checks if user has the current directory and data file to store or load data.
     * Creates the relevant directory and data file if user do not have them.
     *
     */
    private void checkHistory() {
        try {
            FileReader readFile = new FileReader(DATA_FILE_DIRECTORY);
        } catch (FileNotFoundException e) {
            File newData = new File(DATA_FILE_DIRECTORY);
            if (!newData.exists()) {
                newData.mkdirs();
                Ui.addDirectory();
            }
        }

        try {
            if (this.storageFile.createNewFile()) {
                Ui.addDataFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }
    }

    /**
     * Adds the new task into storage file.
     *
     * @param newTask New Task that has been added.
     */
    public void addTask(Task newTask) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(this.storageFile, true);
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
            e.printStackTrace();
        }
    }

    /**
     * Edits any tasks in the current stroage file if they have been marked as done.
     *
     * @param editedTask Task to be edited.
     * @param taskIndex Index of task in the task list.
     * @param currentList Current task list used by bot.
     */
    public void editTask(Task editedTask, int taskIndex, TaskList currentList) {
        File toBeDeleted = new File (DATA_FILE_DIRECTORY + "dataList1.txt");
        BufferedReader readerBuffer = null;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes task from storage file when it is removed from task list.
     *
     * @param removedTask Task to be removed.
     */
    public void deleteTask(Task removedTask) {
        try {
            File removed = new File (DATA_FILE_DIRECTORY + "dataList1.txt");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
