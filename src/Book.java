import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;




public class Book {
   private String title;
   private ArrayList<String> text = new ArrayList<>();

   Book() {
   }

   public String getTitle() {
       return title;
   }




   void setTitle(String title) {
       this.title = title;
   }




   String getLine(int lineNumber) {
       return text.get(lineNumber);
   }




   int getLineCount() {
       return text.size();
   }




   void appendLine(String line) {
       text.add(line);
   }




   public void readFromString(String title, String string) {
       this.title = title;
       Scanner scanner = new Scanner(string);
       while (scanner.hasNext()) {
           String line = scanner.nextLine();
           text.add(line);
       }
       scanner.close();
   }




   public void readFromUrl(String title, String url) {
       this.title = title;
       try {
           URL bookUrl = new URL(url);
           Scanner scanner = new Scanner(bookUrl.openStream());
           while (scanner.hasNext()) {
               text.add(scanner.nextLine());
           }
           scanner.close();
       } catch (IOException ex) {
           ex.printStackTrace();
       }
   }




   public void printlines(int start, int length) {
       System.out.println("Lines " + start + " to " + (start + length) + " of book: " + title);
       for (int i = start; i < start + length; i++) {
           if (i < text.size()) {
               System.out.println(i + ": " + text.get(i));
           } else {
               System.out.println(i + ": line not in book.");
           }
       }
   }




   public void writeToFile(String fileName) throws IOException {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
           writer.write(this.title + "\n\n");
           for (String line : text) {
               writer.write(line + "\n");
           }
       }
   }
}
