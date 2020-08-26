package main.java;

import main.java.command.Command;
import main.java.exception.AnonymousException;
import main.java.exception.DescriptionException;
import main.java.exception.DukeCreateFileException;
import main.java.exception.DukeDateTimeParserException;
import main.java.exception.DukeFileException;
import main.java.exception.DukeFileNotFoundException;
import main.java.exception.NoIndexException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    TaskList tasks;
    Storage storage;
    Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.getExceptionTemplate(new DukeFileNotFoundException());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.getExceptionTemplate(new DukeCreateFileException());
            storage = new Storage();
            System.exit(-1);
        }
    }

    public void mainProgram() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            try {
                Command c = Parser.parseCommand(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FileNotFoundException e) {
                ui.getExceptionTemplate(new DukeFileNotFoundException());
            } catch (IOException e) {
                ui.getExceptionTemplate(new DukeFileException());
            } catch (AnonymousException e) {
                ui.getExceptionTemplate(new AnonymousException(command));
            } catch (DescriptionException e) {
                ui.getExceptionTemplate(new DescriptionException());
            } catch (DukeDateTimeParserException e) {
                ui.getExceptionTemplate(new DukeDateTimeParserException());
            } catch (NoIndexException e) {
                ui.getExceptionTemplate(new NoIndexException());
            }
        }
    }


    public static void main (String[]args){
        Duke duke = new Duke("data/data.txt");
        duke.mainProgram();
    }
}
