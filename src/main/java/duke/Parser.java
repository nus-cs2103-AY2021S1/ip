package duke;

import duke.exception.*;
import duke.command.*;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private String parseMessage;
    private UserInterface ui;

    public Parser(String parseMessage, UserInterface ui) {
        this.parseMessage = parseMessage;
        this.ui = ui;
    }

    public void start(Tasklist tasklist) {

        Scanner sc = new Scanner(System.in);

        try {
            while (!this.parseMessage.equals("bye")) {
                String command = parseMessage.split(" ")[0];
                if (command.equals("list")) {
                    new ListCommand().execute(tasklist, ui);
                } else if (command.equals("find")) {
                    String wordToFind = this.parseMessage.substring(command.length());
                    new FindCommand(wordToFind).execute(tasklist, ui);
                }
                else if (command.equals("done")) {
                    int index = Integer.parseInt(parseMessage.split(" ")[1]) - 1;
                    new DoneCommand(index).execute(tasklist, ui);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(parseMessage.split(" ")[1]) - 1;
                    new DeleteCommand(index).execute(tasklist, ui);
                } else if (command.equals("event") || command.equals("deadline") || command.equals("todo")) {
                    String dissectedMessage = this.parseMessage.substring(command.length());
                    commandTasks(tasklist, command, dissectedMessage);
                } else {
                    String errorMessage = "Command is wrong. Please start with delete, list, done, deadline, todo or event.";
                    throw new DukeCommandException(errorMessage);
                }
                this.parseMessage = sc.nextLine();
                tasklist.updateStorage();
            }
        } catch (DukeCommandException | DukeIndexException | DukeTaskException | DukeListException e) {
            ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.printError("INPUT DATE TIME FORMAT IS WRONG");
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    public void commandTasks(Tasklist tasklist, String tag, String message) throws DukeTaskException {

        String[] parsedMessage = null;
        Task newTask = null;
        int size = tasklist.getTaskSize();

        try {

            if (message.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else if (tag.equals("todo")) {
                newTask = new Todo(message);
            } else if (tag.equals("deadline")) {
                parsedMessage = message.split("/by ");
                newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
            } else if (tag.equals("event")) {
                parsedMessage = message.split("/at ");
                newTask = new Event(parsedMessage[0], parsedMessage[1]);
            }

            tasklist.addTask(newTask);
            ui.printAddTask(newTask.toString(), tasklist.getTaskSize());

        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "You might have left your message or duration empty.";
            throw new DukeTaskException(errorMessage);
        }
    }

}
