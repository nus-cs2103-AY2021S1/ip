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
                return Ui.userMessage(TaskList.printList());
            } else if (Parser.isDone(line)) { // done command
                int index = Integer.parseInt(line.substring(5));
                return Ui.showDone(TaskList.setDone(index));
            } else if (Parser.isFind(line)) { // find command
                String word = line.substring(5);
                return Ui.showFind(TaskList.findTask(word));
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
            return Ui.fileError();
        } catch (NullListException e) {
            return Ui.listError();
        } catch (Exception e) {
            return Ui.commandError();
        }
    }

    @Override
    public String toString() {
        return this.line;
    }
}
