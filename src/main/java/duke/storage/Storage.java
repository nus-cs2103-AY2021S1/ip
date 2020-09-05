package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * A class that represents the file storage of list of task.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs the file Storage.
     *
     * @param filePath the file path to store the Task List.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks that is read from the file storage.
     *
     * @return a list of tasks that is read from the file storage.
     * @throws DukeException if the file is not read correctly.
     */
    public List<Task> readFromFile() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] instructions = line.split(" \\| ");
                String type = instructions[0].strip();
                tasks.addAll(processDataIntoList(type, instructions));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

        assert tasks.size() >= 0 : "The size of tasks should be greater or equal to 0";
        
        return tasks;
    }

    /**
     * Stores the TaskList to the file storage system.
     *
     * @param tasks the TaskList of tasks from the application to be stored in the file storage.
     * @throws DukeException when the file is not written correctly.
     */
    public void storeToFile(TaskList tasks) throws DukeException {
        try {
            String breaker = " | ";
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task taskToStore = tasks.get(i);
                String state = "0";
                if (taskToStore.isDone()) {
                    state = "1";
                }
                if (taskToStore instanceof ToDo) {
                    fileWriter.write("T" + breaker + state + breaker + taskToStore.getDescription() + "\n");
                } else if (taskToStore instanceof Deadline) {
                    Deadline deadline = (Deadline) taskToStore;
                    fileWriter.write("D" + breaker + state + breaker + deadline.getDescription()
                            + breaker + deadline.getBy() + "\n");
                } else if (taskToStore instanceof Event) {
                    Event event = (Event) taskToStore;
                    fileWriter.write("E" + breaker + state + breaker + event.getDescription()
                            + breaker + event.getStartTime() + "\n");
                }
            }
            fileWriter.close();

        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }


    /**
     * Processes the data read from file into correct objects and stores into a list.
     *
     * @param type the type of the task read
     * @param instructions the string array of instruction splits
     * @return a list of tasks that is read from the file.
     * @throws DukeException when the data is not processed correctly from the file
     */
    public List<Task> processDataIntoList(String type, String[] instructions) throws DukeException {
        Task taskToRead;
        int num = instructions.length;
        List<Task> tasks = new ArrayList<>();

        if (type.equals("T")) {
            if (num != 3) {
                throw new DukeException("The data format of the file is incorrect\n");
            }
            taskToRead = new ToDo(instructions[2].strip());
        } else if (type.equals("D")) {
            if (num != 4) {
                throw new DukeException("The data format of the file is incorrect\n");
            }
            LocalDateTime deadlineDateTime = LocalDateTime.parse(instructions[3].strip());
            taskToRead = new Deadline(instructions[2].strip(), deadlineDateTime);
        } else if (type.equals("E")) {
            if (num != 4) {
                throw new DukeException("The data format of the file is incorrect\n");
            }
            LocalDateTime eventDateTime = LocalDateTime.parse(instructions[3].strip());
            taskToRead = new Event(instructions[2].strip(), eventDateTime);
        } else {
            throw new DukeException("Data is not recognized while reading from file.\n");
        }

        if (instructions[1].strip().equals("1")) {
            taskToRead.markAsDone();
        }
        tasks.add(taskToRead);

        return tasks;
    }
}

