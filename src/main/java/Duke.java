import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print greetings of chatbot
        System.out.println("____________________________________________________________\n" +
                           "Hello! I'm Duke\n" +
                           "What can I do for you?" +
                           "\n____________________________________________________________");

        // echo command entered by the user
        String command;
        Scanner sc = new Scanner(System.in);
        while(true){
            command = sc.nextLine();
            if(command.equals("bye")) break;
            else System.out.println("____________________________________________________________\n" +
                               command +
                               "\n____________________________________________________________");
        }
        System.out.println("____________________________________________________________\n" +
                           "Bye. Hope to see you again soon!" +
                           "\n____________________________________________________________");
    }
}
