package TreeClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by windons8 on 2018/3/11.
 */
public class TrieTree {

    private class TreeNode{

        private List<TrieTree> next;
        private char data;
        public TreeNode(){
            this.data=' ';
            this.next=new ArrayList<TrieTree>(5);

        }
        public TreeNode(char c){
            this.data=c;
            this.next=new ArrayList<>(5);
            this.next.get(0).root=new TreeNode();
        }
        public String toString(){
            return  this.data+" ";
        }
    }

    private TreeNode root;

    public TrieTree(){
        this.root=new TreeNode();
    }
    public TrieTree(char c){
        this.root=new TreeNode(c);

    }

    public void insert(String s){
        TreeNode current=root;
        if(current.next.size()>1){
            int k=0;

            for(int i=0;i<current.next.size()&k<s.length();i++){
                if(current.next.get(i).root.data==s.charAt(k)){
                    current=current.next.get(i).root;
                    k++;
                }
            }
            if(k!=s.length()){
                for(int j=k;j<s.length();j++){
                    current=new TreeNode(s.charAt(k));
                    current=current.next.get(0).root;
                }
            }
        }else {
            for(int i=0;i<s.length();i++){
                current.next.get(0).root=new TreeNode(s.charAt(i));
                current=current.next.get(0).root;
            }

        }
    }




    public void printAllTree(){
        TreeNode current=root;

        Queue<TreeNode> que=new LinkedList<>();
        que.add(root);
        while (current.next.size()>0&!que.isEmpty()){

            for(int i=0;i<current.next.size();i++){
                System.out.print( current.next.get(i)+" ");
                que.add(current.next.get(i).root);
            }
        }

    }


    public static void  main(String[] args){

        TrieTree tree=new TrieTree();
        tree.insert("sdfsfa");
        tree.insert("sdffeeg");

        tree.insert("sdfdfrghjjk");

        tree.printAllTree();
    }








}
