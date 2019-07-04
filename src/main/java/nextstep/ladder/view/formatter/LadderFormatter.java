package nextstep.ladder.view.formatter;

import nextstep.ladder.domain.Ladder;
import nextstep.ladder.domain.Line;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LadderFormatter implements Formatter<Ladder> {
    private static final String DELIMITER = "\n";
    private static final String EMPTY = "";

    private final Formatter<Line> lineFormatter;

    public LadderFormatter(final Formatter<Line> lineFormatter) {
        this.lineFormatter = lineFormatter;
    }

    @Override
    public String format(final Ladder ladder) {
        return ladder.stream()
                .map(lineFormatter::format)
                .reduce(EMPTY, this::merge);
    }

    private String merge(final String sourceLine, final String targetLine) {
        if (EMPTY.equals(sourceLine)) {
            return targetLine;
        }
        if (EMPTY.equals(targetLine)) {
            return sourceLine;
        }
        final List<String> sourceLineList = Arrays.asList(sourceLine.split(DELIMITER));
        final List<String> targetLineList = Arrays.asList(targetLine.split(DELIMITER));
        final int height = sourceLineList.size();
        return IntStream.range(0, height)
                .mapToObj(h -> sourceLineList.get(h) + targetLineList.get(h))
                .collect(Collectors.joining(DELIMITER));
    }
}
