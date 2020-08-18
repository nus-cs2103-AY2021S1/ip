import java.util.Scanner;
public class Duke {
    public static void printOutput(String output) {
        System.out.println("          ____________________________________________________________");
        System.out.println("          " + output);
        System.out.println("          ____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printOutput("Hi! I'm Duke. What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            printOutput(input);
            input = sc.nextLine();
        }

        printOutput("Bye. See you again next time!" );
    }
}
