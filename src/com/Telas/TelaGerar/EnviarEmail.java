package com.Telas.TelaGerar;

import com.Gerar.Gerar;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Objetiva;
import com.ListaQuestoes.Questao.Questao;
import com.ListaQuestoes.Questao.VouF;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarEmail {
    private String mailSMTPServer;
    private String mailSMTPServerPort;
    /*
     * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL
     * e a porta usada por ele
     */
    public EnviarEmail(String from, String to[], String subject, String messsage, String filename, String filename2) { //Para o GMAIL
        mailSMTPServer = "smtp.gmail.com";
        mailSMTPServerPort = "465";

        sendMail(from,subject,messsage,filename, filename2,to);
    }
    /*
     * caso queira mudar o servidor e a porta, so enviar para o contrutor
     * os valor como string
     */
    EnviarEmail(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
    }
    public void sendMail(String from, String subject, String message, String filename, String filename2, String ...to) {
        Properties props = new Properties();
        // quem estiver utilizando um SERVIDOR PROXY descomente essa parte e atribua as propriedades do SERVIDOR PROXY utilizado
                /*
                props.setProperty("proxySet","true");
                props.setProperty("socksProxyHost","192.168.155.1"); // IP do Servidor Proxy
                props.setProperty("socksProxyPort","1080");  // Porta do servidor Proxy
                */
        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        //Cria um autenticador que sera usado a seguir
        SimpleAuth auth = null;
        auth = new SimpleAuth ("bancoquestoesuft@gmail.com","senha4002");
        //Session - objeto que ira realizar a conexão com o servidor
        /*Como há necessidade de autenticação é criada uma autenticacao que
         * é responsavel por solicitar e retornar o usuário e senha para
         * autenticação */
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email
        //Objeto que contém a mensagem
        Message msg = new MimeMessage(session);
        try {


            BodyPart messageBodyPart = new MimeBodyPart();
            BodyPart messageBodyPart2 = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageBodyPart2);

            DataSource source = new FileDataSource(new File(filename));
            DataSource source2 = new FileDataSource(new File(filename2));

            messageBodyPart2.setDataHandler(new DataHandler(source2));
            messageBodyPart.setDataHandler(new DataHandler(source));

            messageBodyPart2.setFileName(filename);
            messageBodyPart.setFileName(filename2);

            msg.setContent(multipart);

            InternetAddress[] destinatarios = new InternetAddress[to.length];
            for(int i = 0; i < to.length; i++){

                destinatarios[i] = new InternetAddress(to[i].trim());

            }
            //Setando o destinatário
            msg.setRecipients(Message.RecipientType.TO, destinatarios);
            //Setando a origem do email
            msg.setFrom(new InternetAddress(from));
            //Setando o assunto
            msg.setSubject(subject);
            //Setando o conteúdo/corpo do email

        } catch (Exception e) {
            System.out.println(">> Erro: Completar Mensagem");
            e.printStackTrace();
        }
        //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = session.getTransport("smtp"); //define smtp para transporte
            /*
             *  1 - define o servidor smtp
             *  2 - seu nome de usuario do gmail
             *  3 - sua senha do gmail
             */
            tr.connect(mailSMTPServer, "bancodequestoesuft@gmail.com", "senha4002");
            msg.saveChanges(); // don't forget this
            //envio da mensagem
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(">> Erro: Envio Mensagem");
            e.printStackTrace();
        }
    }
}
//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
class SimpleAuth extends Authenticator {
    public String username = null;
    public String password = null;
    public SimpleAuth(String user, String pwd) {
        username = user;
        password = pwd;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication (username,password);
    }
}
