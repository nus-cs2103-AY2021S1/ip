import java.time.LocalDate;

/**
 * Handles parsing of user inputs.
 */
public class Parser {

    /**
     * Stores the parsed user inputs and prints response messages for user.
     * @param userInput User input read by scanner.
     * @param lst List of tasks.
     * @param ui Ui object that deals with user interaction.
     * @param storage Storage object that deals with loading tasks from the file and saving tasks in the file.
     */
    public static void parse(String userInput, TaskList lst, Ui ui, Storage storage) {
        String[] strArr = userInput.split(" ");
        try {
            check(strArr);
            if (userInput.equals("bye")) {
                ui.bye();
                System.exit(0);
            } else if (userInput.equals("list")) {
                ui.printTaskList(lst);
            } else if (strArr[0].equals("done")) {
                lst.getTask(Integer.parseInt(strArr[1]) - 1).markAsDone();
                ui.printDoneTask(lst.getTask(Integer.parseInt(strArr[1]) - 1));
                storage.writeFile(lst.getTaskList());
            } else if (strArr[0].equals("delete")) {
                int i = Integer.parseInt(strArr[1]) - 1;
                Task t = lst.getTask(Integer.parseInt(strArr[1]) - 1);
                lst.deleteTask(i);
                ui.printDeleteTask(t, lst);
                storage.writeFile(lst.getTaskList());
            } else {
                if (strArr[0].equals("todo")) {
                    String sd = "";
                    for (int i = 1; i < strArr.length; i++) {
                        sd += strArr[i] + " ";
                    }
                    Task task = new Todo(sd);
                    lst.addTask(task);
                    ui.printAddTask(task, lst);
                    storage.writeFile(lst.getTaskList());
                } else if (strArr[0].equals("deadline")) {
                    String sd = "";
                    String sb = "";
                    boolean b = true;
                    for (int i = 1; i < strArr.length - 1; i++) {
                        if (strArr[i].equals("/by")) {
                            b = false;
                            sd = sd.substring(0, sd.length() - 1);
                        }
                        if (b) {
                            sd += strArr[i] + " ";
                        } else {
                            if (i == strArr.length - 2) {
                                sb = sb + strArr[i + 1];
                            } else {
                                sb = sb + strArr[i + 1] + " ";
                            }
                        }
                    }
                    Task task = new Deadline(sd, LocalDate.parse(sb));
                    lst.addTask(task);
                    ui.printAddTask(task, lst);
                    storage.writeFile(lst.getTaskList());
                } else if (strArr[0].equals("event")) {
                    String sd = "";
                    String sa = "";
                    boolean b = true;
                    for (int i = 1; i < strArr.length - 1; i++) {
                        if (strArr[i].equals("/at")) {
                            b = false;
                            sd = sd.substring(0, sd.length() - 1);
                        }
                        if (b) {
                            sd += strArr[i] + " ";
                        } else {
                            if (i == strArr.length - 2) {
                                sa = sa + strArr[i + 1];
                            } else {
                                sa = sa + strArr[i + 1] + " ";
                            }
                        }
                    }

                    Task task = new Event(sd, LocalDate.parse(sa));
                    lst.addTask(task);
                    
                    ui.printAddTask(task, lst);
                    storage.writeFile(lst.getTaskList());
                }
            }
        } catch (Exception e) {
            ui.showError(e.toString());
        }
    }

    /**
     * Checks whether has invalid user inputs.
     * @param arr Array of user input.
     * @throws DukeException Exception specific to Duke.
     */
    public static void check(String[] arr) throws DukeException {
        if (arr.length == 1 && (arr[0].equals("todo") || arr[0].equals("deadline") 
                || arr[0].equals("event") || arr[0].equals("done") || arr[0].equals("delete"))) {
            throw new DukeException(arr[0]);
        } else if (!arr[0].equals("todo") && !arr[0].equals("deadline") 
                && !arr[0].equals("event") && !arr[0].equals("list") 
                && !arr[0].equals("bye") && !arr[0].equals("done") && !arr[0].equals("delete")) {
            throw new DukeException("other");
        }
    }
}
