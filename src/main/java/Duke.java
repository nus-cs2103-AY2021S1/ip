import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, my name is\n" + logo);
        System.out.println("What can I do for you");

        // Initialise booleans and scanners
        boolean quitProgram = false;
        Scanner inputScanner = new Scanner(System.in);

        while (!quitProgram) {
            // blocks program until input is received
            String newInput = inputScanner.nextLine();
            // then prints the input
            if (newInput.equals("bye")) {
                quitProgram = true;
            } else {
                System.out.println(newInput);
            }
        }

        // quit
        System.out.println("See you space cowboy!");
    }
}
