import java.util.*;
public class Backtracking {
public static int back(int[] value, int[] weight, int item, int capacity)
 {
 if (capacity < 0) {
 return Integer.MIN_VALUE;
 }
 if (item < 0 || capacity == 0) {
 return 0;
 }
 int i = value[item] + back(value, weight, item-1, capacity - weight[item]);
 int j = back(value, weight, item-1, capacity);
 return Integer.max(i,j);
 }
 public static void main(String[] args)
 {
 int[] value = { 10, 55, 20, 10, 15, 5 };
 int[] weight = {5, 9, 2, 8, 7, 4 };
 int capacity = 10;
 System.out.println("Value is: " +back(value, weight, value.length - 1, capacity));
 }
}