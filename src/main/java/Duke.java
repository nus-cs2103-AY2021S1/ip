import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Initial greetings
        System.out.println("_______________________________________________________________");
        System.out.println("Greetings from me, Dave! \n"  + "How can I help you? ^_^");
        System.out.println("_______________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        //Looping and echoing user inputs
        while(scanner.hasNext()) {
            String userInput = scanner.next(); //may be string, int, etc
            if(userInput.equals("Bye")) {
                System.out.println("Goodbye! Hope to see you again soon! ^_^");
                break;
            } else {
                System.out.println("Not Bye");
            }

        }








//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
