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

/**
 * Duke chatbot class to encapsulate the behavior of the chatbot.
 * Task id starts from 1.
 */
public class Duke implements IDuke {
    /** List for storing Tasks */
    private final TaskList list;
    private final Storage storage;

    private Duke(TaskList list, Storage storage) {
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
    public String greet() {
        return Ui.greet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeTask(ITask task) {
        this.list.add(task);
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
    public String handleCommand(String command) {
        try {
            Command c = Parser.parse(command);
            return c.setDuke(this).execute();
        } catch (DukeIllegalArgumentException e) {
            String output = "Meow?!! " + e.getMessage();
            System.out.print(output);
            return output;
        } catch (DukeUnrecognizedArgumentException e) {
            String output = Message.CAT_DOUBT.toString();
            System.out.print(TextFormatter.getFormattedText(output));
            return output;
        } catch (Exception e) {
            String output = Message.CAT_CRY.toString() + e;
            System.out.print(TextFormatter.getFormattedText(output));
            return output;
        }
    }

    /**
     * Returns the storage utility object used in the chatbot.
     *
     * @return Storage utility object.
     */
    public Storage getStorage() {
        return storage;
    }

    @Override
    public String getResponse(String input) {
        return handleCommand(input);
    }

}
