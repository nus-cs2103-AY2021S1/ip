/**
 * Parser class handles all the commands from the King Program
 * and returns a reply from the commands.
 */
package parser;

import king.KingException;
import king.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private final Storage storage;
    private final ParserExceptions exceptions = new ParserExceptions();
    private TaskList taskList;

    /**
     * Parse the commands from the King Program
     *
     * @param storage  storage to save the commands to.
     * @param taskList taskList to be manipulated from the commands.
     * @return Parser parser to parse commands.
     */
    public Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Parse the commands from the King Program.
     * The parser automatically modifies the asset file in the
     * storage and the taskList according to the commands
     * given by the user.
     *
     * @param phrase the command given by the user.
     * @return String the reply from the command given.
     * @throws KingException kingException is thrown when phrase is invalid.
     */
    public String parse(String phrase) throws KingException {

        String mainCommand = phrase.split(" ")[0].toLowerCase();
        String reply;

        if (mainCommand.equals("bye")) {
            reply = UI.kingChatBox("Bye! Come back soon.");
        } else if (mainCommand.equals("list")) {
            reply = UI.showTaskList(taskList);
        } else if (phrase.equals("clear list")) {
            taskList.clear();
            reply = UI.kingChatBox("I have cleared the list!");
        } else if (mainCommand.equals("done")) {
            String stringItem = phrase.substring(4).trim();
            try {
                int taskNumber = Integer.parseInt(stringItem) - 1;
                Task item = taskList.get(taskNumber);
                item.markAsDone();
                reply = UI.doneChatBox(item.toString());
            } catch (IndexOutOfBoundsException e) {
                throw exceptions.itemNotFoundException(stringItem, e);
            } catch (NumberFormatException e) {
                throw (stringItem.isEmpty())
                        ? exceptions.doneNotFollowedByNumberException()
                        : exceptions.invalidNumberException(stringItem, e);
            } catch (Exception e) {
                throw exceptions.badDoneSyntaxException();
            }
        } else if (mainCommand.equals("todo")) {
            String task = phrase.substring(4).trim();
            if (!task.isEmpty()) {
                ToDo todo = new ToDo(task);
                taskList.add(todo);
                reply = UI.addItemChatBox(todo.toString(), taskList.size());
            } else {
                throw exceptions.emptyTodoException();
            }
        } else if (mainCommand.equals("event")) {
            String item = phrase.substring(5).trim();
            String[] tokens = item.split(" /at ");
            if (tokens.length == 2) {
                Event event = new Event(tokens[0], tokens[1]);
                taskList.add(event);
                reply = UI.addItemChatBox(event.toString(), taskList.size());
            } else {
                throw exceptions.badEventSyntaxException();
            }
        } else if (mainCommand.equals("deadline")) {
            String item = phrase.substring(8).trim();
            String[] tokens = item.split(" /by ");
            if (tokens.length != 2) {
                throw exceptions.badDeadlineSyntaxException();
            }
            try {
                LocalDateTime datetime = stringToLocalDateTime(tokens[1]);
                Deadline deadline = new Deadline(tokens[0], datetime);
                taskList.add(deadline);
                reply = UI.addItemChatBox(deadline.toString(), taskList.size());
            } catch (KingException badDateTimeSyntax) {
                throw badDateTimeSyntax;
            }
        } else if (mainCommand.equals("delete")) {
            String stringItem = phrase.substring(6).trim();
            try {
                int itemNo = Integer.parseInt(stringItem) - 1;
                Task item = taskList.get(itemNo);
                taskList.delete(itemNo);
                reply = UI.deleteItemChatBox(item.toString(), taskList.size());
            } catch (IndexOutOfBoundsException e) {
                throw exceptions.itemNotFoundException(stringItem, e);
            } catch (NumberFormatException e) {
                throw (stringItem.isEmpty())
                        ? exceptions.deleteNotFollowedByNumberException()
                        : exceptions.invalidNumberException(stringItem, e);
            } catch (Exception e) {
                throw exceptions.badDeleteSyntaxException();
            }
        } else if (mainCommand.equals("find")) {
            String[] keywords = phrase.substring(4).trim().split(" ");
            return UI.showFoundItems(storage.find(keywords));
        } else {
            throw exceptions.badCommandException();
        }
        storage.persistTaskList(taskList);
        return reply;
    }

    // takes a Date Time string and returns a LocalDateTime
    private LocalDateTime stringToLocalDateTime(String localDateTime) throws KingException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(localDateTime, formatter);
        } catch (Exception e) {
            throw exceptions.badLocalDateTimeException(e);
        }
    }
}
