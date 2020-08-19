import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    public static String line = "===================================================";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];
        int number = 0;

        printPart("Hello! I'm Duke\n" + "What can I do for you?");
        input = s.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < number; i++) {
                    System.out.println(String.format("%d. [%s] ", i + 1, tasks[i].getStatusIcon())
                                     + tasks[i].getDescription());
                }
                System.out.println(line + "\n");

            } else if (input.length() > 5 && input.substring(0, 4).equals("done")) {
                int n = Integer.parseInt(input.substring(5));
                tasks[n - 1].markAsDone();
                printPart("Nice! I've marked this task as done:\n" + "[\u2713] " + tasks[n - 1].getDescription());
            } else {
                printPart("added: " + input);
                tasks[number] = new Task(input);
                number++;

            }
            input = s.nextLine();
        }

        printPart("Bye. Hope to see you again soon!");
    }

    public static void printPart(String str) {
        System.out.println(line);
        System.out.println(str);
        System.out.println(line + "\n");
    }
}
