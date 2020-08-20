import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(start);
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while (true) {
            System.out.println(in);
            in = sc.nextLine();
            if (in != "bye") {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
