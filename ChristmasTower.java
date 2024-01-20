import java.util.*;

public class ChristmasTower {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int height = sc.nextInt();

        int[] blocks = new int[5];
        for (int i = 0; i < 5; i++) {
            blocks[i] = sc.nextInt();
        }

        Arrays.sort(blocks);

        int[] res = new int[10001];
        Arrays.fill(res, -1);
        res[0] = 0;

        for (int b = 1; b <= height; ++b) {
            for (int c : blocks) {
                if (b - c >= 0 && res[b - c] != -1) {
                    res[b] = Math.max(res[b], res[b - c] + 1);
                }
            }
        }

        if (res[height] == -1) {
            System.out.print("Impossible");
            sc.close();
            return;
        }

        Map<Integer, Integer> map = new HashMap<>();
        while (height > 0) {
            for (int c : blocks) {
                if (height - c >= 0 && res[height] == res[height - c] + 1) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                    height -= c;
                    break;
                }
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((d, e) -> {
            if (Objects.equals(d.getValue(), e.getValue())) {
                return Integer.compare(d.getKey(), e.getKey());
            }
            return Integer.compare(e.getValue(), d.getValue());
        });

        for (int i = 0; i < list.size(); ++i) {
            System.out.print(list.get(i).getKey());
            if (i < list.size() - 1) {
                System.out.print(" ");
            }
        }
        sc.close();
    }
    
}

