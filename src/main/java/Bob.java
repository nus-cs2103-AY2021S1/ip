// Package dependencies
import java.util.ArrayList;
import java.util.Scanner;

/*================== Welcome to BOB ==================*/
/*
                    █▀▀▄ █▀▀█ █▀▀▄
                    █▀▀▄ █  █ █▀▀▄
                    ▀▀▀  ▀▀▀▀ ▀▀▀
            Also known as BERY ORDINARY BOT
*/


public class Bob {
    private final String output;
    private final ArrayList<Task> list;
    private final boolean hasExited;

    /*================ Static fields ================*/

    // Print when user first starts the program
    public static String INTRO =
            "█▀▀▄ █▀▀█ █▀▀▄\n" +
            "█▀▀▄ █  █ █▀▀▄\n" +
            "▀▀▀  ▀▀▀▀ ▀▀▀\n"  +
            "Hi, my name is BOB.\n" +
            "What can I do for you?";
    // Print when user exits the program
    public static String OUTRO = "See you soon, maybe?";

    private Bob (String output, ArrayList<Task> list) {
        this.output = output;
        this.list = list;
        this.hasExited = false;
    }

    private Bob (String output, ArrayList<Task> list, boolean hasExited) {
        this.output = output;
        this.list = list;
        this.hasExited = hasExited;
    }

    /*================ Private methods ================*/

    /*---- Task methods ---- */

    // Generates message after adding task
    private String afterAdd(Task task) {
        return "Yes boss, I have added this task to your list:\n" + "  " + task + "\n" +
                "Currently you have " + this.list.size() + " tasks in your list.";
    }

    // Adds an unfinished task to list and returns readable String
    private String addTodo(String description) {
        Task task = new Task(description);
        this.list.add(task);
        return afterAdd(task);
    }

    // Adds an unfinished deadline to list and returns readable String
    private String addDeadline(String description, String timeAndDate) {
        Deadline deadline = new Deadline(description, timeAndDate);
        this.list.add(deadline);
        return afterAdd(deadline);
    }

    // Adds an unfinished event to list and returns readable String
    private String addEvent(String description, String time) {
        Event event = new Event(description, time);
        this.list.add(event);
        return afterAdd(event);
    }

    /*---- List methods ---- */

