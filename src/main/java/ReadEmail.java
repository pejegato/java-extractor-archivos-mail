import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.util.Properties;

public class ReadEmail {

    public static void check(String host, String storeType, String user,
                             String password, String saveDirectory)
    {
        try {

            //create properties field
            Properties properties = System.getProperties();

            if(storeType.equalsIgnoreCase("pop3s")){
                properties.put("mail.pop3.host", host);
                properties.put("mail.pop3.port", "995");
                properties.put("mail.pop3.starttls.enable", "true");
            }else{
                properties.put("mail.imap.ssl.enable", "true");
            }

            properties.put("mail.mime.decodefilename", "true");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore(storeType);
            store.connect(host, user, password);

            Folder[] f = store.getDefaultFolder().list();
            for(Folder fd:f)
                System.out.println(">> "+fd.getName());

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();

            for (int i = 0; i < messages.length; i++){

                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Fecha: " + message.getReceivedDate());
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                //if(message.getSubject() != null && message.getSubject().contains("SP")){


                    String contentType = message.getContentType();

                    String messageContent = "";

                    if (contentType.contains("multipart")) {
                        // content may contain attachments
                        Multipart multiPart = (Multipart) message.getContent();
                        int numberOfParts = multiPart.getCount();

                        for (int partCount = 0; partCount < numberOfParts; partCount++) {

                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);

                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                // this part is attachment
                                String fileName = part.getFileName();
                                part.saveFile(saveDirectory + File.separator + fileName);

                                System.out.println(fileName);
                            } else {
                                // this part may be the message content
                                messageContent = part.getContent().toString();

                                System.out.println(messageContent);
                            }
                        }
                    } else if (contentType.contains("text/plain")
                            || contentType.contains("text/html")) {
                        Object content = message.getContent();
                        if (content != null) {
                            // this part may be the message content
                            messageContent = content.toString();
                            System.out.println(messageContent);
                        }
                    }
                //}
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
