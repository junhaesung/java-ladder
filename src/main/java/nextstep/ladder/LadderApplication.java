package nextstep.ladder;

import nextstep.ladder.view.ConsoleInputView;
import nextstep.ladder.view.ConsoleResultView;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LadderApplication {
    private final InputView inputView;
    private final ResultView resultView;

    public LadderApplication(InputView inputView,
                             ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        ResultView resultView = new ConsoleResultView();

        LadderApplication ladderApplication = new LadderApplication(inputView, resultView);
        ladderApplication.run();
    }

    public void run() {
        List<String> names = inputView.inputNames();

        int height = inputView.inputHeight();

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

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            stringBuilder.append("    ");
            for (int j = 0; j < ladder.size(); j++) {
                stringBuilder.append("|");
                if (ladder.get(j).get(i) == Rung.RIGHT) {
                    stringBuilder.append("-----");
                } else {
                    stringBuilder.append("     ");
                }
            }
            stringBuilder.append("\n");
        }
        String formattedLadder = stringBuilder.toString();

        resultView.print(names, formattedLadder);
    }

    enum Rung {
        LEFT,
        RIGHT,
        NONE
    }
}
