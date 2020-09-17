package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // read file contents and return a String
    private static String readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        String fileContentsInString = "";
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            fileContentsInString += scanner.nextLine() + System.lineSeparator();
        }
        scanner.close();
        return fileContentsInString;
    }

    private static ArrayList<Task> createTaskList(String content) throws DukeException {
        ArrayList<Task> newTaskList = new ArrayList<>();
        Scanner scanner = new Scanner(content);
        while (scanner.hasNext()) {
            String lineOfText = scanner.nextLine();
            String[] splitText = lineOfText.split("\\|");
            String typeOfTask = splitText[0].trim();
            int statusOfTask = Integer.parseInt(splitText[1].trim());
            String taskDescription = splitText[2].trim();
            if (typeOfTask.equals("T")) {
                createTodoTask(newTaskList, statusOfTask, taskDescription);
            } else {
                String date = splitText[3].trim();
                if (typeOfTask.equals("D")) {
                    createTimedTask(newTaskList, statusOfTask, taskDescription, date, Task.Type.DEADLINE);
                } else if (typeOfTask.equals("E")) {
                    createTimedTask(newTaskList, statusOfTask, taskDescription, date, Task.Type.EVENT);
                } else {
                    throw new DukeException("Cannot create invalid type of task!");
                }
            }
        }
        scanner.close();
        return newTaskList;
    }

    private static void createTimedTask(ArrayList<Task> newTaskList, int statusOfTask, String taskDescription,
                                        String date, Task.Type type) {
        LocalDate formattedDate = LocalDate.parse(date, TimedTask.DATE_FORMATTER);
        if (type == Task.Type.EVENT) {
            newTaskList.add(new Event(taskDescription, formattedDate, statusOfTask == Task.DONE));
        } else {
            newTaskList.add(new Deadline(taskDescription, statusOfTask == Task.DONE, formattedDate));
        }

    }

    private static void createTodoTask(ArrayList<Task> list, int statusOfTask, String taskDescription) {
        list.add(new ToDo(taskDescription, statusOfTask == Task.DONE));
    }

    /**
     * Creates an ArrayList of Tasks using the contents of the file.
     *
     * @return An ArrayList of Tasks.
     * @throws FileNotFoundException If the file does not exist in the FilePath.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            String fileContents = Storage.readFileContents(this.filePath);
            return createTaskList(fileContents);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        } catch (DukeException e) {
            throw e;
        }

    }

    /**
     * Creates a file to so that we are able to save all the tasks in the TaskList.
     *
     * @throws IOException If the directory of the file being created doesn't exist.
     */
    public void createFile() throws IOException {
        File file = new File(this.filePath);
        file.createNewFile();
    }

    private static void writeToFile(String filePath, String textToAdd) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file during Save");
        }
    }

    /**
     * Saves all the tasks in TaskList to a text file.
     *
     * @param taskList TaskList containing all the existing tasks entered by user.
     */
    public void save(TaskList taskList) throws DukeException {
        String dataToSave = readDataFromTaskList(taskList);
        try {
            writeToFile(this.filePath, dataToSave);
        } catch (DukeException e) {
            throw e;
        }
    }

    private String readDataFromTaskList(TaskList taskList) {
        String dataToSave = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == taskList.size() - 1) {
                dataToSave += currentTask.toText();
            } else {
                dataToSave += currentTask.toText() + System.lineSeparator();
            }
        }
        return dataToSave;
    }
}
