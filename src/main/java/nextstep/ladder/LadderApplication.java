package nextstep.ladder;

import nextstep.ladder.view.ConsoleInputView;
import nextstep.ladder.view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LadderApplication {
    private final InputView inputView;

    public LadderApplication(InputView inputView) {
        this.inputView = inputView;
    }

    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();

        LadderApplication ladderApplication = new LadderApplication(inputView);
        ladderApplication.run();
    }

    public void run() {
        List<String> names = inputView.inputNames();
        System.out.println(String.join(",", names));

        int height = inputView.inputHeight();
        System.out.println(height);

        // init
        List<List<Rung>> ladder = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            List<Rung> line = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                line.add(Rung.NONE);
            }
            ladder.add(line);
        }

        // draw line
        for (int i = 0; i < ladder.size() - 1; i++) {
            for (int j = 0; j < height; j++) {
                Random random = new Random();
                if (random.nextInt(2) == 0) {
                    continue;
                }
                List<Rung> line1 = ladder.get(i);
                if (line1.get(j) != Rung.NONE) {
                    continue;
                }
                List<Rung> line2 = ladder.get(i + 1);
                if (line2.get(j) != Rung.NONE) {
                    continue;
                }
                line1.remove(j);
                line1.add(j, Rung.RIGHT);
                line2.remove(j);
                line2.add(j, Rung.LEFT);
            }
        }

        System.out.println("실행결과");
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

        for (int i = 0; i < height; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("    ");
            for (int j = 0; j < ladder.size(); j++) {
                stringBuilder.append("|");
                if (ladder.get(j).get(i) == Rung.RIGHT) {
                    stringBuilder.append("-----");
                } else {
                    stringBuilder.append("     ");
                }
            }
            System.out.println(stringBuilder.toString());
        }
    }

    enum Rung {
        LEFT,
        RIGHT,
        NONE
    }
}
