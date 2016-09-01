package koreatech.streaming.stream.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by asif on 9/1/2016.
 */
public class SimpleExecuter {
    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("java -Djna.library.path=C:\\Users\\asif\\VLC -jar C:\\Users\\asif\\multiFIA\\out\\artifacts\\multifia_jar\\multifia.jar");
        sb.append(" ");
        sb.append("Spiderman.mp4");
        sb.append(" ");
        sb.append("rtp");
        sb.append(" ");
        sb.append("127.0.0.1");
        sb.append(" ");
        sb.append("5555");
        //String command = sb.toString();

        //command = "cmd.exe";
        String[] command = { "cmd", "/C", "java -Djna.library.path=C:\\Users\\asif\\VLC -jar C:\\Users\\asif\\multiFIA\\out\\artifacts\\multifia_jar\\multifia.jar Spiderman.mp4 rtp 127.0.0.1 5555" };
        Process p = Runtime.getRuntime().exec(command);
        copy(p.getInputStream(), System.out);
        copy(p.getErrorStream(), System.out);
        System.out.println("Execution: " + command);
        int i = p.waitFor();
        System.out.println("is still ---- " + i);
    }

    static public void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }
}
