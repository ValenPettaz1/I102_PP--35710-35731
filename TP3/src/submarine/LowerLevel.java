package submarine;

public class LowerLevel extends DepthManager {

    @Override
    public void ascendMe(Nemo nemo) {
        nemo.removeDepthLevel();
    }

    @Override
    public void descendMe(Nemo nemo) {
        nemo.addDepthLevel(new LowerLevel());
    }

    @Override
    public void capsuleHasBeenReleased() {
        throw new RuntimeException(Nemo.NemoHasBeenDestoyed);
    }
}