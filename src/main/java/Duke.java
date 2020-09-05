import command.Command;

import exceptions.DukeException;

import parserstorageui.Storage;
import parserstorageui.Ui;

import task.TaskList;

public class Duke{

    /**
     * The storage assigned to Duke
     **/
    private Storage storage;

    /**
     * The TaskList assigned to Duke
     **/
    private TaskList tasks;

    /**
     * The Ui interaction handler
     **/
    private Ui ui;

    public Duke(){
        ui = new Ui();
        storage = new Storage("data");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(Command c) throws DukeException {
        try {
            String out = c.execute(tasks, ui, storage);
            System.out.println(out);
            return out;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}