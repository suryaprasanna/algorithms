package com.segment_trees;

import java.util.*;

class MinRangeQuery {
	public static void main(String [] args) {
		int [] a1 = {1,2,4,6,2,4,8,2,4};//,8,0,1,4,6,89,3,2,46,7};
		MinRangeQuery m = new MinRangeQuery(a1);
		int i = 0, j = 4;
		int ans = m.min(i,j);
		System.out.println("query interval ("+i+", "+j+"): min is :"+ans);
		m.update(0, 10);
		i = 2;
		j = 4;
		ans = m.min(i,j);
		System.out.println("query interval ("+i+", "+j+"): min is :"+ans);
	}

	int [] a, st;
	MinRangeQuery(int [] a) {
		if (a==null || a.length == 0) return;
		this.a = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			this.a[i] = a[i];
		}
		// System.out.println(Arrays.toString(a));
		double d1 = Math.log((double) a.length)/Math.log(2);
		// System.out.println(d1+ ", len:"+a.length);
		int depth = (int) Math.ceil(d1);

		int segmentTreeSize = (int)(2* (Math.pow(2, depth))) - 1;
		// System.out.println("d:"+depth+"s:"+segmentTreeSize);
		st = new int[segmentTreeSize];
		constructSegmentTree(a, st, 0, a.length-1, 0);
		// System.out.println(Arrays.toString(st));
	}
	
	public int constructSegmentTree (int [] a, int [] st, int ss, int se, int sp) {
		if (ss==se) {
			st[sp] = a[ss];
			return st[sp];
		}
		int mid = ss + (se-ss)/2;
		st[sp] = Math.min(constructSegmentTree(a, st, ss, mid, 2*sp+1), constructSegmentTree(a,st,mid+1,se, 2*sp+2));
		return st[sp];
	}

	public void update(int index, int val) {
		if (index < 0 || index > a.length-1) return;
		a[index] = val;
		updateSegmentTree(a,st,0,a.length-1, index, 0);
		// System.out.println(Arrays.toString(st));
	}

	private int updateSegmentTree(int [] a, int [] st, int ss, int se, int ui, int sp) {
		// System.out.println("ss:"+ss+",se:"+se+",sp:"+sp);
		if (ui < ss || ui > se) {
			return st[sp];
		}
		if (ss == se && ss == ui) {
			st[sp] = a[ui];
			return st[sp];
		}
		int mid = ss + (se-ss)/2;
		st[sp] = Math.min(updateSegmentTree(a,st,ss, mid,ui, 2*sp+1), updateSegmentTree(a,st,mid+1, se,ui, 2*sp+2));
		
		return st[sp];
	}

	public int min(int qs, int qe) {
		if (qs > qe || qs < 0 || qe > a.length-1) return Integer.MAX_VALUE;
		return minInSegmentTree(st, 0, a.length-1, qs,qe, 0);
	}

	public int minInSegmentTree(int [] st, int ss, int se, int qs, int qe, int sp) {
		if (qs > se || qe < ss) return Integer.MAX_VALUE;
		if (qs <= ss && qe >= se) return st[sp];
		int mid = ss + (se-ss)/2;
		return Math.min(minInSegmentTree(st, ss, mid, qs,qe, 2*sp+1), minInSegmentTree(st,mid+1, se, qs,qe,2*sp+2));
	}

}
