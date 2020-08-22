import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;
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
            try {
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
                } else if (response.indexOf("delete ") == 0) {
                    indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                    if (indexer >= shelf.size() || indexer < 0) {
                        throw new DukeTaskNonExistException("error");
                    }
                    System.out.println("Noted. I've removed this task: ");
                    shelf.remove(indexer);
                    System.out.println("Now you have " + shelf.size() + " in the list.");
                    System.out.println(LINE);
                } else if (response.indexOf("done ") == 0) {
                    indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                    if (indexer >= shelf.size() || indexer < 0) {
                        throw new DukeTaskNonExistException("error");
                    }
                    System.out.println("Nice! I've marked this task as done: ");
                    book = shelf.get(indexer);
                    book.complete();
                    System.out.println(book);
                    System.out.println(LINE);
                } else {
                    if (response.indexOf("todo ") == 0) {
                        if (response.length() <= 5) {
                            throw new EmptyDescriptionException("todo");
                        }
                        book = new ToDo(response.substring(4), LocalDateTime.now());
                        shelf.add(book);
                    } else if (response.indexOf("deadline ") == 0) {
                        if (response.length() <= 9) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        if (!response.contains("/by ")) {
                            throw new DukeKeywordMissingException("/by ");
                        }
                        date = response.substring(response.indexOf("/by ") + 4);
                        response = response.substring(response.indexOf("deadline ") + 8, response.indexOf("/by "));
                        book = new Deadline(response, LocalDateTime.now(),  date);
                        shelf.add(book);
                    } else if (response.indexOf("event ") == 0) {
                        if (response.length() <= 6) {
                            throw new EmptyDescriptionException("event");
                        }
                        if (!response.contains("/at ")) {
                            throw new DukeKeywordMissingException("/at ");
                        }
                        date = response.substring(response.indexOf("/at ") + 4);
                        response = response.substring(response.indexOf("event ") + 5, response.indexOf("/at "));
                        book = new EventTask(response,LocalDateTime.now(), date);
                        shelf.add(book);
                    } else {
                        throw new DukeUnknownInputException("error");
                    }
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + book);
                    System.out.println("Now you have " + shelf.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (DukeUnknownInputException | EmptyDescriptionException |
                    DukeTaskNonExistException | DukeKeywordMissingException e) {
                System.out.println(e);
                System.out.println(LINE);
            } catch (DateTimeParseException e) {
                System.out.println("☹ OOPS!!! Ensure that the datetime input is in the format YYYY-MM-DD HH:MM");
            }
        }
    }
}
