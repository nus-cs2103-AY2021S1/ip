import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy pardner!! I'm\n" + logo);

        System.out.println("What can I get yer for?");

        while (true) {
            String input = userInput.nextLine();
            if (input.toLowerCase().equals("bye")) {
                System.out.println("Well I'll see you around, pardner!!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
