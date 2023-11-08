package linea;


public class ModeA extends Mode {
    private char mode;
    public ModeA(){
        this.mode = 'A';
    }

    @Override
    public boolean applies(char mode) {
        return this.mode == mode;
    }

    @Override
    public boolean checkWinner(Linea game){
        return verticalCheck(game) || horizontalCheck(game);
    }
}