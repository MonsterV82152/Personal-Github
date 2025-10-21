public class Quicksort {
    
    static int[] swap(int[] arr, int a, int b) {
        // swap arr[i+1] and arr[high]
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return arr;
    }
    
    //returns int of pivot's final position
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // set the last element as pivot
        int i = (low - 1); // index of where to place next smaller element than pivot
        for (int j = low; j < high; j++) {
            //check if element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;

                //place the element in the correct position
                arr = swap(arr, i, j);
            }
        }

        //place the pivot in the correct position
        arr = swap(arr, i+1, high);

        //return the pivot's position in the partitioned list
        return i + 1;
    }

    // Recursive quicksort function
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int splitIndex = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, splitIndex - 1);
            quickSort(arr, splitIndex + 1, high);
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = { 10, 7, 8, 9, 1, 5, 3,6,1,4,45,234,7,21,34,7,234,1,65,45,2,23,41 };
        int n = arr.length;
        
        //sort the array
        quickSort(arr, 0, n - 1);
        System.out.println("Sorted array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}