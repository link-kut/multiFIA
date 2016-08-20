package koreatech.streaming.stream;

import koreatech.streaming.service.OrchidService;
import koreatech.streaming.service.VlcService;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

public class StreamHttpThread extends Thread {
    private String[] args;
    private String mediaFolder;
    private String fileSeparator;
    private OrchidService orchidService = new OrchidService();
    private VlcService vlcService = new VlcService();
    private static MediaPlayerFactory mediaPlayerFactory = null;

    public StreamHttpThread(String[] args, String mediaFolder, String fileSeparator) {
        this.args = args;
        this.mediaFolder = mediaFolder;
        this.fileSeparator = fileSeparator;
        mediaPlayerFactory = new MediaPlayerFactory(args);
        //System.setProperty("jna.library.path", "/Applications/VLC.app/Contents/MacOS/lib");

        //LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", "/Applications/VLC.app/Contents/MacOS/plugins", 1);
        //NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib");
        //Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }

    public void run() {
        try {
            String media = mediaFolder + fileSeparator + args[0];
            String options = vlcService.formatHttpStream(args[1], Integer.parseInt(args[2]));

            System.out.println("***************************");
            System.out.println("ID: " + orchidService.getOrchidContentName(args[0]));
            System.out.println("Streaming '" + media + "' to '" + options + "'");

            HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
            mediaPlayer.playMedia(media, options);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
