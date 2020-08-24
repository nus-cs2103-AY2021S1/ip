package duke;

import task.TaskType;

import java.io.IOException;
import java.security.InvalidParameterException;

public class Command {
    private final String line;

    Command(String command) {
        this.line = command;
    }

    public boolean isExit() {
        return Parser.isExit(line);
    }

    public void execute() {
        if (Parser.isList(line)) {
            try {
                TaskList.printList();
            }
            catch (IOException e) {
                Ui.fileError();
            }
        }
        else if (Parser.isDone(line)) {
            try {
                int index = Integer.parseInt(line.substring(5));
                TaskList.setDone(index);
            }
            catch (IOException e) {
                Ui.fileError();
            }
            catch (Exception e) {
                Ui.commandError();
            }
        }
        else if (Parser.isDelete(line)) {
            try {
                int index = Integer.parseInt(line.substring(7));
                TaskList.delete(index);
            }
            catch (IOException e) {
                Ui.fileError();
            }
            catch (Exception e) {
                Ui.commandError();
            }
        }
        else {
            try {
                TaskType type = Parser.taskType(line);
                if (type == TaskType.TODO) {
                    TaskList.add(type, Parser.getName(line));
                }
                else {
                    TaskList.add(type, Parser.getName(line), Parser.getTime(line));
                }
            }
            catch (NullPointerException
                    | ArrayIndexOutOfBoundsException
                    | InvalidParameterException e) {
                Ui.commandError();
            }
            catch (IOException e) {
                Ui.fileError();
            }
        }
    }

    @Override
    public String toString() {
        return this.line;
    }
}
