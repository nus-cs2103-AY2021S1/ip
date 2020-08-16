package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> items = new ArrayList<>();

    // method generates reply based on the phrase given by the User
    private String generateReply(String phrase) throws DukeException {
        int phraseLength = phrase.length();
        if (phrase.equals("list")){
            return Chat.numberedListChatBox(items);
        } else if((phrase.startsWith("done") && phraseLength == 4) || phrase.startsWith("done ")){
            String stringItem = phrase.substring(4).trim();
            try {
                int itemNo = Integer.parseInt(stringItem) - 1;
                Task item = items.get(itemNo);
                item.markAsDone();
                return Chat.chatBox(
                        "Nice! I've marked this task as done:\n"
                                + "\t\t" + item.toString()
                );
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Item " + stringItem + " not found.", e);
            } catch (NumberFormatException e){
                throw (stringItem.isEmpty())
                    ? new DukeException("Done must be followed by item number", e)
                        : new DukeException(stringItem + " is not a valid item number", e);
            } catch (Exception e) {
                throw new DukeException("Please follow the syntax: done <item no.>", e);
            }
        } else if (phrase.startsWith("todo ") || (phrase.startsWith("todo") && phraseLength == 4)) {
            String item;
            if (phraseLength != 4 && (item = phrase.substring(5).trim()).length() != 0){
                ToDo todo = new ToDo(item);
                items.add(todo);
                return Chat.addItemChatBox(todo.toString(),items.size());
            } else {
                throw new DukeException("Todo cannot be empty", new Throwable("empty field"));
            }
        } else if (phrase.startsWith("event ") || (phrase.startsWith("event") && phraseLength == 5)) {
            String item = phrase.substring(5).trim();
            String[] tokens = item.split(" /at ");
            if (tokens.length == 2) {
                Event event = new Event(tokens[0],tokens[1]);
                items.add(event);
                return Chat.addItemChatBox(event.toString(),items.size());
            } else if(tokens.length < 2){
                throw new DukeException("Event description and time CANNOT be empty!", new Throwable("empty field"));
            } else {
                throw new DukeException("Follow the syntax event: <description> /at <time>", new Throwable(
                        "bad event"
                ));
            }
        } else if (phrase.startsWith("deadline ") || (phrase.startsWith("deadline") && phraseLength == 8)) {
            String item = phrase.substring(8).trim();
            String[] tokens = item.split(" /by ");
            if (tokens.length == 2) {
                Deadline deadline = new Deadline(tokens[0],tokens[1]);
                items.add(deadline);
                return Chat.addItemChatBox(deadline.toString(),items.size());
            } else if(tokens.length < 2){
                throw new DukeException("Deadline description and time CANNOT be empty!", new Throwable("empty field"));
            } else {
                throw new DukeException("Follow the syntax: deadline <description> /by <time>", new Throwable(
                        "bad deadline"
                ));
            }
        } else {
            throw new DukeException("I don't understand you!", new Throwable("invalid command"));
        }
    }

    // handles user input
    public void chat() {
        Scanner scanner = new Scanner(System.in);
        String phrase;
        while(scanner.hasNextLine() && !(phrase = scanner.nextLine()).equals("bye")){
            try {
                System.out.println(generateReply(phrase));
            } catch (DukeException e) {
                System.out.println(Chat.chatBox(e.message));
            }
        }
        System.out.print(Chat.chatBox("Bye. Hope to see you again soon!"));
        scanner.close();
    }

    public static void main(String[] args) {
        String logo =
                " ____  __.__\n" +
                        "|    |/ _|__| ____    ____\n" +
                        "|      < |  |/    \\  / ___\\ \n" +
                        "|    |  \\|  |   |  \\/ /_/  >\n" +
                        "|____|__ \\__|___|  /\\___  /\n" +
                        "        \\/       \\//_____/\n";
        System.out.println(logo);
        System.out.println("Hello! I'm King");
        System.out.println("What can I do for you?");
        Duke king = new Duke();
        king.chat();
    }
}
