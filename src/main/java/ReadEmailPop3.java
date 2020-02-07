
public class ReadEmailPop3 {

    public static void main(String[] args) {

        String host = "pop.gmail.com";
        String mailStoreType = "pop3s";
        String username = "******@gmail.com";
        String password = "******";
        String saveDirectory = "D:/Attachment";
        ReadEmail.check(host, mailStoreType, username, password, saveDirectory);

    }

}

