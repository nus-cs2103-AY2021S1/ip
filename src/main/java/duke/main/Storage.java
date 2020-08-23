package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public TaskList loadData(UI ui) {
        Path path = FileSystems.getDefault().getPath(filepath);
        if (!Files.exists(path)) return new TaskList();

        try {
            TaskList taskList = new TaskList();
            File data = new File(filepath);
            Scanner scanner = new Scanner(data);
            ui.startLoading();

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
                        taskList.addTask(toDo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(str[2], LocalDateTime.parse(str[3], dateFormatter));
                        if (str[1].equals("true")) {
                            deadline.markAsDone();
                        }
                        taskList.addTask(deadline);
                        break;
                    case "E":
                        Event event = new Event(str[2], LocalDateTime.parse(str[3], dateFormatter));
                        if (str[1].equals("true")) {
                            event.markAsDone();
                        }
                        taskList.addTask(event);
                        break;
                    default:
                        throw new IOException();
                }
            }
            scanner.close();
            ui.loadSuccess();
            return taskList;
        } catch (IOException e) {
            System.out.println("Uh oh! An error has occurred. T_T");
            System.out.println(e.getMessage());
        }
        return new TaskList();
    }

    public void saveData(UI ui, TaskList taskList) {
        try {
            File data = new File(filepath);
            data.getParentFile().mkdirs();
            data.createNewFile();
            FileWriter fileWriter = new FileWriter(data);
            PrintWriter writer = new PrintWriter(fileWriter);

            ui.startSaving();
            for (Task task : taskList.getTaskList()) {
                String s = String.format("%s|%b|%s", task.getTaskType(),
                        task.getIsDone(), task.getDescription());
                if (!task.getTaskType().equals("T")) {
                    s = s.concat(String.format("|%s", task.getDate()));
                }

                writer.println(s);
            }
            writer.close();
            ui.saveSuccess();
        } catch (IOException e) {
            System.out.println("Uh oh! An error has occurred. T_T");
            System.out.println(e.getMessage());
        }
    }
}
