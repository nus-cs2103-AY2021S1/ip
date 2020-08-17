import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Chatbot {
    private BufferedReader textParser;
    private List<Task> storedTasks;

    public Chatbot(InputStream in) {
        textParser = new BufferedReader(new InputStreamReader(in));
        storedTasks = new ArrayList<>();
    }

    private String stylise(String textToStyle) {
        return String.format("\t %s", textToStyle);
    }

    private void printHeader() {
        System.out.print(String.format("\t|%s|\n", " @ . ".repeat(20)));
        System.out.print("\n");
    }

    private void printFooter() {
        System.out.print("\n");
        printHeader();
    }

    private void printWithDecorations(String textToPrint) {
        printHeader();
        System.out.println(stylise(textToPrint));
        printFooter();
    }

    private String parseText() {
        String text = "";

        try {
            text = textParser.readLine();
        } catch(IOException e) {
            System.err.println("ERROR: Unable to parse input stream!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return text;
    }

    private void storeTask(String taskDescription) {
        storedTasks.add(new Task(taskDescription));

        printWithDecorations("sInCe yOu'rE So hElPlEsS, " +
                "i'lL ReMeMbEr \""+ taskDescription + "\" FoR YoU.");
    }

    private void list() {
        int i = 0;

        printHeader();
        for (Task t : storedTasks) {
            System.out.println(stylise(String.format("%d. %s", ++i, t.toString())));
        }
        printFooter();
    }

    public void done(int index) {
        // Exception checking should be done here
        storedTasks.get(index).markAsDone();
        printWithDecorations("fInAlLy, I feLL AsLeEp wHiLe wAiTiNg fOr yOu tO FiNiSh: " +
                storedTasks.get(index).toString());
    }

    public void start() {
        printWithDecorations("yOu HavE nO cOnTrOL ovEr ME!");

        for (String text = parseText(); ; text = parseText()) {
            String[] args = text.trim().split("\\s+");

            if (args[0].equals("bye")) {
                printWithDecorations("hOpE To sEe yOu aGaIn. NoT.");
                break;
            } else if (args[0].equals("list")) {
                list();
            } else if (args[0].equals("done")) {
                done(Integer.parseInt(args[1]) - 1);
            } else {
                storeTask(text);
            }
        }
    }
}
