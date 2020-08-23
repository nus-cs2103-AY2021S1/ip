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
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

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

    private static void addToTaskList(Task task) {
        tasks.add(task);

        Viscount.speak("Very well. I've added this task:\n"
                + task.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.size()));
    }

    private static void removeFromTaskList(int taskIndex) {
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        Viscount.speak("Very well. I've removed this task:\n"
                + task.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.size()));
    }

    private static void markAsDone(Task task) {
        task.setDone(true);
        
        Viscount.speak("Very good! I have marked this task as done:\n" + task.toString());
    }
    
    //@@author sc-arecrow-reused
    //Reused from https://stackoverflow.com/a/48281350 with minor modifications
    private static LocalDateTime formatDateTime(String dateTimeString) throws DateTimeParseException {
        LocalDateTime dateTime;

        TemporalAccessor ta = INPUT_DATE_TIME_FORMATTER.parseBest(dateTimeString, LocalDateTime::from, LocalDate::from);
        
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
                        : Viscount.formatDateTime(dateString);

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

    private static void parseInput(List<String> arguments) throws ViscountException {
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
                Todo todo = new Todo(description);
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
                        LocalDateTime dueDate = Viscount.formatDateTime(dueDateString);
                        Deadline deadline = new Deadline(description, dueDate);
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
                        LocalDateTime eventTime = Viscount.formatDateTime(eventTimeString);
                        Event event = new Event(description, eventTime);
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

    private static void chat() {
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
    }

    public static void main(String[] args) {
        Viscount.printLogo();

        Viscount.speak("Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?");

        Viscount.chat();

        Viscount.speak("Farewell my friend, I hope to see you again!");
    }
}
