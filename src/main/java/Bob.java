import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        String exit = "Bye! Hope to see you again.";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
        String command = sc.next();
        while(!command.equals("bye")) {
            System.out.println(command);
            command = sc.next();
        }
        System.out.println(exit);
    }
}
