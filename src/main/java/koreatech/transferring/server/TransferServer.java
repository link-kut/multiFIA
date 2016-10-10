package koreatech.transferring.server;

import koreatech.transferring.client.TransferClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer {
    public final static String FILE_PATH = "/usr/local/share/";
    private String locator;
    private int port;

    public TransferServer(String locator, int port) {
        this.locator = locator;
        this.port = port;
    }

    public void run() {
        OutputStream os = null;
        FileInputStream fis = null;
        try {

            Socket socket = new Socket(locator, port);
            os = socket.getOutputStream();

            //전송파일의 경로 및 이름 : F:/development/test/test_client.zip
            fis = new FileInputStream(new File(FILE_PATH + "Spiderman.mp4"));

            byte[] b = new byte[1024*4];
            int result = 0;

            while((result=fis.read(b))>0) {
                os.write(b, 0, result);
            }

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
}
