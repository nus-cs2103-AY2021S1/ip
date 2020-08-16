import java.util.Scanner;

/**
 * A command line interface application that reads user inputs, executes the input and prints out the
 * result of executing the input
 */
public class CommandLineInterface {
    /** This will determine the number of underscores each divider should be made of */
    private static final int dividerLength = 50;
    /** This will determine the left padding size of the messages that the application outputs */
    private static final int leftPadding = 7;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Formatter formatter = new Formatter(dividerLength, leftPadding);

    public static void run(){
        String welcomeMessage = "Hello! I'm Erica \n" + "How may I be of assistance?\n";
        formatter.print(welcomeMessage);
        while(scanner.hasNext()){
            String userInput = scanner.nextLine();
            if(userInput.toLowerCase().equals("bye")){
                formatter.print("Bye. Hope my service has been satisfactory. Hope to see you again soon.");
                break;
            }
            formatter.print(userInput);
        }
        scanner.close();
    }
}
