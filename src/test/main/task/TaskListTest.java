package main.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;

public class TaskListTest {
    private static Todo taskOne;
    private static Deadline taskTwo;
    private static Event taskThree;

    @BeforeEach
    public void beforeEach() throws InvalidOptionException {
        taskOne = new Todo("task 1", true, new String[0]);
        taskTwo = new Deadline(
                "task 2",
                LocalDateTime.of(1993, 12, 6, 10, 10),
                new HashSet<>(),
                new String[0]
        );
        taskThree = new Event(
                "task 1",
                "",
                "1993-12-06T10:10",
                false,
                new String[0]
        );
    }

    @Nested
    @DisplayName("size")
    class Size {
        @Test
        @DisplayName("should return 0 for empty list")
        public void size_taskList_size() {
            assertEquals(0, new TaskList().size());
        }

        @Test
        @DisplayName("should return list size for alternate list")
        public void size_altTaskList_altSize() {
            TaskList tasks = new TaskList();
            tasks.add(taskOne);
            tasks.add(taskTwo);
            tasks.add(taskThree);

            assertEquals(3, tasks.size());
        }
    }

    @Nested
    @DisplayName("get")
    class Get {
        @Test
        @DisplayName("should return task from list")
        public void size_taskList_size() {
            TaskList tasks = new TaskList();
            tasks.add(taskThree);
            assertEquals(1, tasks.size());
            assertEquals(taskThree, tasks.get(0));
            assertEquals(1, tasks.size());
        }

        @Test
        @DisplayName("should return alternate task from list")
        public void size_altTaskList_altSize() {
            TaskList tasks = new TaskList();
            tasks.add(taskOne);
            tasks.add(taskTwo);
            tasks.add(taskThree);

            assertEquals(3, tasks.size());
            assertEquals(taskTwo, tasks.get(1));
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
            tasks.add(taskOne);
            assertEquals(1, tasks.size());
            assertEquals(taskOne, tasks.get(0));
        }

        @Test
        @DisplayName("should add alternate task to the list")
        public void add_altTask() {
            TaskList tasks = new TaskList();

            assertEquals(0, tasks.size());
            tasks.add(taskTwo);
            assertEquals(1, tasks.size());
            assertEquals(taskTwo, tasks.get(0));
        }
    }

    @Nested
    @DisplayName("remove")
    class Remove {
        @Test
        @DisplayName("should remove and return task from list")
        public void remove_index_removedTask() {
            TaskList tasks = new TaskList();
            tasks.add(taskTwo);

            assertEquals(1, tasks.size());
            assertEquals(taskTwo, tasks.remove(0));
            assertEquals(0, tasks.size());
        }

        @Test
        @DisplayName("should remove and return alt task from list")
        public void remove_altIndex_altRemovedTask() {
            TaskList tasks = new TaskList();
            tasks.add(taskOne);
            tasks.add(taskThree);

            assertEquals(2, tasks.size());
            assertEquals(taskThree, tasks.remove(1));
            assertEquals(1, tasks.size());
        }
    }
}
