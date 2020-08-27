public class Cartona {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    private static String line = "    ____________________________________________________________\n";

    Cartona() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage("./saved/tasklist.txt");
    }

    /**
     * Helper function that parses the "add ..." argument into the console to add a Task to the taskList
     * @param consoleArg the exact string entered into the console
     */
    /**
    private static void parseAndAddTask(String consoleArg) throws EmptyTaskDescriptionException, InvalidTaskTimeException, UnknownCommandException {
        String[] parsedArr = consoleArg.substring(4).split(" ");
        String keyword = parsedArr[0];
        if (keyword.equals("todo")) {
            String name = "";

            for (int i = 1; i < parsedArr.length; i++) {
                if (i != parsedArr.length - 1) {
                    name += parsedArr[i] + " ";
                } else {
                    name += parsedArr[i];
                }
            }
            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            System.out.printf(taskList.addTask(new Todo(name)));

        } else if (keyword.equals("deadline")) {
            String name = "";
            String time = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/by")) {
                        if (name.equals("")) {
                            throw new EmptyTaskDescriptionException("");
                        }
                        nameOrTime = false;
                        continue;
                    } else {
                        name += parsedArr[i] + " ";
                    }
                } else {
                    if (i != parsedArr.length - 1) {
                        time += parsedArr[i] + " ";
                    } else {
                        time += parsedArr[i];
                    }
                }
            }
            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            if (time.equals("")) {
                throw new InvalidTaskTimeException("");
            }

            DateParser.parseDate(time);
            System.out.printf(taskList.addTask(new Deadline(name, time)));

        } else if (keyword.equals("event")) {
            String name = "";
            String timeRange = "";
            boolean nameOrTime = true;
            for (int i = 1; i < parsedArr.length; i++) {
                if (nameOrTime) {
                    if (parsedArr[i].contains("/at")) {
                        nameOrTime = false;
                        continue;
                    } else {
                        name += parsedArr[i] + " ";
                    }
                } else {
                    if (i != parsedArr.length - 1) {
                        timeRange += parsedArr[i] + " ";
                    } else {
                        timeRange += parsedArr[i];
                    }
                }
            }

            if (name.equals("")) {
                throw new EmptyTaskDescriptionException("");
            }
            if (timeRange.equals("")) {
                throw new InvalidTaskTimeException("");
            }

            System.out.printf(taskList.addTask(new Event(name, timeRange)));
        } else {
            throw new UnknownCommandException("");
        }
    }
    **/

    public static void main(String[] args) {
        Cartona cartona = new Cartona();
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        cartona.taskList = cartona.storage.getFileList();

        String nextInput = "";
        while (true) {
            String nextLine = ui.getNextLineInput();


            try {
                Command nextCommand = cartona.parser.parseCommand(nextLine);
                nextCommand.execute(cartona.taskList, cartona.ui, cartona.storage);

                if (nextCommand.isExitCmd())  {
                    break;
                }
            } catch (InvalidInputException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (CartonaException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
            /**
            if (nextInput.equals("bye")) {
                break;
            } else if (nextInput.equals("list")) {
                ui.printTaskList(taskList);

            } else if (nextInput.length() < 4) {
                System.out.printf("%s     Error: Invalid keyword! Please try again%n%s",
                        line, line);
            } else if (nextInput.substring(0, 4).equals("done")) {
                try {
                    String[] doneArr = nextInput.split(" ");
                    int taskNum = Integer.parseInt(doneArr[1]);
                    System.out.printf(taskList.completeTask(taskNum));
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("%s     Error: Invalid input! Did you mean: \"done TASK_NUM\"%n%s",
                                        line, line);
                }
            } else if (nextInput.substring(0, 3).equals("add")) {
                try {
                    parseAndAddTask(nextInput);
                } catch (EmptyTaskDescriptionException e1) {
                    System.out.printf("%s     Error: Empty task description!%n%s",
                            line, line);
                } catch (InvalidTaskTimeException e2) {
                    System.out.printf("%s     Error: Empty deadline/event time - please enter the time after /by " +
                                    "or /at keywords respectively%n%s",
                            line, line);
                } catch (UnknownCommandException e3) {
                    System.out.printf("%s     Error: Unknown keyword after \"add\"%n%s",
                            line, line);
                }
            } else if (nextInput.substring(0, 3).equals("del")) {
                try {
                    String[] doneArr = nextInput.split(" ");
                    int taskNum = Integer.parseInt(doneArr[1]);
                    System.out.printf(taskList.deleteTask(taskNum));
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("%s     Error: Invalid input! Did you mean: \"del TASK_NUM\"%n%s",
                            line, line);
                }
            } else {
                System.out.printf("%s     Error: Invalid keyword! Please try again%n%s",
                        line, line);
            }

        }

        Storage.saveListToFile(taskList);

        String goodbye = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.printf(goodbye);
             **/
    }
}