import java.time.LocalDate;

public class Parser {

    protected Storage storage;
    protected Ui ui;
    protected TaskList tasks;

    public Parser(Storage storage, Ui ui, TaskList list) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = list;
    }

    protected void processInput(String input) throws DukeException {
        if (input.equals("list")) {
            this.provideList();
        } else if (input.contains("done")) {
            this.markAsDone(input);
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            this.newTaskEntry(input);
        } else if (input.equals("bye")) {
            this.end();
        } else if (input.contains("delete")) {
            this.delete(input);
        } else {
            throw new InputNotRecognisedException();
        }
    }

    private void provideList() {
        ui.printLines();
        ui.provideListMsg();
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            String stringedIndex = Integer.toString(tasks.getIndex(task) + 1);
            String outputLine = stringedIndex + ". " + task;
            System.out.println(outputLine);
        }
        ui.printLines();
        storage.saveListToData(tasks.get());
    }

    private void markAsDone(String input) {
        String stringIndex = input.substring(5, input.length());
        int index = Integer.parseInt(stringIndex);
        Task chosen = this.tasks.get(index - 1);
        chosen.finish();
        ui.markAsDoneMsg(chosen);
        storage.saveListToData(this.tasks.get());
    }

    private void newTaskEntry(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.contains("todo")) {
            this.createAndAddTodo(input);
        } else if (input.contains("deadline")) {
            this.createAndAddDeadline(input);
        } else if (input.contains("event")) {
            this.createAndAddEvent(input);
        }
    }

    private void createAndAddTodo(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.length() < 5 || input.substring(5).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("todo");
        } else if (!Character.toString(input.charAt(4)).equals(" ")) {
            throw new WrongFormatException("todo");
        } else {
            Task task = new Todo(input.substring(5, input.length()));
            this.addTaskToTasklist(task);
        }
    }

    private void createAndAddDeadline(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.length() < 9 || input.substring(8).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else if (input.contains("/by")
                && Character.toString(input.charAt(8)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
            String desc = input.substring(9, input.indexOf("/") - 1);
            String by = input.substring(input.indexOf("/") + 4, input.length());
            if (by.matches(".*[a-zA-Z]+.*")) {
                throw new WrongFormatException("deadline");
            }
            LocalDate date = LocalDate.parse(by);
            Task task = new Deadline(desc, date);
            this.addTaskToTasklist(task);
        } else {
            throw new WrongFormatException("deadline");
        }
    }

    private void addTaskToTasklist(Task task) {
        int listCount = this.tasks.length();
        ui.addTaskToTasklistMsg(task, listCount);
        this.tasks.add(task);
        storage.saveListToData(this.tasks.get());
    }

    private void createAndAddEvent(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.length() < 6 || input.substring(5).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("event");
        } else if (input.contains("/at")
                && Character.toString(input.charAt(5)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
            String desc = input.substring(6, input.indexOf("/") - 1);
            String at = input.substring(input.indexOf("/") + 4, input.length());
            Task task = new Event(desc, at);
            this.addTaskToTasklist(task);
        } else {
            throw new WrongFormatException("event");
        }
    }

    private void end() {
        storage.saveListToData(this.tasks.get());
        ui.farewell();
        System.exit(0);
    }

    private void delete(String input) throws EmptyListException, InvalidListIndexException {
        String stringIndex = input.substring(7, input.length());
        int index = Integer.parseInt(stringIndex);
        if (this.tasks.isEmpty()) {
            throw new EmptyListException();
        } else if (index > 0 && index <= this.tasks.length()) {
            Task chosen = this.tasks.get(index - 1);
            this.tasks.remove(chosen);
            int listLength = this.tasks.length();
            ui.deleteMsg(listLength, chosen);
            storage.saveListToData(this.tasks.get());
        } else {
            throw new InvalidListIndexException();
        }
    }




}
