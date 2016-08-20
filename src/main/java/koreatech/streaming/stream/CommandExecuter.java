package koreatech.streaming.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yhhan on 2016. 8. 21..
 */
public class CommandExecuter extends Thread {
    private String command;
    private Process process;

    public CommandExecuter(String name, String targetAddress, String targetPort) {
        StringBuffer sb = new StringBuffer();
        sb.append("/Users/yhhan/git/multiFIA/run2.server.sh");
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(targetAddress);
        sb.append(" ");
        sb.append(targetPort);
        this.command = sb.toString();
    }

    public void run() {
        try {
            Process p = Runtime.getRuntime().exec(command);
            this.process = p;
            copy(p.getInputStream(), System.out);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }

    public Process getProcess() {
        return this.process;
    }
}
