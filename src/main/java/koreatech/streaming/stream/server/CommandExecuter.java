package koreatech.streaming.stream.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yhhan on 2016. 8. 21..
 */
public class CommandExecuter extends Thread {
    private String command;
    private Process process;

    public CommandExecuter(String contentName, String protocol, String targetAddress, String targetPort) {
        StringBuffer sb = new StringBuffer();

        sb.append("java -Djna.library.path=/Applications/VLC.app/Contents/MacOS/lib -jar /Users/yhhan/git/multiFIA/out/artifacts/wsc_jar/wsc.jar");
        sb.append(" ");
        sb.append(contentName);
        sb.append(" ");
        sb.append(protocol);
        sb.append(" ");
        sb.append(targetAddress);
        sb.append(" ");
        sb.append(targetPort);
        this.command = sb.toString();
    }

    public void run() {
        try {
            System.out.println("Execution: " + command);
            Process p = Runtime.getRuntime().exec(command);
            this.process = p;
            copy(p.getInputStream(), System.out);
            System.out.println(process.toString() + " has started!");
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }

    public void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }

    public void killCurrentProcess() {
        if (process != null && process.isAlive()) {
            process.destroy();
            System.out.println(process.toString() + " has beep destoyed!");
        }
    }

    public Process getProcess() {
        return this.process;
    }
}
