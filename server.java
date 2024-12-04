import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class server{
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(12345);
        String s="";

        while(true){
            byte[] buffer= new byte[1500];

            DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String messaggio = new String(packet.getData(), 0, packet.getLength());
            String[] comando=messaggio.split(";");
            String risp;
            byte[] b;

            if(comando[0].equals("get")){
                if(!s.equals("")){
                    risp="1;"+s;
                    
                }
                else{
                    risp="0;";
                }
            }
            else if(comando[0].equals("set")){
                s=comando[1];
                risp="ok";   
            }
            else{
                risp="err";
            }
            b=risp.getBytes();
            DatagramPacket p=new DatagramPacket(b, b.length);
            p.setAddress(packet.getAddress());
            p.setPort(packet.getPort());
            socket.send(p);
        }
    }
}