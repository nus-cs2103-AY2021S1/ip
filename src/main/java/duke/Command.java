package duke;

import task.TaskType;
import utility.NullListException;

import java.io.IOException;
import java.security.InvalidParameterException;

public class Command {
    private final String line;

    Command(String command) {
        this.line = command;
    }

    /**
     * Executes the command
     *
     * @return a string representing the result of command
     */
    public String execute() {
        try {
            if (line.isBlank() || line.isEmpty()) {
                return "";
            } else if (Parser.isExit(line)) { // exit command
                return Ui.exit();
            } else if (Parser.isList(line)) { // list command
                return Ui.showMessage(TaskList.printList());
            } else if (Parser.isListArchived(line)) { // list archived command
                return Ui.showMessage(TaskList.printArchived());
            } else if (Parser.isDone(line)) { // done command
                int index = Integer.parseInt(line.substring(5));
                return Ui.showDone(TaskList.setDone(index));
            } else if (Parser.isFind(line)) { // find command
                String word = line.substring(5);
                return Ui.showFind(TaskList.findTask(word));
            } else if (Parser.isArchive(line)) { // archive command
                int index = Integer.parseInt(line.substring(8));
                return Ui.showArchive(TaskList.archive(index));
            } else if (Parser.isDelete(line)) { // delete command
                int index = Integer.parseInt(line.substring(7));
                return Ui.showDelete(TaskList.delete(index));
            } else { // add command
                TaskType type = Parser.taskType(line);
                String task;
                if (type == TaskType.TODO) {
                    task = TaskList.add(type, Parser.getName(line));
                } else {
                    task = TaskList.add(type, Parser.getName(line), Parser.getTime(line));
                }
                return Ui.showAdd(task);
            }
        } catch (IOException e) {
            return Ui.showFileError();
        } catch (NullListException e) {
            return Ui.showListError();
        } catch (Exception e) {
            return Ui.showCommandError();
        }
    }

    @Override
    public String toString() {
        return this.line;
    }
}
