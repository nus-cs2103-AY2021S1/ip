import java.util.Scanner; // Import the scanner class

public class Duke {

    public static void main(String[] args) {

        // String to store the logo
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        // Print the intro messages
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"
                        + "Hello Master sama, I am Duke chan\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________\n");

        // Create the scanner object
        Scanner sc = new Scanner(System.in);

        // Take in input
        String input = sc.nextLine();

        // While the user input is not bye
        while(!input.equals("bye")){

            // Echo the user input
            System.out.println("____________________________________________________________\n"
                            + input
                            + "\n____________________________________________________________\n");

            // Get the next line as input
            input = sc.nextLine();
        }

        // Print the end message
        System.out.println("____________________________________________________________\n"
                        + "Goodbye master! Hope to see you again >.<\n"
                        + "____________________________________________________________");

    }
}
