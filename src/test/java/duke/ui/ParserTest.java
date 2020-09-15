package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.CommandAdd;
import duke.commands.CommandAddDeadline;
import duke.commands.CommandAddEvent;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;

public class ParserTest {

    @Test
    public void getCommandFromInput_eventDescriptionDurationString_createsAddEventCommand() {
        String input = "event john mayer concert /at 2020-09-10 1800-2200";
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        CommandAdd expectedCommand = new CommandAddEvent(taskList,
                new TaskEvent("john mayer concert", LocalDate.parse("2020-09-10"),
                        LocalTime.parse("18:00"), LocalTime.parse("22:00")));
        assertEquals(expectedCommand.getTask().toString(), (
                (CommandAdd) parser.getCommandFromInput(input)).getTask().toString());
    }

    @Test
    public void getCommandFromInput_deadlineDescriptionDeadlineString_createsAddDeadlineCommand() {
        String input = "deadline mop floor /by 2020-09-10 2000";
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        CommandAdd expectedCommand = new CommandAddDeadline(taskList,
                new TaskDeadline("mop floor", LocalDateTime.of(2020, 9, 10, 20, 0)));
        assertEquals(expectedCommand.getTask().toString(), (
                (CommandAdd) parser.getCommandFromInput(input)).getTask().toString());
    }

    @Test
    public void getPostponeCommandFromInput_singularTimeUnitWithPluralAmount_createsPostponeCommand() {
        TaskList taskList = new TaskList();
        Task event = new TaskEvent("john mayer concert", LocalDate.of(2020, 9, 10),
                LocalTime.of(18, 0), LocalTime.of(21, 0));
        taskList.add(event);
        String input = "postpone 1 /by 2 hour";
        Parser parser = new Parser(taskList);
        Command postponeCommand = parser.getCommandFromInput(input);

        String newTime = "Sep 10 2020, 2000hrs - 2300hrs";
        boolean containsCorrectOutput = postponeCommand.execute().contains(newTime);
        assertTrue(containsCorrectOutput);
    }
}
