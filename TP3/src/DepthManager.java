public abstract class DepthManager {
    public static String NemoHasBeenDestoyed = "El submarino fue destruido, probablemente por exceso de chocolate";

    public abstract void ascendMe(Nemo nemo);

    public abstract void descendMe(Nemo nemo);

    public abstract void capsuleHasBeenReleased();
}