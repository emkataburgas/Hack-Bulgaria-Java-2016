package week6;

import java.util.*;

public class MissingNumbers {
    private static ArrayList<Integer> removeDuplicates(ArrayList<Integer> l) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < l.size(); i++) {
			if( !(result.contains( l.get(i) )) ){
				result.add(l.get(i));
			}
		}
		
		return result;
	}
	
	private static void sortAsc(ArrayList<Integer> l) {
		int min = findMin(l);
		int max = findMax(l);
		
		int[] histo = new int[max - min + 1];
		
		for (int i = 0; i < histo.length; i++) {
			histo[i] = 0;
		}
		
		for (int i = 0; i < l.size(); i++) {
			histo[l.get(i) - min] = histo[l.get(i) - min] + 1;  
		}
		
		int unsortedIndex = 0;
		for (int i = 0; i < histo.length; i++) {
			while(histo[i] > 0) {
				l.set(unsortedIndex, i + min);
				histo[i] = histo[i] - 1;
				unsortedIndex++;
			}
		}
	}
	
	public static int findMin(ArrayList<Integer> l){
		int min = l.get(0);
		for (int i = 1; i < l.size(); i++) {
			int current = l.get(i);
			if (current < min) {
				min = current;
			}
		}
		return min;
	}
	
	public static int findMax(ArrayList<Integer> l){
		int max = l.get(0);
		for (int i = 1; i < l.size(); i++) {
			int current = l.get(i);
			if (current > max) {
				max = current;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sizeA = sc.nextInt();
		ArrayList<Integer> A = new ArrayList<Integer>();
		for (int i = 0; i < sizeA; i++) {
			A.add(sc.nextInt());
        }
		
		int sizeB = sc.nextInt();
		ArrayList<Integer> B = new ArrayList<Integer>();
		for (int i = 0; i < sizeB; i++) {
			B.add(sc.nextInt());
		}
		
		for (int i = 0; i < A.size(); i++) {
			B.remove( A.get(i));
		}
		
		B = removeDuplicates(B);
		sortAsc(B);
        String result = "";
		for (int i = 0; i < B.size(); i++) {
			result = result + B.get(i) + " ";
		}
        
        System.out.println(result.substring(0, result.length() - 1) );
		sc.close();
	}

//	10
//	203 204 205 206 207 208 203 204 205 206
//	13
//	203 204 204 205 206 207 205 208 203 206 205 206 204
}
