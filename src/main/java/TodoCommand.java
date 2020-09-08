/**
 * Represents a todo command.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class TodoCommand extends Command {
    /**
     * The string containing information about the todo task.
     */
    private final String command;
    /**
     * The length of the string "todo"
     */
    private final int TODO_LENGTH = 4;

    /**
     * Todo constructor.
     *
     * @param command   The string containing information about the todo task.
     */
    public TodoCommand(String command) {
        this.command = command;
    }

    private String parseDescAndTag(String descAndTag, TaskList taskList) {
        boolean containsTag = descAndTag.contains("#");
        if (containsTag) {
            String[] descAndTagArray = descAndTag.split("#");
            assert descAndTagArray.length == 2 : "descAndTagArray should have length of 2";
            String desc = descAndTagArray[0].trim();
            String tagString = descAndTagArray[1].trim();
            Tag tag = new Tag(tagString);
            return taskList.addTodo(desc, tag);
        } else {
            return taskList.addTodo(descAndTag, new Tag());
        }

    }

    /**
     * Executes the command.
     *
     * @param storage   The storage object that handles interactions with the
     *                  local file.
     * @param ui        The UI object that handles interactions with the user.
     * @param taskList  The current list of tasks.
     * @return          The string to be displayed to the user.
     * @throws DukeException    When the input command is invalid.
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        String descAndTag = this.command.substring(TODO_LENGTH).trim();
        boolean isDescAndTagEmpty = descAndTag.isEmpty();
        if (!isDescAndTagEmpty) {
            return parseDescAndTag(descAndTag, taskList);
        } else {
            throw new DukeException("Please enter a valid todo");
        }
    }
}
