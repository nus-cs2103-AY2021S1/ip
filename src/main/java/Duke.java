import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private Storage listStorage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String fileName) {
        this.listStorage = new Storage(fileName);
        this.taskList = new TaskList();
        this.ui = new Ui(this.listStorage, this.taskList);
    }

    public void run() {
        this.ui.printWelcome();
        this.listStorage.loadData(this.taskList);
        this.ui.loadingFile();
        
        boolean isExit = false;
        while(!isExit) {
            String command = this.ui.getInput();
            Command c = Parser.parse(command);
            if (c!= null) {
                c.execute(this.ui, this.listStorage, this.taskList);
                isExit = c.isExit();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("dataFile.txt").run();
    }
}
