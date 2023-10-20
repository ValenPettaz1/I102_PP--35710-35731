package submarine;

public class Surface extends DepthManager {

    @Override
    public void ascendMe(Nemo nemo) {}

    @Override
    public void descendMe(Nemo nemo) {nemo.addDepthLevel(new FirstLevel());}

    @Override
    public void capsuleHasBeenReleased(){}
}