    // Converts list to readable String
    private String convertList() {
        String output = "";
        int taskCompleted = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).checkIsDone()) {
                taskCompleted++;
            }
            int taskNumber = i + 1;
            output += taskNumber  + ". " + list.get(i) + "\n";
        }

        return  list.size() == 0
                ? "You currently have no tasks."
                : taskCompleted == list.size()
                ? "Wow congrats, you finished all your tasks.\n" + output
                : "You have " + (list.size() - taskCompleted) + " unfinished tasks.\n" + output;
    }

    // Marks task as done
    private String markTaskDone(int taskNum) throws BobListIndexOutOfBoundsException {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new BobListIndexOutOfBoundsException(list.size(), taskNum, "mark");
        }
        int index = taskNum - 1;
        Task task = list.get(index).markDone();
        list.set(index, task);
        return "I have marked the task as done, good job.\n" + task + "\n";
    }

    //Deletes the task from list
    private String deleteTask(int taskNum) throws BobListIndexOutOfBoundsException {
        if (taskNum > list.size() || taskNum <= 0) {
            throw new BobListIndexOutOfBoundsException(list.size(), taskNum, "delete");
        }
        int index = taskNum - 1;
        Task task = list.get(index);
        list.remove(index);
        return "I have deleted the task.\n" + task + "\n";
    }


    /*---- Command methods ---- */

    // Executes action based on command without need for user input
    private Bob execute(Command command) {
        switch (command) {
            case EXIT:
                return new Bob(OUTRO, this.list, true);
            case LIST:
                return new Bob(convertList(), this.list);
            default:
                return this;
        }
    }

    // Executes action based on command with need for user input
    private Bob execute(Command command, String input) throws BobListIndexOutOfBoundsException,
            BobEmptyDateException, BobInvalidNumberException {
        // for each case, treats return statement as break
        switch (command) {

        // If the command given by user is todo
        case TODO:
            String outputTodo = addTodo(input.substring(5));
            return new Bob(outputTodo, this.list);

        // If the command given by user is deadline, handles empty date exception
        case DEADLINE:
            String[] split1 = input.split("/");
            if (split1.length == 1) {
                throw new BobEmptyDateException();
            }
            String description1 = split1[0].substring(9);
            String by1 = split1[1];
            String outputDeadline = addDeadline(description1, by1);
            return new Bob(outputDeadline, this.list);

        // If the command given by user is event, handles empty date exception
        case EVENT:
            String[] split2 = input.split("/");
            if (split2.length == 1) {
                throw new BobEmptyDateException();
            }
            String description2 = split2[0].substring(6);
            String by2 = split2[1];
            String outputEvent = addEvent(description2, by2);
            return new Bob(outputEvent, this.list);

        // If the command given by user is done, handles invalid number and number out of range exceptions
        case DONE:
            try {
                int taskNumber = Integer.parseInt(input.substring(4).replaceAll("\\s+", ""));
                String out = markTaskDone(taskNumber);
                return new Bob(out, this.list);
            } catch (BobListIndexOutOfBoundsException e){
                throw e;
            } catch (NumberFormatException e) {
                throw new BobInvalidNumberException();
            }

        // If the command given by user is delete, handles invalid number and number out of range exceptions
        case DELETE:
            try {
                int taskNumber = Integer.parseInt(input.substring(6).replaceAll("\\s+", ""));
                String out = deleteTask(taskNumber);
                return new Bob(out, this.list);
            } catch (BobListIndexOutOfBoundsException e){
                throw e;
            } catch (NumberFormatException e) {
                throw new BobInvalidNumberException();
            }

        default:
            return this;
        }
    }

    /*================ Public methods ================*/

    /**
     * Chatbot's current output getter.
     * @return
     */
    public String getOutput() {
        String divider = "========================================================\n";
        return divider + this.output + "\n" + divider;
    }

    /**
     *Checks if Bob has ceased.
     * @return
     */
    public boolean checkHasExited() {
        return this.hasExited;
    }

    /**
     * Initializes Bob to introduce itself
     * @return
     */
    public static Bob initializeBob() {
        return new Bob(INTRO, new ArrayList<>());
    }



    // Handles user input which decides which command Bob executes, handles all exceptions
    public Bob nextCommand(String input) throws BobInvalidCommandException,
            BobEmptyTaskException, BobListIndexOutOfBoundsException, BobEmptyDateException, BobInvalidNumberException {
        try {
            if (input.equals("bye")) {
                return execute(Command.EXIT);
            } else if (input.equals("list")) {
                return execute(Command.LIST);
            } else if (input.length() >= 4 && input.startsWith("done")) {
                return execute(Command.DONE, input);
            } else if (input.length() >= 6 && input.startsWith("delete")) {
                return execute(Command.DELETE, input);
            } else if (input.length() >= 4 && input.startsWith("todo")) {
                if (input.substring(4).replaceAll("\\s+", "").length() == 0) {
                    throw new BobEmptyTaskException();
                }
                return execute(Command.TODO, input);
            } else if (input.length() >= 5 && input.startsWith("event")) {
                if (input.substring(5).replaceAll("\\s+", "").length() == 0) {
                    throw new BobEmptyTaskException();
                }
                return execute(Command.EVENT, input);
            } else if (input.length() >= 8 && input.startsWith("deadline")) {
                if (input.substring(8).replaceAll("\\s+", "").length() == 0) {
                    throw new BobEmptyTaskException();
                }
                return execute(Command.DEADLINE, input);
                // If user's command is invalid/ not recognisable by Bob
            } else {
                throw new BobInvalidCommandException();
            }
        } catch (BobListIndexOutOfBoundsException e) {
            throw e;
        } catch (BobEmptyDateException e) {
            throw e;
        } catch (BobInvalidNumberException e) {
            throw e;
        }

    }


    /**
     * Main environment where Bob runs
     * @param args
     */
    public static void main(String[] args) {
        // Initializing stage
        Bob chatbot = initializeBob();
        // Output for greeting
        System.out.println(chatbot.getOutput());

        Scanner sc = new Scanner(System.in);

        while (!chatbot.checkHasExited()) {
            try {
                String userInput = sc.nextLine();
                chatbot = chatbot.nextCommand(userInput);
                System.out.println(chatbot.getOutput());
            } catch (BobInvalidCommandException e) {
                System.out.println(e.toString());
            } catch (BobEmptyTaskException e) {
                System.out.println(e.toString());
            } catch (BobListIndexOutOfBoundsException e) {
                System.out.println(e.toString());
            } catch (BobEmptyDateException e) {
                System.out.println(e.toString());
            } catch (BobInvalidNumberException e) {
                System.out.println(e.toString());
            }
        }

        sc.close();
    }
}
