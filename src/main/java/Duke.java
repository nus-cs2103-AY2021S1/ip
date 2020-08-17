import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> books = new ArrayList<>();

        while (sc.hasNext()) {
            try {
                String echo = sc.nextLine();
                String[] s = echo.split("\\s");
                String first = s[0];

                if (first.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                if (first.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < books.size(); i++) {
                        int l = i + 1;
                        System.out.println(l + "." + books.get(i));
                    }
                } else if (first.equals("done")) {
                    int index = Integer.parseInt(s[1]);
                    books.get(index - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + books.get(index - 1).getStatusIcon() + " return book");
                } else {
                    if (first.equals("todo")) {
                        if (s.length == 1) {
                            throw new EmptyTodoException();
                        }
                        Todo t = new Todo(echo.substring(5));
                        books.add(t);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + books.get(books.size() - 1));
                        System.out.println("Now you have " + books.size() + " tasks in the list.");
                    } else if (first.equals("event")) {
                        if (s.length == 1) {
                            throw new EmptyEventsException();
                        }
                        int start = echo.indexOf("/at");
                        String date = echo.substring(start + 4);
                        Event t = new Event(echo.substring(6, start - 1), date);
                        books.add(t);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + books.get(books.size() - 1));
                        System.out.println("Now you have " + books.size() + " tasks in the list.");
                    } else if (first.equals("deadline")) {
                        if (s.length == 1) {
                            throw new EmptyDeadlineException();
                        }

                        int start = echo.indexOf("/by");
                        String date = echo.substring(start + 4);
                        Deadline t = new Deadline(echo.substring(9, start - 1), date);
                        books.add(t);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + books.get(books.size() - 1));
                        System.out.println("Now you have " + books.size() + " tasks in the list.");
                    } else if (first.equals("delete")) {
                        int index = Integer.parseInt(s[1]);
                        Task t = books.get(index - 1);
                        books.remove(index - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + books.size() + " tasks in the list.");
                    } else {
                        throw new UnknownCommandException();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}