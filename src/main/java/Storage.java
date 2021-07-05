import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Storage {
    public static final String TASK_DONE = "1";
    public static final String TASK_NOT_DONE = "0";
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file if it exists or creates it if it does not.
     *
     * @return The List of Tasks.
     * @throws DukeException when the file cannot be found or read.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        if (f.exists() && !f.isDirectory()) {
            try {
                for (String line : Files.readAllLines(Paths.get(filePath))) {
                    String[] data = line.split(" \\| ");
                    String taskType = data[0];

                    switch (taskType) {
                    case "T":
                        Todo todo = new Todo(data[2]);

                        if (data[1].equals("1")) {
                            todo.markAsDone();
                        }

                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(data[2], data[3]);

                        if (data[1].equals("1")) {
                            deadline.markAsDone();
                        }

                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(data[2], data[3]);

                        if (data[1].equals("1")) {
                            event.markAsDone();
                        }

                        tasks.add(event);
                        break;
                    default:
                        throw new DukeException("Line cannot be read");
                    }
                }
                Collections.sort(tasks);
                return tasks;
            } catch (IOException ex) {
                System.out.println("Error reading file" + ex);
                throw new DukeException("Error reading file");
            }
        } else {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error creating file" + ex);
                throw new DukeException("Error reading file");
            }
        }
        return new ArrayList<>();
    }

    /**
     * Adds new Task to the file.
     *
     * @param taskType The type of the Task.
     * @param done Whether the task is completed.
     * @param description Description of the Task.
     * @param deadline The time attached to the Task.
     * @throws DukeException When there is error writing to the file.
     */
    public void writeNewDataToFile(String taskType, String done, String description, String deadline)
            throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);

            switch (taskType) {
            case "T":
                myWriter.write("\n" + taskType + " | " + done + " | " + description);
                myWriter.close();
                break;
            case "D":
            case "E":
                myWriter.write("\n" + taskType + " | " + done + " | " + description + " | " + deadline);
                myWriter.close();
                break;
            default:
                throw new DukeException("Data cannot be written to file due to missing task type.");
            }
        } catch (IOException ex) {
            System.out.println("Problem writing to file" + ex);
            throw new DukeException("Error writing to file.");
        }
    }

    /**
     * Edits a Task in the file.
     *
     * @param taskNumber The index of the Task.
     * @param taskType The type of the Task.
     * @param done Whether the task is completed.
     * @param description Description of the Task.
     * @param deadline The time attached to the Task.
     * @param total The total number of Tasks in the list.
     * @throws DukeException When there is error writing to the file.
     */
    public void editCurrentDataInFile(int taskNumber, String taskType, String done,
                                             String description, String deadline, int total) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            //Keep track of the line number
            int linenumber = 1;
            String line;

            while ((line = br.readLine()) != null) {
                //Store each valid line in the string buffer
                if (linenumber != taskNumber && linenumber != total) {
                    sb.append(line + "\n");
                } else if (linenumber != taskNumber) {
                    sb.append(line);
                } else {
                    String data;
                    if (taskType.equals("T")) {
                        data = taskType + " | " + done + " | " + description;
                    } else {
                        data = taskType + " | " + done + " | " + description + " | " + deadline;
                    }

                    if (linenumber == total) {
                        sb.append(data);
                    } else {
                        sb.append(data + "\n");
                    }
                }
                linenumber++;
            }
            br.close();

            FileWriter fw = new FileWriter(new File(filePath));

            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error editing file: " + e.getMessage());
            throw new DukeException("Error editing file");
        }
    }

    /**
     * Deletes a Task in the file.
     *
     * @param taskNumber The index of the Task.
     * @param total The total number of Tasks in the list.
     * @throws DukeException When there is error writing to the file.
     */
    public void deleteCurrentDataInFile(int taskNumber, int total) throws DukeException {
        try {
            String currentDir = System.getProperty("user.dir");
            String pathToFile = currentDir + File.separator + "data" + File.separator + "duke.txt";
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            //Keep track of the line number
            int linenumber = 1;
            String line;

            while ((line = br.readLine()) != null) {
                //Store each valid line in the string buffer
                if (linenumber != taskNumber && linenumber == total + 1) {
                    sb.append(line);
                } else if (linenumber != taskNumber) {
                    sb.append(line + "\n");
                }
                linenumber++;
            }
            br.close();

            FileWriter fw = new FileWriter(new File(pathToFile));
            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        } catch (Exception ex) {
            System.out.println("Error deleting task: " + ex.getMessage());
            throw new DukeException("Error deleting task.");
        }
    }

    /**
     * Updates the entire file according to the updated TaskList.
     *
     * @param tasks The TaskList object containing all the tasks.
     * @throws DukeException When there is error writing to the file.
     */
    public void updateFile(TaskList tasks) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            //String buffer to store contents of the file
            StringBuffer sb = new StringBuffer("");

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                String taskType = task.getTaskType();
                String isDone = task.getIsDone() ? TASK_DONE : TASK_NOT_DONE;
                String lineToAdd;

                switch (taskType) {
                case "T":
                    lineToAdd = "T | " + isDone + " | " + task.getDescription();
                    break;
                case "D":
                    Deadline deadline = (Deadline) task;
                    lineToAdd = "D | " + isDone + " | " + deadline.getDescription() + " | " + deadline.getBy();
                    break;
                case "E":
                    Event event = (Event) task;
                    lineToAdd = "E | " + isDone + " | " + event.getDescription() + " | " + event.getTime();
                    break;
                default:
                    throw new DukeException("Task to be written is not recognised.");
                }

                if (i == tasks.getSize() - 1) {
                    sb.append(lineToAdd);
                } else {
                    sb.append(lineToAdd + "\n");
                }
            }

            br.close();

            FileWriter fw = new FileWriter(new File(filePath));

            //Write entire string buffer into the file
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error editing file: " + e.getMessage());
            throw new DukeException("Error editing file");
        }

    }
}
