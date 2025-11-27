import java.util.Arrays;
import java.util.Comparator;

class Item {
    int weight;
    int value;
    double ratio;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.ratio = (double) value / weight;
    }
}

public class KnapsackGreedy {

    public static double knapsackGreedy(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                if (b.ratio > a.ratio) return 1;
                if (b.ratio < a.ratio) return -1;
                return 0;
            }
        });

        double totalValue = 0.0;
        int currentCapacity = capacity;

        for (Item item : items) {
            if (currentCapacity == 0) {
                break;
            }
            
            if (item.weight <= currentCapacity) {
                currentCapacity -= item.weight;
                totalValue += item.value;
            } else {
                double fraction = (double) currentCapacity / item.weight;
                totalValue += item.value * fraction;
                currentCapacity = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;
        
        double maxValue = knapsackGreedy(weights, values, W);
        System.out.println("Greedy Knapsack Max Value (Fractional): " + maxValue);
    }
}