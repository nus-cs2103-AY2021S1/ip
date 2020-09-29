package duke.task;

import static duke.TestUtils.TODO_DONE_STRING;
import static duke.TestUtils.createDoneDeadline;
import static duke.TestUtils.createDoneToDo;
import static duke.TestUtils.createUndoneDeadline;
import static duke.TestUtils.createUndoneDeadlineDifferentDate;
import static duke.TestUtils.createUndoneToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDone_undoneTask_changesSymbol() {
        Task task = createUndoneToDo();
        task.markAsDone();
        assertEquals(TODO_DONE_STRING, task.toString());
    }

    @Test
    public void markAsDone_doneTask_doesNothing() {
        Task task = createDoneToDo();
        task.markAsDone();
        assertEquals(TODO_DONE_STRING, task.toString());
    }

    @Test
    public void containsKeyword_titleMatchesKeyword_returnsTrue() {
        Task task = createDoneToDo();
        assertTrue(task.containsKeyword("hello"));
    }

    @Test
    public void containsKeyword_titleMatchesKeywordWithWhitespace_returnsTrue() {
        Task task = createDoneToDo();
        assertTrue(task.containsKeyword(" hello  "));
    }

    @Test
    public void isDuplicate_sameTask_returnsTrue() {
        Task task = createUndoneToDo();
        assertTrue(task.isDuplicate(createUndoneToDo()));
    }

    @Test
    public void isDuplicate_sameTitleDifferentType_returnsFalse() {
        Task task = createUndoneToDo();
        assertFalse(task.isDuplicate(createDoneDeadline()));
    }

    @Test
    public void isDuplicate_sameTitleSameTypeDifferentDate_returnsTrue() {
        Task task = createUndoneDeadline();
        assertTrue(task.isDuplicate(createUndoneDeadlineDifferentDate()));
    }

    @Test
    public void isDuplicate_sameTitleSameTypeSameDate_returnsTrue() {
        Task task = createUndoneDeadline();
        assertTrue(task.isDuplicate(createDoneDeadline()));
    }

    @Test
    public void hasSameDate_sameDate_returnsTrue() {
        Task task = createDoneDeadline();
        assertTrue(task.hasSameDate(LocalDate.parse("2020-01-01")));
    }

    @Test
    public void hasSameDate_differentDate_returnsFalse() {
        Task task = createDoneDeadline();
        assertFalse(task.hasSameDate(LocalDate.parse("2019-01-01")));
    }
}
