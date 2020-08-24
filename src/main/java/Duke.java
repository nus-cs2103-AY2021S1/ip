import java.util.Scanner;

public class Duke {
    public static String SPACE = "     ";
    // add outer frame lines
    public static String format(String input_string) {
        return "    ____________________________________________________________\n" +
               "     " + input_string + "\n" +
               "    ____________________________________________________________\n";
    }

    public static void main(String[] args) {
        // greeting and exit messages strings
        String helloMessage = "Hello! I'm Duke\n" + SPACE + "What can I do for you?";
        String byeMessage = "Bye. Hope to see you again soon!";

        // set up scanner
        Scanner scanner = new Scanner(System.in);

        // start to serve
        System.out.println(format(helloMessage));

        // continue if have more commands (that are not "bye")
        // echo the command or say bye
        while (scanner.hasNextLine()) {
            String currentCommand = scanner.nextLine();
            if (currentCommand.equals("bye")) {
                System.out.println(format(byeMessage));
                scanner.close();
                break;
            } else {
                System.out.println(format(currentCommand));
            }
        }
    }
}