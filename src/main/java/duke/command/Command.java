package duke.command;


public enum Command {
    EXIT("^(b|B)(y|Y)(e|E)$"),
    LIST("^(l|L)(i|I)?(s|S)(t|T)?$"),
    DONE("^(d|D)(o|O)(n|N)(e|E)$"),
    TODO("^(t|T)(o|O)(d|D)(o|O)$"),
    DEADLINE("^(d|D)(e|E)(a|A)(d|D)(l|L)(i|I)(n|N)(e|E)$"),
    EVENT("^(e|E)(v|V)(e|E)(n|N)(t|T)$"),
    DELETE("^(d|D)(e|E)(l|L)(e|E)(t|T)(e|E)$"),
    FIND("^(f|F)(i|I)(n|N)(d|D)$"),
    HELP("^(h|H)(e|E)(l|L)(p|P)$");

    private String pattern;

    Command(String pattern) {
        this.pattern = pattern;
    }

    public boolean matchPattern(String input) {
        return input.matches(pattern);
    }

}
