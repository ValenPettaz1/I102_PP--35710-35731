public class FilledBox extends Box {

    private final Object cargo;

    public FilledBox(Object cargo) {
        this.cargo = cargo;
    }

    @Override
    public Object openBox() {
        return cargo;
    }
}


