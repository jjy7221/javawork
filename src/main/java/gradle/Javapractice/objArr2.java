package gradle.Javapractice;

import java.util.Scanner;

class Book2{
    String title, author;

    public Book2(String title, String author){
        this.title = title;
        this.author = author;
    }
}

public class objArr2 {
    public static void main(String[] args){
        Book2 [] book = new Book2[2];

        Scanner scanner = new Scanner(System.in);
        for(int i = 0 ; i < book.length; i++){
            System.out.print("제목 >> ");
            String title = scanner.nextLine();
            System.out.print("저자 >> ");
            String author = scanner.nextLine();
            book[i] = new Book2(title, author);
        }

        for(int i =0 ; i < book.length; i++){
            System.out.print("("+book[i].title +","+book[i].author+")");
        }
        scanner.close();
    }
}
