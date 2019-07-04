package nextstep.ladder;

import nextstep.ladder.domain.Ladder;
import nextstep.ladder.domain.Line;
import nextstep.ladder.domain.Rung;
import nextstep.ladder.view.ConsoleInputView;
import nextstep.ladder.view.ConsoleResultView;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.ResultView;
import nextstep.ladder.view.formatter.Formatter;
import nextstep.ladder.view.formatter.LadderFormatter;
import nextstep.ladder.view.formatter.LineFormatter;
import nextstep.ladder.view.formatter.RungFormatter;

import java.util.List;

public class LadderApplication {
    private final InputView inputView;
    private final ResultView resultView;

    public LadderApplication(final InputView inputView,
                             final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public static void main(final String[] args) {
        final Formatter<Rung> rungFormatter = new RungFormatter();
        final Formatter<Line> lineFormatter = new LineFormatter(rungFormatter);
        final Formatter<Ladder> ladderFormatter = new LadderFormatter(lineFormatter);

        final InputView inputView = new ConsoleInputView();
        final ResultView resultView = new ConsoleResultView(ladderFormatter);

        final LadderApplication ladderApplication = new LadderApplication(inputView, resultView);
        ladderApplication.run();
    }

    public void run() {
        final List<String> names = inputView.inputNames();
        final int height = inputView.inputHeight();
        final int width = names.size();

        final Ladder ladder = Ladder.of(width, height);

        resultView.print(names, ladder);
    }

}
