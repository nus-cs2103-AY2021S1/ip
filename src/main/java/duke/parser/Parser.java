package duke.parser;

import duke.dukeexception.DukeKeywordMissingException;
import duke.dukeexception.DukeTaskNonExistException;
import duke.dukeexception.DukeUnknownInputException;
import duke.dukeexception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    public Storage storage;
    public TaskList shelf;
    public Scanner sc;
    public UI ui;
    public int indexer;

    public Parser(Storage storage, TaskList tasklist, UI ui) {
        this.storage = storage;
        this.shelf = tasklist;
        this.sc = new Scanner(System.in);
        this.ui = ui;
    }

    public boolean toStop(String response) {
        return response.equals("bye");
    }

    public void listen(String response) {
        try {
            if (response.equals("bye")) {
                ui.replyBye();
            } else if (response.equals("list")) {
                ui.replyList();
            } else if (response.indexOf("delete") == 0) {
                indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                ui.replyDelete(indexer);
            } else if (response.indexOf("done ") == 0) {
                indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                ui.replyDone(indexer);
            } else if (response.indexOf("todo ") == 0) {
                if (response.length() <= 5) {
                    throw new EmptyDescriptionException("todo");
                }
                ui.addTodo(response.substring(4));
            } else if (response.indexOf("deadline ") == 0) {
                if (response.length() <= 9) {
                    throw new EmptyDescriptionException("deadline");
                }
                if (!response.contains("/by ")) {
                    throw new DukeKeywordMissingException("/by ");
                }
                String date = response.substring(response.indexOf("/by ") + 4);
                response = response.substring(response.indexOf("deadline ") + 8, response.indexOf("/by "));
                ui.addDeadline(response, date);
            } else if (response.indexOf("event ") == 0) {
                if (response.length() <= 6) {
                    throw new EmptyDescriptionException("event");
                }
                if (!response.contains("/at ")) {
                    throw new DukeKeywordMissingException("/at ");
                }
                String date = response.substring(response.indexOf("/at ") + 4);
                response = response.substring(response.indexOf("event ") + 5, response.indexOf("/at "));
                ui.addEvent(response, date);
            } else {
                throw new DukeUnknownInputException("error");
            }
        } catch (IOException | DukeTaskNonExistException | EmptyDescriptionException |
                DukeKeywordMissingException | DukeUnknownInputException e) {
            ui.showError(e);
        } catch (DateTimeParseException e) {
            System.out.println("☹ OOPS!!! Ensure that the datetime input is in the format YYYY-MM-DD HH:MM");
        }
    }

}
