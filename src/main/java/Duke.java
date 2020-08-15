import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        int index = 0;
        String divider = "____________________________________________________________";
        String intro = "Hello! I'm Bob\n" +
                "What can I do for you?\n";

        System.out.println(divider + "\n" + intro + "\n" + divider);
        String input = sc.next();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < index; i++) {
                    System.out.println(list[i]);
                }
                System.out.println(divider);
            } else {
                list[index] = (index + 1) + ". " + input;
                index++;
                System.out.println(divider + "\n" + "added: " + input + "\n" + divider);
            }
            input = sc.next();
        }

        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

