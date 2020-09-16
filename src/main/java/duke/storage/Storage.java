package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A class to save tasks into the file and to load tasks from the file.
 */
public class Storage {

    /**
     * The file location where Duke's list of tasks if stored.
     */
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }


    /**
     * Loads all the tasks saved in the file.
     * @param taskList the TaskList where all the tasks should be loaded into.
     */
    public void pullList(TaskList taskList) {
        try {
            File file = new File(filepath);
            file.createNewFile();
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                Task temp;
                String line = sc.nextLine();
                String[] nextTaskArr = line.split("\\|");
                int nextTaskLength = nextTaskArr.length;
                String nextTaskType = nextTaskArr[0].strip();

                if (nextTaskType.equals("T")) {
                    if (nextTaskLength != 3) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Todo(nextTaskArr[2].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    taskList.add(temp);
                } else if (nextTaskType.equals("D")) {
                    if (nextTaskLength != 4) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Deadline(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    taskList.add(temp);
                } else if (nextTaskType.equals("E")) {
                    if (nextTaskLength != 4) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Event(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    taskList.add(temp);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * Saves all the tasks into the file.
     * @param tasks the TaskList whose tasks should be stored into the file.
     */
    public void storelist(TaskList tasks) {
        try {
            String seperator = " | ";
            FileWriter fileWriter = new FileWriter(filepath);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task temp = tasks.get(i);
                String doneStatus = "0";
                if (temp.checkDone()) {
                    doneStatus = "1";
                }

                if (temp instanceof Todo) {
                    fileWriter.write("T" + seperator + doneStatus + seperator
                            + temp.getTaskName() + "\n");
                } else if (temp instanceof Deadline) {
                    Deadline tempDeadline = (Deadline) temp;
                    fileWriter.write("D" + seperator + doneStatus + seperator
                            + tempDeadline.getTaskName() + seperator + tempDeadline.getDeadlineDate() + "\n");
                } else if (temp instanceof Event) {
                    Event tempEvent = (Event) temp;
                    fileWriter.write("E" + seperator + doneStatus + seperator
                            + tempEvent.getTaskName() + seperator + tempEvent.getEventDate() + "\n");
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Archives all the tasks into a specified file.
     * @param tasks the TaskList whose tasks should be stored.
     * @param archivePath the file name where the tasks should be archived.
     */
    public void archive(TaskList tasks, String archivePath) {
        try {
            String seperator = " | ";
            FileWriter fileWriter = new FileWriter(archivePath);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task temp = tasks.get(i);
                String doneStatus = "0";
                if (temp.checkDone()) {
                    doneStatus = "1";
                }

                if (temp instanceof Todo) {
                    fileWriter.write("T" + seperator + doneStatus + seperator
                            + temp.getTaskName() + "\n");
                } else if (temp instanceof Deadline) {
                    Deadline tempDeadline = (Deadline) temp;
                    fileWriter.write("D" + seperator + doneStatus + seperator
                            + tempDeadline.getTaskName() + seperator + tempDeadline.getDeadlineDate());
                } else if (temp instanceof Event) {
                    Event tempEvent = (Event) temp;
                    fileWriter.write("E" + seperator + doneStatus + seperator
                            + tempEvent.getTaskName() + seperator + tempEvent.getEventDate());
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
