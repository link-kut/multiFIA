<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--[if lte IE 8]><script src="../resources/assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="../resources/assets/css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="../resources/assets/css/ie8.css" /><![endif]-->
    <!--[if lte IE 9]><link rel="stylesheet" href="../resources/assets/css/ie9.css" /><![endif]-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="../resources/assets/js/jquery.min.js"></script>
    <script src="../resources/assets/js/jquery.scrolly.min.js"></script>
    <script src="../resources/assets/js/skel.min.js"></script>
    <script src="../resources/assets/js/util.js"></script>
    <!--[if lte IE 8]><script src="../resources/assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="../resources/assets/js/main.js"></script>

    <link href="http://vjs.zencdn.net/5.8.8/video-js.css" rel="stylesheet">

    <!-- If you'd like to support IE8 -->
    <script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body>
<div class="container">
    <div align="center">
        <h3>VoD 서비스 이용</h3>
        <div style="margin-top: 2.0em" class="btn-group" data-toggle="buttons" id="stream">
            <video id="myVideo" class="video-js" controls>
                <source src="http://127.0.0.1:5555" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'>
                <source src="http://localhost:8100/resources/videos/Spiderman.mp4" type='video/mp4; codecs="avc1.42E01E, mp4a.40.2"'>

                <p class="vjs-no-js">
                    To view this video please enable JavaScript, and consider upgrading to a web browser that
                    <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
                </p>
            </video>
            </br>
            <button type="button" onclick="vid_play_pause()">Play/Pause</button>
            <script>
                function vid_play_pause() {
                    var myVideo = document.getElementById("myVideo");
                    if (myVideo.paused) {
                        myVideo.play();
                    } else {
                        myVideo.pause();
                    }
                }
            </script>
        </div>
        <c:choose>
            <c:when test="${latest_quality == 'high'}">
                <div data-toggle="buttons" id="streaming">
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="high_start" autocomplete="off" checked> Start
                    </label>
                    &nbsp;
                    <label class="btn btn-danger">
                        <input type="radio" name="options" id="high_stop" autocomplete="off"> Stop
                    </label>
                    &nbsp;
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="high_160" autocomplete="off"> 160p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="high_640" autocomplete="off"> 640p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="high_1280" autocomplete="off"> 1280p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="high_2560" autocomplete="off"> 2560p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="high_3600" autocomplete="off"> 3600p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="high_4900" autocomplete="off"> 4900p
                    </label>
                    <script>
                        jQuery(document).ready(function() {
                            $("#streaming :input").change(function() {
                                var play = this.id;
                                console.log(play); // points to the clicked input button
                                if(play == "high_start") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '640',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                } else if(play == "high_stop") {
                                    $.ajax({
                                        url: "/streaming/stop/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            address: '127.0.0.1',
                                            port: 5555
                                        }
                                    });
                                } else if(play == "high_160") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '160',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                } else if(play == "high_640") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '640',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }else if(play == "high_1280") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '1280',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }else if(play == "high_2560") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '2560',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }else if(play == "high_3600") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '3600',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }else if(play == "high_4900") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '4900',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }
                            });
                        });
                    </script>
                </div>
            </c:when>
            <c:when test="${latest_quality == 'medium'}">
                <div data-toggle="buttons" id="streaming">
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="medium_start" autocomplete="off" checked> Start
                    </label>
                    &nbsp;
                    <label class="btn btn-danger">
                        <input type="radio" name="options" id="medium_stop" autocomplete="off"> Stop
                    </label>
                    &nbsp;
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="medium_160" autocomplete="off"> 160p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="medium_640" autocomplete="off"> 640p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="medium_1280" autocomplete="off"> 1280p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="medium_2560" autocomplete="off"> 2560p
                    </label>
                    <script>
                        jQuery(document).ready(function() {
                            $("#streaming :input").change(function() {
                                var play = this.id;
                                console.log(play); // points to the clicked input button
                                if(play == "medium_start") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '640',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                } else if(play == "medium_stop") {
                                    $.ajax({
                                        url: "/streaming/stop/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            address: '127.0.0.1',
                                            port: 5555
                                        }
                                    });
                                } else if(play == "medium_1280") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '1280',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                } else if(play == "medium_2560") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '2560',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }
                            });
                        });
                    </script>
                </div>
            </c:when>
            <c:otherwise>
                <div data-toggle="buttons" id="streaming">
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="low_start" autocomplete="off" checked> Start
                    </label>
                    &nbsp;
                    <label class="btn btn-danger">
                        <input type="radio" name="options" id="low_stop" autocomplete="off"> Stop
                    </label>
                    &nbsp;
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="low_160" autocomplete="off"> 160p
                    </label>
                    <label class="btn btn-success">
                        <input type="radio" name="options" id="low_640" autocomplete="off"> 640p
                    </label>
                    <script>
                        jQuery(document).ready(function() {
                            $("#streaming :input").change(function() {
                                var play = this.id;
                                console.log(play); // points to the clicked input button
                                if(play == "low_start") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '640',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                } else if(play == "low_stop") {
                                    $.ajax({
                                        url: "/streaming/stop/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            address: '127.0.0.1',
                                            port: 5555
                                        }
                                    });
                                } else if(play == "low_160") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '160',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                } else if(play == "low_640") {
                                    $.ajax({
                                        url: "/streaming/start/5BEE0F5007E6915B32B4EF55",
                                        data: {
                                            quality: '640',
                                            targetAddress: '127.0.0.1',
                                            protocol: 'http',
                                            targetPort: 5555
                                        }
                                    });
                                }
                            });
                        });
                    </script>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>
</html>