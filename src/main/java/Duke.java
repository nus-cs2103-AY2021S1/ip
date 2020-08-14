import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Hans ㋡";
                /*"_   _        _        \n"
                   + "| |_| | ___  _| | _____ \n"
                   + "|  ㋡ | | | | | |/ / _ \\\n"
                   + "| |_| | |_| |   <  __/\n"
                   + "|_| |_|/ \\_|,_|_|\\_\\___|\n"; */
        System.out.println("Hello from " + logo + "\n" + "How may I be of service " +
                "to you this fine day sire?");

        awaitInstructions();
    }

    private static void awaitInstructions() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput =  sc.nextLine();

            switch (userInput) { // Determine output from user input
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    echo(userInput);
                    break;
            }
        }
    }

    private static void echo(String userInput) {
        System.out.println(userInput);
    }
}
