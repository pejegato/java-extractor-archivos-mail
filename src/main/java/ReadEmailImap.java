

public class ReadEmailImap {


    public static void main(String[] args) {

        String host = "imap.gmail.com";
        String mailStoreType = "imap";
        String username = "******@gmail.com";
        String password = "******";

        String saveDirectory = "D:/Attachment";

        ReadEmail.check(host, mailStoreType, username, password, saveDirectory);

    }

}

