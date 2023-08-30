package garage0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Garage {

    public int capacity;
    public List<Car> cars = new ArrayList<>();
    public Set<Car> associatedCars = new HashSet<>();
    private int fees = 0;

    public Garage(int size) {
        this.capacity = size;
    }

    public boolean isEmpty() {
        return cars.isEmpty();
    }

    public int getNumCars() {
        return cars.size();
    }

    public Garage parkCar(Car car) {
        if (cars.size() == capacity) {
            throw new RuntimeException("No space available");
        }


        if (cars.contains(car)) {
            throw new RuntimeException("Twin Cars!");
        }


        fees += getFee(car);
        cars.add(car);
        return this;
    }

    public Garage unparkCar(Car car) {


        if (cars.contains(car)) {
            cars.remove(car);
            return this;
        }

        throw new RuntimeException("Missing car!");
    }

    public int getFee(Car car) {
        if (associatedCars.contains(car)) {
            return 5;
        } else {
            return 10;
        }
    }

    public int totalFees() {
        return fees;
    }

    public Garage associate(Car car) {
        associatedCars.add(car);
        return this;
    }
}