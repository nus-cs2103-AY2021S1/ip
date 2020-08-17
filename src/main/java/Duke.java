import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello, I'm Duke");
        System.out.println("Type anything and I'll add it to the list for you");
        System.out.println("Type 'list' to see the list");
        System.out.println("Type 'bye' if you're fed up with me");
        String next;
        while (true) {
            next = sc.nextLine();
            if (next.equalsIgnoreCase("bye")) {
                System.out.println(":<");
                sc.close();
                System.exit(0);
            } else if (next.equalsIgnoreCase("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(next);
                System.out.println("added: " + next);
            }
        }

    }
}
