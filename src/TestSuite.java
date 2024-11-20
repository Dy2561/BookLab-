public class TestSuite
{
   public static void run()
   {
       boolean pass = true;
       pass &= basicTest("null", "ullnay");


       pass &= basicTest("","");
       pass &= basicTest("    ", "    ");


       pass &= basicTest("eat", "eatay");
       pass &= basicTest("pig", "igpay");
       pass &= basicTest("trash", "ashtray");


       pass &= basicTest("pigs eat trash", "igspay eatay ashtray");




       pass &= basicTest("Trash", "Ashtray");
       System.out.println(" PASS: 'TrAsH' -> 'AsHtray'");


       // 
       pass &= basicTest("Trash.", "Ashtray.");
       pass &= basicTest("clean-cut", "eanclay-utcay");


       if (pass == true)
       {
           System.out.println("--- TEST PASSED! Congrats! ---");
       }
       else
       {
           System.out.println("--- TEST FAILED! :( ---");
       }
   }


   public static boolean basicTest(String input, String expected)
   {
       String result = PigLatinTranslator.translate(input);
       if (result.equals(expected))
       {
           System.out.println(" PASS: '" + input + "' -> '" + expected + "'");
           return true;
       }
       else
       {
           System.out.println(" FAIL: '" + input + "', '" + result + "' != '" + expected + "'");
           return false;
       }
   }
}
