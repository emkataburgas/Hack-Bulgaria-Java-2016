package sortingAlgorithms;

public class SlowSorts {
	
	public static void selectionSort(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i];
			for (int j = i; j < arr.length; j++) {
				if(min > arr[j]) {
					min = arr[j];
					arr[j] = arr[i];
					arr[i] = min;
				}
			}
			arr[i] = min;
		}
	}
	
	public static void insertionSort(int[] arr){
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
	public static void bubbleSort(int[] arr){
		int lastUnsorted = arr.length;
		for (int i = 0; i < lastUnsorted;) {
			for (int j = i; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		lastUnsorted--;
		}
	}
	
	public static void countingSort(int[] arr){
		int min = findMin(arr);
		int max = findMax(arr);
		
		int[] histo = new int[max - min + 1];
		
		for (int i = 0; i < histo.length; i++) {
			histo[i] = 0;
		}
		
		for (int i = 0; i < arr.length; i++) {
			histo[arr[i] - min] = histo[arr[i] - min] + 1;  
		}
		
		int unsortedIndex = 0;
		for (int i = 0; i < histo.length; i++) {
			while(histo[i] > 0) {
				arr[unsortedIndex] = i + min;
				histo[i] = histo[i] - 1;
				unsortedIndex++;
			}
		}
	}
	
	public static int findMin(int[] arr){
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		return min;
	}
	
	public static int findMax(int[] arr){
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] array = new int[25];
	
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 100);			
		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println("-----SORTING-------");
		countingSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
		
	}
}
