import java.util.Scanner;

public class Duke {
    static int count = 0;
    static String[] list = new String[100];

    public static void addList(String str) {
        list[count] = str;
        count++;
        System.out.println("added: " + str);
    }
    public static void printList() {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println((i + 1) + ". " + list[i]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                printList();
            } else {
                addList(command);
            }

        }
        sc.close();
    }
}
