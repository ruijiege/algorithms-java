package com.ruijie.algorithmsJava;

import java.util.Comparator;

public class InsertionSort{

  /**
   * use natural order sort
   */
  public static void sort(Comparable[] a){
    int N=a.length;
    for(int i=0;i<N;i++){
      for(int j=i;j>0&&less(a[j],a[j-1]);j--){
        exch(a,j,j-1);
      }
    }
  }

  /**
   * use custom order sort
   */
  public static void sort(Object[] a, Comparator c){
    int N=a.length;
    for(int i=0;i<N;i++){
      for(int j=i;j>0&&less(c,a[j],a[j-1]);j--){
        exch(a,j,j-1);
      }
    }
  }

  /**
   * return a permutation that gives the elements in 
   * a[] in ascending order.
   * do not change the original array a[]
   */
  public static int[] indexSort(Comparable[] a){
    int N=a.length;
    int[] index = new int[N];
    for(int i=0;i<N;i++)
      index[i]=i;

    for(int i=0;i<N;i++)
      for(int j=i;j>0&&less(a[index[j]],a[index[j-1]]);j--)
        exch(index,j,j-1);

    return index;
  }

  /**
   * is v<w?
   */
  private static boolean less(Comparable v,Comparable w){
    return v.compareTo(w)<0;
  }
  private static boolean less(Comparator c,Object v,Object w){
    return c.compare(v,w)<0;
  }

  /**
   * exchange a[i] and a[j]
   */
  private static void exch(int[] a, int i, int j){
    int swap=a[i];
    a[i]=a[j];
    a[j]=swap;
  }
  private static void exch(Object[] a,int i, int j){
    Object swap=a[i];
    a[i]=a[j];
    a[j]=swap;
  }

  public static void main(String[] args){
    String[] a={"g","f","c","a","e","b","d"};
    InsertionSort.sort(a);
    for(int i=0;i<a.length;i++){
      System.out.println(a[i]+" ");
    }
  }
}
