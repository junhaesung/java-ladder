package nextstep.ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ladder {
    private static final Random RANDOM = new Random();
    private static final int ZERO = 0;
    private static final int BOUND = 2;

    private final List<Line> lines;

    private Ladder(final List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public static Ladder of(final int width, final int height) {
        final List<Line> lines = IntStream.range(0, width)
                .mapToObj(number -> Line.from(height))
                .collect(Collectors.toList());
        final Ladder createdLadder = new Ladder(lines);
        createdLadder.drawLine();
        return createdLadder;
    }

    private void drawLine() {
        for (int i = 0; i < lines.size() - 1; i++) {
            final Line line1 = lines.get(i);
            final Line line2 = lines.get(i + 1);
            Line.drawLine(line1, line2, () -> RANDOM.nextInt(BOUND) == ZERO);
        }
    }

    public Stream<Line> stream() {
        return lines.stream();
    }
}
