package duke;

import duke.exception.*;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeInvalidDataException, DukeInvalidDateTimeInputException {
        File file = new File(filePath);
        List<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String next = sc.nextLine();
                String[] tokens = next.split(" \\| ");
                if (tokens.length < 3) {
                    throw new DukeInvalidDataException("Oops data is invalid");
                }
                String taskType = tokens[0];
                String isDone = tokens[1];
                String description = tokens[2];
                Task task;
                if (taskType.equals("T")) {
                    task = new Todo(description);
                } else if (tokens.length != 4) {
                    throw new DukeInvalidDataException("Oops data is invalid");
                } else if (taskType.equals("D")) {
                    try {
                        List<LocalDateTime> ldtList = Parser.getCustomDateTimeList(tokens[3]);
                        LocalDate date = ldtList.get(0).toLocalDate();
                        LocalTime time = ldtList.size() == 2
                                ? ldtList.get(1).toLocalTime()
                                : null;
                        task = new Deadline(description, date, time);
                    } catch (DukeInvalidDateTimeInputException e) {
                        throw e;
                    }
                } else if (taskType.equals("E")) {
                    List<LocalDateTime> ldtList = Parser.getCustomDateTimeList(tokens[3]);
                    LocalDate date = ldtList.get(0).toLocalDate();
                    LocalTime time = ldtList.size() == 2
                            ? ldtList.get(1).toLocalTime()
                            : null;
                    task = new Event(description, date, time);
                } else {
                    throw new DukeInvalidDataException("Oops data is invalid");
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                    list.add(task);
                } else if (isDone.equals("0")) {
                    list.add(task);
                } else {
                    throw new DukeInvalidDataException("Oops data is invalid");
                }
            }
        } catch (FileNotFoundException e) {
            FileWriter writer;
            try {
                writer = new FileWriter(filePath);
                writer.write("");
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
                list = new ArrayList<>();
            }
        }
        return list;
    }

    public void save(TaskList taskList) throws IOException, DukeInvalidIndexException {
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            TaskType type = task.getTaskType();
            String s = "";
            switch (type) {
                case TODO:
                    s = String.format("T | %d | %s", task.getIsDone() ? 1 : 0, task.getDescription());
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    s = String.format("D | %d | %s | %s", deadline.getIsDone() ? 1 : 0,
                            deadline.getDescription(), deadline.getBy());
                    break;
                case EVENT:
                    Event event = (Event) task;
                    s = String.format("E | %d | %s | %s", event.getIsDone() ? 1 : 0,
                            event.getDescription(), event.getAt());
                    break;
                default:
                    break;
            }

            if (i != taskList.getSize() - 1) {
                s += "\n";
            }
            writer.write(s);
        }
        writer.close();
    }
}
