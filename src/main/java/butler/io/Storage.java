package butler.io;

import butler.command.AddCommand;
import butler.exception.ButlerException;
import butler.task.DeadlineTask;
import butler.task.EventTask;
import butler.task.Task;
import butler.task.TaskList;
import butler.task.TaskType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

// Class deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void storeTaskList(TaskList taskList) throws ButlerException{
        try {
            FileWriter fw = new FileWriter(filePath);
            String fileText = "";
            int size = taskList.getSize();
            for (int i = 0; i < size; i++) {
                Task task = taskList.getTask(i);
                String taskDetails;
                if (task.isComplete()) {
                    taskDetails = "complete";
                } else {
                    taskDetails = "incomplete";
                }
                TaskType taskType = task.getTaskType();
                switch (taskType) {
                    case TODO:
                        taskDetails += " todo " + task.getSummary();
                        break;
                    case DEADLINE:
                        DeadlineTask deadlineTask = (DeadlineTask) task;
                        taskDetails += " deadline " + deadlineTask.getSummary()
                                + " /by " + deadlineTask.getDeadline();
                        break;
                    case EVENT:
                        EventTask eventTask = (EventTask) task;
                        taskDetails += " event " + eventTask.getSummary()
                                + " /at " + eventTask.getStartDate() + " "
                                + eventTask.getEndDate();
                        break;
                    default:
                        throw new ButlerException("Something is wrong. This place should be unreachable.");
                }
                fileText += taskDetails + System.lineSeparator();
            }
            fw.write(fileText);
            fw.close();
        } catch (IOException e) {
            throw new ButlerException("There is an error with writing to the path. File is not detected.");
        } catch (ClassCastException e) {
            throw new ButlerException("Something is wrong. ClassCastException should be unreachable.");
        }
    }

    public ArrayList<Task> load() throws ButlerException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();

            while (s.hasNext()) {
                // read in file input into tasks
                String input = s.nextLine();
                // first detect completion status
                String completionStatus = input.split(" ", 2)[0];
                String details = input.split(" ", 2)[1];
                AddCommand c = (AddCommand) Parser.parse(details);
                Task task = c.getTask();
                if (completionStatus.equals("complete")) {
                    task.markComplete();
                }
                taskList.add(task);
            }
            return taskList;

        } catch (FileNotFoundException e) {
            try {
                Files.createDirectory(Paths.get("./data/"));
                Files.createFile(Paths.get(filePath));
            } catch (IOException f) {
                throw new ButlerException("Code should never reach here." + f.getMessage());
            }
            throw new ButlerException("There is no file to access.");
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("There is an error inside the file. The task has incomplete details.");
        } catch (ClassCastException e) {
            throw new ButlerException("Instead of tasks, a command was written into the file.");
        } catch (ButlerException e) {
            throw new ButlerException("There is an error within the task in the file.");
        }
    }
}
