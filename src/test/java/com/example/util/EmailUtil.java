package com.example.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class EmailUtil {
    public static void zipDirectory(String sourceDirPath, String zipFilePath) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                    try {
                        zs.putNextEntry(zipEntry);
                        Files.copy(path, zs);
                        zs.closeEntry();
                    } catch (IOException e) {
                        System.err.println("Error zipping: " + path);
                    }
                });
        }
    }

    public static void sendEmailWithAttachment(String host, String port, final String user, final String pass,
                                               String toAddress, String subject, String message, String attachFile)
            throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(user));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(message);

        MimeBodyPart attachPart = new MimeBodyPart();
        try {
            attachPart.attachFile(attachFile);
        } catch (IOException ex) {
            throw new MessagingException("Attachment error", ex);
        }

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachPart);

        msg.setContent(multipart);

        Transport.send(msg);
    }

    public static void main(String[] args) {
        String allureReportDir = "allure-report"; // path to your Allure report directory
        String zipFile = "allure-report.zip";
        try {
            zipDirectory(allureReportDir, zipFile);
            sendEmailWithAttachment(
                "smtp.gmail.com", // SMTP host
                "587",              // SMTP port
                "asiftamboli7028@gmail.com",   // sender email
                "asdsahjd@70dsjsjsdhsj28",     // sender password
                "tasif0401@gmail.com", // recipient email
                "Allure Test Report", // subject
                "Please find the attached Allure report.", // message
                zipFile
            );
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}