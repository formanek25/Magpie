package activity5;
import java.util.Random;
// to give you an idea of how much I know about the Beatles,
// when I saw Lennon's name I thought "isn't he in some band?"
public class Chatbot
{
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }

    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Why so negative?";
        }
        else if (findKeyword(statement, "mother") >= 0
        || findKeyword(statement, "father") >= 0
        || findKeyword(statement, "sister") >= 0
        || findKeyword(statement, "brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (findKeyword(statement, "name") >= 0)
        {
            response = "My name is John Lennon.";
        }
        else if (findKeyword(statement, "famous") >= 0
        || findKeyword(statement, "band") >= 0)
        {
            response = "I was one of the founders of The Beatles.";
        }
        else if (findKeyword(statement, "marry") >= 0
        || findKeyword(statement, "married") >= 0
        || findKeyword(statement, "wife") >= 0)
        {
            response = "I married Cynthia Powell in 1962, divorced, and married Yoko Ono in 1969.";
        }
        else if (findKeyword(statement, "kid") >= 0
        || findKeyword(statement, "kids") >= 0
        || findKeyword(statement, "child") >= 0
        || findKeyword(statement, "children") >= 0)
        {
            response = "My older son, Julian, was born in 1963, and my younger son, Sean, was born in 1975.";
        }
        else if (findKeyword(statement, "born") >= 0)
        {
            response = "I was born in 1940 in Liverpool, UK.";
        }
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
        else if (findKeyword(statement, "I want", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }

        else
        {

            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0
            && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else
            {
                psn = findKeyword(statement, "i", 0);

                if (psn >= 0
                && findKeyword(statement, "you", psn) >= 0)
                {
                    response = transformIYouStatement(statement);
                }
                else
                {
                    psn = findKeyword(statement, "where", 0);
                    if (psn >= 0
                    && findKeyword(statement, "live", psn) >= 0)
                    {
                        response = "I last lived in New York.";
                    }
                    else
                    {
                        response = getRandomResponse();
                    }
                }
            }
        }
        return response;
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     */
    private String transformIWantToStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     */
    private String transformIWantStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }

    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     */
    private String transformYouMeStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }

        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);

        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     */
    private String transformIYouStatement(String statement)
    {
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                .length() - 1);
        }

        int psnOfI = findKeyword (statement, "I", 0);
        int psnOfYou = findKeyword (statement, "you", psnOfI);

        String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
        return "Why do you " + restOfStatement + " me?";
    }

    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

        while (psn >= 0) 
        {
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }

            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
            && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }

            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);

        }

        return -1;
    }

    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }

    private String getRandomResponse ()
    {
        Random r = new Random ();
        return randomResponses [r.nextInt(randomResponses.length)];
    }

    private String [] randomResponses = {"Interesting, tell me more",
            "Hmmm.",
            "Do you really think so?",
            "You don't say."
        };

}