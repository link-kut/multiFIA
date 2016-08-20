package koreatech.streaming.httpStream;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import koreatech.streaming.service.OrchidService;
import koreatech.streaming.service.VlcService;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class StreamHttpThread extends Thread {
    private String[] args;
    private String mediaFolder;
    private String fileSeparator;
    private OrchidService orchidService = new OrchidService();
    private VlcService vlcService = new VlcService();

    public StreamHttpThread(String[] args, String mediaFolder, String fileSeparator) {
        this.args = args;
        this.mediaFolder = mediaFolder;
        this.fileSeparator = fileSeparator;
    }

    public void run() {
        try {
            String media = mediaFolder + fileSeparator + args[0];
            String options = vlcService.formatHttpStream(args[1], Integer.parseInt(args[2]));

            System.out.println("Streaming '" + media + "' to '" + options + "'");

            //NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib");

            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
            HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
            mediaPlayer.playMedia(media, options);

            System.out.println("***************************");
            System.out.println("ID: " + orchidService.getOrchidContentName(args[0]));
            System.out.println(args.length);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
