public class Main {
    public static void main(String [] args) {
        EmailMessage.Builder  myBuilder = new EmailMessage.Builder();
        myBuilder.addFrom("from_email");
        myBuilder.addTo("to_email");
        myBuilder.addTo("to_email2");
        myBuilder.addSubject("test");
        myBuilder.addContent("test ");
        myBuilder.build().send();
    }
}

