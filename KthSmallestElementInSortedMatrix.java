import java.util.PriorityQueue;

public class KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        int low = matrix[0][0], high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;

            int count = getCount(matrix, mid, n);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private int getCount(int[][] matrix, int mid, int n) {
        int count = 0;
        int row = n - 1, col = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] <= mid) {
                count = count + row + 1;
                col++;
            } else {
                row--;
            }
        }
        return count;
    }
}

//TC: O(nlog(max-min)), SC: O(1)

//Approach -2
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maxHeap.size() < k) {
                    maxHeap.add(matrix[i][j]);
                } else if (matrix[i][j] < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.add(matrix[i][j]);
                } else {
                    // Since the rest of the row will only be bigger, break inner loop
                    break;
                }
            }
        }
        return maxHeap.peek();
    }
}

//TC: O(Nlog(k)) -> here N is less than n2 elements, SC: O(k)