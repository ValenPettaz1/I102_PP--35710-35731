package queue;

public class FilledBox extends Box {

    private Object cargo;

    public FilledBox(Object cargo) {
        this.cargo = cargo;
    }

    @Override
    public Object openBox() {
        return cargo;
    }
}


