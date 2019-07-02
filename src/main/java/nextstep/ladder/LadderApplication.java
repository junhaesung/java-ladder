package nextstep.ladder;

import nextstep.ladder.domain.Ladder;
import nextstep.ladder.view.ConsoleInputView;
import nextstep.ladder.view.ConsoleResultView;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.ResultView;

import java.util.List;

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

        int width = names.size();

        Ladder ladder = Ladder.of(width, height);

        String formattedLadder = ladder.format(height);

        resultView.print(names, formattedLadder);
    }

}
