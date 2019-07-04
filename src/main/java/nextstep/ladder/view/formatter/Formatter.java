package nextstep.ladder.view.formatter;

@FunctionalInterface
public interface Formatter<T> {
    String format(T rawData);
}
