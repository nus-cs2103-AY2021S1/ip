package duke.task;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DukeTaskTest {
  @Test
  public void createToDoTest() {
    Todo todo = new Todo("Conduct ToDo Test: Creation");
    assert todo.toString().equals("[T][\u2718] Conduct ToDo Test: Creation");
  }

  @Test
  public void createDeadlineTest() {
    LocalDateTime testTime = LocalDateTime.of(2020, 8, 26, 12, 50);
    Deadline deadline = new Deadline("Conduct Deadline Test: Creation", testTime);
    assert deadline
        .toString()
        .equals("[D][\u2718] Conduct Deadline Test: Creation (by: Wed, 26/Aug/2020 1250)");
  }

  @Test
  public void createEventTest() {
    LocalDateTime testStartTime = LocalDateTime.of(2020, 8, 26, 12, 50);
    LocalDateTime testEndTime = LocalDateTime.of(2020, 8, 26, 19, 50);
    Event event = new Event("Conduct Event Test: Creation", testStartTime, testEndTime);
    System.out.println(event);
    assert event
        .toString()
        .equals(
            "[E][\u2718] Conduct Event Test: Creation (at: Wed, 26/Aug/2020 1250 till Wed, 26/Aug/2020 1950)");
  }

  @Test
  public void markTaskDoneTest_testToDo() {
    Todo todo = new Todo("Conduct ToDo Test: Mark Task Done");
    todo.markAsDone();
    assert todo.toString().equals("[T][\u2713] Conduct ToDo Test: Mark Task Done");
  }

  @Test
  public void markTaskDoneTest_testDeadline() {
    LocalDateTime testTime = LocalDateTime.of(2020, 8, 26, 12, 50);
    Deadline deadline = new Deadline("Conduct Deadline Test: Mark Task Done", testTime);
    deadline.markAsDone();
    assert deadline
        .toString()
        .equals("[D][\u2713] Conduct Deadline Test: Mark Task Done (by: Wed, 26/Aug/2020 1250)");
  }

  @Test
  public void markTaskDoneTest_testEvent() {
    LocalDateTime testStartTime = LocalDateTime.of(2020, 8, 26, 12, 50);
    LocalDateTime testEndTime = LocalDateTime.of(2020, 8, 26, 19, 50);
    Event event = new Event("Conduct Event Test: Mark Task Done", testStartTime, testEndTime);
    event.markAsDone();
    assert event
        .toString()
        .equals(
            "[E][\u2713] Conduct Event Test: Mark Task Done (at: Wed, 26/Aug/2020 1250 till Wed, 26/Aug/2020 1950)");
  }

  @Test
  public void equalsTest_testToDo() {
    Todo todo = new Todo("Conduct ToDo Test: equals");
    Todo todoTest = new Todo("Conduct ToDo Test: equals");
    assert todo.equals(todoTest);
  }

  @Test
  public void equalsTest_testDeadline() {
    LocalDateTime testTime = LocalDateTime.of(2020, 8, 26, 12, 50);
    Deadline deadline = new Deadline("Conduct Deadline Test: equals", testTime);
    LocalDateTime testTime2 = LocalDateTime.of(2020, 8, 26, 12, 50);
    Deadline deadlineTest = new Deadline("Conduct Deadline Test: equals", testTime2);
    assert deadline.equals(deadlineTest);
  }

  @Test
  public void equalsTest_testEvent() {
    LocalDateTime testStartTime = LocalDateTime.of(2020, 8, 26, 12, 50);
    LocalDateTime testEndTime = LocalDateTime.of(2020, 8, 26, 19, 50);
    Event event = new Event("Conduct Event Test: Mark Task Done", testStartTime, testEndTime);
    LocalDateTime testStartTime2 = LocalDateTime.of(2020, 8, 26, 12, 50);
    LocalDateTime testEndTime2 = LocalDateTime.of(2020, 8, 26, 19, 50);
    Event eventTest = new Event("Conduct Event Test: Mark Task Done", testStartTime2, testEndTime2);
    assert event.equals(eventTest);
  }
}
