import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public final static String LINE = "*********************************************************";
    public static ArrayList<Task> shelf;

    public static void main(String[] args) {
        welcome();
        shelf = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        handler(sc);
        sc.close();
    }

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
        System.out.println("Hello from " + logo);
        System.out.println("What can I do for you pal? :D");
        System.out.println(LINE);
    }

    public static void handler(Scanner sc) {
        int counter;
        int indexer;
        Task book;
        String date;
        while (true) {
            String response = sc.nextLine();
            System.out.println(LINE);
            if (response.equals("bye")) {
                System.out.println("CYA PAL. Hope to see you again!");
                System.out.println(LINE);
                break;
            } else if (response.equals("list")) {
                Iterator<Task> iter = shelf.iterator();
                counter = 1;
                System.out.println("Here are the tasks in your list: ");
                while (iter.hasNext()) {
                    System.out.println(counter + ". " + iter.next());
                    counter++;
                }
                System.out.println(LINE);
            } else if (response.indexOf("done ") == 0) {
                System.out.println("Nice! I've marked this task as done: ");
                indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                book = shelf.get(indexer);
                book.complete();
                System.out.println(book);
                System.out.println(LINE);
            } else {
                if (response.indexOf("todo ") == 0) {
                    book = new ToDo(response.substring(4));
                    shelf.add(book);
                } else if (response.indexOf("deadline ") == 0) {
                    date = response.substring(response.indexOf("/by ") + 4);
                    response = response.substring(response.indexOf("deadline ") + 8, response.indexOf("/by "));
                    book = new Deadline(response, date);
                    shelf.add(book);
                } else {
                    date = response.substring(response.indexOf("/at ") + 4);
                    response = response.substring(response.indexOf("event ") + 5, response.indexOf("/at "));
                    book = new EventTask(response, date);
                    shelf.add(book);

                }
                // exception
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + book);
                System.out.println("Now you have " + shelf.size() + " tasks in the list.");
                System.out.println(LINE);
            }
        }
    }
}
