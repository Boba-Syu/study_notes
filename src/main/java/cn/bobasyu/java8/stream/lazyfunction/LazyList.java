package cn.bobasyu.java8.stream.lazyfunction;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Boba
 */
public class LazyList<T> {
    private final T head;
    private final Supplier<LazyList<T>> tail;

    public LazyList(T head, Supplier<LazyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T getHead() {
        return head;
    }

    public LazyList<T> getTail() {
        return tail.get();
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public boolean isEmpty() {
        return false;
    }

    public LazyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(getHead()) ?
                        new LazyList<T>(getHead(), () -> getTail().filter(p)) :
                        getTail().filter(p);
    }


    public static LazyList<Integer> primes(LazyList<Integer> numbers) {
        return new LazyList<>(numbers.getHead(),
                () -> primes(numbers.getTail().filter(n -> n % numbers.getHead() != 0)));
    }

    public static <T> void printAll(LazyList<T> list, T t) {
        if (list.isEmpty()) {
            return;
        }
        System.out.println(list.getHead());
        if (!t.equals(list.getHead())) {
            printAll(list.getTail(), t);
        }
    }

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        printAll(primes(numbers), 51263);

    }
}
