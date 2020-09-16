package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The class that saves and loads data.
 */
public class Storage {
    private final String filepath;

    /**
     * Initialises the Storage class which requires a filepath to save and load data from.
     *
     * @param filepath The relative filepath from where Duke was ran, to save and load data from
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    // can improve and abstract out to 2 different functions, one to show load success
    // and other to get tasklist
    /**
     * Loads data using the filepath given.
     *
     * @param ui Needed to display the outcome to the user
     * @return Returns the saved TaskList, if there is any
     */
    public TaskList loadData(UI ui) {
        Path path = FileSystems.getDefault().getPath(filepath);
        if (!Files.exists(path)) {
            return new TaskList();
        }

        try {
            TaskList taskList = new TaskList();
            File data = new File(filepath);
            Scanner scanner = new Scanner(data);

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] str = s.split("\\|");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                switch (str[0]) {
                case "T":
                    ToDo toDo = new ToDo(str[2]);
                    if (str[1].equals("true")) {
                        toDo.markAsDone();
                    }
                    taskList.add(toDo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(str[2], LocalDateTime.parse(str[3], dateFormatter));
                    if (str[1].equals("true")) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(str[2], LocalDateTime.parse(str[3], dateFormatter));
                    if (str[1].equals("true")) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                    break;
                default:
                    throw new IOException();
                }
            }
            scanner.close();
            return taskList;
        } catch (IOException e) {
            System.out.println("Uh oh! An error has occurred. T_T");
            System.out.println(e.getMessage());
        }
        return new TaskList();
    }

    /**
     * Saves data using the filepath given.
     *
     * @param ui       Needed to display the outcome to the user
     * @param taskList The TaskList being saved into the filepath
     */
    public String saveData(UI ui, TaskList taskList) {
        try {
            File data = new File(filepath);
            data.getParentFile().mkdirs();
            data.createNewFile();
            FileWriter fileWriter = new FileWriter(data);
            PrintWriter writer = new PrintWriter(fileWriter);

            String save = UI.SAVE_START;
            for (Task task : taskList) {
                String s = String.format("%s|%b|%s", task.getTaskType(),
                        task.getIsDone(), task.getDescription());
                if (!task.getTaskType().equals("T")) {
                    s = s.concat(String.format("|%s", task.getDate()));
                }

                writer.println(s);
            }
            writer.close();
            return save + "\n" + UI.SAVE_SUCCESS;
        } catch (IOException e) {
            System.out.println("Uh oh! An error has occurred. T_T");
            System.out.println(e.getMessage());
        }
        return "Uh oh! An error has occurred. T_T";
    }

}
