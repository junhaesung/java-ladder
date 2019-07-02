package nextstep.ladder;

import nextstep.ladder.view.ConsoleResultView;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.ResultView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LadderApplicationTest {

    private InputView inputView = new InputView() {
        @Override
        public List<String> inputNames() {
            System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
            System.out.println("pobi, honux, crong, jk");
            return Arrays.asList("pobi", "honux", "crong", "jk");
        }

        @Override
        public int inputHeight() {
            System.out.println("최대 사다리 높이는 몇 개인가요?");
            System.out.println(5);
            return 5;
        }
    };
    private ResultView resultView = new ConsoleResultView();

    @Test
    void test() {
        LadderApplication ladderApplication = new LadderApplication(inputView, resultView);
        ladderApplication.run();
    }
}
