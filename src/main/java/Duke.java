import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void printDivider() {
        System.out.println("          ____________________________________________________________");
    }
    private static void printOutput(String output) {
        System.out.println("          " + output);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        printOutput("Hi! I'm Duke");
        printOutput("What can I do for you?");
        printDivider();

        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputData = new ArrayList<>();
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                int index = 1;
                printDivider();
                for(index = 0; index < inputData.size(); index++) {
                    printOutput((index + 1) + ": " + inputData.get(index));
                }
                printDivider();
            } else {
                inputData.add(input);
                printDivider();
                printOutput("added: " + input);
                printDivider();
            }
            input = sc.nextLine();
        }

        printDivider();
        printOutput("Bye. See you again next time!" );
        printDivider();
    }
}
