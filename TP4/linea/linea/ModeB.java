package linea;



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
    public boolean checkWinner(Linea game){
        return rightDiagonalCheck(game) || leftDiagonalCheck(game);
    }
}