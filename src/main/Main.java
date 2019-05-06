package main;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

class Sender {
    //Не забудьте заполнить mailProperties и FilePathName

    //Метод считывающий данные из файла возвращающий String
    private static String readUsingFiles(String fileName) throws IOException {
        try { return new String(Files.readAllBytes(Paths.get(fileName)));
        }catch (Exception IOE2){ System.out.println("ERROR IOE2"); return "0"; }
    }

    public static void main(String[] args) throws IOException, MessagingException {

        Scanner in = new Scanner(System.in);

        //Подключаю Настройки почты и тд
        Properties properties = new Properties();
        try { properties.load(new FileInputStream(new File("src/main/mailProperties")));
        }catch (Exception IOE1) { System.out.println ("ERROR IOE1"); }

        //Беру из файла и переставляю значения &name% и &phone% на введенные знaчения
        String contents = new String(readUsingFiles("src/main/text"));
        System.out.println("Введите ваше имя");
        contents = (contents.replace("&name%", in.nextLine()));
        System.out.println("Введите ваш номер телефона");
        contents = (contents.replace("&phone%", in.nextLine()));

        //Настраиваю сессию
        Session mailSesion = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSesion);
        message.setFrom(new InternetAddress(properties.getProperty("outMail")));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("toMailAdress")));
        message.setSubject("Имя и Номер телефона");
        message.setText(contents);

        //Шлю сообщение
        Transport tr = mailSesion.getTransport();
        tr.connect(properties.getProperty("outMail"), properties.getProperty("outMailPass"));
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();

    }

}
