import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
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

    private static List<LocalDateTime> customDateTimeFormatter(String dateTimeString) throws DukeInvalidDateTimeInputException {
        //dateTimeString should be given in "dd/mm/yyyy hhmm"
        //will use manual parser to check for invalid date time inputs
        List<LocalDateTime> results = new ArrayList<>();
        //results returns index 0 as date, index 1 as time
        String[] tokens = dateTimeString.split(" ");
        String dateString = tokens[0];
        String[] dateTokens = dateString.split("/");
        if (dateTokens.length != 3) {
            throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date format!");
        } else {
            int year = Integer.parseInt(dateTokens[2]);
            int month = Integer.parseInt(dateTokens[1]);
            int day = Integer.parseInt(dateTokens[0]);
            try {
                LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0);
                results.add(date);
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid date. Date do not exist!");
            }
        }
        if (tokens.length == 1) {
        } else {
            String timeString = tokens[1];
            if (timeString.length() != 4) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid time format!");
            }
            try {
                int hr = Integer.parseInt(timeString.substring(0, 2));
                int min = Integer.parseInt(timeString.substring(2));
                results.add(LocalDateTime.of(LocalDate.now(), LocalTime.of(hr, min)));
            } catch (DateTimeException e) {
                throw new DukeInvalidDateTimeInputException("☹ OOPS!!! Invalid time. You can only input up to 23hr and 59min.");
            }
        }
        return results;
    }

    public List<Task> load() throws DukeInvalidDataException, DukeInvalidStoragePathException, DukeInvalidData, DukeInvalidDateTimeInputException {
        File file = new File(filePath);
        List<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String next = sc.nextLine();
                String[] tokens = next.split(" \\| ");
                if (tokens.length < 3) {
                    throw new DukeInvalidData("Oops data is invalid");
                }
                String taskType = tokens[0];
                String isDone = tokens[1];
                String description = tokens[2];
                Task task;
                if (taskType.equals("T")) {
                    task = new Todo(description);
                } else if (tokens.length != 4) {
                    throw new DukeInvalidData("Oops data is invalid");
                } else if (taskType.equals("D")) {
                    try {
                        List<LocalDateTime> ldtList = customDateTimeFormatter(tokens[3]);
                        LocalDate date = ldtList.get(0).toLocalDate();
                        LocalTime time = ldtList.size() == 2
                                ? ldtList.get(1).toLocalTime()
                                : null;
                        task = new Deadline(description, date, time);
                    } catch (DukeInvalidDateTimeInputException e) {
                        throw e;
                    }
                } else if (taskType.equals("E")) {
                    List<LocalDateTime> ldtList = customDateTimeFormatter(tokens[3]);
                    LocalDate date = ldtList.get(0).toLocalDate();
                    LocalTime time = ldtList.size() == 2
                            ? ldtList.get(1).toLocalTime()
                            : null;
                    task = new Event(description, date, time);
                } else {
                    throw new DukeInvalidData("Oops data is invalid");
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                    list.add(task);
                } else if (isDone.equals("0")) {
                    list.add(task);
                } else {
                    throw new DukeInvalidData("Oops data is invalid");
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

    public void save(TaskList taskList) throws IOException {
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            TaskType type = task.taskType;
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
