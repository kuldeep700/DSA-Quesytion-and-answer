public class RecursiveLinearSearch {

    public static int linearSearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return linearSearchRecursive(arr, target, 0);
    }

    private static int linearSearchRecursive(int[] arr, int target, int index) {
        if (index >= arr.length) {
            return -1;
        }

        if (arr[index] == target) {
            return index;
        }

        return linearSearchRecursive(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] data = {10, 4, 7, 25, 13, 18};
        int targetValue = 7;
        
        int result = linearSearch(data, targetValue);

        if (result != -1) {
            System.out.println("Linear Search: Element " + targetValue + " found at index " + result);
        } else {
            System.out.println("Linear Search: Element " + targetValue + " not found.");
        }
    }
}