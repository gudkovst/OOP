package nsu.fit.oop.java;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            Parser pars = new Parser(args[0]);
            pars.parse();
            pars.printing();
        }
        if (args.length == 2) {
            Parser pars = new Parser(args[0], args[1]);
            pars.parse();
            pars.printing();
        }
    }
}
