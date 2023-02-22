package kn11sp.yaremechko.commands;

import kn11sp.yaremechko.derivative.Derivative;

public class SwitchCountCommand implements Command {
    private final Derivative derivative;

    public SwitchCountCommand(Derivative derivative) {
        this.derivative = derivative;
    }

    @Override
    public void execute() {
        derivative.count();
    }
}
