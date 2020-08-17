import java.util.Scanner;

public class Duke {
    private static String[] tasks;
    private static int count;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner userInput = new Scanner(System.in);
        tasks = new String[100];
        count = 0;

        while (userInput.hasNext()) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
            } else {
                tasks[count] = input;
                count ++;
                System.out.println("added: " + input);
            }
        }
    }
}
