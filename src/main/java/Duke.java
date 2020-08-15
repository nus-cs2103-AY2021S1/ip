import java.util.Scanner;

public class Duke {
    public static String greetings = "Hello! I'm Duke\n     What can I do for you?";
    public static String farewell = "Bye. Hope to see you again soon!";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printAnswer(greetings);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            if (instruction.equals("bye")) {
                printAnswer(farewell);
                break;
            } else {
                printAnswer(instruction);
            }
        }


    }

    public static void printAnswer(String answer) {
        String Line = "____________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + Line);
        System.out.println(bigSpace + answer);
        System.out.println(smallSpace + Line + "\n");
    }
}
