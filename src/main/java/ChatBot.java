import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Encapsulates the chatBot and its behavior.
 */
class ChatBot {
    private String logo;
    private String user;
    private String botName;
    private List<Task> toDoList;

    /**
     * Instantiates a chatBot with a name.
     * @param botName the name of the chatBot
     */
    ChatBot(String botName) {
        logo = logo = "#    #   ##   # ###### ###### #    #\n"
                + "#   #   #  #  #     #  #      ##   #\n"
                + "####   #    # #    #   #####  # #  #\n"
                + "#  #   ###### #   #    #      #  # #\n"
                + "#   #  #    # #  #     #      #   ##\n"
                + "#    # #    # # ###### ###### #    #\n";
        user = "You: ";
        this.botName = botName;
        toDoList = new ArrayList<>();
    }

    /**
     * Prints a welcome message for the user.
     */
    void welcome() {
         String welcomeMessage = "Konichiwa! Welcome to Kaizen \n"
                 + "I am " + this.botName + " what can I do for you today?\n";

        System.out.println(this.logo + "\n"
                + ConsoleColors.YELLOW.getColor() + welcomeMessage + ConsoleColors.RESET.getColor());
    }

    /**
     * Interacts with the user based on his input.
     */
    void getInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(this.user);
            String input = sc.nextLine();

            // guard clause for when user says 'bye'. Program will exit while loop.
            if (input.toLowerCase().equals("bye")) {
                bye();
                break;
            }

            if (input.toLowerCase().equals("list")) {
                showList();
            } else {
                String[] inputArray = input.split(" ", 2); // separates the first word from the rest
                if (inputArray[0].toLowerCase().toLowerCase().equals("done")) {
                    makeTaskDone(inputArray[1]);
                } else {
                    add(inputArray);
                }
            }
        }
        sc.close();
    }

    /**
     * Presents the user with the list of tasks
     */
    void showList() {
        System.out.println(ConsoleColors.YELLOW.getColor()
                + this.botName + ": "
                + "Here are your tasks!"
                + ConsoleColors.RESET.getColor());
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.printf("%-20s\n",
                    ConsoleColors.BLUE_BOLD.getColor()
                    + (i+1) + ". "
                    + toDoList.get(i)
                    + ConsoleColors.RESET.getColor());
        }
        System.out.println();
    }

    /**
     * Produce a done Task
     * @param taskString the position of the task in the arraylist
     */
    void makeTaskDone(String taskString) {
        try {
            int taskNumber = Integer.parseInt(taskString);
            if (taskNumber <= 0 || taskNumber > this.toDoList.size()) {
                System.out.println("Sorry, no such task!");
                return;
            }

            Task currentTask = this.toDoList.get(taskNumber - 1);
            Task newTask = currentTask.markAsDone();
            this.toDoList.set(taskNumber - 1, newTask);
            System.out.println("Sugoi! This task is done!");
            System.out.println(newTask);

        } catch(NumberFormatException e) {
            System.out.println(e);
            System.out.println(taskString + " is not an integer!");
        }
    }

    /**
     * Adds a string task into the arraylist.
     * @param inputArray the name of the task the user entered
     */
    void add(String[] inputArray) {
        if (inputArray[0].toLowerCase().equals("todo")) {

        }
//        Task task = new Task(item);
//        this.toDoList.add(task);
//        System.out.println(ConsoleColors.YELLOW.getColor()
//                + this.botName + ": "
//                + "[" + item + "] has been added to your list! \n"
//                + ConsoleColors.RESET.getColor());
    }

    /**
     * Says 'bye' to the user.
     */
    void bye() {
        System.out.println(ConsoleColors.YELLOW.getColor()
                + this.botName + ": "
                + "Sayonara! See you again my friend!"
                + ConsoleColors.RESET.getColor());
    }
}
