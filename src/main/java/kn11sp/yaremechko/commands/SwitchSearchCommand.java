package kn11sp.yaremechko.commands;

import kn11sp.yaremechko.derivative.Derivative;

public class SwitchSearchCommand implements Command {
    private final Derivative derivative;

    public SwitchSearchCommand(Derivative derivative) {
        this.derivative = derivative;
    }

    @Override
    public void execute() {
        int levelOfRisk = 0;
        double price = 0;
        derivative.search( levelOfRisk,  price);
    }

}
