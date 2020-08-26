import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private Storage s;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws Exception {

        s = new Storage(filePath);
        s.create();
        try {
            tasks = new TaskList(s.toArrayList());
            ui = new Ui(tasks, s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() throws Exception {
        ui.start();
    }

    public static void main(String[] args) throws Exception {
        new Duke("/Users/juanlie/cs2103t/pw2/ip/data").run();
    }
}
