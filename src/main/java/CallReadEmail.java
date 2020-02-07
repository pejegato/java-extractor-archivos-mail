
public class CallReadEmail {

    public static void main(String[] args) {

        /* IMAP
        String host = "imap.gmail.com";
        String mailStoreType = "imap";
        String username = "******@gmail.com";
        String password = "******";
        */

        /* POP3*/
        String host = "pop.gmail.com";
        String mailStoreType = "pop3s";
        String username = "******@gmail.com";
        String password = "******";


        String saveDirectory = "D:/Attachment";
        ReadEmail.check(host, mailStoreType, username, password, saveDirectory);

    }

}

