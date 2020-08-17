import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Clara! :D How may I help you? :)");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye! Have a great day and hope to see you soon! :D");
                break;
            } else {
               System.out.println(">>> " + command);
            }
        }


    }
}
