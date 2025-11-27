public class RecursiveBinarySearch {

    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return binarySearchRecursive(arr, 0, arr.length - 1, target);
    }

    private static int binarySearchRecursive(int[] arr, int low, int high, int target) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (arr[mid] > target) {
            return binarySearchRecursive(arr, low, mid - 1, target);
        }

        return binarySearchRecursive(arr, mid + 1, high, target);
    }

    public static void main(String[] args) {
        int[] sortedData = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int targetValue = 23;
        
        int result = binarySearch(sortedData, targetValue);

        if (result != -1) {
            System.out.println("Binary Search: Element " + targetValue + " found at index " + result);
        } else {
            System.out.println("Binary Search: Element " + targetValue + " not found.");
        }
    }
}