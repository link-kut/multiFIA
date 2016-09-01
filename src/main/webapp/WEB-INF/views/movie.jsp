<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <script type="text/javascript">

        function vidplay() {
            var video = document.getElementById("Video1");
            var button = document.getElementById("play");
            if (video.paused) {
                video.play();
                button.textContent = "||";
            } else {
                video.pause();
                button.textContent = ">";
            }
        }

        function restart() {
            var video = document.getElementById("Video1");
            video.currentTime = 0;
        }

        function skip(value) {
            var video = document.getElementById("Video1");
            video.currentTime += value;
        }
    </script>
</head>
<body>
<!--<video src="../resources/video/Spiderman.mp4" /><br/>-->
<!--<video src="/resources/video/darkknight.mov" /><br/>-->
<p>ORCHIDv2 Identifier: ${id}</p>
<p>Content Name: ${content}</p>

<video id="Video1" autoplay>
    //  Replace these with your own video files.
    <c:choose>
        <c:when test="${content == 'Spiderman.mp4'}">
            <source src="../resources/videos/Spiderman.mp4" type="video/mp4" />
        </c:when>
        <c:when test="${content == 'Darkknight.mp4'}">
            <source src="../resources/videos/Darkknight.mp4" type="video/mp4" />
        </c:when>
        <c:when test="${content == 'Punisher.mp4'}">
            <source src="../resources/videos/Punisher.mp4" type="video/mp4" />
        </c:when>
    </c:choose>
    HTML5 Video is required for this example.
    <a href="demo.mp4">Download the video</a> file.
</video>

<div id="buttonbar">
    <button id="restart" onclick="restart();">[]</button>
    <button id="rew" onclick="skip(-10)">&lt;&lt;</button>
    <button id="play" onclick="vidplay()">&gt;</button>
    <button id="fastFwd" onclick="skip(10)">&gt;&gt;</button>
</div>

</body>
</html>