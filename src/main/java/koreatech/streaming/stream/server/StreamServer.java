/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2016 Caprica Software Limited.
 */

package koreatech.streaming.stream.server;

import koreatech.streaming.stream.common.VlcjCommon;

/**
 * An example of how to stream a media file over HTTP.
 * <p>
 * The client specifies an MRL of <code>http://127.0.0.1:5555</code>
 */
public class StreamServer extends VlcjCommon {
    public static String fileSeparator = System.getProperty("file.separator");
    public static String mediaFolder = "C:\\Users\\asif";


    public static void main(String[] args) throws Exception {
        //LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", "C:\\Program Files\\VideoLAN\\VLC\\plugins", 1);
        if(args.length != 4) {
            System.out.println("Specify a single MRL to stream");
            System.exit(1);
        }

        StreamPlayer streamHttpThread = new StreamPlayer(mediaFolder, fileSeparator, args[0], args[1], args[2], args[3]);
        //streamHttpThread.start();
        Thread.currentThread().join();
    }

    /*
    public static void main(String[] args) throws Exception {
        if(args.length != 4) {
            System.out.println("Specify a single MRL to stream");
            System.exit(1);
        }

        for(int i=0; i<args.length; i++) {
            System.out.println(args[i]);
        }
        OrchidService orchidService = new OrchidService();
        VlcService vlcService = new VlcService();

        String contentName = args[0];
        String protocol = args[1];
        String targetAddress = args[2];
        String targetPort = args[3];

        String media = mediaFolder + "\\" + contentName;
        String options = null;
        String[] libvlcArgs = new String[3];
        libvlcArgs[0] = contentName;
        libvlcArgs[1] = targetAddress;
        libvlcArgs[2] = targetPort;

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(libvlcArgs);
        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();

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
            System.out.println("***************************");
            System.out.println("ContentName: " + contentName + "(ID: " + orchidService.getOrchidContentName(contentName) + ")");
            System.out.println("Protocol: " + protocol);
            System.out.println("Streaming '" + media + "' to '" + options + "'");
        }

        while(true);
    }
    */
}