import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class client{
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        Scanner sc=new Scanner(System.in);

        String messaggio=sc.nextLine();
        byte[] buffer= messaggio.getBytes();

        DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
        packet.setAddress(InetAddress.getByName("127.0.0.1"));
        packet.setPort(12345);
        socket.send(packet);
        byte[] b= new byte[1500];

        DatagramPacket p=new DatagramPacket(b, b.length);
        socket.receive(p);
        String risp = new String(p.getData(), 0, p.getLength());
        System.out.println(risp);
        sc.close();
    }
}