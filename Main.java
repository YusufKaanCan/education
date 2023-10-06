import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Properties;

public class Main {
    public static void SendMail(String emailAddress) {
        final String user = "lukskonya42@hotmail.com"; // Hotmail hesap e-posta adresi
        final String pass = "!LuksKonya0147852!"; // Hotmail hesap şifresi

       // Properties props = new Properties();
       // props.put("mail.smtp.auth", "true");
       // props.put("mail.smtp.starttls.enable", "true");
       // props.put("mail.smtp.host", "smtp.live.com"); // Hotmail için SMTP sunucu adresi
       // props.put("mail.smtp.port", "587"); // Hotmail için SMTP portu (TLS kullanılmalıdır)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
       // props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // veya daha yeni bir sürüm
       // props.put("mail.smtp.ssl.ciphersuites", "TLS_AES_128_GCM_SHA256"); // TLS 1.2 veya daha yeni bir sürüm için uygun bir şifreleme seçin
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            message.setSubject("Kayıt Onayı");
            message.setText("Sayın Kullanıcı,\n\nLüks Konya'ya kaydınız için teşekkür ederiz.");

            Transport.send(message);

            System.out.println("Kayıt onayı e-postası gönderildi: " + emailAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public static void Evaluation() {
        Scanner scan = new Scanner(System.in);
        int evaluation = scan.nextInt();

        switch (evaluation){
            case 1:
                System.out.println("Puanınız 1 ): Çok Düşük");
                break;
            case 2:
                System.out.println("Puanınız 2 ): Düşük");
                break;
            case 3:
                System.out.println("Puanınız 3 ): Orta");
                break;
            case 4:
                System.out.println("Puanınız 4 (: İyi");
                break;
            case 5:
                System.out.println("Puanınız 5 (: Yüksek");
                break;
            default:
                System.out.println("Lütfen Geçerli Bir Sayı Giriniz 1 - 2 - 3 - 4 - 5 <---");
                break;

        }
    }

    public static void SendTicket(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Gideceğiniz Bölge Plakasını Giriniz Ve Fiyat Alınız...");
        System.out.println("");
        int numberPlate = scan.nextInt();

        if (numberPlate == 42) {
            System.out.println("Zaten Konyadasınız. Lütfen Geçerli Bir İl Seçiniz...");
        } else if (numberPlate >= 1 && numberPlate <= 81) {
            System.out.println("Seçtiğiniz İl : " + numberPlate);

            if (numberPlate >= 1 && numberPlate <= 41) {
                System.out.println("1 - 41 Arası Fiyat : 450");
            } else if (numberPlate >= 43 && numberPlate <= 81) {
                System.out.println("43 - 80 Arası Fiyat : 550");
            } else {
                System.out.println("Geçerli Bir İl Seçmediniz");
                return; // Geçerli bir il seçilmediği durumda metodu sonlandır
            }

            System.out.println("Fiyatı Onaylamak İçin 1 Tuşunu Seçiniz");
            System.out.println("Fiyatı Reddetmek İçin 2 Tuşunu Seçiniz");

            int vote = scan.nextInt();

            if (vote == 1) {
                System.out.println("Fiyat Onaylandı. İşlem Tamamlandı. Dekont E-posta Üzerinden Gönderiliyor");
            } else if (vote == 2) {
                System.out.println("Fiyat Reddedildi. İşlem Tamamlandı. Red Talebi E-posta Üzerinden Gönderiliyor");
            } else {
                System.out.println("Geçerli bir seçenek seçilmedi. İşlem Reddedildi.");
            }

        } else {
            System.out.println("Geçerli Bir İl Seçmediniz");
        }
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        List<String> users = new ArrayList<>();
        boolean next = true;
        boolean registered = false;

        while (next){

            System.out.println("Merhabalar Lüks Konya'ya Hoşgeldiniz.");
            System.out.println("Verilen Seçeneklerden Birini Seçiniz");
            if (!registered) {
                System.out.println("0. Kayıt Olmak İçin Seçiniz");
            }
            System.out.println("1. Bilet Almak İçin Seçiniz");
            System.out.println("2. Hakkımızda Ve KVKK Bilgilendirme İçin Seçiniz");
            System.out.println("3. Bizi Değerlendirin");
            System.out.println("4. Çıkış İçin");

            int vote = scan.nextInt();
            scan.nextLine();

            switch (vote){

                case 0 :
                    if(!registered){
                        System.out.println("Lütfen E-Posta Adresinizi Giriniz : ");
                        String post = scan.nextLine();
                        System.out.println("Lütfen Şifrenizi Giriniz : ");
                        String pass = scan.nextLine();
                        String user = post + " , " + pass; // E-posta ve şifreyi birleştirip tek bir String olarak saklayabilirsiniz.
                        users.add(user);
                        SendMail(post);
                        System.out.println("Giriş - Kayıt İşlemleri Tamamlandı. Doğrulama Epostası Gönderiliyor.\n");
                        registered = true;

                    }else {
                        System.out.println("Zaten kayıtlısınız.");
                    }

                    break;

                case 1 :
                    if (!users.isEmpty()) {
                        SendTicket();
                    } else {
                        System.out.println("Önce kayıt olmanız gerekiyor.");
                    }
                    next = true;
                    break;

                case 2:
                    System.out.println("   ");
                    System.out.println("Biz Kimiz? Lüks Konya Olarak 2014'te Konyada Kurulmuştur. ");
                    System.out.println("All Rights Reserved © Lüks Konya");
                    System.out.println("   ");
                    System.out.println("Menüye Dönülüyor");
                    next = true;
                    break;

                case 3:
                    boolean evaluationCompleted = false;

                    if (!users.isEmpty()) {
                        System.out.println("1 - 2 - 3 - 4 - 5 Arasında Bir Puan Veriniz!");
                        Evaluation();
                    }else {
                        System.out.println("Değerlendirmek İçin Önce kayıt olmanız gerekiyor.");
                        System.out.println("Menüye Dönülüyor");
                    }
                    next = true;


                    break;
                case 4:
                    System.out.println("Çıkış Yapılıyor");
                    System.out.println("   ");
                    next = false;
                    break;

                default:
                    System.out.println("   ");
                    System.out.println("Lütfen 1 - 2 - 3 - 4 Seçeneklerinden Birini Seçiniz");
                    System.out.println("   ");
                    next = true;
                    break;
            }

        }

    }

}