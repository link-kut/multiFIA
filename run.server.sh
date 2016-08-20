#!/usr/bin/env bash
(cd /Users/yhhan/git/multiFIA; mvn exec:java -Dexec.mainClass=koreatech.link.StreamRtp -Djna.library.path=/Applications/VLC.app/Contents/MacOS/lib -Dexec.args="$1 $2 $3")
