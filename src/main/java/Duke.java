import java.util.Scanner;

public class Duke {

    /**
     * Prints greeting message.
     * Scans for commands entered by the user, then echos by printing them.
     * Upon user command input "bye", system is exited.
     **/
    public static void main(String[] args) {
        String greeting_message = "____________________________________________________________\n" +
                "Quack! I am Duck \uD83E\uDD86 \n" +
                "How can I help you today?\n" +
                "____________________________________________________________";
        String exit_message = "____________________________________________________________\n" +
                "Waddling off now. See you soon! \uD83D\uDC4B\uD83C\uDFFB \n" +
                "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting_message);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit_message);
                break;
            } else {
                System.out.println("____________________________________________________________\n" +
                        input + "\n____________________________________________________________");
            }
        }
        sc.close();
    }
}
