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
    
    private enum Errors {
        UNDEFINED_INDEX,
        UNDEFINED_DESCRIPTION,
        UNDEFINED_DEADLINE_TIME,
        UNDEFINED_EVENT_TIME,
        WRONG_TYPE,
        UNRECOGNIZED,
    }
    
    private enum Commands {
        BYE,
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
    }

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
    
    public static void delete(int index) {
        System.out.println(divider);
        if (index < 1 || taskCount < index) {
            System.out.println(" Sorry I cannot find your specified task :(");
        } else {
            System.out.println(" Okay, I will remove this task for you");
            System.out.println("   " + tasks.get(index - 1));
            tasks.remove(index - 1);
            taskCount -= 1;
            System.out.println(" You have " + taskCount +
                    " tasks currently.");
        }
        System.out.println(divider + "\n");
    }
    
    public static String generateException(Errors type) throws DukeException {
        String message = "";
        switch (type) {
        case WRONG_TYPE:
            message = " Task index must be an integer :( \n" +
                    " Terminating Hotline... \n";
            break;
        case UNDEFINED_DESCRIPTION:
            message = " Task description cannot be empty :( \n" +
                    " Terminating Hotline... \n";
            break;
        case UNDEFINED_INDEX:
            message = " Task index must be specified :( \n" +
                    " Terminating Hotline... \n";
            break;
        case UNDEFINED_DEADLINE_TIME:
            message = " Deadline date cannot be empty :( \n" +
                    " Terminating Hotline... \n";
            break;    
        case UNDEFINED_EVENT_TIME:
            message = " Event date cannot be empty :( \n" +
                    " Terminating Hotline... \n";
            break;
        default:
            message = " Command not recognized :( \n" +
                    " Terminating Hotline... \n";
            break;
        }
        throw new DukeException("\n" + divider + "\n" + message + divider + "\n");
    }

    private static void echo() throws DukeException {
        try {
            Scanner sc = new Scanner(System.in);
            boolean isFinished = false;
            while (sc.hasNext()) {
                String input = sc.nextLine().trim();
                String[] separated = input.split("\\s+");
                Commands command = Commands.valueOf(separated[0].toUpperCase());
                switch (command) {
                case BYE:
                    System.out.println(goodbyeMessage);
                    isFinished = true;
                    break;
                case LIST:
                    list();
                    break;
                case DONE:
                case DELETE:
                    if (separated.length <= 1) {
                        generateException(Errors.UNDEFINED_INDEX);
                    }
                    try {
                        int index = Integer.parseInt(separated[1]);
                        switch (command) {
                        case DONE:
                            done(index);
                            break;
                        default:
                            delete(index);
                            break;
                        }
                    } catch(NumberFormatException error) {
                        generateException(Errors.WRONG_TYPE);
                    }
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    if (separated.length <= 1) {
                        generateException(Errors.UNDEFINED_DESCRIPTION);
                    }
                    String description;
                    String[] content;
                    String time;
                    switch (command) {
                    case TODO:
                        description = input.substring(5);
                        tasks.add(new ToDo(description, false));
                        taskCount += 1;
                        describe(taskCount - 1);
                        break;
                    case DEADLINE:
                        content = input.substring(9).split(" /by ");
                        if (content.length <= 1) {
                            generateException(Errors.UNDEFINED_DEADLINE_TIME);
                        }
                        description = content[0];
                        time = content[1];
                        tasks.add(new Deadline(description, false, time));
                        taskCount += 1;
                        describe(taskCount - 1);
                        break;
                    default:
                        content = input.substring(6).split(" /at ");
                        if (content.length <= 1) {
                            generateException(Errors.UNDEFINED_EVENT_TIME);
                        }
                        description = content[0];
                        time = content[1];
                        tasks.add(new Event(description, false, time));
                        taskCount += 1;
                        describe(taskCount - 1);
                        break;
                    }
                    break;
                default:
                    generateException(Errors.UNRECOGNIZED);
                    break;
                }
                if (isFinished) {
                    break;
                }
            }
        } catch(IllegalArgumentException error) { 
            generateException(Errors.UNRECOGNIZED);
        }
    }

    public static void main(String[] args) {
        try {
            greetings();
            echo();
        } catch (DukeException error) {
            System.out.println(error);
        }
    }
}
