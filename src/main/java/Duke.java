import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {


        runDuke();

    }

    public static void runDuke() {

        UI.introduction();
        Parser.parseCode(Storage.load(Storage.FILE_PATH), new UI(), false);
    }

}


