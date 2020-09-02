package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private static final Todo TASK_ONE = new Todo("task 1", true);
    private static final Deadline TASK_TWO = new Deadline("task 2",
            LocalDateTime.of(1993, 12, 6, 10, 10));
    private static final Event TASK_THREE = new Event(
            "task 1", "1993-12-06T10:10", false);

    @Nested
    @DisplayName("size")
    class Size {
        @Test
        @DisplayName("should return 0 for empty list")
        public void size_noInput_size() {
            assertEquals(0, new TaskList().size());
        }

        @Test
        @DisplayName("should return list size for alternate list")
        public void size_noInput_altSize() {
            TaskList tasks = new TaskList();
            tasks.add(TASK_ONE);
            tasks.add(TASK_TWO);
            tasks.add(TASK_THREE);

            assertEquals(3, tasks.size());
        }
    }

    @Nested
    @DisplayName("get")
    class Get {
        @Test
        @DisplayName("should return task from list")
        public void size_noInput_size() {
            TaskList tasks = new TaskList();
            tasks.add(TASK_THREE);
            assertEquals(1, tasks.size());
            assertEquals(TASK_THREE, tasks.get(0));
            assertEquals(1, tasks.size());
        }

        @Test
        @DisplayName("should return alternate task from list")
        public void size_noInput_altSize() {
            TaskList tasks = new TaskList();
            tasks.add(TASK_ONE);
            tasks.add(TASK_TWO);
            tasks.add(TASK_THREE);

            assertEquals(3, tasks.size());
            assertEquals(TASK_TWO, tasks.get(1));
            assertEquals(3, tasks.size());
        }
    }

    @Nested
    @DisplayName("add")
    class Add {
        @Test
        @DisplayName("should add task to the list")
        public void add_task() {
            TaskList tasks = new TaskList();

            assertEquals(0, tasks.size());
            tasks.add(TASK_ONE);
            assertEquals(1, tasks.size());
            assertEquals(TASK_ONE, tasks.get(0));
        }

        @Test
        @DisplayName("should add alternate task to the list")
        public void add_altTask() {
            TaskList tasks = new TaskList();

            assertEquals(0, tasks.size());
            tasks.add(TASK_TWO);
            assertEquals(1, tasks.size());
            assertEquals(TASK_TWO, tasks.get(0));
        }
    }

    @Nested
    @DisplayName("remove")
    class Remove {
        @Test
        @DisplayName("should remove and return task from list")
        public void remove_index_removedTask() {
            TaskList tasks = new TaskList();
            tasks.add(TASK_TWO);

            assertEquals(1, tasks.size());
            assertEquals(TASK_TWO, tasks.remove(0));
            assertEquals(0, tasks.size());
        }

        @Test
        @DisplayName("should remove and return alt task from list")
        public void remove_index_altRemovedTask() {
            TaskList tasks = new TaskList();
            tasks.add(TASK_ONE);
            tasks.add(TASK_THREE);

            assertEquals(2, tasks.size());
            assertEquals(TASK_THREE, tasks.remove(1));
            assertEquals(1, tasks.size());
        }
    }
}
