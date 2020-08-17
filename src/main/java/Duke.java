import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  " ____        _                    \n"
                + "|  _ \\ _   _| | _____  ______ ______ ______  ___  _____\n"
                + "| | | | | | | |/ / _ \\|  __  |__  __|___   |/ _ \\|  _  \\\n"
                + "| |_| | |_| |   <  __/| |  | |__||__ /   /_<  __/|     /\n"
                + "|____/ \\__,_|_|\\_\\___|| |  | |______|______|\\___||_|\\__\\ \n";
        System.out.println("Hello from\n" + logo);


        System.out.println("____________________________________");
        System.out.println("Hello! I'm Dukenizer\nWhat can I do for you?");
        System.out.println("____________________________________");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String query = sc.next();

        while (!query.equals("bye")) {
            System.out.println("____________________________________");
            System.out.println(query);
            System.out.println("____________________________________");
            System.out.println();
            query = sc.next();
        }

        System.out.println("____________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________");

    }


}
