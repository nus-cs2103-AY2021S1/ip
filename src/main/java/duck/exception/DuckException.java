package duck.exception;

import duck.ui.Colour;

public class DuckException extends Exception {

    public DuckException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return Colour.Red(super.toString());
    }
}
