package koreatech.transferring.client;


import koreatech.streaming.service.OrchidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TransferClient extends Thread {
    public final static String FILE_PATH = "/usr/local/share";
    private ServerSocket serverSocket;
    private static OrchidService orchidService = new OrchidService();
    public static final String REST_REGISTRAR_URI = "http://localhost:8080/registrar";
    private static TransferClientRestController transferClientRestController = new TransferClientRestController();

    private Socket socket = null;

    public TransferClient(Socket so) {
        this.socket = so;
    }

    public void run() {

        FileOutputStream fos = null;
        InputStream is = null;
        BufferedOutputStream bos = null;
        try {
            //수신파일의 경로 및 이름 : F:/development/test/test_server.zip
            File file = new File("/Users/Kyo/test.mp4");
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024*4];

            is = socket.getInputStream();

            int result = 0;

            //read bytes from inputstream and stores them into buffer array b
            //and return total # of bytes read into buffer or -1 if there's no more data
            bos = new BufferedOutputStream(fos);
            while((result=is.read(b))>0) {
                bos.write(b, 0, result); //byte[] b 의 0 위치에서 부터 result 길이만큼 write한다.
            }

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String hostName = "Jack's Desktop";

        JFrame frame = new JFrame("FTP File Transfer");

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        JButton connectButton = new JButton("Connect");
        JButton downloadButton = new JButton("Download");

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transferClientRestController.startTrnasfer(hostName);
                } catch (Exception x) {}

            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Connect to " + hostName);
            }
        });

        panel.add(connectButton);
        panel.add(downloadButton);
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        Socket so = null;

        try {

            ServerSocket serverSocket = new ServerSocket(6666);

            do {
                so = serverSocket.accept(); //listen for connection, blocks until connection is made
                new TransferClient(so).start();
            } while(true);

        } catch(IOException i) {
            i.printStackTrace();
        } finally {
            try {
                so.close();
            } catch (IOException o){
                o.printStackTrace();
            }
        }



    }

}
