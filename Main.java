import java.util.*;
/* COP 3503C Assignment 1
This program is written by: Devon Villalona */
public class Main {
    // Main method to read input and call the functions to find the pair of points that sum to T in a game  of two players with sorted and unsorted points array.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();  // Number of test cases
        sc.nextLine(); // Consume newline
        
        for (int t = 1; t <= k; t++) {
            String[] firstLine = sc.nextLine().split(" ");
            int sortedStatus = Integer.parseInt(firstLine[0]);
            int n = Integer.parseInt(firstLine[1]);
            int[] points = new int[n];
            
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(firstLine[2 + i]);
            }
            
            int T = Integer.parseInt(sc.nextLine());
            
            if (sortedStatus == 1) {
                Pair result = getCandidatePair(points, T);
                if (result.n1 == 0 && result.n2 == 0) {
                    System.out.println("Test case#" + t + ": No way you can spend exactly " + T + " points.");
                } else {
                    System.out.println("Test case#" + t + ": Spend " + T + " points by playing the games with " + result.n1 + " points and " + result.n2 + " points.");
                }
            } else {
                Pair result = getUnsortedPair(points, T);
                if (result.n1 == 0 && result.n2 == 0) {
                    System.out.println("Test case#" + t + ": No way you can spend exactly " + T + " points.");
                } else {
                    System.out.println("Test case#" + t + ": Spend " + T + " points by playing the games with " + result.n1 + " points and " + result.n2 + " points.");
                }
            }
        }
        sc.close();
    }
    
    // Function to find the pair in a sorted array
    public static Pair getCandidatePair(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        
        while (left < right) {
            int sum = A[left] + A[right];
            if (sum == target) {
                return new Pair(A[left], A[right]);
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new Pair(0, 0);
    }
    
    // Function to find the pair in an unsorted array
    public static Pair getUnsortedPair(int[] A, int target) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : A) {
            int complement = target - num;
            if (set.contains(complement)) {
                return new Pair(Math.min(num, complement), Math.max(num, complement));
            }
            set.add(num);
        }
        return new Pair(0, 0);
    }
    
    // Class to hold a pair of integers
    static class Pair {
        int n1;
        int n2;
        
        Pair(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }
    }
}
