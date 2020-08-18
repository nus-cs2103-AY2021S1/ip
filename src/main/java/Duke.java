import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nSay something and I'll echo it.\nSay \"bye\" to end our conversation.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while(!input.equals("bye")) {
            System.out.println(input);
            input = scanner.next();
        }
        System.out.println("Bye, hope to chat again soon!");
    }
}
