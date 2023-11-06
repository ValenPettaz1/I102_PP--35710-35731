package linea;

import java.awt.*;


public class ModeB extends Mode {
    private char mode;
    public ModeB(){
        this.mode = 'B';
    }

    @Override
    public boolean applies(char mode) {
        return this.mode == mode;
    }

    @Override
    public boolean checkFinish(Linea game){


        return rightDiagonalCheck(game) || leftDiagonalCheck(game);
    }
}
