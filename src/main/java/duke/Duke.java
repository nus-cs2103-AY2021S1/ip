package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageOperationException;
import duke.parser.CommandLineInterfaceParser;
import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.ResourceHandler;
import duke.utils.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskManager taskManager;

    public Duke (){
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            this.taskManager = storage.load();
        } catch (InvalidFilePathException e){
            ui.print(Colour.Red(e.getMessage()));
        } catch (StorageOperationException e){
            ui.print(Colour.Red(e.getMessage()));
        }
    }

    public void run(){
        ui.print(ResourceHandler.getMessage("commandline.welcomeMessage"));
        boolean isExit = false;
        while(!isExit){
            try{
                Command c = CommandLineInterfaceParser.parse();
                c.executeCommand(taskManager, ui, storage);
                isExit = c.isExit();
            } catch(DukeException e){
                ui.print(Colour.Red(e.getMessage()));
            } catch(IllegalArgumentException e){
                ui.print(Colour.Red(ResourceHandler.getMessage("commandline.invalidCommandInputMessage")));
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}



