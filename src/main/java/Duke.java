import java.util.Scanner;

public class Duke {

    public static String line = "===================================================";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        String[] list = new String[100];
        int number = 0;

        printPart("Hello! I'm Duke\n" + "What can I do for you?");
        input = s.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(line);
                for(int i = 0; i < number; i++) {
                    System.out.println(String.format("%d. ", i + 1) + list[i]);
                }
                System.out.println(line + "\n");
            } else {
                printPart("added: " + input);
                list[number] = input;
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
