import java.util.Scanner;

public class Duke {
    static String[] inputs = new String[100];
    static int index = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (input.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (inputs[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + inputs[i]);
                }
            } else {
                inputs[index] = input;
                index++;
                System.out.println("added: " + input);
            }
        }
    }
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
