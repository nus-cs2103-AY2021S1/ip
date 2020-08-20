import java.util.Scanner;

public class Duke {

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        //initialize Duke and send welcome message
        Duke duke = new Duke();
        duke.greet();

        //input loop
        Scanner sc = new Scanner(System.in);

        while(true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            }
            System.out.println(s);
        }

        //send exit message
        duke.bye();

    }
}
