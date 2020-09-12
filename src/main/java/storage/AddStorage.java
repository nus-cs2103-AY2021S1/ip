package storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import command.Command;
import mugexception.MugException;
import validator.Validator;

/**
 * Adds task to the storage
 */
public class AddStorage extends Storage {
    /**
     * Constructs a AddStorage object with given path.
     *
     * @param filepath File's path
     */
    public AddStorage(String filepath) {
        super(filepath);
    }

    /**
     * Adds Task to local file.
     *
     * @param command User command.
     * @param info Task description.
     * @throws MugException When MugException cause by other method.
     */
    public void appendTask(Command command, String info) throws MugException {
        try {
            FileWriter fw = new FileWriter(super.filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String task;
            switch (command) {
            case TODO:
                task = addTodo(info);
                break;
            case DEADLINE:
                task = addDeadLine(info);
                break;
            case EVENT:
                task = addEvent(info);
                break;
            default:
                task = " | | ";
                break;
            }
            pw.println(task);
            pw.flush();
            pw.close();
            int lineNum = lineCounter(super.filepath);
            writeUndoRecord(Action.ADD, task, lineNum);
        } catch (MugException ex) {
            throw new MugException(ex.getMessage());
        } catch (IOException ex) {
            throw new MugException("Something went wrong. Mug fail to add the Tasks.Task :_:");
        }
    }

    private String addTodo(String info) {
        return "TODO|0|" + info;
    }

    private String addDeadLine(String info) throws MugException {
        String[] deadlineInfo = info.split(" /by ");
        // Validate info
        Validator.input(Command.DEADLINE, deadlineInfo.length, true);
        Validator.info(Command.DEADLINE, deadlineInfo[1], true);
        // info
        String deadlineEvent = deadlineInfo[0];
        LocalDate deadlineTime = Validator.date(deadlineInfo[1]);
        return "DEADLINE|0|" + deadlineEvent + "|" + deadlineTime;
    }

    private String addEvent(String info) throws MugException {
        String[] eventInfo = info.split(" /at ");
        // Validate info
        Validator.input(Command.EVENT, eventInfo.length, true);
        Validator.info(Command.EVENT, eventInfo[1], true);
        // info
        String eventEvent = eventInfo[0];
        LocalDate eventTime = Validator.date(eventInfo[1]);
        return "EVENT|0|" + eventEvent + "|" + eventTime;
    }
}
