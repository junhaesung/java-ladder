package nextstep.ladder.domain;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Line {
    private final List<Rung> rungs;

    private Line(final List<Rung> rungs) {
        if (rungs == null) {
            throw new IllegalArgumentException("'rungs' must not be null");
        }
        this.rungs = rungs;
    }

    public static Line from(final int height) {
        final List<Rung> rungs = IntStream.range(0, height)
                .mapToObj(number -> Rung.NONE)
                .collect(Collectors.toList());
        return new Line(rungs);
    }

    private static boolean canDrawLine(final Line one, final Line theOther, final int height) {
        if (one.rungs.size() < height) {
            return false;
        }
        if (theOther.rungs.size() < height) {
            return false;
        }
        return one.rungs.get(height) == Rung.NONE && theOther.rungs.get(height) == Rung.NONE;
    }

    private static void drawLine(final Line leftLine, final Line rightLine, final int height) {
        if (!canDrawLine(leftLine, rightLine, height)) {
            return;
        }
        leftLine.rungs.remove(height);
        leftLine.rungs.add(height, Rung.RIGHT);
        rightLine.rungs.remove(height);
        rightLine.rungs.add(height, Rung.LEFT);
    }

    public static void drawLine(final Line leftLine, final Line rightLine, final Supplier<Boolean> strategy) {
        final int minimumHeight = Integer.min(leftLine.height(), rightLine.height());
        IntStream.range(0, minimumHeight)
                .forEach(height -> {
                    if (!strategy.get()) {
                        return;
                    }
                    Line.drawLine(leftLine, rightLine, height);
                });
    }

    public int height() {
        return rungs.size();
    }

    public Stream<Rung> stream() {
        return rungs.stream();
    }

    public boolean hasRungOnRight(final int height) {
        return rungs.get(height) == Rung.RIGHT;
    }
}
