import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        String close = "_______________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "_______________________________________ \n";
        String line = "_______________________________________";
        System.out.println(open);

        boolean run = true;
        String user_input;
        String output;
        Scanner scanner = new Scanner(System.in);
        while(run) {
            user_input = scanner.next();
            if (user_input == "bye") {
                run = false;
            } else {
                System.out.println(line + "\n" +user_input + "\n" + line);
            }
        }

        scanner.close();
        System.out.println(close);
    }
}
