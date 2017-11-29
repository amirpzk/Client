package Client;

/**
 * Created by MaXxxiiiiiiiiiiiaR on 11/27/2017.
 */
public interface ImpMultithreadedClient {

    public String getFromServer();

    public void sendStringToServer(String text);

    public void makeUser(String username, String user);

    public void pmHandler(String str1 , String str2);

    public String pmHandlerChat(String pm, String from);

    public void close();

}
