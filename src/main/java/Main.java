import java.util.Scanner;

/**
 * This is the main class which handles user input.
 */
public class Main {
    //Commands for the bot to execute
    public enum Command {
        LIST, DONE, NONE
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
        while (sc.hasNextLine()) {
            int taskIndex = -1;
            String input = sc.nextLine();

            //Splits the string to find keywords for commands
            if (input.equals("bye")) {
                break;
            }
            String parse = input.length() > 3 ? input.substring(0, 4) : input;
            if (parse.equals("list")){
                cmd = Command.LIST;
            } else if (parse.equals("done")){
                cmd = Command.DONE;
                taskIndex = Integer.parseInt(input.substring(5, 6)) - 1;
            }

            //instructs the bot given the command
            switch (cmd) {
                case LIST:
                   tb.listTasks();
                   break;
                case DONE:
                    tb.completeTask(taskIndex);
                    break;
                case NONE:
                    tb.addTask(input);
            }
        }

        //The bot says bye and the program terminates
        tb.sayBye();
    }

}
