package main.java;

import java.io.FileNotFoundException;

public class DukeFileNotFoundException extends DukeException {

    @Override
    public String toString() {
        return super.toString() +" I cannot find your file. Sorry! :(";
    }
}
