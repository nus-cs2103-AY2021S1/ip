import java.util.ArrayList;

public class Duke {
    private static Storage taskStorage;
    private static Storage notesStorage;
    private static TaskList tasks;
    private static NoteList notes;

    private boolean isWriting;
    private StringBuilder tempWriting = new StringBuilder();
    private Note tempNote;

    /**
     * Creates a new instance of Duke which stores information at the given path.
     *
     * @param filePathTasks the path where task information is stored.
     * @param filePathNotes the path where notes information is stored.
     */
    public Duke(String filePathTasks, String filePathNotes) {
        taskStorage = new Storage(filePathTasks);
        notesStorage = new Storage(filePathNotes);
        tasks = new TaskList(taskStorage.readTasks());
        notes = new NoteList(notesStorage.readNotes());
    }

    /**
     * Generates response to user input.
     * @param in the user input string.
     * @return response to user input.
     */
    String getResponse(String in) {
        String result = "";
        if (in.equals("bye")) {
            return Ui.print("bye bye!");
        }

        Command cmd = Parser.parse(in, isWriting);
        if (cmd == Command.BLANK) {
            result = Responses.BLANK;
        } else if (cmd == Command.WRITE) {
            tempWriting.append(in).append("\n");
            result = Responses.WRITE;
        } else if (cmd == Command.LOL) {
            result = Responses.LOL;
        } else if (cmd == Command.JUSTIN) {
            result = Responses.JUSTIN;
        } else if (cmd == Command.CLEAR) {
            taskStorage.clear();
            tasks = new TaskList(new ArrayList<Task>());
            result = Responses.CLEAR;
        } else if (cmd == Command.LIST) {
            assert tasks != null : "Tasks is null";
            result = Ui.print(tasks.toString());
        } else if (cmd == Command.NOTES) {
            result = Ui.print(notes.toString());
        } else if (cmd == Command.NEW) {
            if (in.length() <= Parser.NEW_LENGTH) {
                result = Responses.NEW_ERROR;
            } else {
                isWriting = true;
                String noteName = in.substring(Parser.NEW_LENGTH);
                tempNote = new Note(noteName);
                notes.add(tempNote);
                result = Responses.NEW_SUCCESS;
            }
        } else if (cmd == Command.COMPLETE) {
            isWriting = false;
            tempNote.updateContent(tempWriting.toString());
            tempWriting = new StringBuilder();
            result = Responses.COMPLETE;
        } else if (cmd == Command.VIEW) {
            int current;
            try {
                current = Integer.parseInt(in.substring(Parser.VIEW_LENGTH));
                current--;
                result = notes.viewNote(current);
            } catch (Exception e) {
                result = Responses.VIEW_NO_INDEX;
            }
        } else if (cmd == Command.FIND) {
            if (in.length() <= Parser.FIND_LENGTH) {
                result = Responses.FIND_NO_KEYWORD;
            } else {
                String keyword = in.substring(Parser.FIND_LENGTH);
                assert keyword.length() > 0 : "keyword does not exist";
                result = Responses.FIND_SUCCESS + Ui.print(tasks.findTasks(keyword).toString());
            }
        } else if (cmd == Command.DONE) {
            int current;
            try {
                current = Integer.parseInt(in.substring(Parser.DONE_LENGTH));
                current--;
                result = tasks.completeTask(current);
            } catch (Exception e) {
                result = Responses.DONE_NO_INDEX;
            }
        } else if (cmd == Command.DELETE) {
            int current;
            try {
                current = Integer.parseInt(in.substring(Parser.DELETE_LENGTH));
                current--;
                result = tasks.deleteTask(current);
            } catch (Exception e) {
                result = Responses.DELETE_NO_INDEX;
            }
        } else if (cmd == Command.ADD) {
            Task temp = Parser.getTask(in);
            if (temp != null) {
                tasks.add(temp);
                result = Ui.print("i've added this task for you: \n\t" + temp + "\nnow you have " + tasks.size()
                        + " items in your tasklist.");
            } else {
                assert Parser.getMsg() != null : "parser error message is null";
                result = Parser.getMsg();
            }
        } else {
            result = Responses.ERROR;
        }
        taskStorage.saveTasks(tasks.getTaskList());
        notesStorage.saveNotes(notes.getNotesList());
        return result;
    }
}
