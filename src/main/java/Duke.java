import java.util.Scanner;
import java.util.ArrayList;

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

        String user_input;
        ArrayList<String> user_list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            user_input = scanner.nextLine();
            if (user_input.equals("bye")) {
                break;
            } else {
                user_list.add(user_input);
                System.out.println(line + "\n" +user_input + "\n" + line);
            }
        }

        scanner.close();
        System.out.println(close);
    }
}
