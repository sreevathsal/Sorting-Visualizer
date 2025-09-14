import java.util.*;

public class Main {

    // Metrics
    static int comparisons = 0;
    static int swaps = 0;

    // Print array as bars
    static void printArray(int[] arr) {
        for (int val : arr) {
            for (int i = 0; i < val; i++) {
                System.out.print("*");
            }
            System.out.println(" (" + val + ")");
        }
        System.out.println("---------------------");
    }

    // Bubble Sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    swaps++;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    printArray(arr);
                }
            }
        }
    }

    // Selection Sort
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swaps++;
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                printArray(arr);
            }
        }
    }

    // Insertion Sort
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                comparisons++;
                swaps++;
                arr[j + 1] = arr[j];
                j--;
                printArray(arr);
            }
            arr[j + 1] = key;
        }
    }

    // Merge Sort (with visualization on merging)
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    static void merge(int[] arr, int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    int[] L = new int[n1];
    int[] R = new int[n2];

    for (int i = 0; i < n1; i++) L[i] = arr[l + i];
    for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

    int i = 0, j = 0, k = l;

    while (i < n1 && j < n2) {
        comparisons++; // count comparison
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
            swaps++; // right element placed before left → swap
        }
        printArray(arr);
    }

    while (i < n1) {
        arr[k++] = L[i++];
        printArray(arr);
    }

    while (j < n2) {
        arr[k++] = R[j++];
        printArray(arr);
    }
}

    

    // Quick Sort (with visualization on partition)
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            printArray(arr);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                swaps++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        swaps++;
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Main Program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Sorting Visualizer (Console Edition) =====");
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(30) + 1;

        System.out.println("Initial Array:");
        printArray(arr);

        System.out.println("Choose Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        comparisons = 0;
        swaps = 0;
        long start = System.nanoTime();

        switch (choice) {
            case 1: bubbleSort(arr); break;
            case 2: selectionSort(arr); break;
            case 3: insertionSort(arr); break;
            case 4: mergeSort(arr, 0, n - 1); break;
            case 5: quickSort(arr, 0, n - 1); break;
            default: System.out.println("Invalid choice!"); return;
        }

        long end = System.nanoTime();

        System.out.println("===== Final Sorted Array =====");
        System.out.println(Arrays.toString(arr));
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Execution Time: " + (end - start) / 1_000_000.0 + " ms");
    }
}
