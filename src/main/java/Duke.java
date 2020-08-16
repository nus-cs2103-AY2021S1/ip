import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            System.out.println("     "+ input);
            System.out.println(" "+"-----------------");
            input = sc.nextLine();
        }
        System.out.println(" "+"-----------------");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(" "+"-----------------");
    }
}
