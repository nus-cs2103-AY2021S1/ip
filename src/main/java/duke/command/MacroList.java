package duke.command;

import java.util.HashMap;
import java.util.Map;

import duke.exception.DukeException;
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

    public void addMacro(String declaration) throws DukeException {
        Macro newMacro = Macro.newMacro(declaration);
        if (this.macroNameUnavailable(newMacro.getName())) {
            throw DukeException.Errors.MACRO_ALREADY_DEFINED.create();
        }
        this.macros.put(newMacro.getName(), newMacro);
    }

    public void addMacro(String declaration, Ui ui) throws DukeException {
        this.addMacro(declaration);
        ui.systemMessage("macro have been add ;)");
    }

    public Macro getMacro(String name) {
        return this.macros.get(name);
    }
}
