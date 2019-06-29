package nextstep.ladder;

import java.util.*;
import java.util.stream.Collectors;

public class LadderApplication {

    public static void main(String[] args) {
        LadderApplication ladderApplication = new LadderApplication();
        ladderApplication.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
//        List<String> names = Arrays.asList(scanner.nextLine().split(","));
        List<String> names = Arrays.asList("pobi", "honux", "crong", "jk");
        System.out.println(String.join(",", names));

        System.out.println("최대 사다리 높이는 몇 개인가요?");
//        int height = Integer.parseInt(scanner.nextLine());
        int height = 5;
        System.out.println(height);

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

        System.out.println("실행결과");
        String formattedNames = names.stream()
                .map(name -> {
                    StringBuilder stringBuilder = new StringBuilder(name);
                    while (stringBuilder.length() < 5) {
                        stringBuilder.insert(0, " ");
                    }
                    return stringBuilder.toString();
                })
                .collect(Collectors.joining(" "));
        System.out.println(formattedNames);

        for (int i = 0; i < height; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("    ");
            for (int j = 0; j < ladder.size(); j++) {
                stringBuilder.append("|");
                if (ladder.get(j).get(i) == Rung.RIGHT) {
                    stringBuilder.append("-----");
                } else {
                    stringBuilder.append("     ");
                }
            }
            System.out.println(stringBuilder.toString());
        }
    }

    enum Rung {
        LEFT,
        RIGHT,
        NONE
    }
}
