interface Command {

    void execute(String command, Storage s, Ui ui) throws DukeException;

}
