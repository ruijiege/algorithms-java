package com.ruijie.algorithmsJava;
import java.util.NoSuchElementException;
import com.ruijie.algorithmsJava.Queue;
/**
 * Binary Search Tree.
 */
public class BST<Key extends Comparable<Key>,Value>{
  private Node root;

  private class Node{
    private Key key;
    private Value val;
    private Node left,right;
    private int N;

    public Node(Key key,Value value,int N){
      this.key=key;
      this.val=value;
      this.N=N;
    }
  }

  /**
   *return number of key-value pairs in BST
   */
  public int size(){
    return size(root);
  }
  public int size(Node x){
    if(x==null)
      return 0;
    else
      return x.N;
  }
  public boolean isEmpty(){
    return size()==0;
  }

  /**
   * Search BST for given key, and return associated 
   * value if found, return null if not found
   */
  public boolean contains(Key key){
    return get(key)!=null;
  }
  public Value get(Key key){
    return get(root,key);
  }
  public Value get(Node x,Key key){
    if(x==null) return null;
    int cmp=key.compareTo(x.key);
    if(cmp<0)      return get(x.left, key);
    else if(cmp>0) return get(x.right, key);
    else           return x.val;
  }

  /**
   * Insert key-value pairs to BST.
   */
  public void put(Key key,Value val){
    if(val==null){
      delete(key);
      return;
    }
    root=put(root,key,val);
  }
  private Node put(Node x,Key key,Value val){
    if(x==null) return new Node(key,val,1);
    int cmp=key.compareTo(x.key);
    if(cmp<0)      x.left=put(x.left,key,val);
    else if(cmp>0) x.right=put(x.right,key,val);
    else           x.val=val;
    x.N=1+size(x.left)+size(x.right);
    return x;
  }

  /**
   * Delete from BST.
   */
  public void delete(Key key){
    root=delete(root,key);
  }
  private Node delete(Node x,Key key){
    if(x==null) return null;
    int cmp=key.compareTo(x.key);
    if(cmp<0) x.left=delete(x.left,key);
    else if(cmp>0) x.right=delete(x.right,key);
    else{
      if(x.left==null) return x.right;
      if(x.right==null) return x.left;
      Node t=x;
      x=min(t.right);
      x.left=t.left;
      x.right=deleteMin(t.right);
    }
    x.N=1+size(x.right)+size(x.left);
    return x;
  }
  public void deleteMin(){
    if(isEmpty()) 
      throw new NoSuchElementException("Empty");
    root=deleteMin(root);
  }
  public Node deleteMin(Node x){
    if(x.left==null) return x.right;
    x.left=deleteMin(x.left);
    x.N=1+size(x.right)+size(x.left);
    return x;
  }
  public void deleteMax(){
    if(isEmpty()) 
      throw new NoSuchElementException("Empty");
    root=deleteMax(root);
  }
  public Node deleteMax(Node x){
    if(x.right==null) return x.left;
    x.right=deleteMax(x.right);
    x.N=1+size(x.right)+size(x.left);
    return x;
  }

  /**
   * min,max,floor,ceiling
   */
  public Key min(){
    if(isEmpty()) return null;
    return min(root).key;
  }
  private Node min(Node x){
    if(x.left==null) return x;
    else return min(x.left);
  }
  public Key max(){
    if(isEmpty()) return null;
    return max(root).key;
  }
  private Node max(Node x){
    if(x.right==null) return x;
    else return max(x.right);
  }

  //floor
  public Key floor(Key key){
    Node x=floor(root,key);
    if(x==null) return null;
    else return x.key;
  }
  private Node floor(Node x,Key key){
    if(x==null) return null;
    int cmp=key.compareTo(x.key);
    if(cmp<0) floor(x.left,key);
    if(cmp==0) return x;
    Node t=floor(x.right,key);
    if(t!=null) return t;
    else return x;
  }

  //ceiling
  public Key ceiling(Key key){
    Node x=ceiling(root,key);
    if(x==null) return null;
    else return x.key;
  }
  private Node ceiling(Node x,Key key){
    if(x==null) return null;
    int cmp=key.compareTo(x.key);
    if(cmp>0) ceiling(x.right,key);
    if(cmp==0) return x;
    Node t=ceiling(x.left,key);
    if(t!=null) return t;
    else return x;
  }

