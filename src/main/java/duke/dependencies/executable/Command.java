package duke.dependencies.executable;

import duke.dependencies.task.Task;


/**
 * Class to encapsulate a command given by the user to Duke.
 * Commands have predefined types such as delete, todo... etc.
 * Contains the task associated with the command. For which the command is to be executed.
 * Some task may be empty and could be associated with Commands. For eg. a 'list' command does not have a task.
 *
 */
public class Command implements Executable {

    private CommandType command;
    private Task task;

    private Command(CommandType command, Task task) {
        this.command = command;
        this.task = task;
    }

    @Override
    /**
     * Returns the type of the command.
     *
     * @return Command type.
     */
    public CommandType getType() {
        return command;
    }

    @Override
    /**
     * Returns the Task object for this command.
     *
     * @return Task object.
     */
    public Task getTask() {
        return this.task;
    }


    /* -------------------------------------- Static Factory Methods --------------------------------------------------*/


    /**
     * Returns a Command of type ADD. This command encapsulates the instruction to add a specific Task object to
     * the task list of the user.
     * @param task The Task object representing the task user wants to complete.
     * @return Command of type ADD.
     */
    public static Command createAddCommand(Task task) {
        return new Command(CommandType.ADD, task);
    }
    /**
     * Returns a Command of type DONE. This command encapsulates the instruction to complete a specific Task
     * object that the user wants to complete.
     * @param task The Task object representing the task user wants to complete.
     * @return Command of type DONE.
     */
    public static Command createDoneCommand(Task task) {
        return new Command(CommandType.DONE, task);
    }

    /**
     * Returns a Command of type LIST. This command encapsulates the instruction to list all task in the task list.
     * @param task This does not affect the command in any way aas LIST commands do not inherently associate with any
     *             task.
     * @return Command of type LIST.
     */
    public static Command createListCommand(Task task) {
        return new Command(CommandType.LIST, Task.createEmptyTask());
    }

    /**
     * Returns a Command of type DELETE. This command encapsulates the instruction to remove a Task from the task
     * list by the index of the Task in the list.
     * @param task The Task object that the user wants to delete.
     * @return Command of type DELETE.
     */
    public static Command createDeleteCommand(Task task) {
        return new Command(CommandType.DELETE, task);
    }

    /**
     * Returns a Command of type FIND. This command encapsulates the instruction to find tasks in the list that has
     * words matching the keyword specified by the user in the 'task'.
     * @param keyword Task object, which holds the keyword that has to be found in the list (This task
     *             object is a Misc. Task).
     * @return Command of type FIND.
     */
    public static Command createFindCommand(Task keyword) {
        return new Command(CommandType.FIND, keyword);
    }

    public static Command createClearCacheCommand(Task task) {
        return new Command(CommandType.CLEAR, null);
    }

    public static Command createCheckAuthCommand(Task task) {
        return new Command(CommandType.AUTHCHECK, null);
    }

    /* -------------------------------------------- DEPRECATED ------------------------------------------------------ */

//    public static Command createEventCommand(Task task) {
//        return new Command(CommandType.EVENT, task);
//    }
//
//    public static Command createDeadlineCommand(Task task) {
//        return new Command(CommandType.DEADLINE, task);
//    }
//
//    public static Command createTodoCommand(Task task) {
//        return new Command(CommandType.TODO, task);
//    }
//
//    public static Command createGetCompletedCommand(Task task) {
//        return new Command(CommandType.GETCOMPLETED, null);
//    }
//
//    public static Command createGetIncompleteCommand(Task task) {
//        return new Command(CommandType.GETINCOMPLETE, null);
//    }
//
//    public static Command createGetListSizeCommand(Task task) {
//        return new Command(CommandType.GETLISTSIZE, null);
//    }
}
