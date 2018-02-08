package gawe.imb.karya.mainlibs;

import java.util.List;

/**
 * used as a base repository
 * Created by korneliussendy on 1/20/18.
 */

public interface Repository<T> {

    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    void remove(Specification specification);

    List<T> query(Specification specification);

    T get(Specification specification);
}
