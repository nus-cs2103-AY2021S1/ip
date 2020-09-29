package duke;

import static duke.TestUtils.TODO_UNDONE_STRING;
import static duke.TestUtils.createUndoneToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessageTest {
    @Test
    public void getMessage_welcome_welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String expected = "Hello from\n" + logo;
        String actual = Message.getWelcome().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getMessage_goodbye_goodbyeMessage() {
        String expected = "Have a nice day.\n";
        String actual = Message.getGoodbye().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getMessage_newTask_newTaskMessage() {
        String expected = "Task added: " + TODO_UNDONE_STRING + "\n";
        String actual = Message.getTaskAdded(createUndoneToDo()).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getMessage_doneTask_doneTaskMessage() {
        String expected = "Task marked as done: " + TODO_UNDONE_STRING + "\n";
        String actual = Message.getTaskDone(createUndoneToDo()).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getMessage_deletedTask_deletedTaskMessage() {
        String expected = "Task deleted: " + TODO_UNDONE_STRING + "\n";
        String actual = Message.getTaskDeleted(createUndoneToDo()).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getText_multiLineMessage_hasLineBreak() {
        String actual = new Message(new String[]{"hello", "how are you"}).toString();
        assertEquals("hello\nhow are you\n", actual);
    }
}
