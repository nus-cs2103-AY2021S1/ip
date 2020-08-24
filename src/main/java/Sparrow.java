import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Sparrow {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (handle(command)) {
            command = sc.nextLine();
        }
        sc.close();
    }

    public static boolean handle(String command) {
        String[] commandArr = command.trim().split("\\s+", 2);
        try {
            switch (commandArr[0]) {
            case "bye":
                reply("Bye. Hope t' see ye again soon!");
                return false;
            case "list":
                if (commandArr.length == 1) {
                    displayList(taskList);
                    break;
                } else if (commandArr.length == 2) {
                    try {
                        displayList(filterList(commandArr[1]));
                    } catch (DateTimeParseException e) {
                        System.out.println(standardExceptionMessage() + "Please enter a date in this format: yyyy-mm-dd");
                    }
                    break;
                }
            case "done":
                try {
                    markAsDone(commandArr[1]);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingTaskNumberException("No task number passed to done command.", e);
                }
            case "todo":
                try {
                    addTask("todo", commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyTodoDescriptionException("No description provided for todo.", e);
                }
                break;
            case "deadline":
                try {
                    addTask("deadline", commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDeadlineDescriptionException("No description provided for deadline.", e);
                }
                break;
            case "event":
                try {
                    addTask("event", commandArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyEventDescriptionException("No description provided for event.", e);
                }
                break;
            case "delete":
                try {
                    deleteTask(commandArr[1]);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingTaskNumberException("No task number passed to delete command.", e);
                }
            default:
                throw new UnknownCommandException(commandArr[0]);
            }
        } catch (MissingTaskNumberException e) {
            System.out.println(standardExceptionMessage() + "️ Please enter a task number after the \"done\"/\"delete\" command.");
        } catch (EmptyTodoDescriptionException e) {
            System.out.println(standardExceptionMessage() + "️ The description of a todo cannot be empty.");
        } catch (EmptyDeadlineDescriptionException e) {
            System.out.println(standardExceptionMessage() + "️ The description of a deadline cannot be empty.");
        } catch (EmptyEventDescriptionException e) {
            System.out.println(standardExceptionMessage() + "️ The description of an event cannot be empty.");
        } catch (UnknownCommandException e) {
            System.out.println(standardExceptionMessage() + "️ What be the meaning of this?");
        }
        return true;
    }

    public static void greet() {
        String welcome = "  _  _ _   ___ _                    \n" +
                " | || (_) |_ _( )_ __               \n" +
                " | __ | |  | ||/| '  \\              \n" +
                " |_||_|_| |___| |_|_|_|             \n" +
                " / __|_ __  __ _ _ _ _ _ _____ __ __\n" +
                " \\__ \\ '_ \\/ _` | '_| '_/ _ \\ V  V /\n" +
                " |___/ .__/\\__,_|_| |_| \\___/\\_/\\_/ \n" +
                "     |_|                            ";
        System.out.println(welcome);
        reply("How can I help ye?");
    }

    public static void reply(String message) {
        System.out.println("    ________________________________________");
        Scanner sc = new Scanner(message);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println("      " + line);
        }
        System.out.println("    ________________________________________");
        sc.close();
    }

    public static void addTask(String type, String details) {
        StringBuilder sb = new StringBuilder("Aye Aye Captain! I've added this task:\n  ");
        try {
            switch (type) {
            case "todo":
                Todo todo = new Todo(details);
                taskList.add(todo);
                sb.append(todo);
                break;
            case "deadline":
                try {
                    String[] taskDetails = details.trim().split("/by");
                    String description = taskDetails[0];
                    LocalDate dueDate = stringToDate(taskDetails[1].trim());

                    Deadline deadline = new Deadline(description, dueDate);
                    taskList.add(deadline);
                    sb.append(deadline);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidDeadlineByException("/by not passed", e);
                }
            case "event":
                try {
                    String[] taskDetails = details.trim().split("/at");
                    String description = taskDetails[0];
                    LocalDate date = stringToDate(taskDetails[1].trim());

                    Event event = new Event(description, date);
                    taskList.add(event);
                    sb.append(event);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidEventAtException("/at not passed", e);
                }

            }
            String summary = String.format("\nNow you have %d task(s) in the list.", taskList.size());
            sb.append(summary);
            reply(sb.toString());
        } catch (InvalidDeadlineByException e) {
            System.out.println(standardExceptionMessage() + "️ Please pass a /by to the deadline");
        } catch (InvalidEventAtException e) {
            System.out.println(standardExceptionMessage() + "️ Please pass a /at to the event");
        } catch (DateTimeParseException e) {
            System.out.println(standardExceptionMessage() + "Please enter a date in this format: yyyy-mm-dd");
        }
    }

    public static void displayList(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, taskList.get(i));
            sb.append(temp);
        }
        reply(sb.toString());
    }

    public static ArrayList<Task> filterList(String dateFilter) throws DateTimeParseException {
        String[] dateArr = dateFilter.trim().split("\\s+", 2);
        LocalDate dateToCompare = stringToDate(dateArr[1]);
        ArrayList<Task> filteredList = new ArrayList<>();

        switch (dateArr[0]) {
        case "on":
            for (Task task : taskList) {
                LocalDate taskDate;

                // Get date from Deadline/Event
                if (task instanceof Deadline) {
                    taskDate = ((Deadline) task).getDueDate();
                } else if (task instanceof Event) {
                    taskDate = ((Event) task).getDate();
                } else {
                    continue;
                }

                // Check if task's date is on date specified
                if (taskDate.isEqual(dateToCompare)) {
                    filteredList.add(task);
                }
            }
            break;
        case "before":
            for (Task task : taskList) {
                LocalDate taskDate;

                // Get date from Deadline/Event
                if (task instanceof Deadline) {
                    taskDate = ((Deadline) task).getDueDate();
                } else if (task instanceof Event) {
                    taskDate = ((Event) task).getDate();
                } else {
                    continue;
                }

                // Check if task's date is before date specified
                if (taskDate.isBefore(dateToCompare)) {
                    filteredList.add(task);
                }
            }
            break;
        }
        return filteredList;
    }

    public static void markAsDone(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (taskNumber <= taskList.size() && taskNumber > 0) {
                taskList.get(taskNumber - 1).markAsDone();
                reply("Jolly riddance! I've marked this task as done:\n" + taskList.get(taskNumber - 1));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }

    public static String standardExceptionMessage() {
        return "ARR!\uD83C\uDFF4\u200D\u2620\uFE0F️ ";
    }

    public static void deleteTask(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (taskNumber <= taskList.size() && taskNumber > 0) {
                Task removedTask = taskList.remove(taskNumber - 1);
                reply("Jolly riddance! I've deleted this task:\n" + "  " + removedTask.toString() + String.format("\nNow you have %d task(s) in the list.", taskList.size()));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }

    public static LocalDate stringToDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }
}
