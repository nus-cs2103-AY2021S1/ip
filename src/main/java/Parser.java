package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private final Storage storage;
    private TaskList taskList;

    Parser(Storage storage, TaskList taskList){
        this.storage = storage;
        this.taskList = taskList;
    }

    // method generates reply based on the phrase given by the User
    public String parse(String phrase) throws KingException {
        int phraseLength = phrase.length();
        String reply;
        if (phrase.equals("list")){
            return UI.listTaskList(taskList);
        } else if (phrase.equals("clear list")){
            taskList.clear();
            reply = UI.chatBox("I have cleared the list!");
        } else if((phrase.startsWith("done") && phraseLength == 4) || phrase.startsWith("done ")){
            String stringItem = phrase.substring(4).trim();
            try {
                int itemNo = Integer.parseInt(stringItem) - 1;
                Task item = taskList.get(itemNo);
                item.markAsDone();
                reply = UI.chatBox(
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
                taskList.add(todo);
                reply = UI.addItemChatBox(todo.toString(),taskList.size());
            } else {
                throw new KingException("Todo cannot be empty!", new Throwable("empty field"));
            }
        } else if (phrase.startsWith("event ") || (phrase.startsWith("event") && phraseLength == 5)) {
            String item = phrase.substring(5).trim();
            String[] tokens = item.split(" /at ");
            if (tokens.length == 2) {
                Event event = new Event(tokens[0],tokens[1]);
                taskList.add(event);
                reply = UI.addItemChatBox(event.toString(),taskList.size());
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
                taskList.add(deadline);
                reply = UI.addItemChatBox(deadline.toString(),taskList.size());
            } catch (KingException e) {
                throw e;
            }
        } else if (phrase.startsWith("delete ") || (phrase.startsWith("delete") && phraseLength == 6)) {
            String stringItem = phrase.substring(6).trim();
            try {
                int itemNo = Integer.parseInt(stringItem) - 1;
                Task item = taskList.get(itemNo);
                taskList.delete(itemNo);
                reply = UI.chatBox(
                        "I have deleted the following item:\n"
                                + "\t\t" + item.toString() +
                                "\n\t You got " + taskList.size() + " task(s) left."
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
        storage.persistTaskList(taskList);
        return reply;
    }

    // takes a Date Time string and returns a LocalDateTime
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
}
