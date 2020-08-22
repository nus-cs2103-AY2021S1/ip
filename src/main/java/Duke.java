import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> storage;
    private TaskManager taskManager;
    private Ui ui;
    private Path filePath = Path.of("/data","data.txt");
    private SaveManager saveManager;

    public Duke() {
        this(Path.of("/data", "data.txt"));
    }

    public Duke(Path filePath) {
        //this.storage = new ArrayList<>();
        this.filePath = filePath;
        this.ui = new Ui(new InputHandler(), new OutputHandler());
        this.saveManager = new SaveManager(this.filePath);
        try {
            this.taskManager = saveManager.load();
        } catch (DukeSaveDataException e) {
            this.ui.displayException(e);
            this.taskManager = new TaskManager();
        }

    }

    public String saveDataGenerator() {
        final String[] saveData = {""};

        this.taskManager.forEach(task -> saveData[0] += this.saveManager.toSaveFormat(task.convertToHashMap()));
        return saveData[0];
    }

    public void bye() throws DukeSaveDataException{
        this.ui.displayGoodbye();
        this.saveManager.save(this.taskManager);
        /*
        try {
            BufferedWriter bw = Files.newBufferedWriter(this.filePath);
            bw.write(this.saveDataGenerator());
            bw.close();
        } catch (IOException e) {
            throw new DukeInputException("Unable to write to file <" + this.filePath + "> when exiting");
        } finally {
            System.out.println("Bye. Hope to see you again soon!");
        }

         */
    }

    public void store(Task t) {
        this.storage.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + t.toString());
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
    }

    public void addToDo(String input) throws DukeInputException{
        if (input.equals("")) {
            throw new DukeInputException("'todo' requires parameters.\n" +
                    "Use case: todo <name>");
        }
        ToDo newToDo = new ToDo(input);
        this.taskManager.storeTask(newToDo);
    }

    public void addDeadline(String input) throws DukeInputException{
        if (input.equals("")) {
            throw new DukeInputException("'deadline' requires parameters.\n" +
                    "Use case: deadline <name> /by <deadline>");
        }

        String[] params = input.split("/by ", 2);
        if (params.length != 2) {
            throw new DukeInputException("<" + input + "> is not valid for the 'deadline' command.\n" +
                    "Please add a /by deadline to the task.");
        }
        Deadline newDeadline = new Deadline(params[0], params[1]);
        this.taskManager.storeTask(newDeadline);
    }

    public void addEvent(String input) throws DukeInputException{
        if (input.equals("")) {
            throw new DukeInputException("'event' requires parameters.\n" +
                    "Use case: event <name> /at <timing>");
        }

        String[] params = input.split("/at ", 2);
        if (params.length != 2) {
            throw new DukeInputException("<" + input + "> is not valid for the 'event' command.\n" +
                    "Please add a /at timing to the task.");
        }
        Event newEvent = new Event(params[0], params[1]);
        this.taskManager.storeTask(newEvent);
    }

    public Task getTask(int i) {
        return this.storage.get(i-1);
    }

    public Task removeTask(int i) {
        return this.storage.remove(i-1);
    }

    //TODO: add exception for out of bounds index
    public void doTask(String params) throws DukeInputException {
        if (params.equals("")) {
            throw new DukeInputException("'done' requires parameters.\n" +
                    "Use case: done <task number>");
        }
        int i;
        try {
            i = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <" + params + "> after a 'done' command!");
        }

        Task temp = this.taskManager.getTask(i-1);
        temp.doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + temp.toString());
    }

    public void printList() {
        /*
        for (int i = 0; i < this.storage.size(); i++) {
            String printText = (i + 1) + ". " + this.storage.get(i).toString();
            System.out.println(printText);
        }

         */
        this.ui.display(this.taskManager.toString());
    }

    public void deleteTask(String params) throws DukeInputException{
        if (params.equals("")) {
            throw new DukeInputException("'delete' requires parameters.\n" +
                    "Use case: delete <task number>");
        }
        int i;
        try {
            i = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            throw new DukeInputException("Please input number instead of <" + params + "> after a 'delete' command!");
        }

        //TODO: Throw DukeInputException for wrong out of bounds index
        Task temp = this.taskManager.removeTask(i-1);
        System.out.println("Alright. I've removed this task:");
        System.out.println("\t" + temp.toString());
        System.out.println("Now you have " + this.taskManager.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        //initialize Duke with save data and send welcome message
        //TODO: Find where is this file???? Cannot find this file anywhere.
        Duke duke = new Duke(Path.of("data/data.txt"));
        duke.ui.displayGreet();

        //input loop
        Scanner sc = new Scanner(System.in);

        while(true) {
            String s = duke.ui.readCommand();
            String[] inputs = s.split(" ", 2);
            String command = inputs[0];
            String params = "";
            if (inputs.length == 2) {
                params = inputs[1];
            }

            //TODO: Capture the command switch as a method of Duke
            try {
                if (s.equals("bye")) {
                    break;
                } else if (s.equals("list")) {
                    duke.printList();
                } else if (command.equals("done")) {
                    duke.doTask(params);
                } else if (command.equals("todo")) {
                    duke.addToDo(params);
                } else if (command.equals("deadline")) {
                    duke.addDeadline(params);
                } else if (command.equals("event")) {
                    duke.addEvent(params);
                } else if (command.equals("delete")) {
                    duke.deleteTask(params);
                } else {
                    throw new DukeInputException("Invalid command <" + s + "> given.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        //send exit message
        try {
            duke.bye();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        /*testing code to check if printout to savefile worked
        try {
            Scanner sc2 = new Scanner(new File("/data/data.txt"));
            while (sc2.hasNext()) {
                System.out.println(sc2.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

         */

    }
}
