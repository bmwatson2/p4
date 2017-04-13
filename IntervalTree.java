import java.util.ArrayList;
import java.util.List;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root = null;

	@Override
	public IntervalNode<T> getRoot() {
		return root;
	}

	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		if(interval == null) {
			throw new IllegalArgumentException();
		}
		
		IntervalNode<T> curr = root;
		root = insert(interval, curr);
	}
	
	private IntervalNode<T> insert(IntervalADT<T> interval, IntervalNode<T> curr)
			throws IllegalArgumentException {
		//Link the new node if curr is NULL
		if(curr == null) {
			return new IntervalNode<T>(interval);
		}
		// If curr's start value is smaller, then new interval goes to the left subtree
		if(interval.getStart().compareTo(root.getInterval().getStart()) < 0) {
			curr.setLeftNode(insert(interval, root.getLeftNode()));
		}
		else if(interval.getStart().compareTo(root.getInterval().getStart()) > 0) {
			curr.setRightNode(insert(interval, root.getRightNode()));
		}
		else {
			throw new IllegalArgumentException();
		}
		
		//check and possibly update the maxEnd in the IntervalNode
		if(curr.getMaxEnd().compareTo(interval.getEnd()) < 0) {
			curr.setMaxEnd(interval.getEnd());
		}
		return curr;
	}

	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		if(interval == null) {
			throw new IllegalArgumentException();
		}
		root = deleteHelper(root, interval);
	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		if(node == null) {
			throw new IntervalNotFoundException(interval.getLabel());
		}
		// If node's start value is smaller, then new interval goes to the left subtree
		if(interval.equals(node.getInterval())) {
			if(node.getRightNode() != null) {
				node.setInterval(node.getRightNode().getInterval());
				//Update the new maxEnd.
				if(node.getRightNode()!=null) {
					node.setMaxEnd(node.getRightNode().getMaxEnd());
				}
				else {
					node.setMaxEnd(node.getInterval().getEnd());
				}
				return node;
			}
			else {
				return node.getLeftNode();
			}
		}
		//If interval is in the left subtree,
		if(interval.getStart().compareTo(node.getInterval().getStart()) < 0) {
			//Set left child to result of calling deleteHelper on left child.
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
			//Update the maxEnd if necessary.
			if(node.getRightNode()!=null) {
				node.setMaxEnd(node.getRightNode().getMaxEnd());
			}
			else {
				node.setMaxEnd(node.getInterval().getEnd());
			}
		}
		else if(interval.getStart().compareTo(node.getInterval().getStart()) < 0) {
			node.setRightNode(deleteHelper(node.getRightNode(), interval));
			//Update the maxEnd if necessary.
			if(node.getRightNode()!=null) {
				node.setMaxEnd(node.getRightNode().getMaxEnd());
			}
			else {
				node.setMaxEnd(node.getInterval().getEnd());
			}
		}
		return node;
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		List<IntervalADT<T>> ol = new ArrayList<IntervalADT<T>>();
		findOverlappingHelper(root, interval, ol);
		return ol;
	}

	private void findOverlappingHelper(IntervalNode<T> node, IntervalADT<T> interval, List<IntervalADT<T>> result) {
		//if node is null, return
		if(node == null) {
			return;
		}
		//if node interval overlaps with the given input interval, add it to the result.
		if(node.getInterval().overlaps(interval)) {
			result.add(node.getInterval());
		}
		//if left subtree's max is greater than the interval's start, call findOverlappingHelper in the left subtree.
		if(node.getLeftNode().getMaxEnd().compareTo(interval.getStart()) > 0) {
			findOverlappingHelper(node.getLeftNode(), interval, result);
		}
		 //if right subtree's max is greater than the interval's start, call call findOverlappingHelper in the rightSubtree.
		if(node.getRightNode().getMaxEnd().compareTo(interval.getStart()) > 0) {
			findOverlappingHelper(node.getRightNode(), interval, result);
		}
	}
	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		List<IntervalADT<T>> sp = new ArrayList<IntervalADT<T>>();
		sPH(point, sp, root);
		return sp;
	}
	private void sPH(T point, List<IntervalADT<T>> sp, IntervalNode<T> curr) {
		if((curr.getInterval().getStart().compareTo(point) <= 0) && (curr.getInterval().getEnd().compareTo(point) >= 0)) {
			sp.add(curr.getInterval());
		}
		sPH(point, sp, root.getLeftNode());
		sPH(point, sp, root.getRightNode());
	}
	
	@Override
	public int getSize() {
		return recSize(root);
	}

	private int recSize(IntervalNode<T> curr) {
		int size = 0;
		if(curr != null) {
			size++;
			size += recSize(curr.getRightNode());
			size += recSize(curr.getLeftNode());
		}
		return size;
	}
	@Override
	public int getHeight() {
		return recHeight(root);
	}
	
	private int recHeight(IntervalNode<T> curr) {
		int height = 0;
		if(curr != null) {
			int h2 = 0;
			height += recHeight(curr.getRightNode());
			h2 += recHeight(curr.getLeftNode());
			if(height < h2) {
				height = h2;
			}
		}
		return height;
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		return recC(root, interval);
	}

	private boolean recC(IntervalNode<T> curr, IntervalADT<T> interval) {
		if((curr.getInterval().getStart().equals(interval.getStart())) && (curr.getInterval().getEnd().equals(interval.getEnd()))) {
			return true;
		}
		if(recC(curr.getLeftNode(), interval)) {
			return true;
		}
		if(recC(curr.getRightNode(), interval)) {
			return true;
		}
		return false;
	}
	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.print("Height: ");
		System.out.println(getHeight());
		System.out.print("Size: ");
		System.out.println(getSize());
		System.out.println("-----------------------------------------");
	}

}
