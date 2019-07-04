package nextstep.ladder.view;

import nextstep.ladder.domain.Ladder;
import nextstep.ladder.view.formatter.Formatter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleResultView implements ResultView {

    private static final int MAXIMUM_LENGTH_OF_NAME = 5;
    private static final String MESSAGE_FOR_RESULT = "실행결과\n";
    private static final String MARGIN_NAME = " ";
    private static final String DELIMITER_NAME = " ";
    private static final String MARGIN_LADDER = "    ";
    private static final String DELIMITER_LADDER = "\n";

    private final Formatter<Ladder> ladderFormatter;

    public ConsoleResultView(final Formatter<Ladder> ladderFormatter) {
        this.ladderFormatter = ladderFormatter;
    }

    @Override
    public void print(final List<String> names, final Ladder ladder) {
        System.out.println(MESSAGE_FOR_RESULT);
        final String formattedNames = this.formatNames(names);
        System.out.println(formattedNames);
        final String formattedLadder = ladderFormatter.format(ladder);
        final String result = this.addMargin(formattedLadder);
        System.out.println(result);
    }

    private String formatNames(final List<String> names) {
        return names.stream()
                .map(this::formatName)
                .collect(Collectors.joining(DELIMITER_NAME));
    }

    private String formatName(final String name) {
        final StringBuilder stringBuilder = new StringBuilder(name);
        while (stringBuilder.length() < MAXIMUM_LENGTH_OF_NAME) {
            stringBuilder.insert(0, MARGIN_NAME);
        }
        return stringBuilder.toString();
    }

    private String addMargin(final String formattedLadder) {
        return Arrays.stream(formattedLadder.split(DELIMITER_LADDER))
                .map(str -> MARGIN_LADDER + str)
                .collect(Collectors.joining(DELIMITER_LADDER));
    }
}
