package Client;


/**
 * Created by amirpez on 11/15/17.
 */
public class Controller {

    private MultithreadedClient threadedClient ;

    public Controller(MultithreadedClient multithreadedClient) {
        this.threadedClient = multithreadedClient;

    }

    public void identifier(String str) {
        String[] words = str.split("#");
        if (words[0].equalsIgnoreCase("signin")) {
            if (words[1].equalsIgnoreCase("true")) {
                threadedClient.setLoginAccessBoolean(true);
                threadedClient.makeUser(words[2], words[3]);
            } else if (words[1].equalsIgnoreCase("false")) {
                threadedClient.setLoginAccessBoolean(false);
            }
        }
        if (words[0].equalsIgnoreCase("users")) {
            for (int i = 1; i < words.length; i++) {
                threadedClient.setAllUsers(words[i]);
            }
        }
        if (words[0].equalsIgnoreCase("serverPm")) {
            String pm = words[2];
            String from = words[1];
            threadedClient.pmHandler(from,pm);
        }
        if (words[0].equalsIgnoreCase("onlineusers")){
            threadedClient.clearOnlineUsers();
            System.out.println("00");
            for ( int i=1 ; i<words.length ; i++ ){
                threadedClient.setOnlineUsers(words[i]);
            }
            System.out.println("11");
            threadedClient.setOnlineListUpdated(false);
            System.out.println("22");
            System.out.println(threadedClient.isOnlineListUpdated());
        }
    }

}
