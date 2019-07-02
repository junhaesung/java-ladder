package nextstep.ladder.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInputView implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String MESSAGE_FOR_NAMES = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String MESSAGE_FOR_HEIGHT = "최대 사다리 높이는 몇 개인가요?";

    @Override
    public List<String> inputNames() {
        System.out.println(MESSAGE_FOR_NAMES);
        final String inputString = SCANNER.nextLine();
        final String[] splitString = inputString.split(DELIMITER);
        return Stream.of(splitString)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public int inputHeight() {
        System.out.println(MESSAGE_FOR_HEIGHT);
        final String inputString = SCANNER.nextLine();
        return Integer.parseInt(inputString);
    }
}
