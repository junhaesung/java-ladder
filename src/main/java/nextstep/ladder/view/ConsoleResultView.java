package nextstep.ladder.view;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleResultView implements ResultView {

    private static final int MAXIMUM_LENGTH_OF_NAME = 5;
    private static final String MARGIN_NAME = " ";
    private static final String DELIMITER_NAME = " ";

    @Override
    public void print(List<String> names, String ladder) {
        System.out.println("실행결과\n");
        final String formattedNames = this.formatNames(names);
        System.out.println(formattedNames);
        System.out.println(ladder);
    }

    private String formatNames(List<String> names) {
        return names.stream()
                .map(this::formatName)
                .collect(Collectors.joining(DELIMITER_NAME));
    }

    private String formatName(String name) {
        StringBuilder stringBuilder = new StringBuilder(name);
        while (stringBuilder.length() < MAXIMUM_LENGTH_OF_NAME) {
            stringBuilder.insert(0, MARGIN_NAME);
        }
        return stringBuilder.toString();
    }
}
