import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Viscount {
    private static final String VISCOUNT_LOGO =
            "        _  _____  _____                  _    \n" +
            "       (_)/ ____|/ ____|                | |   \n" +
            " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
            " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
            "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
            "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";

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

    private static String convertTaskListToString() {
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

    private static void parseInput(List<String> arguments) throws ViscountException {
        String command = arguments.get(0);

        if (command.equals("list")) {
            Viscount.speak("Here are the tasks in your list:\n" + Viscount.convertTaskListToString());
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
                String dueDate = String.join(" ", arguments.subList(indexOfDueDate + 1, arguments.size()));

                if (description.isEmpty()) {
                    throw new ViscountMissingDescriptionException("deadline");
                } else if (dueDate.isEmpty()) {
                    throw new ViscountMissingArgumentDescriptionException("/by");
                } else {
                    Deadline deadline = new Deadline(description, false, dueDate);
                    Viscount.addToTaskList(deadline);
                }
            }
        } else if (command.equals("event")) {
            int indexOfEventTime = arguments.indexOf("/at");

            if (indexOfEventTime == -1) {
                throw new ViscountMissingArgumentException("/at");
            } else {
                String description = String.join(" ", arguments.subList(1, indexOfEventTime));
                String eventTime = String.join(" ", arguments.subList(indexOfEventTime + 1, arguments.size()));

                if (description.isEmpty()) {
                    throw new ViscountMissingDescriptionException("event");
                } else if (eventTime.isEmpty()) {
                    throw new ViscountMissingArgumentDescriptionException("/at");
                } else {
                    Event event = new Event(description, false, eventTime);
                    Viscount.addToTaskList(event);
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
