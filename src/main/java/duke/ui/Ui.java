package duke.ui;

import duke.parser.Parser;

/**
 * User Interface
 */
public class Ui {
    private Parser parser;

    /**
     * Construct an User interface.
     */
    public Ui(){
        parser=new Parser();
    }

    /**
     * Run the programme.
     */
    public void run(){
        parser.processInput();
    }
}
