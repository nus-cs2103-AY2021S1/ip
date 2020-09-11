package duke.ui;

import java.util.Objects;
import java.util.Scanner;

import duke.DukeCommandMatcher;
import duke.storage.Storage;
import duke.utils.Constants;
import duke.utils.UtilFunction;


public class Cli {

    private static Cli cli;
    private static Scanner dukeScanner;
    private DukeCommandMatcher dcm;
    private Storage storage;

    private Cli() {} //this class is not meant to be instantiated

    /**
     * Get the unique instance of Cli.
     * @return instance of Cli
     */
    public static synchronized Cli getInstance() {
        if (cli == null) {
            cli = new Cli();
            cli.setStorage(Constants.DEFAULTSTORAGE);
        }
        return cli;
    }

    /**
     * Storage setter.
     * @param storage
     * @return Cli object with storage set.
     */
    public Cli setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    private boolean checkShouldEndLoop(String response) {
        return Objects.equals(response, Constants.EXITRESPONSE);
    }

    private void startLoop() {
        Cli.dukeScanner = new Scanner(System.in);
    }

    private boolean hasInput() {
        assert dukeScanner != null : "duke scanner has not been initiated yet.";
        return dukeScanner.hasNextLine();
    }

    private String getInput() {
        return dukeScanner.nextLine();
    }

    private String execute() {
        //setStorage must be invoked before execute
        assert this.storage != null: "storage is not set yet";
        this.dcm = new DukeCommandMatcher(this.storage);

        assert !(this.dcm == null) : "duke command matcher is not created yet";
        String response;
        try {
            response = this.dcm.handleCommand(getInput());
        } catch (Exception err) {
            UtilFunction.printLimit(err.toString());
            response = "exception thrown";
        }
        return response;
    }


    /**
     * Launch the Duke loop.
     * @throws IllegalStateException
     * @see IllegalStateException
     */
    public void loop() throws IllegalStateException {
        Printer.printGreeting();
        startLoop();
        while (hasInput()) {
            Printer.printDivision();
            String response = execute();
            if (checkShouldEndLoop(response)) {
                break;
            }
            Printer.printDivision();
        }
        dukeScanner.close();
    }
}
