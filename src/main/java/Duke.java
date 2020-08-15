import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "\t==================================================\n\t  ";

    private static String output(String message) {
        return horizontalLine + message + "\n" + horizontalLine;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(output("Hello! I'm Duke\n\t  What can I do for you?"));
        Task[] list = new Task[100];
        int count = 0;
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.print(horizontalLine);
                for (int i = 0; i < count; i++) {
                    if (i == 0) {
                        System.out.println((i + 1) + ". " + list[0]);
                    } else {
                        System.out.println("\t  " + (i + 1) + ". " + list[i]);
                    }
                }
                System.out.println(horizontalLine);
            } else {
                System.out.println(output("added: " + input));
                list[count++] = new Task(input);
            }
            input = sc.nextLine();
        }
        System.out.println(output("Bye. Hope to see you again soon!"));
    }
}
