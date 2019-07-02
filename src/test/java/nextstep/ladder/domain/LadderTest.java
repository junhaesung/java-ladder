package nextstep.ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {
    @Test
    @DisplayName("Ladder 객체가 잘 생성되는지")
    void test() {
        // given
        final int width = 4;
        final int height = 5;
        // when
        final Ladder ladder = Ladder.of(width, height);
        // then
        assertThat(ladder).isInstanceOf(Ladder.class);
    }
}