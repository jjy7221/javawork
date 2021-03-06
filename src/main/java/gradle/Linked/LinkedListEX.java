 package gradle.Linked;

 import java.util.Scanner;

 class Node{
     private int data;
     private Node next;

     Node(){
         this.data = 0;
         this.next = null;
     }

     Node(int data){
         this.data = data;
         this.next = null;
     }

     void Setnext(Node nextnode){
         this.next = nextnode;
     }

     Node Getnext(){
         return this.next;
     }

     void Setdata(int data){
         this.data = data;
     }

     int Getdata(){
         return this.data;
     }
 }

 class LinkedList{
     private Node head;

     LinkedList(){
         this.head = null;
     }

     void Insert_data(int data){
         if(head == null){
             this.head = new Node(data);
         }
         else{
             Node temp;
             temp = this.head;
             while(temp.Getnext() != null){
                 temp = temp.Getnext();
             }
             temp.Setnext(new Node(data));
         }
     }

     void Show_Linked(){
         if(this.head == null){
             System.out.print("Empty ");
             return;
         }
         Node temp;
         temp = this.head;
         while(temp != null){
             System.out.print(temp.Getdata() + " ");
             temp = temp.Getnext();
         }
         //System.out.println();
     }

     void Sethead(Node L_node){
         this.head = L_node;
     }

     Node Gethead(){
         return this.head;
     }
 }

 public class LinkedListEX {

     static LinkedList[] MakeArr(){
         Scanner scanner = new Scanner(System.in);

         int data;
         int cnt = 0;
         LinkedList dummy = new LinkedList();

         System.out.print("Input an integer(exit input 0) >> ");
         while( 0 < (data = scanner.nextInt())){
             dummy.Insert_data(data);
             cnt++;
         }

         System.out.println(" List-Size : " + cnt);

         LinkedList[] ret = new LinkedList[cnt*2-1];

         for(int i = 0; i < ret.length; i++){
             ret[i] = new LinkedList();
         }

         ret[0].Sethead(dummy.Gethead());

         dummy.Sethead(null);

         System.out.println("Array-Size : " + ret.length);

         scanner.close();

         return ret;
     }

     static void split(LinkedList[] Link){
         int[] N = new int[Link.length];

         N[0] = (Link.length+1)/2; // ?????? ????????? ????????? ????????????

         for(int i =0; i < Link.length/2; i++){

             // Complete binary tree??? ????????? ??????
             int L = 0;

             // 2???< N < 2????????
             for(int j = 0 ; ;j++){
                 if(Math.pow(2,j) > N[i]){
                     // N = 2??? + 2???????? + a
                     int a = N[i] - (int)Math.pow(2,j-1) - (int)Math.pow(2,j-2);

                     if(a > 0){
                         L = N[i] - ((int)Math.pow(2,j-2)+a);
                     }
                     else
                     {
                         L = (int)Math.pow(2,j-1) + a;
                     }

                     N[i*2+1] = L;   // i?????? ????????? ?????? ??????

                     N[i*2+2] = N[i]-L; //       ???????????? ????????? ??????

                     break;
                 }
             }

             Node temp = Link[i].Gethead();

             for(int j = 1 ; j < L; j++){
                 temp = temp.Getnext();
             }

             Link[2*i+2].Sethead(temp.Getnext());     // ???????????? ????????? ????????? ??????

             temp.Setnext(null);                      // ????????? ????????? ?????????

             Link[2*i+1].Sethead(Link[i].Gethead());  // ????????? ????????? ????????? ??????


             Link[i].Sethead(null);                   // ?????? ????????? head??? null??? ?????????

         }
     }

     static void merge(LinkedList[] Link) {
         for (int i = Link.length - 1; i > 0; i -= 2) {
             Node left = Link[i - 1].Gethead(); // ?????? ??????
             Node right = Link[i].Gethead();    // ????????? ??????
             Node P = Link[(i - 2) / 2].Gethead(); // ?????? ??????

             while (left != null || right != null) {
                 if (left == null) {     // ?????? ????????? ??? ????????? ????????? null??? ?????? ?????? ????????? ?????? ????????????
                     P.Setnext(right);
                     break;
                 }
                 else if (right == null) {
                     P.Setnext(left);
                     break;
                 }
                 else if (left.Getdata() < right.Getdata()) {
                     if (Link[(i - 2) / 2].Gethead() == null) {
                         P = left;
                         Link[(i - 2) / 2].Sethead(P);
                         left = left.Getnext();
                     } else {
                         P.Setnext(left);
                         P = P.Getnext();
                         left = left.Getnext();
                     }
                 }
                 else{
                     if (Link[(i - 2) / 2].Gethead() == null) {
                         P = right;
                         Link[(i - 2) / 2].Sethead(P);
                         right = right.Getnext();
                     } else {
                         P.Setnext(right);
                         P = P.Getnext();
                         right = right.Getnext();
                     }
                 }
             }

             Link[i].Sethead(null);
             Link[i - 1].Sethead(null);
         }
     }

     static void showLink( LinkedList[] Link ){
         for(int i = 0; i < Link.length; i++){
             System.out.print("Link[" + i + "] = ");
             Link[i].Show_Linked();
             System.out.println();
         }
     }

     public static void main(String[] args){

         LinkedList[] Link = MakeArr();

         split(Link);

         showLink(Link);

          merge(Link);

         showLink(Link);

     }
 }
