package com.range_queries;

import java.util.*;

class LowestCommonAncestor {

	static class node {
		int val;
		node parent;
	} 

	public static void main(String [] args) {

	}

	public static void preprocess(int [] arr, int [] parent, int n) {
		int [] level = new int[n], sqrtparent = new int[n];
		int sqrtval = (int) Math.sqrt(n);
		dfs(arr, parent, level, sqrtparent, 0, 0, sqrtval);
	}

	public static void dfs(int [] arr, int [] parent, int [] levelarr, int [] sqrtparent, int pntr, int level, int sqrtval) {
		level[pntr] = level;
		if (parent[pntr] < 0) {
			sqrtparent[pntr] = pntr;
		} else if (level%sqrtval != 0) {
			level[pntr] = level;
			sqrtparent[pntr] = sqrtparent[parent[pntr]];
		} else {
			sqrtparent[pntr] = parent[pntr];
		}
		for 
	}

}