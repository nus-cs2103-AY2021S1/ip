import java.time.LocalDate;

/**
 * Handles parsing of user inputs.
 */
public class Parser {

    /**
     * Stores the parsed user inputs and prints response messages for user.
     * @param userInput User input read by scanner.
     * @param duke Duke object.
     */
    public String parse(String userInput, Duke duke) {
        String[] strArr = userInput.split(" ");
        try {
            check(strArr);
            if (userInput.equals("bye")) {
                return duke.getUi().bye();
                //System.exit(0);
            } else if (userInput.equals("list")) {
                return duke.getUi().printTaskList(duke.getTasks());
            } else if (strArr[0].equals("done")) {
                duke.getTasks().getTask(Integer.parseInt(strArr[1]) - 1).markAsDone();
                duke.getStorage().writeFile(duke.getTasks().getTaskList());
                return duke.getUi().printDoneTask(duke.getTasks().getTask(Integer.parseInt(strArr[1]) - 1));
            } else if (strArr[0].equals("delete")) {
                int i = Integer.parseInt(strArr[1]) - 1;
                Task t = duke.getTasks().getTask(Integer.parseInt(strArr[1]) - 1);
                duke.getTasks().deleteTask(i);
                duke.getStorage().writeFile(duke.getTasks().getTaskList());
                return duke.getUi().printDeleteTask(t, duke.getTasks());
            } else {
                if (strArr[0].equals("todo")) {
                    String sd = "";
                    for (int i = 1; i < strArr.length; i++) {
                        sd += strArr[i] + " ";
                    }
                    Task task = new Todo(sd);
                    duke.getTasks().addTask(task);
                    duke.getStorage().writeFile(duke.getTasks().getTaskList());
                    return duke.getUi().printAddTask(task, duke.getTasks());
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
                    duke.getTasks().addTask(task);
                    duke.getStorage().writeFile(duke.getTasks().getTaskList());
                    return duke.getUi().printAddTask(task, duke.getTasks());
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
                    duke.getTasks().addTask(task);
                    duke.getStorage().writeFile(duke.getTasks().getTaskList());
                    return duke.getUi().printAddTask(task, duke.getTasks());
                } else if (strArr[0].equals("find")) {
                    String keyword = strArr[1];
                    TaskList taskLst = new TaskList();
                    for (int i = 0; i < duke.getTasks().getSize(); i++) {
                        if (duke.getTasks().getTask(i).getDescription().contains(keyword)) {
                            taskLst.addTask(duke.getTasks().getTask(i));
                        }
                    }
                    return duke.getUi().printMatchingTasks(taskLst);
                }
            }
        } catch (Exception e) {
            return duke.getUi().showError(e.toString());
        }
        return "";
    }

    /**
     * Checks whether has invalid user inputs.
     * @param arr Array of user input.
     * @throws DukeException Exception specific to Duke.
     */
    public void check(String[] arr) throws DukeException {
        if (arr.length == 1 && (arr[0].equals("todo") || arr[0].equals("deadline") || arr[0].equals("event") ||
                arr[0].equals("done") || arr[0].equals("delete") || arr[0].equals("find"))) {
            throw new DukeException(arr[0]);
        } else if (!arr[0].equals("todo") && !arr[0].equals("deadline") && !arr[0].equals("event") &&
                !arr[0].equals("list") && !arr[0].equals("bye") && !arr[0].equals("done") 
                && !arr[0].equals("delete") && !arr[0].equals("find")) {
            throw new DukeException("other");
        }
    }
}
