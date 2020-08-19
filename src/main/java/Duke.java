import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String logo = " _   _       _   _ _            \n" +
            "| | | |     | | | (_)           \n" +
            "| |_| | ___ | |_| |_ _ __   ___ \n" +
            "|  _  |/ _ \\| __| | | '_ \\ / _ \\\n" +
            "| | | | (_) | |_| | | | | |  __/\n" +
            "\\_| |_/\\___/ \\__|_|_|_| |_|\\___| \n";
    private static final String divider = "<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>";
    private static final String welcomeMessage = divider + "\n Thanks for contacting Hotline! \n" +
            " How can I help you today? \n"
            + divider + "\n";
    private static final String goodbyeMessage = "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "██                  ████      ████████  ██████████████████      ████    ██  ▒▒██████████  ████████  ████████        ░░██    ██████████\n" +
            "██                  ████      ████████    ████████  ██████      ██      ██    ████████    ░░████▒▒    ████            ██      ▓▓      \n" +
            "██                  ████      ██████      ████████    ▒▒██      ██              ██████      ████                              ██      \n" +
            "████      ████      ████      ██████        ██████              ██            ████████                        ████            ██      \n" +
            "████      ████      ▒▒        ████          ▒▒████              ██          ████████████            ██      ██████            ██      \n" +
            "████      ████                ████    ░░      ████              ██        ████████████████          ██      ██████            ██      \n" +
            "████      ████                ██                ██              ██          ██████████████        ████      ████▒▒            ██      \n" +
            "████      ████      ████    ██░░                ▒▒      ██      ██            ▓▓██████████        ████                ██              \n" +
            "████      ████      ████    ██        ████              ████░░  ██            ████████████        ████▒▒              ██              \n" +
            "████      ████      ████    ██▒▒      ████    ████      ██████████      ██    ████████████        ██████░░          ██████          ██\n" +
            "████████████████████████████████████████████████████████████████████████████████████████████████████████████    ████████████    ██████\n" +
            "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████████      ████████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████            ██████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████      ██    ██████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████            ██████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████▓▓        ██  ████████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████                  ██████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████      ██          ██████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████                  ██████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████▒▒              ████████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "██████████████████        ████████████      ██████████      ██████▒▒        ██████▒▒        ██████  ██████░░████████████▒▒  ██████████\n" +
            "██████████████              ░░████            ████            ██              ██              ██    ██████    ░░            ██████████\n" +
            "████████████░░              ████                                                                      ██                    ██████████\n" +
            "████████████      ░░████░░████        ████            ████            ██░░            ██    ▒▒██              ██      ████████████████\n" +
            "██████████      ████                ██████          ██████            ████                  ░░████          ▒▒██          ████████████\n" +
            "██████████      ████                ██████          ██████            ████                      ████        ████          ████████████\n" +
            "██████████      ██████              ████            ████              ██      ██      ████      ████      ██████            ██████████\n" +
            "██████████░░                ▒▒                ██              ██              ██                ████      ██████            ██████████\n" +
            "████████████                ████            ████            ░░██            ████              ██████      ██████            ██████████\n" +
            "██████████████            ████████          ██████          ████          ██████            ████████      ██████            ██████████\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    private static void greetings() {
        System.out.println(logo);
        System.out.println(welcomeMessage);
    }

    public static void list() {
        System.out.println(divider);
        if (taskCount == 0) {
            System.out.println(" You've got no tasks now. \n" +
                    " If you want to get busy add more task. \n" +
                    " I'll remember them for you :)");
        } else {
            System.out.println(" Let me list out all your tasks...");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(divider + "\n");
    }

    public static void done(int index) {
        System.out.println(divider);
        if (index < 1 || taskCount < index) {
            System.out.println(" Sorry I cannot find your specified task :(");
        } else {
            System.out.println(" Congratulations for finishing this task! \n" +
                    " Let me mark this as done for you.");
            tasks.get(index - 1).completeTask();
            System.out.println("   " + tasks.get(index - 1));
        }
        System.out.println(divider + "\n");
    }

    public static void describe(int taskIndex) {
        System.out.println(divider + "\n Your task has been recorded. \n   " +
                tasks.get(taskIndex) + "\n You have " + taskCount +
                " tasks currently. \n" + divider + "\n");
    }
    
    public static String generateErrorMessage(String message) {
        return "\n" + divider + "\n" + message + divider + "\n";
    }

    private static void echo() {
        try {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String input = sc.nextLine().trim();
                String[] separated = input.split("\\s+");
                String command = separated[0];
                if (command.equals("bye")) {
                    System.out.println(goodbyeMessage);
                    break;
                } else if (command.equals("list")) {
                    list();
                } else if (command.equals("done")) {
                    if (separated.length <= 1) {
                        throw new DukeException(generateErrorMessage(" Task index must be specified :( \n" +
                                " Terminating Hotline... \n"));
                    }
                    try {
                        int index = Integer.parseInt(input.substring(5));
                        done(index);
                    } catch(NumberFormatException error) {
                        throw new DukeException(generateErrorMessage(" Task index must be an integer :( \n" +
                                " Terminating Hotline... \n"));
                    }
                } else {
                    if (separated.length <= 1) {
                        throw new DukeException(generateErrorMessage(" Task description cannot be empty :( \n" +
                                " Terminating Hotline... \n"));
                    }
                    if (command.equals("todo")) {
                        String description = input.substring(5);
                        tasks.add(new ToDo(description, false));
                        taskCount += 1;
                        describe(taskCount - 1);
                    } else if (command.equals("deadline")) {
                        String[] content = input.substring(9).split(" /by ");
                        if (content.length <= 1) {
                            throw new DukeException(generateErrorMessage(" Deadline date cannot be empty :( \n" +
                                    " Terminating Hotline... \n"));
                        }
                        String description = content[0];
                        String time = content[1];
                        tasks.add(new Deadline(description, false, time));
                        taskCount += 1;
                        describe(taskCount - 1);
                    } else if (command.equals("event")) {
                        String[] content = input.substring(6).split(" /at ");
                        if (content.length <= 1) {
                            throw new DukeException(generateErrorMessage(" Event date cannot be empty :( \n" +
                                    " Terminating Hotline... \n"));
                        }
                        String description = content[0];
                        String time = content[1];
                        tasks.add(new Event(description, false, time));
                        taskCount += 1;
                        describe(taskCount - 1);
                    } else {
                        throw new DukeException(generateErrorMessage(" Command not recognized :( \n" +
                                " Terminating Hotline... \n"));
                    }
                }
            }
        } catch(DukeException error) {
            System.out.println(error);
        }
    }

    public static void main(String[] args) {
        greetings();
        echo();
    }
}
