import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import command.Command;
import command.DeadlineCommand;
import command.DeleteAllCommand;
import command.DeleteCommand;
import command.DoneAllCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.ListCommand;
import command.ShowAfterCommand;
import command.ShowBeforeCommand;
import command.TodoCommand;
import command.WrongCommand;
import parser.Parser;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;





public class ParserTest {

    @Test
    public void readFileTest() {
        Task test1 = Parser.readFileParser("T | 0 | read book");
        TodoTask todoTask = new TodoTask("read book");

        assertEquals(todoTask.toString(), test1.toString());
        assertEquals(todoTask.getDescription(), test1.getDescription());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task test2 = (Parser.readFileParser("D | 0 | return book | 2020-06-06 06:00"));
        DeadlineTask deadlineTask = new DeadlineTask("return book", LocalDateTime
                .parse("2020-06-06 06:00", formatter));

        assertEquals(deadlineTask.toString(), test2.toString());
        assertEquals(deadlineTask.getDescription(), test2.getDescription());
        assertEquals(deadlineTask.getClass(), test2.getClass());
        assertEquals(deadlineTask.getDateTime(), ((DeadlineTask) test2).getDateTime());

        Task test3 = Parser.readFileParser("E | 0 | return book | 2020-06-06 06:00");
        EventTask eventTask = new EventTask("return book", LocalDateTime.parse("2020-06-06 06:00", formatter));

        assertEquals(eventTask.toString(), test3.toString());
        assertEquals(eventTask.getDescription(), test3.getDescription());
        assertEquals(eventTask.getDateTime(), ((EventTask) test3).getDateTime());
    }

    @Test
    public void parseCommandTest() {
        Command test1 = Parser.parseCommand("bye");
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(exitCommand.getClass(), test1.getClass());

        Command test2 = Parser.parseCommand("list");
        ListCommand listCommand = new ListCommand();
        assertEquals(listCommand.getClass(), test2.getClass());

        Command test3 = Parser.parseCommand("done all");
        DoneAllCommand doneAllCommand = new DoneAllCommand();
        assertEquals(doneAllCommand.getClass(), test3.getClass());

        Command test4 = Parser.parseCommand("done 1");
        DoneCommand doneCommand = new DoneCommand("done 1");
        assertEquals(doneCommand.getClass(), test4.getClass());

        Command test5 = Parser.parseCommand("todo borrow book");
        TodoCommand todoCommand = new TodoCommand("todo borrow book");
        assertEquals(todoCommand.getClass(), test5.getClass());

        Command test6 = Parser.parseCommand("deadline eat /by 2020-04-04 16:00");
        DeadlineCommand deadlineCommand = new DeadlineCommand("deadline eat /by 2020-04-04 16:00");
        assertEquals(deadlineCommand.getClass(), test6.getClass());

        Command test7 = Parser.parseCommand("event eat /by 2020-04-04 16:00");
        EventCommand eventCommand = new EventCommand("event eat /by 2020-04-04 16:00");
        assertEquals(eventCommand.getClass(), test7.getClass());

        Command test8 = Parser.parseCommand("--help");
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(helpCommand.getClass(), test8.getClass());

        Command test9 = Parser.parseCommand("delete all");
        DeleteAllCommand deleteAllCommand = new DeleteAllCommand();
        assertEquals(deleteAllCommand.getClass(), test9.getClass());

        Command test10 = Parser.parseCommand("delete 1");
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        assertEquals(deleteCommand.getClass(), test10.getClass());

        Command test11 = Parser.parseCommand("show after 2020-04-04");
        ShowAfterCommand showAfterCommand = new ShowAfterCommand("show after 2020-04-04");
        assertEquals(showAfterCommand.getClass(), test11.getClass());

        Command test12 = Parser.parseCommand("show before 2020-04-04");
        ShowBeforeCommand showBeforeCommand = new ShowBeforeCommand("show before 2020-04-04");
        assertEquals(showBeforeCommand.getClass(), test12.getClass());

        Command test13 = Parser.parseCommand("blah");
        WrongCommand wrongCommand = new WrongCommand("blah");
        assertEquals(wrongCommand.getClass(), test13.getClass());
    }

    @Test
    public void findIndexParserTest() {
        Assertions.assertDoesNotThrow(() -> {
            int test1 = Parser.findIndexParser("done 1");
            assertEquals(1, test1);

            int test2 = Parser.findIndexParser("delete 1");
            assertEquals(1, test2);
        });
    }

    @Test
    public void findDateParserTest() {
        Assertions.assertDoesNotThrow(()-> {
            LocalDate test1 = Parser.findDateParser("show before 2020-05-05");
            LocalDate localDate = LocalDate.parse("2020-05-05");
            assertEquals(localDate, test1);

            LocalDate test2 = Parser.findDateParser("show after 2020-05-05");
            assertEquals(localDate, test2);
        });
    }

    @Test
    public void findDescriptionParserTest() {
        Assertions.assertDoesNotThrow(() -> {
            Map<String, String> test1 = Parser.findDescriptionParser("event borrow book /by 2020-04-04 18:00");
            assertEquals("borrow book", test1.get("taskDescription"));
            assertEquals("2020-04-04 18:00", test1.get("taskTime"));

            Map<String, String> test2 = Parser.findDescriptionParser("deadline eating a lot /by 2020-04-04 18:00");
            assertEquals("eating a lot", test2.get("taskDescription"));
            assertEquals("2020-04-04 18:00", test2.get("taskTime"));
        });
    }

    @Test
    public void findTodoParserTest() {
        Assertions.assertDoesNotThrow(() -> {
            String test1 = Parser.findTodoParser("todo borrow book");
            assertEquals("borrow book", test1);
        });
    }
}
