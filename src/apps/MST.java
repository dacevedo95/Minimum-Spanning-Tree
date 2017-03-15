package apps;

import structures.*;
import java.util.ArrayList;
import java.util.Iterator;

import apps.PartialTree.Arc;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		PartialTreeList list = new PartialTreeList();
		for(int i = 0; i < graph.vertices.length; i++) {
			PartialTree tree = new PartialTree(graph.vertices[i]);
			for (Vertex.Neighbor nbr = graph.vertices[i].neighbors; nbr != null; nbr=nbr.next) {
				PartialTree.Arc edge = new PartialTree.Arc(graph.vertices[i], nbr.vertex, nbr.weight);
				tree.getArcs().insert(edge);
			}
			list.append(tree);
		}
		
		return list;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		ArrayList<PartialTree.Arc> result = new ArrayList<PartialTree.Arc>();
		PartialTreeList list = ptlist;
		int counter = list.size();
		
		while(counter > 1) {
			PartialTree v2Tree = null;
			PartialTree tree = list.remove();
			
			Vertex v1, v2;
			Arc edge = tree.getArcs().deleteMin();
			
			v1 = tree.getRoot();
			v2 = edge.v2;
			
			while(v2.parent != v2 || v2 == tree.getRoot()) {
				if(v2 == v1) {
					edge = tree.getArcs().deleteMin();
					v2 = edge.v2;
				} else {
					v2 = v2.parent;
				}
			}
			
			result.add(edge);
			
			for(Iterator<PartialTree> iter = list.iterator(); iter.hasNext();) {
				PartialTree element = iter.next();
				if(v2 == element.getRoot()) {
					v2Tree = element;
					
				}
			}
						
			tree.merge(v2Tree);
			list.append(tree);
			list.removeTreeContaining(v2);
			counter--;
		}
		return result;
	}	
}
