package parser;

public class Parser {

    public String parse(String echo) {
        String[] s = echo.split("\\s");
        String first = s[0];
        return first;
    }
}