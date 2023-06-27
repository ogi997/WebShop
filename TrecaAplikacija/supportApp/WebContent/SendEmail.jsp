<%@ page import = "java.io.*,java.util.*,javax.mail.*"%>
<%@ page import = "javax.mail.internet.*,javax.activation.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
 
<%
   String result;
  
	System.out.println("SendEmail: "+request.getParameter("submit"));
   // Assuming email sent from localhost
   String host = "localhost";
 
   // Get properties object
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
 // Get a Properties object
    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.port", "465");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", "true");
    props.put("mail.store.protocol", "pop3");
    props.put("mail.transport.protocol", "smtp");
    final String username = "webshopip79@gmail.com";
    final String password = "mwjdymdbsxcqzucl";
    try{
      Session sessionA = Session.getInstance(props, 
                          new Authenticator(){
                             protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                             }});

   // -- Create a new message --
      Message msg = new MimeMessage(sessionA);
   
   String emailToSend = session.getAttribute("email").toString();

   // -- Set the FROM and TO fields --
      msg.setFrom(new InternetAddress("webshopip79@gmail.com"));
      msg.setRecipients(Message.RecipientType.TO, 
                        InternetAddress.parse(emailToSend,false));
      msg.setSubject("Webshop Internet programiranje Support");
      
      String porukaZaPoslati = session.getAttribute("porukaZaSlanje").toString();
      
      msg.setText(porukaZaPoslati);
      msg.setSentDate(new Date());
      Transport.send(msg);
      //System.out.println("Message sent.");
      result = "Poruka je uspjesno poslana na: " + emailToSend;
    }catch (MessagingException e){
    	result="Doslo je do greske prilikom slanja poruke\nPokusajte ponovo kasnije.";
      System.out.println("Erreur d'envoi, cause: " + e);
    }
%>
 
<html>
   <head>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <title>Slanje poruke</title>
   </head>
  
   <body>
       
       <div style="height: 100vh; display: flex; justify-content: center; align-items: center; flex-direction: column;">
       		<h1>Slanje poruke</h1>
       		<%
          		out.println(result + "\n");
       		%>
       		<a href="messages.jsp" class="btn btn-primary">Vrati me na poruke</a>
       </div>
   
   </body>
</html>