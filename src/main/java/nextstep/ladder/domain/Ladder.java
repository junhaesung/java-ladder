package nextstep.ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladder {
    private final List<List<Rung>> ladder;

    private Ladder(List<List<Rung>> ladder) {
        this.ladder = new ArrayList<>(ladder);
    }

    public static Ladder of(int width, int height) {
        final List<List<Rung>> ladder = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            List<Rung> line = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                line.add(Rung.NONE);
            }
            ladder.add(line);
        }
        final Ladder createdLadder = new Ladder(ladder);
        createdLadder.drawLine();
        return createdLadder;
    }

    private void drawLine() {
        for (int i = 0; i < ladder.size() - 1; i++) {
            for (int j = 0; j < ladder.get(i).size(); j++) {
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
    }

    public String format(int height) {
        final StringBuilder stringBuilder = new StringBuilder();
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
        return stringBuilder.toString();
    }
}
