public class CountingSort {

    private static int getMax(int[] arr) {
        if (arr.length == 0) return -1;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void countingSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        int max = getMax(arr);
        
        int[] count = new int[max + 1];
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {4, 2, 2, 8, 3, 3, 1};

        System.out.print("Original array: ");
        printArray(data);

        countingSort(data);

        System.out.print("Sorted array: ");
        printArray(data);
    }
}