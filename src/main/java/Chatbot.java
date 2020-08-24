import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Chatbot {
    private enum CommandStatus {
        STOP, SUCCESS
    }

    private BufferedReader textParser;
    private List<Task> storedTasks;
    private Storage storage;

    public Chatbot(InputStream in, String filePath) {
        textParser = new BufferedReader(new InputStreamReader(in));
        storedTasks = new ArrayList<>();
        storage = Storage.init(filePath);
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

    private Command parseCommand(String[] args)
            throws IncorrectArgumentException, UnknownCommandException {
        if (args.length <= 0) {
            throw new IncorrectArgumentException("i hAtE YoU! "
                    + "sToP MaKiNg mE Do sOmEtHiNg iMpOsSiBlE.");
        }

        Command command = Command.getCommand(args[0]);

        if (command == null) {
            throw new UnknownCommandException("sToP TrYiNg tO FoOl mE. \""
                    + args[0] + "\" iS An uNkNoWn cOmMaNd.");
        }

        return command;
    }

    private CommandStatus runCommand(Command command, String[] args)
            throws IncorrectArgumentException {
        try {
            switch (command) {
            case EXIT:
                printWithDecorations("hOpE To sEe yOu aGaIn. NoT.");
                return CommandStatus.STOP;
            case LIST:
                list();
                break;
            case MARK_AS_DONE:
                done(args);
                break;
            case DELETE:
                delete(args);
                break;
            case ADD_TODO:
                Task task = createTodo(args);
                storeTask(task);
                break;
            case ADD_DEADLINE:
                task = createDeadline(args);
                storeTask(task);
                break;
            case ADD_EVENT:
                task = createEvent(args);
                storeTask(task);
                break;
            }
        } catch(IncorrectArgumentException e) {
            throw e;
        }

        return CommandStatus.SUCCESS;
    }

    private void storeTask(Task task) {
        storedTasks.add(task);

        printWithDecorations("sInCe yOu'rE So hElPlEsS, "
                + "i'lL ReMeMbEr \""+ task.toString() + "\" FoR YoU.\n"
                + "yOu hAvE MaDe mE ReMeMbEr " + storedTasks.size() +" tAsK(s).");

        save();
    }

    private void save() {
        List<String> taskSummary = new ArrayList<>();

        for (Task task : storedTasks) {
            taskSummary.add(task.summarize());
        }

        storage.save(taskSummary);
    }

    private void load() {
        List<String> taskSummary = storage.load();

        for (String summary : taskSummary) {
            String[] args = summary.split("\\s\\|\\s");

            boolean isDone = args[1].equals("1") ? true : false;

            switch (args[0]) {
            case "T":
                storedTasks.add(new Todo(args[2], isDone));
                break;
            case "D":
                storedTasks.add(new Deadline(args[2], isDone, args[3]));
                break;
            case "E":
                storedTasks.add(new Event(args[2], isDone, args[3]));
                break;
            default:
                storedTasks.add(new Task(args[2], isDone));
                break;
            }
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

        save();
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

        save();
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

        String description;
        LocalDate by;
        int index = findIndex(args, "/by");

        if (index != -1) {
            description = strArrJoin(args, 1, index, " ");

            try {
                by = LocalDate.parse(strArrJoin(args, index + 1, args.length, " "));
            } catch (DateTimeParseException e) {
                throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                        + " dAtE MuSt bE In yEaR-MoNtH-DaY FoRmAt.");
            }
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

        String description;
        LocalDate at;
        int index = findIndex(args, "/at");

        if (index != -1) {
            description = strArrJoin(args, 1, index, " ");
            try {
                at = LocalDate.parse(strArrJoin(args, index + 1, args.length, " "));
            } catch (DateTimeParseException e) {
                throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                        + " dAtE MuSt bE In yEaR-MoNtH-DaY FoRmAt.");
            }
        } else {
            throw new IncorrectArgumentException("sToP TrYiNg tO FoOl mE."
                    + " tHe tImE MuSt cOmE BeFoRe \"/at\".");
        }

        return new Event(description, at);
    }

    public void start() {
        CommandStatus status = CommandStatus.SUCCESS;

        load();

        printWithDecorations("yOu HavE nO cOnTrOL ovEr ME!");

        for (String text = parseText(); status == CommandStatus.SUCCESS; text = parseText()) {
            String[] args = text.trim().split("\\s+");

            try {
                Command command = parseCommand(args);
                status = runCommand(command, args);
            } catch (IncorrectArgumentException | UnknownCommandException e) {
                printWithDecorations(e.getMessage());
            }
        }
    }
}
