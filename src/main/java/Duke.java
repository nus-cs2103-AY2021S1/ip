import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private DukeFile dukeFile;
    private ArrayList<Task> arraylst;
    private Processor processor;

    public Duke(String filePath) {
        this.arraylst = new ArrayList<>();
        this.dukeFile = DukeFile.createDukeFile(filePath);
        this.processor = new Processor();
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Initializing\n" + logo);
        System.out.println("Yo what's up man, it's ya boi DUKE \n" + "What can I do for you today, Sir?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (!cmd.equals("bye")) {
                try {
                    String[] stringarr = cmd.split(" ");
                    if (stringarr[0].equals("list")) {
                        this.processor.processorList(arraylst);
                    } else if (stringarr[0].equals("done")) {
                        int index = Integer.parseInt(stringarr[1]);
                        String record = this.processor.processorDone(arraylst, index);
                        this.dukeFile.updateRecord(record, index);
                    } else if (stringarr[0].equals("delete")) {
                        int index = Integer.parseInt(stringarr[1]);
                        this.processor.processorDelete(arraylst, index);
                        this.dukeFile.deleteRecord(index);
                    } else {
                        String record = this.processor.processorAdd(cmd, arraylst);
                        this.dukeFile.saveRecord(record);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("_________________________________________\n" + "Bye. Hope to see you again soon!" + "\n" + "_________________________________________");
                break;
            }
        }
        try {
            dukeFile.saveToFile();
        } catch (IOException e) {
            System.out.println("Sorry an unexpected error occurred.");
            e.printStackTrace();
        }
    };


    public static void main(String[] args) {
        new Duke("Saved").run();
    }
}
