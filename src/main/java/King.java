package main.java;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class King {
    private ArrayList<Task> items = new ArrayList<>();
    Storage storage;

    King(String filepath){
        storage = new Storage(filepath);
        items.addAll(storage.load());
    }


    private LocalDateTime StringToLocalDateTime(String localDateTime) throws KingException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(localDateTime,formatter);
        } catch (Exception e){
            throw new KingException("Date and Time must be formatted as /by <date> <time>. E.g. 2/1/2020 1400", new Throwable(
                    "bad datetime"
            ));
        }
    }

    // method generates reply based on the phrase given by the User
    private String generateReply(String phrase) throws KingException {
        int phraseLength = phrase.length();
        if (phrase.equals("list")){
            return UI.numberedListChatBox(items);
        } else if((phrase.startsWith("done") && phraseLength == 4) || phrase.startsWith("done ")){
            String stringItem = phrase.substring(4).trim();
            try {
                int itemNo = Integer.parseInt(stringItem) - 1;
                Task item = items.get(itemNo);
                item.markAsDone();
                return UI.chatBox(
                        "Nice! I've marked this task as done:\n"
                                + "\t\t" + item.toString()
                );
            } catch (IndexOutOfBoundsException e) {
                throw new KingException("Item " + stringItem + " not found.", e);
            } catch (NumberFormatException e){
                throw (stringItem.isEmpty())
                    ? new KingException("Done must be followed by item number", e)
                        : new KingException(stringItem + " is not a valid item number!", e);
            } catch (Exception e) {
                throw new KingException("Please follow the syntax: done <item no.>", e);
            }
        } else if (phrase.startsWith("todo ") || (phrase.startsWith("todo") && phraseLength == 4)) {
            String item;
            if (phraseLength != 4 && (item = phrase.substring(5).trim()).length() != 0){
                ToDo todo = new ToDo(item);
                items.add(todo);
                return UI.addItemChatBox(todo.toString(),items.size());
            } else {
                throw new KingException("Todo cannot be empty!", new Throwable("empty field"));
            }
        } else if (phrase.startsWith("event ") || (phrase.startsWith("event") && phraseLength == 5)) {
            String item = phrase.substring(5).trim();
            String[] tokens = item.split(" /at ");
            if (tokens.length == 2) {
                Event event = new Event(tokens[0],tokens[1]);
                items.add(event);
                return UI.addItemChatBox(event.toString(),items.size());
            } else if(tokens.length < 2){
                throw new KingException("Event description and time CANNOT be empty!", new Throwable("empty field"));
            } else {
                throw new KingException("Follow the syntax event: <description> /at <time>", new Throwable(
                        "bad event"
                ));
            }
        } else if (phrase.startsWith("deadline ") || (phrase.startsWith("deadline") && phraseLength == 8)) {
            String item = phrase.substring(8).trim();
            String[] tokens = item.split(" /by ");
            if (tokens.length != 2){
                throw new KingException("Follow the syntax: deadline <description> /by <date> <time>", new Throwable(
                        "bad deadline"
                ));
            }
            try {
                LocalDateTime datetime = StringToLocalDateTime(tokens[1]);
                Deadline deadline = new Deadline(tokens[0],datetime);
                items.add(deadline);
                return UI.addItemChatBox(deadline.toString(),items.size());
            } catch (KingException e) {
                throw e;
            }
        } else if (phrase.startsWith("delete ") || (phrase.startsWith("delete") && phraseLength == 6)) {
            String stringItem = phrase.substring(6).trim();
            try {
                int itemNo = Integer.parseInt(stringItem) - 1;
                Task item = items.get(itemNo);
                items.remove(itemNo);
                return UI.chatBox(
                        "I have deleted the following item:\n"
                                + "\t\t" + item.toString() +
                                "\n\t You got " + items.size() + " task(s) left."
                );
            } catch (IndexOutOfBoundsException e) {
                throw new KingException("Item " + stringItem + " not found.", e);
            } catch (NumberFormatException e){
                throw (stringItem.isEmpty())
                        ? new KingException("delete must be followed by item number", e)
                        : new KingException(stringItem + " is not a valid item number", e);
            } catch (Exception e) {
                throw new KingException("Please follow the syntax: delete <item no.>", e);
            }
        } else {
            throw new KingException("I don't understand you!", new Throwable("invalid command"));
        }
    }

    // handles user input
    public void chat() {
        Scanner scanner = new Scanner(System.in);
        String phrase;
        while(scanner.hasNextLine() && !(phrase = scanner.nextLine()).equals("bye")){
            try {
                System.out.println(generateReply(phrase));
            } catch (KingException e) {
                System.out.println(UI.chatBox(e.message));
            }
        }
        System.out.print(UI.chatBox("Bye. Hope to see you again soon!"));
        scanner.close();
        storage.persistData(items);
    }

    public static void main(String[] args) {
        System.out.println(UI.welcome());
        King king = new King("data/king.txt");
        king.chat();
    }
}
