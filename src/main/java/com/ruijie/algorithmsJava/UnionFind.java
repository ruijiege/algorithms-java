package com.ruijie.algorithmsJava;

public class UnionFind {
  private int[] id;//id[i]=parent of i;
  private int[] sz;//sz[i]=number of objects in subtree rooted at i;
  private int count;//number of components;

  public UnionFind(int N){
    count = N;
    id=new int[N];
    sz=new int[N];
    for(int i=0;i<N;i++){
      id[i]=i;
      sz[i]=1;
    }
  }
  //return the number of the disjoint sets  
  public int count(){
    return count;
  }

  /**
   * Return component identifier for component containing p.
   */
  public int find(int p){
    int root=p;
    while(root!=id[root]){
      root=id[root];
    }
    while(p!=root){
      int newp=id[p];
      id[p]=root;
      p=newp;
    }
    return root;
  }

  /**
   * Are objects p and q in the same set?
   */
  public boolean connected(int p, int q){
    return find(p)==find(q);
  }

  /**
   * union setw contains p and q
   */
  public void union(int p, int q){
    int i=find(p);
    int j=find(q);
    if(i==j){
      return;
    }
    //make smaller root point to larger one
    if(sz[i]<sz[j]){
      id[i]=j;
      sz[j]+=sz[i];
    }else{
      id[j]=i;
      sz[i]+=sz[j];
    }
    count--;
  }

  public static void main(String[] args){
    int num[]={1,2,3,4,5,6,7,8,9,0};
    UnionFind uf=new UnionFind(10);
    uf.union(0,1);
    uf.union(2,1);
    uf.union(3,1);
    uf.union(4,0);
    uf.union(8,9);
    for(int i=0;i<10;i++){
      if(uf.connected(0,i)){
        System.out.print(i+" ");
      }
    }
  }



}
