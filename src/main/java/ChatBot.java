import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Encapsulates the chatBot and its behavior.
 */
class ChatBot {
    String logo;
    String user;
    String botName;
    List<String> toDoList;

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
            reply(input);
        }
        sc.close();
    }

    /**
     * Replies the user by echoing his input.
     * @param input the string that the user enters
     */
    void reply(String input) {
        System.out.println(ConsoleColors.YELLOW.getColor()
                + this.botName + ": " + input + "\n"
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
