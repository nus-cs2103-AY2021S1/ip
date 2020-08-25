package duke;

import duke.exception.CalendarException;
import duke.exception.DukeException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static String filePath;
    private TaskList taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static List<Task> readFile() throws IOException, DukeException {
        Path folder = Path.of("data");
        Path file = folder.resolve(filePath);

        //Create a new directory by creating all parent directories first
        Files.createDirectories(folder);

        //if user is new and file does not exist, create the file
        if(!Files.exists(file)) {
            Files.createFile(file);
        }

        //read from file
        BufferedReader reader = Files.newBufferedReader(file);
        List<Task> tasks = new ArrayList<>(); //this does not update this.todos
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            try {
                System.out.println(currentLine);
                Task task = parseData(currentLine);
                tasks.add(task);
            } catch (StorageException e) {
                System.out.println(e.getMessage());
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            }
        }
        return tasks;
    }

    private static Task parseData(String line) throws DukeException {

        try {
            String[] parsed = line.split("\\s\\|\\s");
            Task task;
            if(parsed.length < 2) {
                throw new StorageException(line + "is in invalid format.");
            } else {
                String identifier = parsed[0]; //get the type of duke.task
                String doneIndicator = parsed[1];
                String taskName = parsed[2];

                if (identifier.equals("T")) {
                    if(doneIndicator.equals("1")) {
                        task = new Todo(taskName, true);
                    } else {
                        task = new Todo(taskName);
                    }

                } else if (identifier.equals("E")) {
                    //String dateAndTime = parsed[3];
                    String date = parsed[3];
                    //String[] dateTime = dateString.split("\\s");
                    //String date = dateTime[0];
                    LocalDate localDate = LocalDate.parse(date);

                    if(parsed.length < 5) {
                        if (doneIndicator.equals("1")) {
                            task = new Event(taskName, true, localDate);
                        } else {
                            task = new Event(taskName, localDate);
                        }

                    } else {
                        String time = parsed[4];
                        String[] startEndTime = time.split("-");
                        String startTime = startEndTime[0];
                        LocalTime localStartTime = LocalTime.parse(startTime);

                        if(startEndTime.length < 2) {
                            if (doneIndicator.equals("1")) {
                                task = new Event(taskName, true, localDate, localStartTime);
                            } else {
                                task = new Event(taskName, false, localDate, localStartTime);
                            }
                        } else {
                            String endTime = startEndTime[1];
                            LocalTime localEndTime = LocalTime.parse(endTime);
                            if (doneIndicator.equals("1")) {
                                task = new Event(taskName, true, localDate, localStartTime, localEndTime);
                            } else {
                                task = new Event(taskName, false, localDate, localStartTime, localEndTime);
                            }
                        }
                    }

                } else if (identifier.equals("D")) {
                    //String dateAndTime = parsed[3];
                    String date = parsed[3];
                    //String[] dateTime = dateAndTime.split(" ");
                    //String date = dateTime[0];
                    LocalDate localDate = LocalDate.parse(date);

                    if(parsed.length < 5) {
                        if (doneIndicator.equals("1")) {
                            task = new Deadline(taskName, true, localDate);
                        } else {
                            task = new Deadline(taskName, localDate);
                        }
                    } else {
                        String time = parsed[4];
                        LocalTime localTime = LocalTime.parse(time);
                        if (doneIndicator.equals("1")) {
                            task = new Deadline(taskName, true, localDate, localTime);
                        } else {
                            task = new Deadline(taskName, false, localDate, localTime);
                        }
                    }

                } else {
                    throw new StorageException("Invalid format. Moving on to the next task.");
                }

            }

//            String doneIndicator = parsed[1];
//            if (doneIndicator.equals("1")) {
//                duke.task.markAsDone();
//            }
            return task;

        } catch(DateTimeParseException e) {
            throw new CalendarException("Please input the correct date and time format. YYYY-MM-DD for date and HH:MM for time.");
        }

    }

    public static void updateData(List<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of("data/tasks.txt"));
            for(Task task : tasks) {
                String type = task.getType();
                Boolean status = task.getStatus();
                String taskName = task.getDescription();
                String stored = "";

                if(type.equals("T")) {
                    stored = String.format("%s | %d | %s", type, status ? 1 : 0, taskName);

                } else if (type.equals("E")) {
                    Event event = (Event) task;
                    LocalDate date = event.getDate();
                    LocalTime startTime = event.getStartTime();
                    LocalTime endTime = event.getEndTime();

                    if(startTime == null && endTime == null) {
                        stored = String.format("%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
                    } else if(endTime == null) {
                        stored = String.format("%s | %d | %s | %s | %s", type, status ? 1 : 0, taskName, date, startTime);
                    } else {
                        stored = String.format("%s | %d | %s | %s | %s-%s", type, status ? 1 : 0, taskName, date, startTime, endTime);
                    }

                } else if (type.equals("D")) {
                    Deadline deadline = (Deadline) task;
                    LocalDate date = deadline.getDeadline();
                    LocalTime time = deadline.getTime();

                    if(time == null) {
                        stored = String.format("%s | %d | %s | %s", type, status ? 1 : 0, taskName, date);
                    } else {
                        stored = String.format("%s | %d | %s | %s | %s", type, status ? 1 : 0, taskName, date, time);

                    }

                }
                writer.write(stored);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void showData() throws IOException {
//        Path folder = Path.of("data");
//        Path file = folder.resolve(filePath);
//
//        //Create a new directory by creating all parent directories first
//        Files.createDirectories(folder);
//
//        //if user is new and file does not exist, create the file
//        if(!Files.exists(file)) {
//            Files.createFile(file);
//        }
//        //read from file
//        BufferedReader reader = Files.newBufferedReader(file);
//        String currentLine;
//        System.out.println("Here are your duke.task(s) : ");
//        while((currentLine = reader.readLine()) != null) {
//            System.out.println(currentLine);
//        }
//    }

}
