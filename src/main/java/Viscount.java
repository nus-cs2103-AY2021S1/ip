import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Viscount {
    private static final String VISCOUNT_LOGO =
            "        _  _____  _____                  _    \n" +
            "       (_)/ ____|/ ____|                | |   \n" +
            " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
            " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
            "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
            "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";
    private static final String DATA_FILE_PATH = System.getProperty("user.dir") + "/data/viscount.txt";
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TASK_DATA_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");

    private static List<Task> tasks = new ArrayList<>();

    private static void printLogo() {
        System.out.println(Viscount.VISCOUNT_LOGO);
    }

    private static void speak(String message) {
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println();
    }

    private static String convertTaskListToString(List<Task> tasks) {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += (i == tasks.size() - 1)
                ? String.format("%d.%s", i + 1, tasks.get(i).toString())
                : String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }

        return result;
    }

    private static void addToTaskList(Task task) throws IOException {
        tasks.add(task);
        Viscount.saveToDisk();

        Viscount.speak("Very well. I've added this task:\n"
                + task.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.size()));
    }

    private static void removeFromTaskList(int taskIndex) throws IOException {
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        Viscount.saveToDisk();

        Viscount.speak("Very well. I've removed this task:\n"
                + task.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.size()));
    }

    private static void markAsDone(Task task) throws IOException {
        task.setDone(true);
        Viscount.saveToDisk();
        
        Viscount.speak("Very good! I have marked this task as done:\n" + task.toString());
    }
    
    //@@author sc-arecrow-reused
    //Reused from https://stackoverflow.com/a/48281350 with minor modifications
    private static LocalDateTime parseDateTime(String dateTimeString, DateTimeFormatter formatter) 
            throws DateTimeParseException {
        LocalDateTime dateTime;

        TemporalAccessor ta = formatter.parseBest(dateTimeString, LocalDateTime::from, LocalDate::from);
        
        if (ta instanceof LocalDateTime) {
            dateTime = (LocalDateTime) ta;
        } else {
            dateTime = ((LocalDate) ta).atStartOfDay();
        }
        
        return dateTime;
    }
    //@@author
    
    private static void filterAndListTasks(String modifier, String dateString) throws ViscountException {
        if (!modifier.isEmpty()) {
            try {
                TaskType.valueOf(modifier.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ViscountUnknownCommandException(modifier);
            }
        }

        Predicate<Task> filterByModifier = task -> modifier.isEmpty()
                || task.getTaskType() == TaskType.valueOf(modifier.toUpperCase());

        if (dateString.isEmpty()) {
            List<Task> filteredTasks = tasks
                    .stream()
                    .filter(filterByModifier)
                    .collect(Collectors.toList());
            
            Viscount.speak(String.format("Here are the %ss in your list:\n%s",
                    modifier.isEmpty() ? "task" : modifier,
                    Viscount.convertTaskListToString(filteredTasks)));
        } else {
            try {
                LocalDateTime queriedDateTime = dateString.equals("today")
                        ? LocalDate.now().atStartOfDay()
                        : Viscount.parseDateTime(dateString, Viscount.INPUT_DATE_TIME_FORMATTER);

                List<Task> filteredTasks = tasks
                        .stream()
                        .filter(Task::hasDateTime)
                        .filter(filterByModifier)
                        .filter(task -> task.getDateTime().toLocalDate().isEqual(queriedDateTime.toLocalDate()))
                        .sorted(Comparator.comparing(Task::getDateTime))
                        .collect(Collectors.toList());
                
                Viscount.speak(String.format("Here are the %ss occurring %s in your list:\n%s",
                        modifier.isEmpty() ? "task" : modifier,
                        dateString.equals("today") 
                                ? dateString 
                                : "on " + queriedDateTime.format(Viscount.OUTPUT_DATE_FORMATTER),
                        Viscount.convertTaskListToString(filteredTasks)));
            } catch (DateTimeParseException e) {
                throw new ViscountDateTimeParseException("date query");
            }
        }
    }

    private static void parseInput(List<String> arguments) throws ViscountException, IOException {
        String command = arguments.get(0);

        if (command.equals("list")) {
            int indexOfDate = arguments.indexOf("/on");
            
            if (indexOfDate == -1) {
                String modifier = String.join(" ", arguments.subList(1, arguments.size()));
                Viscount.filterAndListTasks(modifier, "");
            } else {
                String modifier = String.join(" ", arguments.subList(1, indexOfDate));
                String dateString = String.join(" ", arguments.subList(indexOfDate + 1, arguments.size()));
                
                if (modifier.equals("todo")) {
                    Viscount.speak("Alas, todos do not have dates! Try again without /on");
                } else if (dateString.isEmpty()) {
                    throw new ViscountMissingArgumentDescriptionException("/on");
                } else {
                    Viscount.filterAndListTasks(modifier, dateString);
                }
            }            
        } else if (command.equals("todo")) {
            String description = String.join(" ", arguments.subList(1, arguments.size()));

            if (description.isEmpty()) {
                throw new ViscountMissingDescriptionException("todo");
            } else {
                Todo todo = new Todo(description, false);
                Viscount.addToTaskList(todo);
            }
        } else if (command.equals("deadline")) {
            int indexOfDueDate = arguments.indexOf("/by");

            if (indexOfDueDate == -1) {
                throw new ViscountMissingArgumentException("/by");
            } else {
                String description = String.join(" ", arguments.subList(1, indexOfDueDate));
                String dueDateString = String.join(" ", arguments.subList(indexOfDueDate + 1, arguments.size()));

                if (description.isEmpty()) {
                    throw new ViscountMissingDescriptionException("deadline");
                } else if (dueDateString.isEmpty()) {
                    throw new ViscountMissingArgumentDescriptionException("/by");
                } else {
                    try {
                        LocalDateTime dueDate = Viscount.parseDateTime(dueDateString, Viscount.INPUT_DATE_TIME_FORMATTER);
                        Deadline deadline = new Deadline(description, false, dueDate);
                        Viscount.addToTaskList(deadline);
                    } catch (DateTimeParseException e) {
                        throw new ViscountDateTimeParseException("due date");
                    }
                }
            }
        } else if (command.equals("event")) {
            int indexOfEventTime = arguments.indexOf("/at");

            if (indexOfEventTime == -1) {
                throw new ViscountMissingArgumentException("/at");
            } else {
                String description = String.join(" ", arguments.subList(1, indexOfEventTime));
                String eventTimeString = String.join(" ", arguments.subList(indexOfEventTime + 1, arguments.size()));

                if (description.isEmpty()) {
                    throw new ViscountMissingDescriptionException("event");
                } else if (eventTimeString.isEmpty()) {
                    throw new ViscountMissingArgumentDescriptionException("/at");
                } else {
                    try {
                        LocalDateTime eventTime = Viscount.parseDateTime(eventTimeString, Viscount.INPUT_DATE_TIME_FORMATTER);
                        Event event = new Event(description, false, eventTime);
                        Viscount.addToTaskList(event);
                    } catch (DateTimeParseException e) {
                        throw new ViscountDateTimeParseException("event date");
                    }
                }
            }
        } else if (command.equals("done")) {
            if (arguments.size() < 2) {
                throw new ViscountMissingArgumentException("task number");
            } else {
                int indexOfTask = 0;

                try {
                    indexOfTask = Integer.parseInt(arguments.get(1));
                    Viscount.markAsDone(tasks.get(indexOfTask - 1));
                } catch (NumberFormatException e) {
                    throw new ViscountNumberFormatException(arguments.get(1));
                } catch (IndexOutOfBoundsException e) {
                    throw new ViscountIndexOutOfBoundsException(indexOfTask);
                }
            }
        } else if (command.equals("delete")) {
            if (arguments.size() < 2) {
                throw new ViscountMissingArgumentException("task number");
            } else {
                int indexOfTask = 0;

                try {
                    indexOfTask = Integer.parseInt(arguments.get(1));
                    Viscount.removeFromTaskList(indexOfTask - 1);
                } catch (NumberFormatException e) {
                    throw new ViscountNumberFormatException(arguments.get(1));
                } catch (IndexOutOfBoundsException e) {
                    throw new ViscountIndexOutOfBoundsException(indexOfTask);
                }
            }
        } else {
            throw new ViscountUnknownCommandException(command);
        }
    }

    private static void chat() throws IOException {
        Viscount.speak("Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?");
        
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            List<String> arguments = Arrays.asList(input.split(" "));

            try {
                Viscount.parseInput(arguments);
            } catch (ViscountException e) {
                Viscount.speak(e.toString());
            } finally {
                input = sc.nextLine();
            }
        }

        Viscount.speak("Farewell my friend, I hope to see you again!");
    }

    private static void loadFromDisk() throws IOException {
        System.out.println(">> Loading data...");
        
        Path path = Paths.get(Viscount.DATA_FILE_PATH);
        boolean doesFileExist = Files.exists(path);

        if (doesFileExist) {
            File f = new File(Viscount.DATA_FILE_PATH);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    // If the data file has empty lines, skip them
                    continue;
                } else {
                    List<String> taskData = Arrays.asList(line.split("\\|"));

                    TaskType taskType = TaskType.valueOf(taskData.get(0));
                    boolean taskIsDone = !taskData.get(1).equals("0");
                    String taskDescription = taskData.get(2);

                    if (taskType == TaskType.TODO) {
                        tasks.add(new Todo(taskDescription, taskIsDone));
                    } else if (taskType == TaskType.DEADLINE) {
                        LocalDateTime dueDate = Viscount.parseDateTime(taskData.get(3), Viscount.TASK_DATA_DATE_TIME_FORMATTER);
                        tasks.add(new Deadline(taskDescription, taskIsDone, dueDate));
                    } else if (taskType == TaskType.EVENT) {
                        LocalDateTime eventTime = Viscount.parseDateTime(taskData.get(3), Viscount.TASK_DATA_DATE_TIME_FORMATTER);
                        tasks.add(new Event(taskDescription, taskIsDone, eventTime));
                    }
                }
            }
        } else {
            Files.write(path, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        }

        System.out.println(">> Data loaded successfully.");
    }

    private static void saveToDisk() throws IOException {
        Path path = Paths.get(Viscount.DATA_FILE_PATH);
        List<String> savedData = new ArrayList<>();

        for (Task task : tasks) {
            savedData.add(task.toTaskData());
        }

        Files.write(path, savedData, StandardCharsets.UTF_8);
    }
    
    private static void initialise() throws IOException {
        Viscount.printLogo();
        Viscount.loadFromDisk();
    }

    public static void main(String[] args) {
        try {
            Viscount.initialise();
            Viscount.chat();
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println(">> Viscount shutting down.");
        }
    }
}
