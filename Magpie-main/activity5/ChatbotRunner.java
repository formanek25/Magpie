package activity5;

import java.util.Scanner;
public class ChatbotRunner
{
    public static void main(String[] args)
    {
        Chatbot chatbot = new Chatbot();

        System.out.println (chatbot.getGreeting());
        Scanner in = new Scanner (System.in);
        String statement = in.nextLine();

        while (!statement.equals("Bye"))
        {
            System.out.println (chatbot.getResponse(statement));
            statement = in.nextLine();
        }
    }

}
