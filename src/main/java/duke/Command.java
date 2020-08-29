package duke;

import task.TaskType;

import java.io.IOException;
import java.security.InvalidParameterException;

public class Command {
    private final String line;

    Command(String command) {
        this.line = command;
    }

    /**
     * Executes the command.
     *
     * @return a string representing the result of command
     */
    public String execute() {
        if (line.isBlank() || line.isEmpty()) {
            return "";
        } else if (Parser.isExit(line)) {
            return Ui.exit();
        } else if (Parser.isList(line)) {
            try {
                return TaskList.printList();
            } catch (IOException e) {
                return Ui.fileError();
            }
        } else if (Parser.isDone(line)) {
            try {
                int index = Integer.parseInt(line.substring(5));
                return TaskList.setDone(index);
            } catch (IOException e) {
                return Ui.fileError();
            } catch (Exception e) {
                return Ui.commandError();
            }
        } else if (Parser.isFind(line)) {
            try {
                String word = line.substring(5);
                return TaskList.findTask(word);
            } catch (IOException e) {
                return Ui.fileError();
            } catch (Exception e) {
                return Ui.commandError();
            }
        } else if (Parser.isDelete(line)) {
            try {
                int index = Integer.parseInt(line.substring(7));
                return TaskList.delete(index);
            } catch (IOException e) {
                return Ui.fileError();
            } catch (Exception e) {
                return Ui.commandError();
            }
        } else {
            try {
                TaskType type = Parser.taskType(line);
                if (type == TaskType.TODO) {
                    return TaskList.add(type, Parser.getName(line));
                } else {
                    return TaskList.add(type, Parser.getName(line), Parser.getTime(line));
                }
            } catch (NullPointerException
                    | ArrayIndexOutOfBoundsException
                    | InvalidParameterException e) {
                return Ui.commandError();
            } catch (IOException e) {
                return Ui.fileError();
            }
        }
    }

    @Override
    public String toString() {
        return this.line;
    }
}
