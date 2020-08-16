// Package dependancies
import java.util.*;

/*================== Welcome to BOB ==================*/
/*
                ██████╗░░█████╗░██████╗░
                ██╔══██╗██╔══██╗██╔══██╗
                ██████╦╝██║░░██║██████╦╝
                ██╔══██╗██║░░██║██╔══██╗
                ██████╦╝╚█████╔╝██████╦╝
                ╚═════╝░░╚════╝░╚═════╝░

            Also known as BERY ORDINARY BOT
*/


public class Bob {
    private final String output;
    private final ArrayList<Task> list;
    private final boolean exited;

    /*================ Static fields ================*/

    // Print when user first starts the program
    public static String INTRO = "Hi, my name is BOB.\n" +
            "What can I do for you?";
    // Print when user exits the program
    public static String OUTRO = "See you soon, maybe?";

    private Bob (String output, ArrayList<Task> list) {
        this.output = output;
        this.list = list;
        this.exited = false;
    }

    private Bob (String output, ArrayList<Task> list, boolean exited) {
        this.output = output;
        this.list = list;
        this.exited = exited;
    }

    /*================ Private methods ================*/

    // Adds an unfinished task to list and returns readable String
    private String addTask(String description) {
        Task task = new Task(description);
        this.list.add(task);
        return  "\"" + description + "\" " + "is added to your list.";
    }

    // Converts list to readable String
    private String convertList() {
        String output = "";
        int taskCompleted = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isitDone()) {
                taskCompleted++;
            }
            int taskNumber = i + 1;
            output += taskNumber  + ". " + list.get(i) + "\n";
        }

        return  list.size() == 0
                ? "Wow, you currently have no tasks."
                : taskCompleted == list.size()
                ? "Wow congrats, you finished all your tasks.\n" + output
                : "You have " + (list.size() - taskCompleted) + " unfinished tasks.\n" + output;
    }

    // Marks task as done
    private String markTaskDone(int taskNum) {
        int index = taskNum - 1;
        Task task = list.get(index).markDone();
        list.set(index, task);
        return "I have marked the task as done, good job. \n" + task + "\n";
    }

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
    private Bob execute(Command command, String input) {
        switch (command) {
            case ADD:
                String output = addTask(input);
                return new Bob(output, this.list);
            case DONE:
                int taskNumber = Integer.parseInt(input.substring(5,6));
                String out = markTaskDone(taskNumber);
                return new Bob(out, this.list);
            default:
                return this;
        }
    }

    /*================ Public methods ================*/

    // Chatbot's current output getter
    public String getOutput() {
        String divider = "========================================================\n";
        return divider + this.output + "\n" + divider;
    }

    // Checks if Bob has ceased
    public boolean hasExited() {
        return this.exited;
    }

    // Initializes Bob to introduce itself
    public static Bob initializeBob() {
        return new Bob(INTRO, new ArrayList<Task>());
    }



    // Handles user input which decides which command Bob executes
    public Bob nextCommand(String input) {
        if (input.toLowerCase().equals("bye")) {
            return execute(Command.EXIT);
        } else if (input.toLowerCase().equals("list")) {
            return execute(Command.LIST);
        } else if (input.length() == 6 && input.toLowerCase().substring(0,4).equals("done")) {
            return execute(Command.DONE, input);
        } else {
            return execute(Command.ADD, input);
        }
    }

    public static void main(String[] args) {
        // Initializing stage
        Bob chatbot = initializeBob();
        // Output for greeting
        System.out.println(chatbot.getOutput());

        Scanner sc = new Scanner(System.in);

        while (!chatbot.hasExited()) {
            String userInput = sc.nextLine();
            chatbot = chatbot.nextCommand(userInput);
            System.out.println(chatbot.getOutput());
        }

        sc.close();
    }
}
