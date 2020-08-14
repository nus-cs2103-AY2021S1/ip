import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String logo = " ______  ___       __         __        _____\n"
                     + "   |    /         /  \\       /  \\     /\n"
                     + "   |    \\___     /____\\     /____\\   |\n"
                     + "   |        \\   /      \\   /      \\   \\\n"
                     + " ------  ___/  /        \\ /        \\    -----\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
        String next = "";
        while (true) {
            next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                for (String i :list) {
                  System.out.println(list.indexOf(i)+1 + ". " + i);
                }
            } else {
                System.out.println("added: " + next);
                list.add(next);
                }
            }
    }
}
