package duke.input;

import static org.junit.jupiter.api.Assertions.*;

import duke.utils.Parser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParserTest {

  @Test
  void isExitCommand_ShouldBeCaseInsensitive() {
    assertTrue(Parser.isExitCommand(Parser.CMD_TERMINATE.toLowerCase()));
    assertTrue(Parser.isExitCommand(Parser.CMD_TERMINATE.toUpperCase()));
  }

  @Test
  void runCommand_AddTask_ShouldReturnTask() {
    String command = "test task";
    List<String> res = Parser.runCommand(command);
    assertEquals(Collections.singletonList(command), res);
  }

  @Test
  void runCommand_ListTasks_ShouldReturnEmpty_WhenNoTasksAdded() {
    List<String> res = Parser.runCommand(Parser.CMD_TASK_LIST);
    assertEquals(new ArrayList<String>(), res);
  }

//  @Test
//  void runCommand_ListTasks_ShouldReturnAddedTasks() {
//    Parser.runCommand(Parser.CMD_TASK_LIST);
//  }
}