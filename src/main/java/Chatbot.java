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

    private String indentText(String textToIndent) {
        return String.format("\t %s", textToIndent);
    }

    private void printDecorations() {
        System.out.printf("\t|%s|\n", " @ . ".repeat(20));
        System.out.print("\n");
    }

    private void printWithDecorations(String textToPrint) {
        String[] sentences = textToPrint.split("\n");

        printDecorations();
        for (String sentence : sentences) {
            System.out.println(indentText(sentence));
        }
        System.out.print("\n");
        printDecorations();
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

    private int findIndex(String[] arr, String searchTerm) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(searchTerm)) {
                return i;
            }
        }

        return -1;
    }

    private String strArrJoin(String[] arr, int startIndex, int endIndex, String delimiter) {
        StringBuilder builder = new StringBuilder();

        for (int i = startIndex; i < endIndex; ++i) {
            builder.append(arr[i]);

            // Do not add delimiter in final iteration
            if (i != endIndex - 1) {
                builder.append(delimiter);
            }
        }

        return builder.toString();
    }

    private void storeTask(String[] args) {
        Task newTask = null;
        String description = "";

        if (args[0].equals("todo")) {
            description = strArrJoin(args, 1, args.length, " ");

            newTask = new Todo(description);
        } else if (args[0].equals("deadline")) {
            String by = "";
            int index = findIndex(args, "/by");

            if (index != -1) {
                description = strArrJoin(args, 1, index, " ");
                by = strArrJoin(args, index + 1, args.length, " ");
            }

            newTask = new Deadline(description, by);
        } else if (args[0].equals("event")) {
            String at = "";
            int index = findIndex(args, "/at");

            if (index != -1) {
                description = strArrJoin(args, 1, index, " ");
                at = strArrJoin(args, index + 1, args.length, " ");
            }

            newTask = new Event(description, at);
        }

        if (newTask != null) {
            storedTasks.add(newTask);

            printWithDecorations("sInCe yOu'rE So hElPlEsS, " +
                    "i'lL ReMeMbEr \""+ newTask.toString() + "\" FoR YoU.");
        }
    }

    private void list() {
        StringBuilder builder = new StringBuilder();
        int i = 0;

        builder.append("dO YoU ReAlLy nEeD Me tO NaMe tHeM OuT foR yOu?\n");
        for (Task task : storedTasks) {
            builder.append(String.format("%d. %s\n", ++i, task.toString()));
        }

        printWithDecorations(builder.toString());
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
                storeTask(args);
            }
        }
    }
}
