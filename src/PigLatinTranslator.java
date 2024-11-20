import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class PigLatinTranslator {


   public static Book translate(Book input) {
       Book translatedBook = new Book();
       translatedBook.setTitle(input.getTitle());


       for (int i = 0; i < input.getLineCount(); i++) {
           String line = input.getLine(i);
           translatedBook.appendLine(translate(line));
       }


       return translatedBook;
   }


   public static String translate(String input) {
       if (input.trim().isEmpty()) {
           return input;
       }


       StringBuilder result = new StringBuilder();
       for (String word : input.split(" ")) {
           if (!word.isEmpty()) {
               result.append(translateWord(word)).append(" ");
           }
       }
       return result.toString().trim();
   }


   private static String translateWord(String word) {
       if (word.contains("-")) {
           StringBuilder result = new StringBuilder();
           String[] parts = word.split("-");
           for (int i = 0; i < parts.length; i++) {
               result.append(translateSingleWord(parts[i]));
               if (i < parts.length - 1) {
                   result.append("-");
               }
           }
           return result.toString();
       } else {
           return translateSingleWord(word);
       }
   }


   private static String translateSingleWord(String word) {
       Pattern pattern = Pattern.compile("([a-zA-Z]+)([^a-zA-Z]*)");
       Matcher matcher = pattern.matcher(word);


       if (matcher.matches()) {
           String mainPart = matcher.group(1);  
           String punctuation = matcher.group(2);  

           String translated = translateCore(mainPart);


           return applyOriginalCapitalization(mainPart, translated) + punctuation;
       }
       return word; 
   }


   private static String translateCore(String word) {
       if (word.isEmpty()) return word;


       boolean startsWithVowel = "aeiouAEIOU".indexOf(word.charAt(0)) != -1;
       if (startsWithVowel) {
           return word + "ay";
       } else {
           int firstVowelIndex = -1;
           for (int i = 0; i < word.length(); i++) {
               if ("aeiouAEIOU".indexOf(word.charAt(i)) != -1) {
                   firstVowelIndex = i;
                   break;
               }
           }


           if (firstVowelIndex != -1) {
               String start = word.substring(firstVowelIndex);
               String end = word.substring(0, firstVowelIndex);
               return start + end + "ay";
           } else {
               return word + "ay";  
           }
       }
   }


   private static String applyOriginalCapitalization(String original, String translated) {
       if (Character.isUpperCase(original.charAt(0))) {
           return capitalizeWord(translated);
       } else {
           return translated.toLowerCase();
       }
   }


   private static String capitalizeWord(String word) {
       if (word.length() == 0) {
           return word;
       }
       return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
   }
}
