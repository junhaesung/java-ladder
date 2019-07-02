package nextstep.ladder;

import nextstep.ladder.view.InputView;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LadderApplicationTest {
    @Test
    void name() {
        InputView inputView = new InputView() {
            @Override
            public List<String> inputNames() {
                System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
                return Arrays.asList("pobi", "honux", "crong", "jk");
            }

            @Override
            public int inputHeight() {
                System.out.println("최대 사다리 높이는 몇 개인가요?");
                return 5;
            }
        };
        LadderApplication ladderApplication = new LadderApplication(inputView);
        ladderApplication.run();
    }
}
