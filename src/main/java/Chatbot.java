import java.util.Scanner;

public class Chatbot {
    String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    String indent = "        ";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(indent + "What can I do for you delightful human?\n" + divider);

        while(scanner.hasNext()) {
            String input = scanner.next();

            System.out.println(divider);
            if(input.equals("bye")) { // Termination statement
                System.out.println(indent + "Guess its time for us to part ways\n"
                        + indent + "Thanks for the memories\n" + indent + ":`(");
                System.out.println(divider);
                break;
            } else { // Echo back user input
                System.out.println(indent + input);
                System.out.println(divider);
            }
        }
    }
}
