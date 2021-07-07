package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommandTest {
    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        HelpCommand helpCommand = new HelpCommand();
        CommandResponse actual = helpCommand.execute(tasks, ui, storage);
        String expectedMessage = "Here is what you can do with me:\n\t "
                + "* help: list of the commands that I understand\n\t "
                + "* todo <description>: add a new TODO\n\t "
                + "* deadline <description> /by <dd/MM/yyyy HH:mm>: add a new DEADLINE\n\t "
                + "* event <description> /at <dd/MM/yyyy HH:mm>: add a new EVENT\n\t "
                + "* list: list out the tasks you have currently\n\t "
                + "* delete <task number>: delete the task from your list\n\t "
                + "* done <task number>: mark the task as complete\n\t "
                + "* retrieve <dd/MM/yyyy>: retrieves the tasks due on or happening on this date\n\t "
                + "* find <keyword>: retrieves the tasks that contain the keyword\n\t "
                + "* sort: sorts your list by their task type and then by their date and time if any\n\t "
                + "* bye: exit the application";
        CommandResponse expected = new CommandResponse(expectedMessage, false);
        assertEquals(expected, actual);
    }
}
