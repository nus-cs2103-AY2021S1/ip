package duke.command;


import java.util.HashMap;
import java.util.Map;
import duke.ui.Ui;

public class MacroList {
    // TODO: handle infinite loops: disable macros internally?
    private Map<String, Macro> macros;

    public MacroList() {
        this.macros = new HashMap<>();
    }

    public boolean hasMacro(String name) {
        return this.macros.containsKey(name);
    }

    private boolean macroNameUnavailable(String name) {
        return this.hasMacro(name) || Command.hasCommand(name);
    }

    public void addMacro(String declaration) {
        Macro newMacro = Macro.newMacro(declaration);
        if (this.macroNameUnavailable(newMacro.getName())) {
            // TODO: throw some exception
        }
        this.macros.put(newMacro.getName(), newMacro);
    }

    public void addMacro(String declaration, Ui ui) {
        this.addMacro(declaration);
        ui.systemMessage("macro have been add ;)");
    }

    public Macro getMacro(String name) {
        return this.macros.get(name);
    }
}
