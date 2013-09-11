package com.ruijie.algorithmsJava;

public class QuickSort{
  /**
   * quick sort the array a[]
   */
  public static void sort(Comparable[] a){
    shuffle(a);
    sort(a,0,a.length-1);
  }

  /**
   * quick sort sunarray form a[lo] to b[hi].
   */
  private static void sort(Comparable[] a, int lo,int hi){
    if(hi<=lo) return;
    int j=partition(a,lo,hi);
    sort(a,lo,j-1);
    sort(a,j+1,hi);
  }

  /**
   * partition the subarray a[lo..hi] by returning an index j
   * so that a[lo..j-1]<=a[j]<=a[j+1..hi]
   */
  private static int partition(Comparable[] a,int lo,int hi){
    int i=lo;
    int j=hi+1;
    Comparable v=a[lo];
    while(true){
      while(less(a[++i],v))//find larger element on left side to swap
        if(i==hi) break;
      while(less(v,a[--j]))//find smaller element on right side to swap
        if(j==lo) break;
      if(i>=j) break;//check if pointer cross
      exch(a,i,j);
    }
    exch(a,lo,j);//put v=a[j] into position
    return j;
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

/**
 * Rearranges the elements in a so that a[k] is the kth smallest element,
 * and a[0] through a[k-1] are less than or equal to a[k], and
 * a[k+1] through a[n-1] are greater than or equal to a[k].
 */
  public static Comparable select(Comparable[] a,int k){
    if(k<0||k>=a.length){
      throw new IndexOutOfBoundsException("out of bound");
    }
    shuffle(a);
    int lo=0, hi=a.length-1;
    while(hi>lo){
      int i=partition(a,lo,hi);
      if(i>k) hi=i-1;
      else if(i<k) lo=i+1;
      else return a[i];
    }
    return a[lo];
  }

  //helper method
  private static boolean less(Comparable v,Comparable w){
    return (v.compareTo(w)<0);
  }
  private static void exch(Object[] a,int i,int j){
    Object swap =a[i];
    a[i]=a[j];
    a[j]=swap;
  }

  public static void main(String[] args){
    String[] a={"g","f","c","a","e","b","d"};
    QuickSort.sort(a);
    for(int i=0;i<a.length;i++){
    System.out.println("quick sort a[]: "+a[i]);
    }
    String[] b={"g","f","c","a","e","b","d"};
    System.out.println("select 4th: "+QuickSort.select(b,4));
    for(int i=0;i<b.length;i++){
      System.out.println("after select 4th: "+b[i]);
    }
  }
}

