package submarine;

public class FirstLevel extends DepthManager {

    @Override
    public void ascendMe(Nemo nemo) {
        nemo.removeDepthLevel();
    }

    @Override
    public void descendMe(Nemo nemo) {
        nemo.addDepthLevel(new LowerLevel());
    }


}