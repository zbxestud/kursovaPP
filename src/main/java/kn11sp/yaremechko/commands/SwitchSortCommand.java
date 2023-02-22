package kn11sp.yaremechko.commands;

import kn11sp.yaremechko.derivative.Derivative;

public class SwitchSortCommand implements Command {
    private final Derivative derivative;

    public SwitchSortCommand(Derivative derivative) {
        this.derivative = derivative;
    }

    @Override
    public void execute() {
        derivative.sort();
    }
}
