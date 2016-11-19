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
    private String quality;
    private String mediaFolder;
    private String fileSeparator;
    private OrchidService orchidService = new OrchidService();
    private VlcService vlcService = new VlcService();
    private MediaPlayerFactory mediaPlayerFactory = null;
    private HeadlessMediaPlayer mediaPlayer = null;

    public StreamPlayer(String mediaFolder, String fileSeparator, String contentName, String protocol, String targetAddress, String targetPort, String quality) {
        this.mediaFolder = mediaFolder;
        this.fileSeparator = fileSeparator;
        this.contentName = contentName;
        this.protocol = protocol;
        this.targetAddress = targetAddress;
        this.targetPort = targetPort;
        this.quality = quality;

        String[] libvlcArgs = new String[3];
        libvlcArgs[0] = contentName;
        libvlcArgs[1] = targetAddress;
        libvlcArgs[2] = targetPort;
        mediaPlayerFactory = new MediaPlayerFactory(libvlcArgs);
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
    }

    public void play() {
        try {
            String media = mediaFolder + fileSeparator + contentName;
            String options = null;
            if (protocol.equals("http")) {
                options = vlcService.formatHttpStream(targetAddress, Integer.parseInt(targetPort), quality);
                mediaPlayer.playMedia(media, options);
            } else if (protocol.equals("rtp")) {
                options = vlcService.formatRtpStream(targetAddress, Integer.parseInt(targetPort), quality);
                mediaPlayer.playMedia(media,
                        options,
                        ":no-sout-rtp-sap",
                        ":no-sout-standard-sap",
                        ":sout-all",
                        ":sout-keep"
                );
            }

            if (options != null) {
                System.out.println("--- Streaming '" + media + "' to '" + options + "'");
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

    public void pausePlay() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resumeplay() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.play();
        }
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(String targetPort) {
        this.targetPort = targetPort;
    }

    public String getMediaFolder() {
        return mediaFolder;
    }

    public void setMediaFolder(String mediaFolder) {
        this.mediaFolder = mediaFolder;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public void setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
    }

    public OrchidService getOrchidService() {
        return orchidService;
    }

    public void setOrchidService(OrchidService orchidService) {
        this.orchidService = orchidService;
    }

    public VlcService getVlcService() {
        return vlcService;
    }

    public void setVlcService(VlcService vlcService) {
        this.vlcService = vlcService;
    }

    public MediaPlayerFactory getMediaPlayerFactory() {
        return mediaPlayerFactory;
    }

    public void setMediaPlayerFactory(MediaPlayerFactory mediaPlayerFactory) {
        this.mediaPlayerFactory = mediaPlayerFactory;
    }

    public HeadlessMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(HeadlessMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
