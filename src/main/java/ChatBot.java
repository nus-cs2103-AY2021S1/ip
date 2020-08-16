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
            if (input.equals("bye")) {
                bye();
                break;
            }

            if (input.equals("list")) {
                showList();
            } else {
                add(input);
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
            System.out.println(ConsoleColors.BLUE_BOLD.getColor()
                    + (i+1) + ". "
                    + "\t"
                    + toDoList.get(i));
        }
        System.out.println();
    }

    /**
     * Adds a string task into the arraylist.
     * @param item the name of the task the user entered
     */
    void add(String item) {
        Task task = new Task(item);
        this.toDoList.add(task);
        System.out.println(ConsoleColors.YELLOW.getColor()
                + this.botName + ": "
                + "[" + item + "] has been added to your list! \n"
                + ConsoleColors.RESET.getColor());
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
