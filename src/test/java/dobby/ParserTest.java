package dobby;

import dobby.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void getMessageByeTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // test bye command
        String bye = "";
        try {
            bye = parser.getMessage("bye");
        } catch (DobbyException e) {
            bye = e.getMessage();
        }
        assertEquals("\n    Goodbye. Hope to see you again soon!\n    ", bye);
    }

    @Test
    public void getMessageTodoTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // for todo command with no description
        String todoNoDescription = "";
        try {
            todoNoDescription = parser.getMessage("todo");
        } catch (DobbyException e){
            todoNoDescription = e.getMessage();
        }
        assertEquals("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                + (Commands.TODO).getUsage() + "\n    ", todoNoDescription);

        // for valid todo command
        String todoTask = "";
        try {
            todoTask = parser.getMessage("todo test todo command");
        } catch (DobbyException e) {
            todoTask = e.getMessage();
        }
        assertEquals("\n    Great! I've added the following task:\n      [T][✘] test todo command"
                + "\n    Now you have 1 task in the list.\n    ", todoTask);

    }

    @Test
    public void getMessageDeadlineTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // for deadline command with no description
        String deadlineNoDescription = "";
        try {
            deadlineNoDescription = parser.getMessage("deadline");
        } catch (DobbyException e){
            deadlineNoDescription = e.getMessage();
        }
        assertEquals("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                + (Commands.DEADLINE).getUsage() + "\n    ", deadlineNoDescription);

        // for deadline command with no deadline details
        String emptyDeadlineDetails = "";
        try {
            emptyDeadlineDetails = parser.getMessage("deadline week3 iP /by ");
        } catch (DobbyException e){
            emptyDeadlineDetails = e.getMessage();
        }
        assertEquals("\n    Incorrect usage of command. Deadline details cannot be empty. Please try again."
                + (Commands.DEADLINE).getUsage() + "\n    ", emptyDeadlineDetails);

        // for valid deadline command
        String deadlineTask = "";
        try {
            deadlineTask = parser.getMessage("deadline week3 iP /by 28/08/2020 1400");
        } catch (DobbyException e) {
            deadlineTask = e.getMessage();
        }
        assertEquals("\n    Great! I've added the following task:\n      [D][✘] week3 iP (by: Aug 28 2020 2:00 pm)"
                + "\n    Now you have 1 task in the list.\n    ", deadlineTask);

    }

    @Test
    public void getMessageEventTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // for event command with no description
        String eventNoDescription = "";
        try {
            eventNoDescription = parser.getMessage("event");
        } catch (DobbyException e){
            eventNoDescription = e.getMessage();
        }
        assertEquals("\n    Incorrect usage of command. Description cannot be empty. Please try again."
                + (Commands.EVENT).getUsage() + "\n    ", eventNoDescription);

        // for event command with no schedule details
        String emptyEventDetails = "";
        try {
            emptyEventDetails = parser.getMessage("event week3 lec /at ");
        } catch (DobbyException e){
            emptyEventDetails = e.getMessage();
        }
        assertEquals("\n    Incorrect usage of command. Schedule details cannot be empty. Please try again."
                + (Commands.EVENT).getUsage() + "\n    ", emptyEventDetails);

        // for valid event command
        String eventTask = "";
        try {
            eventTask = parser.getMessage("event week3 lec /at 28/08/2020 1400");
        } catch (DobbyException e) {
            eventTask = e.getMessage();
        }
        assertEquals("\n    Great! I've added the following task:\n      [E][✘] week3 lec (at: Aug 28 2020 2:00 pm)"
                + "\n    Now you have 1 task in the list.\n    ", eventTask);

    }

    @Test
    public void getMessageListDoneDeleteTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // create a todo task for testing purpose
        try {
            String message = parser.getMessage("todo test done and delete commands");
        } catch (DobbyException e) {
            System.out.println(e.getMessage());
        }

        // test list command
        String listTodo = "";
        try {
            listTodo = parser.getMessage("list");
        } catch (DobbyException e) {
            listTodo = e.getMessage();
        }
        assertEquals("\n    1. [T][✘] test done and delete commands\n    ", listTodo);

        // test done command
        String doneTodo = "";
        try {
            doneTodo = parser.getMessage("done 1");
        } catch (DobbyException e) {
            doneTodo = e.getMessage();
        }
        assertEquals("\n    Great! I've marked this task as done:\n      "
                + "[T][✓] test done and delete commands\n    ", doneTodo);

        // test delete command
        String deleteTodo = "";
        try {
            deleteTodo = parser.getMessage("delete 1");
        } catch (DobbyException e) {
            deleteTodo = e.getMessage();
        }
        assertEquals("\n    Noted. I've removed this task:\n      "
                + "[T][✓] test done and delete commands\n    ", deleteTodo);
    }

    @Test
    public void getMessageScheduledTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // deadline task for testing purpose
        try {
            String message = parser.getMessage("deadline push A-JUnit to github /by 24/08/2020 2359");
        } catch (DobbyException e) {
            String message = e.getMessage();
        }

        // test scheduled command
        String scheduled = "";
        try {
            scheduled = parser.getMessage("scheduled 24/08/2020");
        } catch (DobbyException e) {
            scheduled = e.getMessage();
        }
        assertEquals("\n    1. [D][✘] push A-JUnit to github (by: Aug 24 2020 11:59 pm)\n    ", scheduled);
    }

    @Test
    public void findTypeTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        String message = "";
        String noType = "";
        try {
            String todo = parser.getMessage("todo test findtype command");
            message = parser.getMessage("findtype T");
        } catch (DobbyException e) {
            System.out.println(e.getMessage());
        }

        try {
            noType = parser.getMessage("findtype");
        } catch (DobbyException e) {
            noType = e.getMessage();
        }

        assertEquals("\n    1. [T][✘] test findtype command\n    ", message);
        assertEquals("\n    Incorrect usage of command. Please try again." +
                "\n      findtype _T/D/E_\n    ", noType);
    }

    @Test
    public void findKeywordTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        String message = "";
        String noKeyword = "";
        String noTaskContainingKeyword = "";
        try {
            String todo = parser.getMessage("todo test findtype command");
            message = parser.getMessage("find command");
            noKeyword = parser.getMessage("find");
        } catch (DobbyException e) {
            noKeyword = e.getMessage();
        }

        try {
            noTaskContainingKeyword = parser.getMessage("find none");
        } catch (DobbyException e) {
            noTaskContainingKeyword = e.getMessage();
        }

        assertEquals("\n    1. [T][✘] test findtype command\n    ", message);
        assertEquals("\n    Incorrect usage of command. Keyword required cannot be empty. " +
                "Please try again.\n      find _keyword_\n    ", noKeyword);
        assertEquals("\n    There are no tasks of containing the word - none\n    ",
                noTaskContainingKeyword);
    }
}
