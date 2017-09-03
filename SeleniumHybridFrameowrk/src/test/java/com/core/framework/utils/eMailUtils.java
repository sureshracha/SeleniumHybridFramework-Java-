package com.core.framework.utils;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 

 

import com.sun.mail.smtp.SMTPTransport;


/**
 * This class constructs and sends out an email message.
 *
 * @author sracha 
 * @version 1.0
 */
public final class eMailUtils
{
    //~ Instance variables ---------------------------------------------------------------

    private String bcc;
    private String cc;
    private String from;
    private String host;
    private String mailer;
    private String mailhost;
    private String password;
    private String protocol;
    private String record; // name of folder in which to record mail
    public String subject;
    private String text;
    private String to;
    private String url;
    private String user;
    private boolean auth;
    private boolean debug;
    private Boolean flag;
    private boolean ssl;
    

    //~ Constructors ---------------------------------------------------------------------

    public eMailUtils(String property) {
		// TODO Auto-generated constructor stub
    	this.to = property;
	}





	/**
     * Constructs an instance of Mailer.
     * @return 
     */
    public void setEmailFlagAndServer(Boolean emailflag,String smtpServer)
    {
    	this.flag = emailflag;
        this.mailhost = smtpServer;

        if ((to == null) || to.equals(""))
        {
            flag = false;
        }

       
    }
    
    
    
    
    
    public void setSendList(String fromlist,String tolist,String cclist,String bcclist)
    {
    	
         from = fromlist; // Base.get("FromProject");
         cc = cclist;
         to = tolist;
         bcc = bcclist;
         url = null;
         mailer = "smtpsend";
         //    file = null;
         protocol = "smtp";
         host = "";
         user = "";
         password = null;
         record = null; // name of folder in which to record mail
         text = "";
    }
    
    public void setEmailSubject(String subject){
    	 this.subject = subject;
    	
    }
    
    public void setEmailBody(String text)
    {
    	this.text = text;
    }
    //~ Methods --------------------------------------------------------------------------

    /**
     * This method will sends the mail if the required information in the
     * 
     *
     * @param file a path to the attachment (single test).
     * @param subjectSuffix subject test fail/pass/skip info.
     * @param files paths to the attachments for the suite test.
     *
     * @throws Exception
     */
    public void sendFile(
        String       file,
        String       subjectSuffix,
        List<String> files)
      throws Exception
    {
        
    	 if (!flag)
         {
             return;
         }

         try
         {
             subject = "IE" + 
 					  subjectSuffix;

             Properties props = System.getProperties();

             if (mailhost != null)
             {
                 props.put("mail.smtp.host", mailhost);
             }

             if (auth)
             {
                 props.put("mail.smtp.auth", "true");

                 // Get a Session object
             }

             Session session = Session.getInstance(props, null);

             if (debug)
             {
                 session.setDebug(true);

                 // construct the message
             }

             Message msg = new MimeMessage(session);

             if (from != null)
             {
                 InternetAddress addr = new InternetAddress(from );
                 addr.setPersonal(from);
                 msg.setFrom(addr);
             }
             else
             {
                 msg.setFrom();
             }

             msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

             if (cc != null)
             {
                 msg.setRecipients(
                     Message.RecipientType.CC, InternetAddress.parse(cc, false));
             }

             if (bcc != null)
             {
                 msg.setRecipients(
                     Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
             }

             msg.setSubject(subject);

             //String text = "";
             if (file != null)
             {
                 // Attach the specified file.
                 // We need a multipart message to hold the attachment.
                 MimeBodyPart mbp1 = new MimeBodyPart();
                 mbp1.setText(text);

                 MimeBodyPart mbp2 = new MimeBodyPart();
                 mbp2.attachFile(file);

                 MimeMultipart mp = new MimeMultipart();
                 mp.addBodyPart(mbp1);
                 mp.addBodyPart(mbp2);
                 msg.setContent(mp);
             }
             else if (files != null)
             {
                 MimeMultipart mp = new MimeMultipart();
                 MimeBodyPart mbp1 = new MimeBodyPart();
                 mbp1.setText(this.text);
                 mp.addBodyPart(mbp1);

                 for (String f : files)
                 {
                     if (f != null)
                     {
                         MimeBodyPart mbp2 = new MimeBodyPart();
                         mbp2.attachFile(f);
                         mp.addBodyPart(mbp2);
                     }
                 }

                 msg.setContent(mp);
             }
             else
             {
                 // If the desired charset is known, you can use
                 // setText(text, charset)
                 msg.setText(this.text);
             }

             msg.setHeader("X-Mailer", this.mailer);
             msg.setSentDate(new Date());

             // send the thing off
             SMTPTransport t = (SMTPTransport) session.getTransport(ssl ? "smtps"
                                                                        : "smtp");

             try
             {
                 if (auth)
                 {
                     t.connect(this.mailhost, this.user, this.password);
                 }
                 else
                 {
                     t.connect();
                 }

                 t.sendMessage(msg, msg.getAllRecipients());
             }
             finally
             {
                 if (t != null)
                 {
                     

                     t.close();
                 }
             }

             //System.out.println("\nMail was sent successfully.");

             // Keep a copy, if requested.
             if (record != null)
             {
                 // Get a Store object
                 Store store = null;

                 if (url != null)
                 {
                     URLName urln = new URLName(url);
                     store = session.getStore(urln);
                     store.connect();
                 }
                 else
                 {
                     if (protocol != null)
                     {
                         store = session.getStore(protocol);
                     }
                     else
                     {
                         store = session.getStore();

                         // Connect
                     }

                     if ((host != null) || (user != null) || (password != null))
                     {
                         store.connect(host, user, password);
                     }
                     else
                     {
                         store.connect();
                     }
                 }

                 // Get record Folder.  Create if it does not exist.
                 Folder folder = store.getFolder(record);

                 if (folder == null)
                 {
                     throw new Exception("Can't get record folder.");
                 }

                 if (!folder.exists())
                 {
                     folder.create(Folder.HOLDS_MESSAGES);
                 }

                 Message[] msgs = new Message[1];
                 msgs[0] = msg;
                 folder.appendMessages(msgs);

                 //System.out.println("Mail was recorded successfully.");
             }
         }
         catch (Exception e)
         {
         	e.printStackTrace();
             throw new Exception(e.getMessage());
         }
    }


    /**
     * Sets the text of message.
     *
     * @param text that need to set to the mail.
     */
    public void setText(String text)
    {
        this.text = text;
    }

    
    /**
     * Need to send mail or not
     *
     * @return true/false
     */
    public boolean willSend()
    {
        return flag;
    }
}
