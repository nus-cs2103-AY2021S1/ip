package main.java.duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    protected static String MEMORY_FILE_DIR = "./data/";
    protected static String MEMORY_FILE_NAME = "task_list.txt";

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

    public void run() {
        ui.processRequests();
    }

    public static void main(String[] args) {
        new Duke(MEMORY_FILE_DIR, MEMORY_FILE_NAME).run();
    }

}

//compile when current directory is at IndividualProject/text-ui-test
//javac -cp ../src/ ../src/main/java/Task.java   etc. (Task, Deadline, Event, Todo, Duke)
//sh runtest.sh