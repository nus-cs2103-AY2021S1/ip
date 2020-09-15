package bob;

import java.io.IOException;

import bob.commands.Command;
import bob.commands.DeadlineCommand;
import bob.commands.DeleteCommand;
import bob.commands.DoneCommand;
import bob.commands.EventCommand;
import bob.commands.TodoCommand;
import bob.commands.UndoCommand;
import bob.common.Messages;
import bob.common.MsgGenerator;
import bob.data.task.Tasklist;
import bob.exceptions.BobException;
import bob.exceptions.BobInvalidUndoException;
import bob.parser.Parser;
import bob.storage.Storage;

/**
 * Represents the task-managing ChatBot.
 */
public class Bob {
    private final Storage storage;
    private Tasklist tasks;
    private boolean hasExited;

    /**
     * Creates a Bob.
     *
     * @throws IOException If saved file can't be loaded.
     */
    public Bob() throws IOException, BobException {
        String filePath = getPathName();
        Tasklist tempTasks = null;
        this.storage = new Storage(filePath);
        try {
            tempTasks = new Tasklist(storage);
        } catch (BobException | IOException e) {
            tempTasks = new Tasklist();
            throw e;
        } finally {
            this.tasks = tempTasks;
            this.hasExited = false;
        }
    }

    private String getPathName() {
        boolean ifPathDirIsIp = System.getProperty("user.dir").endsWith("ip");
        return ifPathDirIsIp
                ? "data/bob.txt"
                // Creates a save file on the user's home directory if user is not in ip directory
                : System.getProperty("user.dir") + "/bob.txt";
    }

    private void setStateOfTasklist(Command c) throws BobInvalidUndoException {
        boolean undoCondition = c.getClass().isAssignableFrom(UndoCommand.class);
        boolean saveTasklistCondition = c.getClass().isAssignableFrom(DeadlineCommand.class)
                || c.getClass().isAssignableFrom(DeleteCommand.class)
                || c.getClass().isAssignableFrom(DoneCommand.class)
                || c.getClass().isAssignableFrom(EventCommand.class)
                || c.getClass().isAssignableFrom(TodoCommand.class)
                || c.getClass().isAssignableFrom(DeadlineCommand.class);
        boolean noCondition = !(undoCondition || saveTasklistCondition);

        if (undoCondition) {
            this.tasks = this.tasks.getPreviousTasklist();
        } else if (saveTasklistCondition) {
            tasks = tasks.savePreviousTasklist();
        } else {
            assert noCondition;
        }
    }

    /**
     * Executes an action based on user input
     * @param userInput User input.
     * @return Response message to user.
     */
    public String nextAction(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            setStateOfTasklist(c);
            String replyMessage = c.execute(tasks, storage);
            this.hasExited = c.isExited();
            return replyMessage;
        } catch (IOException e) {
            return Messages.UPDATE_ERROR;
        } catch (BobException e) {
            return MsgGenerator.generateError(e);
        }
    }

    public String getReminders() {
        return MsgGenerator.generateReminderMessages(this.tasks);
    }

    public boolean checkExited() {
        return this.hasExited;
    }
}
