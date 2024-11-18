import java.net.*;
import java.io.*;
class server{
ServerSocket server;
Socket socket;
BufferedReader br;
PrintWriter out;
public server(){
try{
server=new ServerSocket(7777);
System.out.println("Server ready to accept at port 7777");
System.out.println("waiting...");
socket=server.accept();
br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
out= new PrintWriter(socket.getOutputStream());
startReading();
startwriting();
}catch(Exception e){
System.out.println(e);
}
}
public void startReading(){
Runnable r1=()->{
System.out.println("reader start...");
try{
while(true){
String msg=br.readLine();
if(msg.equals("exit")){
System.out.println("client is Terminated chat");
socket.close();
break;
}
System.out.println("client:"+msg);
}
}catch(Exception e){
System.out.println(e);
}
};
new Thread(r1).start();
}
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
System.out.println("Server is going to start...");
new server();
}
}