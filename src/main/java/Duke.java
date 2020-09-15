import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Storage taskStorage;
    private static Storage notesStorage;
    private static TaskList tasks;
    private static NoteList notes;

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
    public Duke() {}

    public boolean isWriting;
    private StringBuilder tempWriting = new StringBuilder();
    private Note tempNote;

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
            result = Ui.errorMsg("you haven't entered any command!");
        } else if (cmd == Command.CLEAR) {
            taskStorage.clear();
            //notesStorage.clear();
            tasks = new TaskList(new ArrayList<Task>());
            //notes = new NoteList(new ArrayList<Note>());
        } else if (cmd == Command.LIST) {
            assert tasks != null : "Tasks is null";
            result = Ui.print(tasks.toString());
        } else if (cmd == Command.WRITE) {
            tempWriting.append(in).append("\n");
            result = Ui.print("add another line or type 'complete' to save your note");
        } else if (cmd == Command.NOTES) {
            result = Ui.print(notes.toString());
        } else if (cmd == Command.NEW) {
            isWriting = true;
            String noteName = in.substring(Parser.NEW_LENGTH);
            if (noteName.length() == 0) {
                result = Ui.errorMsg("enter a note name! e.g. note mynote");
            } else {
                result = Ui.print("new note added! now type in the content of your note.");
                tempNote = new Note(noteName);
                notes.add(tempNote);
            }
        } else if (cmd == Command.COMPLETE) {
            isWriting = false;
            tempNote.updateContent(tempWriting.toString());
            tempWriting = new StringBuilder();
            result = Ui.print("your note has been saved!");
        } else if (cmd == Command.VIEW) {
            int current;
            try {
                assert in.length() >= Parser.VIEW_LENGTH : "input is too short";
                current = Integer.parseInt(in.substring(Parser.VIEW_LENGTH));
                current--;
                Note note;
                if (current < 0 || current >= notes.size()) {
                    result = Ui.errorMsg("that is not the number of a note in the list!");
                } else {
                    note = notes.get(current);
                    result = Ui.print(note.getContent());
                }
            } catch (Exception e){
                result = Ui.errorMsg("you haven't entered a note number to view!");
            }
        } else if (cmd == Command.FIND) {
            assert in.length() >= Parser.FIND_LENGTH : "input is too short";
            String keyword = in.substring(Parser.FIND_LENGTH);
            if (keyword.length() == 0) {
                result = Ui.errorMsg("you haven't entered a search keyword!");
            } else {
                result = Ui.print("here's the list of tasks that contain the keyword!") + Ui.print(tasks.findTasks(keyword).toString());
            }
        } else if (cmd == Command.DONE) {
            int current;
            try {
                assert in.length() >= Parser.DONE_LENGTH : "input is too short";
                current = Integer.parseInt(in.substring(Parser.DONE_LENGTH));
                current--;
                Task task;
                if (current < 0 || current >= tasks.size()) {
                    result = Ui.errorMsg("that is not the number of a task in the list!");
                } else {
                    task = tasks.get(current);
                    assert task != null : "task is null";
                    if (task.isDone) {
                        result = Ui.errorMsg("you have already completed " + task.task + "!");
                    } else {
                        task.complete();
                        result = Ui.print("congrats on finishing your task :) it's marked as done:\n\t" + task);
                    }

                }
            } catch (Exception e){
                result = Ui.errorMsg("you haven't entered a task number to complete!");
            }
        } else if (cmd == Command.DELETE){
            int current;
            try {
                assert in.length() >= Parser.DELETE_LENGTH : "input is too short";
                current = Integer.parseInt(in.substring(Parser.DELETE_LENGTH));
                current--;
                Task task;
                if (current < 0 || current >= tasks.size()) {
                    result = Ui.errorMsg("that is not the number of a task in the list!");
                } else {
                    task = tasks.get(current);
                    assert task != null : "task is null";
                    tasks.delete(current);
                    result = Ui.print("i've removed the following task from the list:\n\t" + task + "\nnow you have " + tasks.size() + " items in your tasklist.");
                }
            } catch (Exception e){
                result = Ui.errorMsg("you haven't entered a task number to delete!");
            }
        } else if (cmd == Command.ADD){
            Task temp = Parser.getTask(in);
            if (temp != null) {
                tasks.add(temp);
                result = Ui.print("i've added this task for you: \n\t" + temp + "\nnow you have " + tasks.size() + " items in your tasklist.");
            } else {
                assert Parser.msg != null : "parser error message is null";
                result = Parser.msg;
            }
        } else {
            result = Ui.errorMsg("i don't know what that means :(");
        }
        taskStorage.saveTasks(tasks.getTaskList());
        notesStorage.saveNotes(notes.getNotesList());
        return result;
    }
}
