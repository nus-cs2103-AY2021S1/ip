package undoStack;

import exception.UndoException;
import task.Task;
import taskList.TaskList;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The stack to store all lists of tasks after every action.
 * This is for the undo action.
 */
public class UndoStack {

    private static Stack<TaskList> stack;

    /**
     * Constructor.
     */
    public UndoStack() {
        stack = new Stack<TaskList>();
    }

    /**
     * Returns a static stack for undo action.
     */
    public static void initialize() {
        new UndoStack();
    }

    /**
     * Adds the current list of task into the stack.
     * @param taskList current list of tasks.
     */
    public static void add(TaskList taskList) {
        System.out.println("stack added");
        stack.push(new TaskList(new ArrayList<Task>(taskList.getTaskList())));
    }

    /**
     * Gets the previous list of tasks.
     * @return previous list of tasks.
     * @throws UndoException the exception for invalid user input.
     */
    public static TaskList getPreviousTaskList() throws UndoException {
        if (stack.size() == 0) {
            throw new UndoException();
        } else {
            return stack.pop();
        }
    }
}
