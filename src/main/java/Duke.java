import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> lst= new ArrayList<>();

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
        String query = sc.nextLine();

        while (!query.equals("bye")) {

            System.out.println("____________________________________");

            //handle and print query string
            if (query.equals("list")) {
                for (int i = 0; i <lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i));
                }
            } else {
                System.out.println("added: " + query);
                lst.add(query);
            }


            System.out.println("____________________________________");
            System.out.println();


            query = sc.nextLine();
        }

        System.out.println("____________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________");

    }


}
