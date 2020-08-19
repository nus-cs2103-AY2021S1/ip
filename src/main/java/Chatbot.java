import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Chatbot {
    public static final int COMMAND_STOP = -1;
    public static final int COMMAND_SUCCESS = 1;

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

    private int runCommand(String[] args)
            throws IncorrectArgumentException, UnknownCommandException {
        if (args.length <= 0) {
            throw new IncorrectArgumentException("i hAtE YoU! "
                    + "sToP MaKiNg mE Do sOmEtHiNg iMpOsSiBlE.");
        }

        try {
            switch (args[0]) {
            case "bye":
                printWithDecorations("hOpE To sEe yOu aGaIn. NoT.");
                return COMMAND_STOP;
            case "list":
                list();
                break;
            case "done":
                done(args);
                break;
            case "delete":
                delete(args);
                break;
            case "todo":
                Task task = createTodo(args);
                storeTask(task);
                break;
            case "deadline":
                task = createDeadline(args);
                storeTask(task);
                break;
            case "event":
                task = createEvent(args);
                storeTask(task);
                break;
            default:
                throw new UnknownCommandException("sToP TrYiNg tO FoOl mE. \""
                        + args[0] + "\" iS An uNkNoWn cOmMaNd.");
            }
        } catch(IncorrectArgumentException e) {
            throw e;
        }

        return COMMAND_SUCCESS;
    }

    private void storeTask(Task task) {
        storedTasks.add(task);

        printWithDecorations("sInCe yOu'rE So hElPlEsS, "
                + "i'lL ReMeMbEr \""+ task.toString() + "\" FoR YoU.\n"
                + "yOu hAvE MaDe mE ReMeMbEr " + storedTasks.size() +" tAsK(s).");
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

    public void done(String[] args) throws IncorrectArgumentException {
        try {
            // The index provided by the user is off by one
            int index = Integer.parseInt(args[1]) - 1;

            try {
                storedTasks.get(index).markAsDone();
                printWithDecorations("fInAlLy, I feLL AsLeEp wHiLe wAiTiNg fOr yOu tO FiNiSh: "
                        + storedTasks.get(index).toString());
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE. taSK #"
                        + (index + 1) + " dOeS NoT ExIsT.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE. tHe \"done\" ComMand"
                    + " mUsT Be FolLoWed bY tHe InDEx Of THe TAsK.");
        }
    }

    public void delete(String[] args) throws IncorrectArgumentException {
        try {
            // The index provided by the user is off by one
            int index = Integer.parseInt(args[1]) - 1;

            try {
                Task removedTask = storedTasks.remove(index);
                printWithDecorations("gReAt! OnE FeWeR ThInG To rEmEmBeR: "
                        + removedTask.toString() + "\n"
                        + "i sTiLl nEeD To rEmEmBeR " + storedTasks.size() +" tAsK(s).");
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE. taSK #"
                        + (index + 1) + " dOeS NoT ExIsT.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE. tHe \"delete\" ComMand"
                    + " mUsT Be FolLoWed bY tHe InDEx Of THe TAsK.");
        }
    }

    public Todo createTodo(String[] args) throws IncorrectArgumentException {
        if (args.length <= 1) {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                    + " a tOdO MuSt bE FoLlOwEd bY A DesCrIpTiOn.");
        }

        String description = strArrJoin(args, 1, args.length, " ");

        return new Todo(description);
    }

    public Deadline createDeadline(String[] args) throws IncorrectArgumentException {
        if (args.length <= 2) {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                    + " a dEaDlInE MuSt bE FoLlOwEd bY A DeScRiPtIoN AnD TiMe.");
        }

        String description = "";
        String by = "";
        int index = findIndex(args, "/by");

        if (index != -1) {
            description = strArrJoin(args, 1, index, " ");
            by = strArrJoin(args, index + 1, args.length, " ");
        } else {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                    + " tHe tImE MuSt cOmE BeFoRe \"/by\".");
        }

        return new Deadline(description, by);
    }

    public Event createEvent(String[] args) throws IncorrectArgumentException {
        if (args.length <= 2) {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                    + " aN EvEnT MuSt bE FoLlOwEd bY A DeScRiPtIoN AnD TiMe.");
        }

        String description = "";
        String at = "";
        int index = findIndex(args, "/at");

        if (index != -1) {
            description = strArrJoin(args, 1, index, " ");
            at = strArrJoin(args, index + 1, args.length, " ");
        } else {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                    + " tHe tImE MuSt cOmE BeFoRe \"/at\".");
        }

        return new Event(description, at);
    }

    public void start() {
        int success = COMMAND_SUCCESS;

        printWithDecorations("yOu HavE nO cOnTrOL ovEr ME!");

        for (String text = parseText(); success != COMMAND_STOP; text = parseText()) {
            String[] args = text.trim().split("\\s+");

            try {
                success = runCommand(args);
            } catch (IncorrectArgumentException | UnknownCommandException e) {
                printWithDecorations(e.getMessage());
            }
        }
    }
}
