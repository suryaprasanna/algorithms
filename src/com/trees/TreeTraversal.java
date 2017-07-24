package com.trees;

import java.util.*;

public class TreeTraversal {
	public static void main (String [] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(11);
		TreeNode t2 = new TreeNode(12);
		TreeNode t3 = new TreeNode(13);
		t1.left = t2;
		t1.right = t3;
		TreeNode t4 = new TreeNode(14);
		TreeNode t5 = new TreeNode(15);
		t2.left = t4;
		t2.right = t5;
		TreeNode t6 = new TreeNode(16);
		TreeNode t7 = new TreeNode(17);
		t3.left = t6;
		t3.right = t7;
		TreeNode t8 = new TreeNode(18);
		t6.left = t8;
		new TreeTraversal().inorderTraversalWithStack(t1);
	}
	
	public void inorderTraversalWithStack(TreeNode root) {
		if (root == null) return;
		Stack<TreeNode> st = new Stack<>();
		TreeNode curr = root;
		while(curr != null) {
			st.push(curr);
			curr = curr.left;
		}
		while(!st.isEmpty()) {
			curr = st.pop();
			System.out.println(curr.val);
			if (curr.right != null) {
				st.push(curr.right);
				curr = curr.right;
				curr = curr.left;
				while(curr != null) {
					st.push(curr);
					curr = curr.left;
				}
			}
		}
	}
}
