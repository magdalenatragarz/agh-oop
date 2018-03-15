import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailMessage {

    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional


    private EmailMessage() {
        from = new String();
        to = new LinkedList<String>();
        subject = null;
        content = null;
        mimeType = null;
        cc = null;
        bcc = null;
    }

    static public class Builder {
        private String from; //required (must be e-mail)
        private LinkedList<String> to; //required at least one (must be e-mail)
        private String subject; //optional
        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc; //optional
        private LinkedList<String> bcc; // optional
        //zestaw pol ma byc taki sam a pozniej wywolujemy metode build gdzie sie sprawdza wszystko, wsadza do
        //elementu emailmessage i jesli jest ok to zwraca stworzony element emailmessage
        /*public Builder(String _from, String _to) {
            from = _from;
            to.addLast(_to);
        }*/

        public Builder(){}

        void addFrom(String _from){
            from = new String();
            from = _from;
        }
        void addTo(String _to) {
            if (to==null){
                to = new LinkedList<String>();
            }
            to.addLast(_to);
        }

        void addSubject(String _subject) {
            subject = new String();
            subject = _subject;
        }

        void addContent(String _content) {
            content = new String();
            content = _content;
        }

        void mimeType(String _mimeType) {
            mimeType = new String();
            mimeType = _mimeType;
        }

        void addCC(String _cc) {
            if (cc == null) {
                cc = new LinkedList<String>();
            }
            cc.addLast(_cc);
        }

        void addBCC(String _bcc) {
            if (bcc == null) {
                bcc = new LinkedList<String>();
            }
            bcc.addLast(_bcc);
        }

        EmailMessage build() {
            int counter = 0;
            boolean flag = false;
            EmailMessage myEmail = new EmailMessage();
            if (from != null && from.matches("(.*)@gmail.com")) {
                if (to.size() >= 1) {
                    for (String i : to) {
                        if (i.matches("(.*)@gmail.com")) {
                            counter++;
                        }
                    }
                    if (counter == to.size()) {
                        myEmail.from = from;
                        myEmail.to = to;
                        myEmail.subject = subject;
                        myEmail.content = content;
                        myEmail.mimeType = mimeType;
                        myEmail.bcc = bcc;
                        myEmail.cc = cc;
                        flag = true;
                    }
                }
            }
            if (flag) {
                System.out.println("From: " + "\n" + from);
                System.out.println("To: ");
                for (String i : to) {
                    System.out.println(i);
                }
                System.out.println("Subject: " + "\n" + subject);
                System.out.println("Content: " + "\n" + content);
                return myEmail;
            } else {
                System.out.println("Nieprawidłowe dane");
                return myEmail;
            }
        }

    }


    public boolean send() {
        int i=0;
        final String host = "smtp.gmail.com";
        Scanner reader = new Scanner(System.in);
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.stmp.host",host);
        props.put("mail.stmp.user",from);
        System.out.println("Password: ");
        String password = new String();
        password = reader.nextLine();
        props.put("mail.stmp.password",password);
        props.put("mail.smtp.port","587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,null);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setFrom(new InternetAddress(from));
            for (String x:to){
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(x));
            }
            mimeMessage.setSubject(subject);
            mimeMessage.setText(content);
            Transport transport = session.getTransport("smtp");
            transport.connect(host,from,password);
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
            return true;
        }catch(MessagingException mex){
            mex.printStackTrace();
        }
        return false;
    }

}



