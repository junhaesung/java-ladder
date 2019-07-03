package nextstep.ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ladder {
    private static final Random RANDOM = new Random();
    private static final int ZERO = 0;

    private final List<Line> lines;

    private Ladder(List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public static Ladder of(int width, int height) {
        final List<Line> lines = IntStream.range(0, width)
                .mapToObj(number -> Line.from(height))
                .collect(Collectors.toList());
        final Ladder createdLadder = new Ladder(lines);
        createdLadder.drawLine();
        return createdLadder;
    }

    private void drawLine() {
        for (int i = 0; i < lines.size() - 1; i++) {
            Line line1 = lines.get(i);
            Line line2 = lines.get(i + 1);
            Line.drawLine(line1, line2, () -> RANDOM.nextInt(2) == ZERO);
        }
    }

    public String format(int height) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int h = 0; h < height; h++) {
            stringBuilder.append("    ");
            for (int j = 0; j < lines.size(); j++) {
                stringBuilder.append("|");
                if (lines.get(j).hasRungOnRight(h)) {
                    stringBuilder.append("-----");
                } else {
                    stringBuilder.append("     ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
