import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.time.LocalDate;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() throws IOException {
        this.ui.uiRun(this.tasks, this.storage);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("src/main/java/todo.txt").run();
    }
}
