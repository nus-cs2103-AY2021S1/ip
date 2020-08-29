package stub;
import java.util.ArrayList;

import duke.logic.TaskList;


/**
 * Represents a TaskList during testing
 */
public class TaskListStub extends TaskList {
    public TaskListStub() {
        super(new ArrayList<>());
    }
}
