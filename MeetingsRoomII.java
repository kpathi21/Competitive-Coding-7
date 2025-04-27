import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingsRoomII {
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll(); //re-use
            }
            minHeap.add(interval[1]);
        }

        return minHeap.size();
    }
}

//TC: O(nlogn), SC: O(k) - heap size
