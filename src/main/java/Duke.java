import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    public Duke() throws FileNotFoundException {
        ui = new UI();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage = new Storage(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void run() throws IOException {
        UI.intro();
        boolean isExit = false;

        while(!isExit) {
            String toPrint = ui.nextInput();
            ui.dividerLine(); //show the divider line ("_____")
            Command c = parser.parse(toPrint);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.dividerLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }


}

//    public static boolean isValidDate(String dateStr) {
//        try {
//            LocalDate.parse(dateStr);
//        } catch (DateTimeParseException e){
//            return false;
//        }
//        return true;
//    }
