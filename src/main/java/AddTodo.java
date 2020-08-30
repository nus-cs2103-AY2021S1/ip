public class AddTodo extends AddCommand {
    public AddTodo(String[] words) {
        super(words);
    }

    @Override
    public void execute(TaskList ls, Ui ui) {
        ToDo newTD = new ToDo(words[1], false);
        ls.add(newTD);
        String thing = "Alright then, add more things to your ever-growing list of tasks:\n" +
                newTD.getStatus().replaceAll("(?m)^", "\t") +
                "\nNow you have " + ls.size() + " tasks in the list.";
        ui.printResult(thing);
    }
}
