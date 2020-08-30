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
     * @param storage the storage system.
     * @param fullCommand tbe full input.
     * @param first the first word of the input.
     * @throws EmptyTodoException  when the todo request is empty.
     * @throws EmptyEventsException  when the event request is empty.
     * @throws EmptyDeadlineException  when the deadline request is empty.
     * @throws UnknownCommandException  when the command is unknown.
     */
    public String operate(Storage storage, String fullCommand, String first) throws IOException {
        String[] s = fullCommand.split("\\s");
        String text = "";

        try {
            if (first.equals("list")) {
                text = "Here are the tasks in your list:\n";
                storage.appendToFile(text);
                for (int i = 0; i < books.size(); i++) {
                    int l = i + 1;
                    text += l + "." + books.get(i) + "\n";
                    storage.appendToFile(text);
                }
            } else if (first.equals("done")) {
                int index = Integer.parseInt(s[1]);
                books.get(index - 1).markAsDone();
                text = "Nice! I've marked this task as done:\n  "
                        + books.get(index - 1).getStatusIcon()
                        + " return book";
                storage.appendToFile(text);
            } else {
                if (first.equals("todo")) {
                    if (s.length == 1) {
                        throw new EmptyTodoException();
                    }
                    Todo t = new Todo(fullCommand.substring(5));
                    books.add(t);
                    text = "Got it. I've added this task:\n"
                            + "  " + books.get(books.size() - 1) + "\n"
                            + "Now you have "
                            + books.size()
                            + " tasks in the list.";
                    storage.appendToFile(text);
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
                    text = "Got it. I've added this task:\n"
                            + "  " + books.get(books.size() - 1) + "\n"
                            + "Now you have "
                            + books.size()
                            + " tasks in the list.";
                    storage.appendToFile(text);

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
                    text = "Got it. I've added this task:\n"
                            + "  " + books.get(books.size() - 1) + "\n"
                            + "Now you have "
                            + books.size()
                            + " tasks in the list.";
                    storage.appendToFile(text);
                } else if (first.equals("delete")) {
                    int index = Integer.parseInt(s[1]);
                    Task t = books.get(index - 1);
                    books.remove(index - 1);
                    text = "Noted. I've removed this task:\n"
                            + "  " + t + "\n"
                            + "Now you have "
                            + books.size()
                            + " tasks in the list.";
                    storage.appendToFile(text);
                } else if (first.equals("find")) {
                    ArrayList<Task> tempList = new ArrayList<>();
                    for (int i = 0; i < books.size(); i++) {
                        if (books.get(i).description.contains(s[1])) {
                            tempList.add(books.get(i));
                        }
                    }
                    text = "Here are the matching tasks in your list:\n";
                    storage.appendToFile(text);

                    for (int i = 0; i < tempList.size(); i++) {
                        int l = i + 1;
                        text += l + "." + tempList.get(i) + "\n";
                        storage.appendToFile(text);
                    }
                } else {
                    throw new UnknownCommandException();
                }
            }

            return text;
        } catch (DukeException e) {
            text += "\n" + e.getMessage();
            storage.appendToFile(text);
        }
        return text;
    }
}