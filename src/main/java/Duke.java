import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int listSize = list.size();
                if (listSize == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Your list is as follows:");
                    for (int i = 0; i < listSize; i++) {
                        int index = i + 1;
                        System.out.print(index + ". ");
                        System.out.println(list.get(i));
                    }
                }
            } else {
                list.add(input);
                System.out.println("I have added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
