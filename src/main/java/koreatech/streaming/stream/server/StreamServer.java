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
import uk.co.caprica.vlcj.binding.LibC;

/**
 * An example of how to stream a media file over HTTP.
 * <p>
 * The client specifies an MRL of <code>http://127.0.0.1:5555</code>
 */
public class StreamServer extends VlcjCommon {
    public static String fileSeparator = System.getProperty("file.separator");
    public static String mediaFolder = fileSeparator + "usr" + fileSeparator + "local" + fileSeparator + "share";

    public static void main(String[] args) throws Exception {
        LibC.INSTANCE.setenv("VLC_PLUGIN_PATH", "/Applications/VLC.app/Contents/MacOS/plugins", 1);
        if(args.length != 4) {
            System.out.println("Specify a single MRL to stream");
            System.exit(1);
        }

        StreamThread streamHttpThread = new StreamThread(mediaFolder, fileSeparator, args[0], args[1], args[2], args[3]);
        streamHttpThread.start();
        Thread.currentThread().join();
    }
}
