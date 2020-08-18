import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int index = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n" +
                "Send me a task and I'll store it for you.\n" +
                "Send \"list\" to see all tasks." +
                "Send \"bye\" to end our conversation.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                if (index == 0) {
                    System.out.println("Theres currently nothing in your list.");
                } else {
                    for (int i = 0; i < index ; i++) {
                        System.out.printf("%d. %s%n", i + 1, tasks[i]);
                    }
                }
                input = scanner.nextLine();
            } else {
                tasks[index++] = input;
                System.out.println("added: " + input);
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye, hope to chat again soon!");
    }
}
