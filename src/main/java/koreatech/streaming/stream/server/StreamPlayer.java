package koreatech.streaming.stream.server;

import koreatech.streaming.service.OrchidService;
import koreatech.streaming.service.VlcService;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

public class StreamPlayer {
    private String contentName;
    private String protocol;
    private String targetAddress;
    private String targetPort;
    private String mediaFolder;
    private String fileSeparator;
    private OrchidService orchidService = new OrchidService();
    private VlcService vlcService = new VlcService();
    private MediaPlayerFactory mediaPlayerFactory = null;
    private HeadlessMediaPlayer mediaPlayer = null;

    public StreamPlayer(String mediaFolder, String fileSeparator, String contentName, String protocol, String targetAddress, String targetPort) {
        this.mediaFolder = mediaFolder;
        this.fileSeparator = fileSeparator;
        this.contentName = contentName;
        this.protocol = protocol;
        this.targetAddress = targetAddress;
        this.targetPort = targetPort;

        String[] libvlcArgs = new String[3];
        libvlcArgs[0] = contentName;
        libvlcArgs[1] = targetAddress;
        libvlcArgs[2] = targetPort;
        mediaPlayerFactory = new MediaPlayerFactory(libvlcArgs);
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        //System.setProperty("jna.library.path", "/Applications/VLC.app/Contents/MacOS/lib");
        //LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", "/Applications/VLC.app/Contents/MacOS/plugins", 1);
        //NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib");
        //Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }

    public void play() {
        try {
            String media = mediaFolder + "\\" + contentName;
            String options = null;
            if (protocol.equals("http")) {
                options = vlcService.formatHttpStream(targetAddress, Integer.parseInt(targetPort));
                mediaPlayer.playMedia(media, options);
            } else if (protocol.equals("rtp")) {
                options = vlcService.formatRtpStream(targetAddress, Integer.parseInt(targetPort));
                mediaPlayer.playMedia(media,
                        options,
                        ":no-sout-rtp-sap",
                        ":no-sout-standard-sap",
                        ":sout-all",
                        ":sout-keep"
                );
            }

            if (options != null) {
                System.out.println("ContentName: " + contentName + "(ID: " + orchidService.getOrchidContentName(contentName) + ")");
                System.out.println("Protocol: " + protocol);
                System.out.println("Streaming '" + media + "' to '" + options + "'");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPlay() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
