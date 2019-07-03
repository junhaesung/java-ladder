package nextstep.ladder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LadderApplication {

    public static void main(String[] args) {
        LadderApplication application = new LadderApplication();
        application.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        List<String> names = Arrays.asList(scanner.nextLine().split(","));

        System.out.println("최대 사다리 높이는 몇 개인가요?");
        int height = Integer.parseInt(scanner.nextLine());

        System.out.println("실행 결과\n");
        printResult(names, height);
    }

    public void printResult(List<String> names, int height) {
        String formattedNames = names.stream()
                .map(name -> {
                    StringBuilder stringBuilder = new StringBuilder(name);
                    while (stringBuilder.length() < 5) {
                        stringBuilder.insert(0, " ");
                    }
                    return stringBuilder.toString();
                })
                .collect(Collectors.joining(" "));

        System.out.println(formattedNames);

        Random random = new Random();
        List<List<VerticalLineType>> lines = names.stream()
                .map(name -> IntStream.range(0, height)
                        .map(number -> random.nextInt(2))
                        .mapToObj(number -> VerticalLineType.values()[number])
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        String result = lines.stream()
                .map(types -> types.stream()
                        .map(type -> type.line)
                        .collect(Collectors.joining("\n")))
                .reduce("", this::merge);

        System.out.println(result);
    }

    public String merge(String sourceLine, String targetLine) {
        if ("".equals(sourceLine)) {
            return targetLine;
        }
        if ("".equals(targetLine)) {
            return sourceLine;
        }
        List<String> sourceLineList = Arrays.asList(sourceLine.split("\n"));
        List<String> targetLineList = Arrays.asList(targetLine.split("\n"));
        int size = sourceLineList.size();
        return IntStream.range(0, size)
                .mapToObj(number -> sourceLineList.get(number) + targetLineList.get(number))
                .collect(Collectors.joining("\n"));
    }

    enum VerticalLineType {
        LEFT("-----|", true),
        RIGHT("     |", true),
        NONE("     |", false);

        private final String line;
        private final boolean hasLine;

        VerticalLineType(String line, boolean hasLine) {
            this.line = line;
            this.hasLine = hasLine;
        }
    }
}
