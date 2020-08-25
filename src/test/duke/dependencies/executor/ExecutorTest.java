package duke.dependencies.executor;

import duke.dependencies.executable.Command;
import duke.dependencies.executable.Executable;
import duke.dependencies.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {

    private final Executor exe = Executor.initExecutor();
    private final Executable doneCommand = Command.createDoneCommand(Task.createMiscTask("1"));
    private final Executable addCommand = Command.createAddCommand(Task.createTodo("Run"));
    private final Executable listCommand = Command.createListCommand(Task.createEmptyTask());
    private final Executable delCommand = Command.createDeleteCommand(Task.createMiscTask("1"));

    @Test
    void test_execute_DoneCommand() {




    }
}