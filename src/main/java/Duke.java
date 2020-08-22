import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public final static String FILEPATH = System.getProperty("user.dir") + (System.getProperty("user.dir").endsWith("text-ui-test")
            ? "/duke.txt"
            : "/text-ui-test/duke.txt");
    
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        //System.out.println(textToAppend);
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        
        boolean directoryExists = new File(FILEPATH).exists();

        if (!directoryExists) {
            FileWriter fw = new FileWriter(FILEPATH, true);
        }
        appendToFile(FILEPATH, "Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> books = new ArrayList<>();

        while (sc.hasNext()) {
            try {
                String echo = sc.nextLine();
                String[] s = echo.split("\\s");
                String first = s[0];

                if (first.equals("bye")) {
                    appendToFile(FILEPATH, "Bye. Hope to see you again soon!");
                    break;
                }
                if (first.equals("list")) {
                    appendToFile(FILEPATH, "Here are the tasks in your list:");
                    for (int i = 0; i < books.size(); i++) {
                        int l = i + 1;
                        appendToFile(FILEPATH, l + "." + books.get(i));
                    }
                } else if (first.equals("done")) {
                    int index = Integer.parseInt(s[1]);
                    books.get(index - 1).markAsDone();
                    appendToFile(FILEPATH, "Nice! I've marked this task as done:\n  " + books.get(index - 1).getStatusIcon() + " return book");
                } else {
                    if (first.equals("todo")) {
                        if (s.length == 1) {
                            throw new EmptyTodoException();
                        }
                        Todo t = new Todo(echo.substring(5));
                        books.add(t);

                        appendToFile(FILEPATH, "Got it. I've added this task:");
                        appendToFile(FILEPATH, "  " + books.get(books.size() - 1));
                        appendToFile(FILEPATH, "Now you have " + books.size() + " tasks in the list.");
                    } else if (first.equals("event")) {
                        if (s.length == 1) {
                            throw new EmptyEventsException();
                        }
                        int start = echo.indexOf("/at");
                        String date = echo.substring(start + 4);
                        Event t = new Event(echo.substring(6, start - 1), date);
                        books.add(t);

                        appendToFile(FILEPATH, "Got it. I've added this task:");
                        appendToFile(FILEPATH, "  " + books.get(books.size() - 1));
                        appendToFile(FILEPATH, "Now you have " + books.size() + " tasks in the list.");
                    } else if (first.equals("deadline")) {
                        if (s.length == 1) {
                            throw new EmptyDeadlineException();
                        }

                        int start = echo.indexOf("/by");
                        String date = echo.substring(start + 4);
                        Deadline t = new Deadline(echo.substring(9, start - 1), date);
                        books.add(t);

                        appendToFile(FILEPATH, "Got it. I've added this task:");
                        appendToFile(FILEPATH, "  " + books.get(books.size() - 1));
                        appendToFile(FILEPATH, "Now you have " + books.size() + " tasks in the list.");
                    } else if (first.equals("delete")) {
                        int index = Integer.parseInt(s[1]);
                        Task t = books.get(index - 1);
                        books.remove(index - 1);
                        appendToFile(FILEPATH, "Noted. I've removed this task:");
                        appendToFile(FILEPATH, "  " + t);
                        appendToFile(FILEPATH, "Now you have " + books.size() + " tasks in the list.");
                    } else {
                        throw new UnknownCommandException();
                    }
                }
            } catch (DukeException e) {
                appendToFile(FILEPATH, e.getMessage());
            }
        }
    }
}