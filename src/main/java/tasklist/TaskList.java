package tasklist;

import exceptions.*;
import storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TaskList class handles the bulk of the Duke bot after each command
 * has been processed by the parser.
 * @author Maguire Ong
 */

public class TaskList {
    public static ArrayList<Task> books = new ArrayList<>();

    /**
     * Checks which command the Duke bot is taking in
     * and adds the printed line to the saved file.
     *
     * @param storage the storage system
     * @param fullCommand tbe full input
     * @param first the first word of the input
     */
    public void operate(Storage storage, String fullCommand, String first) throws IOException {
        String[] s = fullCommand.split("\\s");

        try {
            if (first.equals("list")) {
                storage.appendToFile("Here are the tasks in your list:");
                for (int i = 0; i < books.size(); i++) {
                    int l = i + 1;
                    storage.appendToFile(l + "." + books.get(i));
                }
            } else if (first.equals("done")) {
                int index = Integer.parseInt(s[1]);
                books.get(index - 1).markAsDone();
                storage.appendToFile("Nice! I've marked this task as done:\n  " + books.get(index - 1).getStatusIcon() + " return book");
            } else {
                if (first.equals("todo")) {
                    if (s.length == 1) {
                        throw new EmptyTodoException();
                    }
                    Todo t = new Todo(fullCommand.substring(5));
                    books.add(t);

                    storage.appendToFile("Got it. I've added this task:");
                    storage.appendToFile("  " + books.get(books.size() - 1));
                    storage.appendToFile("Now you have " + books.size() + " tasks in the list.");
                } else if (first.equals("event")) {
                    if (s.length == 1) {
                        throw new EmptyEventsException();
                    }
                    int start = fullCommand.indexOf("/at");
                    String date = fullCommand.substring(start + 4);
                    LocalDate d = LocalDate.parse(date);
                    String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Event t = new Event(fullCommand.substring(6, start - 1), formattedDate);
                    books.add(t);

                    storage.appendToFile("Got it. I've added this task:");
                    storage.appendToFile("  " + books.get(books.size() - 1));
                    storage.appendToFile("Now you have " + books.size() + " tasks in the list.");
                } else if (first.equals("deadline")) {
                    if (s.length == 1) {
                        throw new EmptyDeadlineException();
                    }

                    int start = fullCommand.indexOf("/by");
                    String date = fullCommand.substring(start + 4);
                    LocalDate d = LocalDate.parse(date);
                    String formattedDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Deadline t = new Deadline(fullCommand.substring(9, start - 1), formattedDate);
                    books.add(t);

                    storage.appendToFile("Got it. I've added this task:");
                    storage.appendToFile("  " + books.get(books.size() - 1));
                    storage.appendToFile("Now you have " + books.size() + " tasks in the list.");
                } else if (first.equals("delete")) {
                    int index = Integer.parseInt(s[1]);
                    Task t = books.get(index - 1);
                    books.remove(index - 1);
                    storage.appendToFile("Noted. I've removed this task:");
                    storage.appendToFile("  " + t);
                    storage.appendToFile("Now you have " + books.size() + " tasks in the list.");
                } else if (first.equals("find")) {
                    ArrayList<Task> tempList = new ArrayList<>();
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).description.contains(s[1])) {
                            tempList.add(books.get(i));
                        }
                    }
                    storage.appendToFile("Here are the matching tasks in your list:");
                    for (int i = 0; i < tempList.size(); i++) {
                        int l = i + 1;
                        storage.appendToFile(l + "." + tempList.get(i));
                    }
                } else {
                    throw new UnknownCommandException();
                }
            }
        } catch (DukeException e) {
            storage.appendToFile(e.getMessage());
        }
    }

}