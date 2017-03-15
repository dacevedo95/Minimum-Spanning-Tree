package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import structures.Graph;
import structures.Vertex;

public class Driver {
	
public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter file containing graph => ");
		String filename = sc.next();
		System.out.println();
		
		
		
		Graph graph = new Graph("testcases/" + filename);
		PartialTreeList list = MST.initialize(graph);
		ArrayList<PartialTree.Arc> result = MST.execute(list);
		
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		
	
		
		
		/*
		for(Iterator<PartialTree> iter = list.iterator(); iter.hasNext();) {
			PartialTree element = iter.next();
			System.out.println(element);
		}*/		
	}
}
