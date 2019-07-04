package nextstep.ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {
    @Test
    @DisplayName("객체가 잘 생성되는지")
    void test() {
        final int height = 5;
        final Line actual = Line.from(5);
        assertThat(actual).isInstanceOf(Line.class);
    }

    @Test
    @DisplayName("둘 다 좌우에 선을 그리지 않은 상태이면, 선을 새로 그릴 수 있음")
    void from1() {
        // given
        final Line leftLine = Line.from(1);
        final Line rightLine = Line.from(1);
        this.assertLineHas(leftLine, Rung.NONE);
        this.assertLineHas(rightLine, Rung.NONE);
        // when
        Line.drawLine(leftLine, rightLine, () -> true);
        // then
        this.assertLineHas(leftLine, Rung.RIGHT);
        this.assertLineHas(rightLine, Rung.LEFT);
    }

    @Test
    @DisplayName("한쪽이라도 선이 있으면, 선을 새로 그릴 수 없음")
    void from2() {
        // given
        final Line leftLine = Line.from(1);
        final Line middleLine = Line.from(1);
        final Line rightLine = Line.from(1);
        Line.drawLine(leftLine, middleLine, () -> true);
        this.assertLineHas(leftLine, Rung.RIGHT);
        this.assertLineHas(middleLine, Rung.LEFT);
        // when
        Line.drawLine(middleLine, rightLine, () -> true);
        // then
        this.assertLineHas(middleLine, Rung.LEFT);
        this.assertLineHas(rightLine, Rung.NONE);
    }

    @Test
    @DisplayName("높이를 잘 반환하는지")
    void height() {
        // given
        final Line line = Line.from(5);
        // when
        final int actual = line.height();
        // then
        assertThat(actual).isEqualTo(5);
    }

    private void assertLineHas(final Line line, final Rung rung) {
        final boolean actual = line.stream().allMatch(r -> r == rung);
        assertThat(actual).isTrue();
    }

}