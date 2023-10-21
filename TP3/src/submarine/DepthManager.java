package submarine;

public abstract class DepthManager {

    public abstract void ascendMe(Nemo nemo);

    public abstract void descendMe(Nemo nemo);

    public void capsuleHasBeenReleased() {
    }
}