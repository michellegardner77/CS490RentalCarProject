package DAL;

import model.Car;

@Deprecated
public class CarRepository implements IRepository<Car> {
    @Override
    public Iterable<Car> GetAll() {
        return null;
    }

    @Override
    public Car GetByID(int id) {
        return null;
    }
}
