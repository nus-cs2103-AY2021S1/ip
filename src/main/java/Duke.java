package main.java;

import main.java.Command.Command;
import main.java.Exception.AnonymousException;
import main.java.Exception.DescriptionException;
import main.java.Exception.DukeCreateFileException;
import main.java.Exception.DukeDateTimeParserException;
import main.java.Exception.DukeFileException;
import main.java.Exception.DukeFileNotFoundException;
import main.java.Exception.NoIndexException;
import main.java.Parser.Parser;
import main.java.Storage.Storage;
import main.java.Task.Task;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
            ui.exceptionTemplate(new DukeFileNotFoundException());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.exceptionTemplate(new DukeCreateFileException());
            storage = new Storage();
            System.exit(-1);
        }
    }


    private List<Task> list = new ArrayList<>();

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static final String LINE = "     ___________________________________________________________________________\n";
    static final String DOUBLE_TAB = "      ";


    static final String PATH = "data/data.txt";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public void mainProgram() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            try {
                Command c = Parser.commandParser(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (FileNotFoundException e) {
                ui.exceptionTemplate(new DukeFileNotFoundException());
            } catch (IOException e) {
                ui.exceptionTemplate(new DukeFileException());
            } catch (AnonymousException e) {
                ui.exceptionTemplate(new AnonymousException(command));
            } catch (DescriptionException e) {
                ui.exceptionTemplate(new DescriptionException());
            } catch (DukeDateTimeParserException e) {
                ui.exceptionTemplate(new DukeDateTimeParserException());
            } catch (NoIndexException e) {
                ui.exceptionTemplate(new NoIndexException());
            }
        }
    }


    public static void main (String[]args){
        Duke duke = new Duke("data/data.txt");
        duke.mainProgram();
    }
}
