import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private DukeUi dukeUi;
    private DukeFile dukeFile;
    private ArrayList<Task> arraylst;
    private Processor processor;

    public Duke(String filePath) {
        this.dukeUi = new DukeUi();
        this.arraylst = new ArrayList<>();
        this.dukeFile = DukeFile.createDukeFile(filePath);
        this.processor = new Processor();
    }

    public void run() {
        this.dukeUi.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine().trim().toLowerCase();
            if (!cmd.equals("bye")) {
                try {
                    this.processor.process(cmd, this.arraylst, this.dukeFile);
                } catch (DukeException e) {
                    this.dukeUi.showError(e.getMessage());
                }
            } else {
                this.dukeUi.goodbyeMessage();
                break;
            }
        }
        try {
            this.dukeFile.saveToFile();
        } catch (IOException e) {
            this.dukeUi.showError(e.getMessage());
        }
    }


    public static void main(String[] args) {
        new Duke("Saved").run();
    }
}
