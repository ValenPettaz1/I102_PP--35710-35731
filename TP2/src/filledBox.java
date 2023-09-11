public class filledBox extends Box {

    private final Object cargo;

    public filledBox(Object cargo) {
        this.cargo = cargo;
    }

    @Override
    public Object openBox() {
        return cargo;
    }
}


