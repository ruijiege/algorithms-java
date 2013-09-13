package com.ruijie.algorithmsJava;

public class MergeSort{
  /**
   * mergesort a[lo..hi] using auxiliary array aux[lo..hi]
   */
  public static void sort(Comparable[] a){
    Comparable[] aux = new Comparable[a.length];
    sort(a,aux,0,a.length-1);
  }

  private static void sort(Comparable[] a, Comparable[] aux, int lo,int hi){
    if(hi<=lo) return;
    int mid=lo+(hi-lo)/2;
    sort(a,aux,lo,mid);
    sort(a,aux,mid+1,hi);
    merge(a,aux,lo,mid,hi);
  }
  
  /**
   * stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
   */
  public static void merge(Comparable[] a, Comparable[] aux, int lo,int mid,int hi){
    for(int k=lo;k<=hi;k++){
      aux[k]=a[k];
    }
    int i=lo,j=mid+1;
    for(int k=lo;k<=hi;k++){
      if(i>mid)                    a[k]=aux[j++];
      else if(j>hi)                a[k]=aux[i++];
      else if(less(aux[j],aux[i])) a[k]=aux[j++];
      else                         a[k]=aux[i++];
    }
  }

  /**
   * helper function
   */
  private static boolean less(Comparable v, Comparable w){
    return (v.compareTo(w)<0);
  }

  /**
   * Index merge Sort.
   * return a permutation that gives the elements in a[] in ascending order
   * do not change the original array a[]
   */
  public static int[] indexSort(Comparable[] a){
    int N=a.length;
    int[] index=new int[N];
    for(int i=0;i<N;i++)
      index[i]=i;
    int[] aux=new int[N];
    sort(a,index,aux,0,N-1);
    return index;
  }

  /**
   * mergesort a[lo..hi] using auxiliary array aux[lo..hi]
   */
  private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi){
    if(hi<=lo) return;
    int mid=lo+(hi-lo)/2;
    sort(a,index,aux,lo,mid);
    sort(a,index,aux,mid+1,hi);
    merge(a,index,aux,lo,mid,hi);
  }

  /**
   *stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
   */
  private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi){
    for(int k=lo;k<=hi;k++){
      aux[k]=index[k];
    }
    int i=lo,j=mid+1;
    for(int k=lo;k<=hi;k++){
      if(i>mid)                          index[k]=aux[j++];
      else if(j>hi)                      index[k]=aux[i++];
      else if(less(a[aux[j]],a[aux[i]])) index[k]=aux[j++];
      else                               index[k]=aux[i++];
    }
  }

  public static void main(String[] args){
    Integer[] a={2,4,3,5,6,8,1,5,2,5,6,8,9,3,6};
    MergeSort.sort(a);
    for(int i=0;i<a.length;i++){
      System.out.println(a[i]);
    }
    Integer[] b={2,4,3,5,6,8,1,5,2,5,6,8,9,3,6};
    int[] c=new int[b.length];
    c=MergeSort.indexSort(b);
    for(int i=0;i<c.length;i++){
      System.out.println(c[i]);
    }
  }
}
