package nextstep.ladder.view.formatter;

import nextstep.ladder.domain.Line;
import nextstep.ladder.domain.Rung;

import java.util.stream.Collectors;

public class LineFormatter implements Formatter<Line> {

    private static final String DELIMITER = "\n";

    private final Formatter<Rung> rungFormatter;

    public LineFormatter(final Formatter<Rung> rungFormatter) {
        this.rungFormatter = rungFormatter;
    }

    @Override
    public String format(final Line line) {
        return line.stream()
                .map(rungFormatter::format)
                .collect(Collectors.joining(DELIMITER));
    }
}
