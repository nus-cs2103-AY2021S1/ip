package luoyi.duke.data;

import luoyi.duke.commands.Command;
import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.exception.DukeUnrecognizedArgumentException;
import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.parser.Parser;
import luoyi.duke.storage.Storage;
import luoyi.duke.ui.Ui;

import java.util.Scanner;

/**
 * Immutable Duke chatbot class to encapsulate the behavior of the chatbot.
 * Task id starts from 1.
 */
public class Duke implements IDuke {
    /** List for storing Tasks */
    private final TaskList list;
    private final Storage storage;

    public Duke(TaskList list, Storage storage) {
        this.list = new TaskList(list.getList());
        this.storage = storage;
    }

    /**
     * Returns a new Duke chatbot object.
     *
     * @return New Duke chatbot object.
     */
    public static Duke getDuke(String filePath) {
        Storage sm = Storage.getStorage(filePath);
        return new Duke(new TaskList(sm.read()), sm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void greet() {
        Ui.greet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bye() {
       Ui.bye();
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException
     */
    @Override
    public ITask getTask(int id) {
        if (id - 1 > list.size() || id < 0) {
            throw new IllegalArgumentException("Task id out of bound!");
        }
        return list.get(id - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskList getTasks() {
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumTask() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDuke handleCommand(String command) {
        try {
            Command c = Parser.parse(command);
            return c.setDuke(this).execute();
        } catch (DukeIllegalArgumentException e) {
            System.out.println(TextFormatter.getFormattedText(
                    "Meow?!! " + e.getMessage()));
        } catch (DukeUnrecognizedArgumentException e) {
            System.out.println(TextFormatter.getFormattedText(
                    Message.CAT_DOUBT.toString()));
        } catch (Exception e) {
            System.out.println(TextFormatter.getFormattedText(
                    Message.CAT_CRY.toString() + e));
        }

        return this;
    }

    public Storage getStorage() {
        return storage;
    }

}
