import java.io.IOException;


public class App {
   public static void main(String[] args) throws IOException {

       String bookUrl1 = "https://www.gutenberg.org/cache/epub/1513/pg1513.txt";
       String bookUrl2 = "https://www.gutenberg.org/cache/epub/145/pg145.txt";


       Book input1 = new Book();
       input1.readFromUrl("Romeo and Juliet", bookUrl1);


       int originalWordCount1 = countWords(input1);
       System.out.println("Book: Romeo and Juliet");
       System.out.println("Original word count: " + originalWordCount1);


       Book translatedBook1 = PigLatinTranslator.translate(input1);


       int translatedWordCount1 = countWords(translatedBook1);
       System.out.println("Translated word count: " + translatedWordCount1);


       String fileName1 = "RomeoandJuliet.txt";
       saveToFile(translatedBook1, fileName1);
       System.out.println("Translated book saved to: " + fileName1);




       Book input2 = new Book();
       input2.readFromUrl("Middlemarch", bookUrl2);


       int originalWordCount2 = countWords(input2);
       System.out.println("Book: Middlemarch");
       System.out.println("Original word count: " + originalWordCount2);


       Book translatedBook2 = PigLatinTranslator.translate(input2);


       int translatedWordCount2 = countWords(translatedBook2);
       System.out.println("Translated word count: " + translatedWordCount2);


       String fileName2 = "Middlemarch.txt";
       saveToFile(translatedBook2, fileName2);
       System.out.println("Translated book saved to: " + fileName2);


       System.out.println("\nRunning Test Suite...");
       TestSuite.run();  
   }


   private static int countWords(Book book) {
       int wordCount = 0;
       for (int i = 0; i < book.getLineCount(); i++) {
           String line = book.getLine(i);
           wordCount += line.split("\\s+").length;  
       }
       return wordCount;
   }


   private static void saveToFile(Book book, String fileName) throws IOException {
       try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileName))) {
           writer.write(book.getTitle() + "\n\n");  
           for (int i = 0; i < book.getLineCount(); i++) {
               writer.write(book.getLine(i) + "\n");
           }
           System.out.println("Book saved to: " + fileName);
       }
   }
}
