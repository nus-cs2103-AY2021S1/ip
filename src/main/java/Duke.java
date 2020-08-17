import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String lines = "____________________________________________________________";

        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(greeting);
        input = sc.nextLine();
        String[] stringArray = new String[100];
        int counter = 0;

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(lines);
                for (int i = 0; i < counter; i++) {
                    int index = i+1;
                    String stringedIndex = Integer.toString(index);
                    String item = stringArray[i];
                    String outputLine = stringedIndex + ". " + item;
                    System.out.println(outputLine);
                }
                System.out.println(lines);
                input = sc.nextLine();
            } else {
                System.out.println(lines);
                stringArray[counter] = input;
                counter = counter + 1;
                String outputLine = "added: " + input;
                System.out.println(outputLine);
                input = sc.nextLine();
            }
        }


        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);


    }

}
