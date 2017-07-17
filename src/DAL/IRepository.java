package DAL;

/**
 * Created by mgard on 7/15/2017.
 */
// generic DAL interface for each db table model
public interface IRepository<T> {
    Iterable<T> GetAll();
    T GetByID(int id);
}
