import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Beginning of my own code:
        System.out.println("Hello! Duke at your serve. Please name your request.");
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();
            if (!userMessage.equals("bye")) {
                System.out.println(userMessage);
            }

            if (userMessage.equals("bye")) {
                System.out.println("Bye! Nice serving you. Hope to see you again soon! :D");
                break;
            }
        }


    }
}
