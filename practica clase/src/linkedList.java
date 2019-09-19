import java.util.ArrayList;
import java.util.Collections;

public class linkedList {

	
    public static void main(String[] args) 
    { 
    	ArrayList<Integer> nums = new ArrayList<Integer>();
    	for(int i = 100; i > 0; i--) {
    		nums.add(i);
    	}
    	for(int i = 0; i < 99; i++) {
    		System.out.println(nums.get(i));
    	}
    	Collections.sort(nums);
    	System.out.println("Ordenada : \n");
    	for(int i = 0; i < 99; i++) {
    		System.out.println(nums.get(i));
    	}
    }
	
}
