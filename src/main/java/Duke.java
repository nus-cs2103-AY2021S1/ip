import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        chat();
    }

    public static void chat() {
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();
        int i = 1;
        while (true) {
            String in = sc.nextLine();
            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                for (int j = 0; j < arr.size(); j++) {
                    System.out.println(arr.get(j));
                }
            } else {
                String toAdd = i + ". " + in;
                arr.add(toAdd);
                i++;
                String toPrint = "added: " + in;
                System.out.println(toPrint);
            }
        }
    }
}
