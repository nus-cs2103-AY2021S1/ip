package main.java.duke;

public class Duke {

    /** Storage object of the Duke object */
    private Storage storage;

    /** TaskList object of the Duke object */
    private TaskList tasks;

    /** Ui object of the Duke object */
    private Ui ui;

    /** Directory of file to store tasks */
    protected static String MEMORY_FILE_DIR = "./data/";

    /** Name of the file to store tasks */
    protected static String MEMORY_FILE_NAME = "task_list.txt";


    /**
     * Constructor of Duke.
     * Initialize storage, tasks, ui of Duke object.
     *
     * @param memoryFileDir  Directory of the file to store tasks.
     * @param memoryFileName  Name of the file to store tasks.
     */
    public Duke(String memoryFileDir, String memoryFileName) {
        this.storage = new Storage(memoryFileDir, memoryFileName);
        try {
            this.tasks = new TaskList(storage.readMemoTasks(), memoryFileDir, memoryFileName);
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.READ_FILE);
            tasks = new TaskList();
            System.out.println(SpecialFormat.INDENT + "Let's start first without the task records!");
        }
        this.ui = new Ui(memoryFileDir, memoryFileName, tasks.showList());
    }


    /**
     * Run process by calling processRequests() method of ui object.
     *
     */
    public void run() {
        ui.processRequests();
    }


    /**
     * Create a new Duke object and call its run() method.
     *
     * @param args  Compulsory constitution of a main method.
     */
    public static void main(String[] args) {
        new Duke(MEMORY_FILE_DIR, MEMORY_FILE_NAME).run();
    }

}

//compile when current directory is at IndividualProject/text-ui-test
//javac -cp ../src/ ../src/main/java/Task.java   etc. (Task, Deadline, Event, Todo, Duke)
//sh runtest.sh