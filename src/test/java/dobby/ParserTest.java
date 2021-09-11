package dobby;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void getMessageByeTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // test bye command
        String bye;
        try {
            bye = parser.getMessage("bye");
        } catch (DobbyException e) {
            bye = e.getMessage();
        }
        assertEquals("Goodbye.\nHope to see you again soon!", bye);
    }

    @Test
    public void getMessageTodoTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // for todo command with no description
        String todoNoDescription;
        try {
            todoNoDescription = parser.getMessage("todo");
        } catch (DobbyException e) {
            todoNoDescription = e.getMessage();
        }
        assertEquals("Incorrect usage of command.\n"
                + "Description cannot be empty. Please try again.", todoNoDescription);

        // for valid todo command
        String todoTask;
        try {
            todoTask = parser.getMessage("todo test todo command");
        } catch (DobbyException e) {
            todoTask = e.getMessage();
        }
        assertEquals("Great! I've added the following task:\n  [T][\u2718] test todo command"
                + "\nNow you have 1 task in the list.", todoTask);
    }

    @Test
    public void getMessageDeadlineTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // for deadline command with no description
        String deadlineNoDescription;
        try {
            deadlineNoDescription = parser.getMessage("deadline");
        } catch (DobbyException e) {
            deadlineNoDescription = e.getMessage();
        }
        assertEquals("Incorrect usage of command.\n"
                + "Description cannot be empty. Please try again.", deadlineNoDescription);

        // for deadline command with no deadline details
        String emptyDeadlineDetails;
        try {
            emptyDeadlineDetails = parser.getMessage("deadline week3 iP /by ");
        } catch (DobbyException e) {
            emptyDeadlineDetails = e.getMessage();
        }
        assertEquals("Incorrect usage of command.\n"
                + "Deadline details cannot be empty. Please try again.", emptyDeadlineDetails);

        // for valid deadline command
        String deadlineTask;
        try {
            deadlineTask = parser.getMessage("deadline week3 iP /by 28/08/2020 1400");
        } catch (DobbyException e) {
            deadlineTask = e.getMessage();
        }
        assertEquals("Great! I've added the following task:\n  [D][\u2718] week3 iP "
                + "(by: Aug 28 2020 2:00 pm)"
                + "\nNow you have 1 task in the list.", deadlineTask);

    }

    @Test
    public void getMessageEventTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // for event command with no description
        String eventNoDescription;
        try {
            eventNoDescription = parser.getMessage("event");
        } catch (DobbyException e) {
            eventNoDescription = e.getMessage();
        }
        assertEquals("Incorrect usage of command."
                + "\nDescription cannot be empty. Please try again.", eventNoDescription);

        // for event command with no schedule details
        String emptyEventDetails;
        try {
            emptyEventDetails = parser.getMessage("event week3 lec /at ");
        } catch (DobbyException e) {
            emptyEventDetails = e.getMessage();
        }
        assertEquals("Incorrect usage of command."
                + "\nSchedule details cannot be empty. Please try again.", emptyEventDetails);

        // for valid event command
        String eventTask;
        try {
            eventTask = parser.getMessage("event week3 lec /at 28/08/2020 1400");
        } catch (DobbyException e) {
            eventTask = e.getMessage();
        }
        assertEquals("Great! I've added the following task:\n  [E][\u2718] week3 lec "
                + "(at: Aug 28 2020 2:00 pm)"
                + "\nNow you have 1 task in the list.", eventTask);

    }

    @Test
    public void getMessageListDoneDeleteTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // create a todo task for testing purpose
        try {
            parser.getMessage("todo test done and delete commands");
        } catch (DobbyException e) {
            System.out.println(e.getMessage());
        }

        // test list command
        String listTodo;
        try {
            listTodo = parser.getMessage("list");
        } catch (DobbyException e) {
            listTodo = e.getMessage();
        }
        assertEquals("1. [T][\u2718] test done and delete commands", listTodo);

        // test done command
        String doneTodo;
        try {
            doneTodo = parser.getMessage("done 1");
        } catch (DobbyException e) {
            doneTodo = e.getMessage();
        }
        assertEquals("Great! I've marked this task as done:\n  "
                + "[T][\u2713] test done and delete commands", doneTodo);

        // test delete command
        String deleteTodo;
        try {
            deleteTodo = parser.getMessage("delete 1");
        } catch (DobbyException e) {
            deleteTodo = e.getMessage();
        }
        assertEquals("Noted. I've removed this task:\n  "
                + "[T][\u2713] test done and delete commands", deleteTodo);
    }

    @Test
    public void getMessageScheduledTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        // deadline task for testing purpose
        try {
            parser.getMessage("deadline push A-JUnit to github /by 24/08/2020 2359");
        } catch (DobbyException e) {
            e.printStackTrace();
        }


        // test scheduled command
        String scheduled;
        try {
            scheduled = parser.getMessage("scheduled 24/08/2020");
        } catch (DobbyException e) {
            scheduled = e.getMessage();
        }
        assertEquals("1. [D][\u2718] push A-JUnit to github (by: Aug 24 2020 11:59 pm)\n", scheduled);
    }

    @Test
    public void findTypeTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        String message = "";
        String noType;
        try {
            parser.getMessage("todo test findtype command");
            message = parser.getMessage("findtype T");
        } catch (DobbyException e) {
            System.out.println(e.getMessage());
        }

        try {
            noType = parser.getMessage("findtype");
        } catch (DobbyException e) {
            noType = e.getMessage();
        }

        assertEquals("1. [T][\u2718] test findtype command\n", message);
        assertEquals("Incorrect usage of command. Please try again.", noType);
    }

    @Test
    public void findKeywordTest () {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        String message = "";
        String noKeyword;
        String noTaskContainingKeyword;
        try {
            parser.getMessage("todo test findtype command");
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

        assertEquals("1. [T][\u2718] test findtype command\n", message);
        assertEquals("Incorrect usage of command."
                + "\nKeyword required cannot be empty. "
                + "Please try again.", noKeyword);
        assertEquals("There are no tasks of containing the word - none",
                noTaskContainingKeyword);
    }
}
