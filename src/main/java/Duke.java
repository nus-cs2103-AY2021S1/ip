import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> toDoList = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Omo!! hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(" "+"-----------------");
            if (!input.equals("list")){
                System.out.println("    added: "+ input);
                toDoList.add(input);
            } else {
                int count = 1;
                for (String s: toDoList) {
                    System.out.println("  "+ count + ": " + s);
                    count++;
                }
            }
            System.out.println(" "+"-----------------");
            input = sc.nextLine();
        }
        System.out.println(" "+"-----------------");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(" "+"-----------------");
    }
}
