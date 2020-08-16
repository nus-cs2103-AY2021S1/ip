import java.util.Scanner;

/**
 * This is the main class which handles user input.
 */
public class Main {
    //Commands for the bot to execute
    public enum Command {
        LIST, DONE, NONE,
        TODO, DEADLINE, EVENT
    }
    public static void main(String[] args) {
        //The logo to be used for TaskBot
        String logo =
                "████████╗░█████╗░░██████╗██╗░░██╗██████╗░░█████╗░████████╗\n" +
                "╚══██╔══╝██╔══██╗██╔════╝██║░██╔╝██╔══██╗██╔══██╗╚══██╔══╝\n" +
                "░░░██║░░░███████║╚█████╗░█████═╝░██████╦╝██║░░██║░░░██║░░░\n" +
                "░░░██║░░░██╔══██║░╚═══██╗██╔═██╗░██╔══██╗██║░░██║░░░██║░░░\n" +
                "░░░██║░░░██║░░██║██████╔╝██║░╚██╗██████╦╝╚█████╔╝░░░██║░░░\n" +
                "░░░╚═╝░░░╚═╝░░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░░░░╚═╝░░░\n";

        //Initialising the Taskbot
        Taskbot tb = new Taskbot(logo);

        //Prints the title to the console and greets the user
        tb.printTitle();
        tb.greet();

        //Initialises a Scanner to take in user input
        Scanner sc = new Scanner(System.in);

        Command cmd = Command.NONE;
        //Loops while the user does not input "bye"
        label:
        while (sc.hasNextLine()) {
            int taskIndex = -1;
            String input = sc.nextLine();

            //Splits the string to find keywords for commands
            String[] key = input.split(" ", 2);

            switch (key[0]) {
                case "bye":
                    break label;
                case "list":
                    cmd = Command.LIST;
                    break;
                case "done":
                    cmd = Command.DONE;
                    taskIndex = Integer.parseInt(key[1]) - 1;
                    break;
                case "todo":
                    cmd = Command.TODO;
                    break;
                case "deadline":
                    cmd = Command.DEADLINE;
                    break;
                case "event":
                    cmd = Command.EVENT;
                    break;
            }

            //instructs the bot given the command
            switch (cmd) {
                case LIST:
                   tb.listTasks();
                   break;
                case DONE:
                    tb.completeTask(taskIndex);
                    break;
                case TODO:
                    tb.addTodoTask(key[1]);
                    break;
                case EVENT:
                    tb.addEventTask(key[1]);
                    break;
                case DEADLINE:
                    tb.addDeadlineTask(key[1]);
                    break;
                case NONE:
                    System.out.println("That was not a valid command. Please try again.");
                    break;
            }
            //Reset the cmd for the next user input
            cmd = Command.NONE;
        }

        //The bot says bye and the program terminates
        tb.sayBye();
    }

}
