package com.ruijie.algorithmsJava;

public class Quick3way{
  /**
   * quicksort the array a[] using 3-way partitionin
   */
  public static void sort(Comparable[] a){
    shuffle(a);
    sort(a,0,a.length-1);
  }

  /**
   * quicksort the subarray a[lo..hi] using 3-way partitioning
   */
  private static void sort(Comparable[] a, int lo, int hi){
    if(hi<=lo) return;
    int lt=lo,gt=hi;
    Comparable v=a[lo];
    int i=lo;
    while(i<=gt){
      int cmp=a[i].compareTo(v);
      if(cmp<0) exch(a,lt++,i++);
      else if(cmp>0) exch(a,gt--,i);
      else i++;
    }
    // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
    sort(a,lo,lt-1);
    sort(a,gt+1,hi);
  }

  /**
   * Helper functions
   */
  private static void exch(Object[] a, int i,int j){
    Object swap=a[i];
    a[i]=a[j];
    a[j]=swap;
  }

  /**
   * Knuth shuffle
   */
  private static void shuffle(Object[] a){
    int N=a.length;
    for(int i=0;i<N;i++){
      int r=i+(int)(Math.random()*(N-i));
      Object swap = a[r];
      a[r]=a[i];
      a[i]=swap;
    }
  }

  public static void main(String[] args){
    Integer[] a={1,1,5,4,4,7,3,1,6,4,7,2,9,8,4,3,2,4,5,5,6,3,3,1,1};
    Quick3way.sort(a);
    for(int i=0;i<a.length;i++){
      System.out.println(a[i]);
    }
  }
}
