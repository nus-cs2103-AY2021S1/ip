package duke.duke;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DukeTest {

  @Test
  void getInstance_ShouldGiveSingleton() {
    Duke duke1 = Duke.getInstance();
    Duke duke2 = Duke.getInstance();
    assertEquals(duke1, duke2);
  }

  @Test
  void addTask_ShouldReturnCorrectTask() {
    List<String> task = Duke.getInstance().addTask("task one");
    List<String> expectedTask = Collections.singletonList("task one");
    assertEquals(expectedTask, task);
  }

  @Test
  void getTasks_ShouldGiveCorrectAddedTasks() {
    Duke.getInstance().addTask("task one");
    Duke.getInstance().addTask("task two");
    Duke.getInstance().addTask("task three");
    List<String> tasks = Duke.getInstance().getTasks();
    List<String> expectedTasks = Arrays.asList("task one", "task two", "task three");
    assertEquals(expectedTasks, tasks);
  }

  @Test
  void getTasks_ShouldNotGiveWronglyOrderedAddedTasks() {
    Duke.getInstance().addTask("task one");
    Duke.getInstance().addTask("task three");
    Duke.getInstance().addTask("task two");
    List<String> tasks = Duke.getInstance().getTasks();
    List<String> expectedTasks = Arrays.asList("task one", "task two", "task three");
    assertNotEquals(expectedTasks, tasks);
  }
}