package taskbot.storage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

import taskbot.task.*;

public class Storage {
    private ArrayList<Task> tasks;
    private String dirPath;
    /**
     * Creates a new Storage
     */
    public Storage(String dirPath) {
        tasks = new ArrayList<>();
        //Path of project directory
        this.dirPath = dirPath;

        //Outputs message from searching database
        System.out.println(createStorage());

        //Loads tasks from file into tasks array
        loadTasks();
    }

    public ArrayList<Task> getTasksList() {
        return tasks;
    }

    public void setTasksList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        saveTasks();
    }

    /**
     * Creates directory and file for task storage.
     * If directory and file already exists, this method
     * does nothing.
     * @return Message describing outcome.
     * @throws IOException Thrown if file creation fails
     */
    private String createStorage() {
        //Create directory and file paths for storage
        Path directoryName = Paths.get(dirPath, "data");
        Path fileName = Paths.get(directoryName.toString(), "tasks.txt");

        //Directory/File to be created
        File directory = new File(directoryName.toString());
        File file = new File(fileName.toString());

        //Check if directory was successfully created or the txt file does not exist
        if (directory.mkdir() || !file.exists()) {
            //Creates the txt file to store tasks
            try {
                //File creation successful
                file.createNewFile();
                return "Database for tasks list initialized at " + directoryName
                        + "\n------------------------------------------------------------------";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "Database for tasks list found. Tasks will be loaded from that file.\n" +
                    "------------------------------------------------------------------";
        }

        //Code is not supposed to reach this point.
        return "Unexpected Error.";
    }

    /**
     * Loads tasks from the database
     */
    private void loadTasks() {
        try {
            //Makes new file instance
            Path filePath = Paths.get(dirPath, "data", "tasks.txt");
            File file = new File(filePath.toString());

            //Reads the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Adds each task to the tasks array
            String taskType;
            while ((taskType = bufferedReader.readLine()) != null) {
                switch (taskType) {
                case "todo":
                    boolean isDone = bufferedReader.readLine().equals("1");
                    String description = bufferedReader.readLine();
                    //Creates the todo
                    Todo todo = new Todo(description, isDone);
                    //Adds the task to the list
                    tasks.add(todo);
                    break;
                case "event":
                    isDone = bufferedReader.readLine().equals("1");
                    description = bufferedReader.readLine();
                    LocalDateTime at = LocalDateTime.parse(bufferedReader.readLine());
                    //Creates the event
                    Event event = new Event(description, at, isDone);
                    //Adds the task to the list
                    tasks.add(event);
                    break;
                case "deadline":
                    isDone = bufferedReader.readLine().equals("1");
                    description = bufferedReader.readLine();
                    LocalDateTime by = LocalDateTime.parse(bufferedReader.readLine());
                    //Creates the deadline
                    Deadline deadline = new Deadline(description, by, isDone);
                    //Adds the task to the list
                    tasks.add(deadline);
                    break;
                default:
                    System.out.println("Error has occured. This is a placeholder to be replaced by an InvalidFileException");
                }
            }

            //Closes the reader
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the tasks to the database
     */
    private void saveTasks() {
        //Makes new file instance
        Path filePath = Paths.get(dirPath, "data", "tasks.txt");
        try {
            FileWriter fileWriter = new FileWriter(filePath.toString());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    bufferedWriter.write("todo");
                    bufferedWriter.newLine();

                    bufferedWriter.write(task.getIsDone() ? "1" : "0");
                    bufferedWriter.newLine();

                    bufferedWriter.write(task.getTask());
                    bufferedWriter.newLine();
                } else if (task instanceof Event) {
                    bufferedWriter.write("event");
                    bufferedWriter.newLine();

                    bufferedWriter.write(task.getIsDone() ? "1" : "0");
                    bufferedWriter.newLine();

                    bufferedWriter.write(task.getTask());
                    bufferedWriter.newLine();

                    bufferedWriter.write(((Event) task).getAt().toString());
                    bufferedWriter.newLine();
                } else if (task instanceof Deadline) {
                    bufferedWriter.write("deadline");
                    bufferedWriter.newLine();

                    bufferedWriter.write(task.getIsDone() ? "1" : "0");
                    bufferedWriter.newLine();

                    bufferedWriter.write(task.getTask());
                    bufferedWriter.newLine();

                    bufferedWriter.write(((Deadline) task).getBy().toString());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
