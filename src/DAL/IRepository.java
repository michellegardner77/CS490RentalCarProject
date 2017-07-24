package DAL;


@Deprecated
public interface IRepository<T> {
    Iterable<T> GetAll();
    T GetByID(int id);
}
