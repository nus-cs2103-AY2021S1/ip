import java.util.Scanner;

/**
 * This is the main class which handles user input.
 */
public class Main {
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

        //Loops while the user does not input "bye"
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")){
                tb.listTasks();
            } else {
                tb.addTask(input);
            }
        }

        //The bot says bye and the program terminates
        tb.sayBye();
    }

}
