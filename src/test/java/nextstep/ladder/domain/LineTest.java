package nextstep.ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {
    @Test
    @DisplayName("객체가 잘 생성되는지")
    void test() {
        int height = 5;
        Line actual = Line.from(5);
        assertThat(actual).isInstanceOf(Line.class);
    }

    @Test
    @DisplayName("둘 다 좌우에 선을 그리지 않은 상태이면, 선을 새로 그릴 수 있음")
    void from1() {
        // given
        Line leftLine = Line.from(1);
        Line rightLine = Line.from(1);
        assertThat(leftLine.hasRungOnRight(0)).isFalse();
        // when
        Line.drawLine(leftLine, rightLine, () -> true);
        // then
        assertThat(leftLine.hasRungOnRight(0)).isTrue();
    }

    @Test
    @DisplayName("한쪽이라도 선이 있으면, 선을 새로 그릴 수 없음")
    void from2() {
        // given
        Line leftLine = Line.from(1);
        Line middleLine = Line.from(1);
        Line rightLine = Line.from(1);
        Line.drawLine(leftLine, middleLine, () -> true);
        assertThat(leftLine.hasRungOnRight(0)).isTrue();
        assertThat(middleLine.hasRungOnRight(0)).isFalse();
        // when
        Line.drawLine(middleLine, rightLine, () -> true);
        // then
        assertThat(middleLine.hasRungOnRight(0)).isFalse();
    }

    @Test
    @DisplayName("높이를 잘 반환하는지")
    void height() {
        // given
        Line line = Line.from(5);
        // when
        int actual = line.height();
        // then
        assertThat(actual).isEqualTo(5);
    }

}