import java.net.*;
import java.io.*;
public class client{
Socket socket;
BufferedReader br;
PrintWriter out;
//Constructor
public client(){
try{
System.out.println("sending request to server");
socket=new Socket("127.0.0.1",7777);
System.out.println("Connection Done");
br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
out= new PrintWriter(socket.getOutputStream());
startReading();
startwriting();
}catch(Exception e){
System.out.println(e);
}
}
//Reading [Method]
public void startReading(){
Runnable r1=()->{
System.out.println("reader start...");
try{
while(true){
String msg=br.readLine();
if(msg.equals("exit")){
System.out.println("Server is Terminated chat");
socket.close();
break;
}
System.out.println("Server:"+msg);
}
}catch(Exception e){
System.out.println(e);
}
};
new Thread(r1).start();
}
//Writing [Method]
public void startwriting(){
System.out.println("Writer started...");
Runnable r2= () ->{
try{
while(!socket.isClosed()){
BufferedReader br1=new BufferedReader(new
InputStreamReader(System.in));
String content=br1.readLine();
out.println(content);
out.flush();
if(content.equals("exit")){
socket.close();
break;
}
}
}catch(Exception e){
System.out.println(e);
}
};
new Thread(r2).start();
}
public static void main(String gg[]){
System.out.println("client is active...");
new client();
}
}