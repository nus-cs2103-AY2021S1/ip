import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> inputList = new ArrayList<String>();

    public static void main(String[] args) {
        String sectionBreak = "------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\nHello buddy! I am");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(sectionBreak);

        Scanner sc=new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                inputList.add(input);
                System.out.printf("added: %s\n", input);
            }

            System.out.println(sectionBreak);
        }

        System.out.println("Bye. Hope to see you soon!");
        System.out.println(sectionBreak);
    }

    private static void printList() {
        for (int i = 0; i < inputList.size(); i++) {
            System.out.printf("%d. %s\n", i, inputList.get(i));
        }
    }
}
