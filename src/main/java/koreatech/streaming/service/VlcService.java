package koreatech.streaming.service;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class VlcService {
    public String formatRtpStream(String targetAddress, int targetPort, String quality) {
        StringBuilder sb = new StringBuilder(60);
        if(quality.equals("4096")) {
            sb.append(":sout=#transcode{vcodec=h264,acodec=mp4a,vb=4096,ab=128,channels=2,samplerate=44100}:duplicate{dst=rtp{dst=");
            sb.append(targetAddress);
            sb.append(",port=");
            sb.append(targetPort);
            sb.append(",mux=ts}");
            return sb.toString();
        }
        else if(quality.equals("16")) {
            sb.append(":sout=#transcode{vcodec=h264,acodec=mp4a,vb=16,ab=128,channels=2,samplerate=44100}:duplicate{dst=rtp{dst=");
            sb.append(targetAddress);
            sb.append(",port=");
            sb.append(targetPort);
            sb.append(",mux=ts}");
            return sb.toString();
        }
        else{
            sb.append(":sout=#transcode{vcodec=h264,acodec=mp4a,vb=1600,ab=128,channels=2,samplerate=44100}:duplicate{dst=rtp{dst=");
            sb.append(targetAddress);
            sb.append(",port=");
            sb.append(targetPort);
            sb.append(",mux=ts}");
            return sb.toString();
        }
    }

    public String formatHttpStream(String targetAddress, int targetPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
        sb.append("dst=");
        sb.append(targetAddress);
        sb.append(':');
        sb.append(targetPort);
        sb.append("}}");
        return sb.toString();
    }
}
