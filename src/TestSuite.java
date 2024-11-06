public class TestSuite
{
    // Run a bunch of basic tests on PigLatinTranslator
    public static void run()
    {
        boolean pass = true;
        // "null" :) 
        pass &= basicTest("null", "ullnay");

        // Test that empty strings don't crash.
        pass &= basicTest("","");
        pass &= basicTest("    ", "    ");

        // Vowel first letter
        pass &= basicTest("eat", "eatay");
        // Single consonant
        pass &= basicTest("pig", "igpay");
        // Double consonant
        pass &= basicTest("trash", "ashtray");

        // Multiple words
        pass &= basicTest("pigs eat trash", "igspay eatay ashtray");

        // Special words
        // M.C. - initials

        // Capitalization
        pass &= basicTest("Trash", "Ashtray");
        pass &= basicTest("TrAsH", "AsHtray");

        // Punctuation
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