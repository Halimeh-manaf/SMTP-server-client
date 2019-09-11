import java.io.*;
import java.net.*;
import java.util.Base64;


public class smtp_client {
    Socket  socket;
   // String sd[];
  String attachment1 = "Untitled.gif";
  String attachment2 = "file.txt";

 public static void main(String sd[]) {
	 smtp_client test = new smtp_client();
	 if (sd.length >0) {
		 System.out.println("TRUE");
		 test.smtpwithinputfile("debby.vs.uni-due.de", "spam@debby.vs.uni-due.de",sd[0]);
		 //sd[0]="";
	 }else
	 {
	 //sd[0]="f.txt";
		 test.smtp("debby.vs.uni-due.de", "spam@debby.vs.uni-due.de");
	 }
	 }
 
 public void smtp(String serv, String recip) {
  try {
    Socket s = new Socket(serv, 25);
    BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    String senderOK3 = in.readLine();
    String seperator = "17pEHd4RhPHOinZp";
    
    //start the protocol
    send(in, out,"HELO "+"there");
    //String senderOK4 = in.readLine();
    //System.out.println("senderOK3= "+senderOK4);

    send(in, out, "MAIL From: <a@a.de>");
   // String senderOK = in.readLine();
    //System.out.println("senderOK= "+senderOK);
    send(in, out, "RCPT TO: <" + recip + ">"  );

    send(in, out, "DATA");
    send(out, "From: dd <a@a.de>");
    send(out, "To: "+"<"+recip+"<");
    send(out, "Subject: EG is back OMEGALUL");
    send(out, "MIME-Version: 1.0");
    send(out,"Content-Type: multipart/mixed; boundary=\"" + seperator +"\"");
    send (out, "Content-Disposition: inline");
    send(out, "\r\n--" + seperator);

 
    // text
    send(out, "Content-Type: text/plain; charset=\"us-ascii\"");
    send (out, "Content-Disposition: inline\n");
  byte[] encodedBytes = Base64.getEncoder().encode("Message is encoded".getBytes());
    //send(out,new String(encodedBytes)+"\n");
    byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
    //System.out.println("decodedBytes " + new String(decodedBytes));
    send(out,new String(decodedBytes)+"\n");

   // send(out, "It's working\n");
    send(out,"--" +  seperator );
    
    //gif
    send(out, "Content-Type:image/gif; name="+attachment1);
    send
      (out, "Content-Disposition: attachment;filename=\""+attachment1+"\"");
    send(out, "Content-transfer-encoding: base64\r\n");
    base64.encode(attachment1, out);    
    //send(out, "\r\n");
    send(out, "\r\n--" + seperator);
  //  send(out, "\r\n");

    // the text file
     send(out, "Content-Type: text/plain; name="+attachment2);
     send(out,"Content-Disposition: attachment;filename=\""+attachment2+"\"");
    send(out, "Content-Transfer-Encoding: base64\r\n");
    base64.encode(attachment2, out);
    send(out, "\r\n\r\n--" + seperator + "--\r\n");
    
    
    receiveID(in, out,".");
    send(in, out, "QUIT");
    s.close();
  
    }
  catch (Exception e) {
    e.printStackTrace();
    }
  }

 
 
 
 
 

 public void smtpwithinputfile(String serv, String recip, String sd) {
  try {
    Socket s = new Socket(serv, 25);
    BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream()));
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    String senderOK3 = in.readLine();
    String seperator = "17pEHd4RhPHOinZp";
    
    //start the protocol
    send(in, out,"HELO "+"there");
    //String senderOK4 = in.readLine();
    //System.out.println("senderOK3= "+senderOK4);

    send(in, out, "MAIL From: <a@a.de>");
   // String senderOK = in.readLine();
    //System.out.println("senderOK= "+senderOK);
    send(in, out, "RCPT TO: <" + recip + ">"  );

    send(in, out, "DATA");
    send(out, "From: dd <a@a.de>");
    send(out, "To: "+"<"+recip+"<");
    send(out, "Subject: EG is back OMEGALUL");
    send(out, "MIME-Version: 1.0");
    send(out,"Content-Type: multipart/mixed; boundary=\"" + seperator +"\"");
    send (out, "Content-Disposition: inline");
    send(out, "\r\n--" + seperator);

 

    
    // text
    send(out, "Content-Type: text/plain; charset=\"us-ascii\"");
    send (out, "Content-Disposition: inline\n");
    byte[] encodedBytes = Base64.getEncoder().encode("Message is encoded".getBytes());
    //send(out,new String(encodedBytes)+"\n");
    byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
    //System.out.println("decodedBytes " + new String(decodedBytes));
    send(out,new String(decodedBytes)+"\n");
    send(out,"--" +  seperator );
    
    //gif
    send(out, "Content-Type:image/gif; name="+attachment1);
    send
      (out, "Content-Disposition: attachment;filename=\""+attachment1+"\"");
    send(out, "Content-transfer-encoding: base64\r\n");
    base64.encode(attachment1, out);    
    //send(out, "\r\n");
    send(out, "\r\n--" + seperator);
  //  send(out, "\r\n");

    //  text file
     send(out, "Content-Type: text/plain; name="+attachment2);
     send(out,"Content-Disposition: attachment;filename=\""+attachment2+"\"");
    send(out, "Content-Transfer-Encoding: base64\r\n");
    base64.encode(attachment2, out);
    send(out, "\r\n\r\n--" + seperator);
    
    
    // the inputed file
   // if (sd =="" ) {
    //	System.out.println("No path is entered");
    //}else {
    send(out, "Content-Type: text/plain; name="+sd);
    send(out,"Content-Disposition: attachment;filename=\""+sd+"\"");
    send(out, "Content-Transfer-Encoding: base64\r\n");
    base64.encode(sd, out);
    send(out, "\r\n\r\n--" + seperator + "--\r\n");
    
    // done
    receiveID(in, out,".");
    send(in, out, "QUIT");
    s.close();
  //  }
    }
  catch (Exception e) {
    e.printStackTrace();
    }
  }
 
 
 
 
 public void send(BufferedReader in, BufferedWriter out, String message) {
	  try {
	    out.write(message + "\n");
	    out.flush();
	     message = in.readLine();
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	 }

	 public void send(BufferedWriter out, String message) {
	   try {
	    out.write(message + "\n");
	    out.flush();
	    System.out.println(message);
	   }
	   catch (Exception e) {
	    e.printStackTrace();
	   }
	 }
	 
	 public void receiveID(BufferedReader in, BufferedWriter out, String message) {
		  try {
		    out.write(message + "\n");
		    out.flush();
		     message = in.readLine();
		     System.out.println("Messaige-ID= "+message.substring(10));
		  }
		  catch (Exception e) {
		    e.printStackTrace();
		  }
		 }

}