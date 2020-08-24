package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class CommandTest {
  @Test
  void create_commandTypeList_returnListCommand() throws DukeException {
    assert Command.create(CommandType.LIST, "something") instanceof ListCommand;
  }

  @Test
  void create_commandTypeListDate_returnListDateCommand() throws DukeException {
    assert Command.create(CommandType.LIST_DATE, "something") instanceof ListDateCommand;
  }

  @Test
  void create_commandTypeDone_returnDoneCommand() throws DukeException {
    assert Command.create(CommandType.DONE, "something") instanceof DoneCommand;
  }

  @Test
  void create_commandTypeToDo_returnToDoCommand() throws DukeException {
    assert Command.create(CommandType.TODO, "something") instanceof ToDoCommand;
  }

  @Test
  void create_commandTypeDeadline_returnDeadlineCommand() throws DukeException {
    assert Command.create(CommandType.DEADLINE, "something") instanceof DeadlineCommand;
  }

  @Test
  void create_commandTypeEvent_returnEventCommand() throws DukeException {
    assert Command.create(CommandType.EVENT, "something") instanceof EventCommand;
  }

  @Test
  void create_commandTypeDelete_returnDeleteCommand() throws DukeException {
    assert Command.create(CommandType.DELETE, "something") instanceof DeleteCommand;
  }

  @Test
  void create_commandTypeBye_returnByeCommand() throws DukeException {
    assert Command.create(CommandType.BYE, "something") instanceof ByeCommand;
  }
}
