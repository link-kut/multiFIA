#!/usr/bin/env bash
(cd /Users/yhhan/git/multiFIA; mvn exec:java -Dexec.mainClass=uk.co.caprica.vlcj.test.basic.TestPlayer -Djna.library.path=/Applications/VLC.app/Contents/MacOS/lib)