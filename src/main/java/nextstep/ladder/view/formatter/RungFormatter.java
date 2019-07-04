package nextstep.ladder.view.formatter;

import nextstep.ladder.domain.Rung;

public class RungFormatter implements Formatter<Rung> {

    private static final String HORIZONTAL_LINE = "|-----";
    private static final String VERTICAL_LINE = "|     ";

    @Override
    public String format(final Rung rung) {
        if (rung == Rung.RIGHT) {
            return HORIZONTAL_LINE;
        }
        return VERTICAL_LINE;
    }
}