  /**
   * rank
   */
  public int rank(Key key){
    return rank(root, key);
  }
  private int rank(Node x,Key key){
    if(x==null) return 0;
    int cmp=key.compareTo(x.key);
    if(cmp<0)       return rank(x.left,key);
    else if(cmp==0) return size(x.left);
    else            return 1+size(x.left)+rank(x.right,key);
  }

  /**
   * select
   */
  public Key select(int k){
    if(k<0||k>=size()) return null;
    Node x=select(root,k);
    return x.key;
  }
  public Node select(Node x,int k){
    if(x==null) return null;
    int t=size(x.left);
    if(t>k)      return select(x.left,k);
    else if(t<k) return select(x.right,k-t+1);
    else         return x;
  }

  /**
   * traversal
   */
  // level order traversal
  public Iterable<Key> levelOrder(){
    Queue<Key> keyQueue=new Queue<Key>();
    Queue<Node> nodeQueue=new Queue<Node>();
    nodeQueue.enqueue(root);
    while(!nodeQueue.isEmpty()){
      Node x=nodeQueue.dequeue();
      if(x==null) continue;
      keyQueue.enqueue(x.key);
      nodeQueue.enqueue(x.left);
      nodeQueue.enqueue(x.right);
    }
    return keyQueue;
  }
  // inorder traversal
  // key sorted order
  public Iterable<Key> inOrder(){
    Queue<Key> queue=new Queue<Key>();
    inOrder(root,queue);
    return queue;
  }
  private void inOrder(Node x,Queue<Key> q){
    if(x==null) return;
    inOrder(x.left,q);
    q.enqueue(x.key);
    inOrder(x.right,q);
  }
  // preorder traversal
  // A preorder traversal is a natural way to print a directory’s structure.
  public Iterable<Key> preOrder(){
    Queue<Key> queue=new Queue<Key>();
    preOrder(root,queue);
    return queue;
  }
  private void preOrder(Node x,Queue<Key> q){
    if(x==null) return;
    q.enqueue(x.key);
    preOrder(x.left,q);
    preOrder(x.right,q);
  }
  
  //preOrder traversal don't use recursively.
  public Iterable<Key> preOrderStack(){
    Queue<Key> keyQueue=new Queue<Key>();
    Stack<Node> nodeStack=new Stack<Node>();
    nodeStack.push(root);
    while(!nodeStack.isEmpty()){
      Node x=nodeStack.pop();
      if(x==null) continue;
      keyQueue.enqueue(x.key);
      nodeStack.push(x.right);
      nodeStack.push(x.left);
    }
    return keyQueue;
  }
  //postOrder traversal
  //A postorder traversal is the natural way to sum the total disk space used in
  //the root directory and its descendants.
  public Iterable<Key> postOrder(){
    Queue<Key> queue=new Queue<Key>();
    postOrder(root,queue);
    return queue;
  }
  private void postOrder(Node x,Queue<Key> q){
    if(x==null) return;
    postOrder(x.left,q);
    postOrder(x.right,q);
    q.enqueue(x.key);
  }
  
  /**
   * test client
   */
  public static void main(String[] args){
    BST<Integer,Integer> bst=new BST<Integer,Integer>();
    Integer[] keyVals={5,3,7,2,4,6,8,1,9};
    for(int i=0;i<keyVals.length;i++){
      bst.put(keyVals[i],keyVals[i]);
    }
    System.out.println("levelorder traversal");
    for(Integer i:bst.levelOrder()){
      System.out.println(bst.get(i));
    }
    System.out.println("inorder traversal");
    for(Integer i:bst.inOrder()){
      System.out.println(bst.get(i));
    }
    System.out.println("preorder traversal");
    for(Integer i:bst.preOrder()){
      System.out.println(bst.get(i));
    }
    System.out.println("postorder traversal");
    for(Integer i:bst.postOrder()){
      System.out.println(bst.get(i));
    }
    System.out.println("preorderStack traversal");
    for(Integer i:bst.preOrderStack()){
      System.out.println(bst.get(i));
    }
  }
}
