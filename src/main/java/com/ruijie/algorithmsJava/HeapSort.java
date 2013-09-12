package com.ruijie.algorithmsJava;

/**
 * Sink-based heap construction is linear time.
 * Heapsort users fewer than 2N lg N compare and exchanges to sort N items.
 */

public class HeapSort{
  public static void sort(Comparable[] pq){
    int N=pq.length;
    //build the heap using bottom-up order
    for(int k=N/2;k>=1;k--)
      sink(pq,k,N);
    //remove the maximum, one at a time;
    //leave in array, instead of nulling out
    while(N>1){
      exch(pq,1,N--);
      sink(pq,1,N);
    }
  }

  /**
   * Helper functions to restore the heap invariant.
   */ 
  private static void sink(Comparable[] pq, int k, int N){
    while(2*k<=N){
      int j=2*k;
      if(j<N&&less(pq,j,j+1))//find larger child
        j++;
      if(!less(pq,k,j))//compare k with the larger child
        break;
      exch(pq,k,j);
      k=j;
    } 
  }


  private static boolean less(Comparable[] pq, int i, int j) {
    return pq[i - 1].compareTo(pq[j - 1]) < 0;
  }

  private static void exch(Object[] pq, int i, int j) {
    Object swap = pq[i - 1];
    pq[i - 1] = pq[j - 1];
    pq[j - 1] = swap;
  }

  public static void main(String[] args){
    Integer[] a={2,4,3,5,6,8,1,5,2,5,6,8,9,3,6};
    HeapSort.sort(a);
    for(int i=0;i<a.length;i++){
      System.out.println(a[i]);
    }
  }
}
