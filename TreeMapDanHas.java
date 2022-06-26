package javaprogram;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMapDanHas {
	public static void main(String[] args) {
		
		TreeSet<Integer> tugas1 = new TreeSet<>();
		tugas1.add(150);
		tugas1.add(100);
		tugas1.add(200);
		
		for (int hasilTugas1 : tugas1) {
			System.out.println(hasilTugas1);
		}
		
		TreeMap<Integer, String> tugas2 = new TreeMap<Integer, String>();
		tugas2.put(7, "Ronaldo");
		tugas2.put(10, "Messi");
		tugas2.put(1, "De GEa");
		
		System.out.println(tugas2);
		
	
			}
		
		



 

   public static void main1(String args[]) {
      // create a hash set
      HashSet hase = new HashSet();
      
      // add elements to the hash set
      hase.add("B");
      hase.add("A");
      hase.add("D");
      hase.add("E");
      hase.add("C");
      hase.add("F");
      System.out.println(hase);
   }
}

