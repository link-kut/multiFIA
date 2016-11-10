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
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body>
<div class="container">
    <div align="center">
        <h3>동영상 컨텐츠 제공 (서버)</h3>
        <div class="btn-group" data-toggle="buttons" id="contents" width="800" style="display: inline">
            <div class="col-md-6">
                <a href="movie?title=Spiderman.mp4"><img src="../resources/images/Spiderman.jpeg" alt="" width="186" height="269" /></a>
                <p style="margin-top: 2.0em">ContentName: multifia/Spiderman.mp4</p>
                <p>ORCHID: 5BEE0F5007E6915B32B4EF55</p>
                <p>Scheme: rtp</p>
                <label class="btn btn-warning btn-lg">
                    <input type="radio" name="options" id="spiderman" autocomplete="off" style="margin-left: -2.0em">Registration
                </label>
            </div>
            <div class="col-md-6">
                <a href="movie?title=Darkknight.mp4"><img src="../resources/images/darkknight.jpg" alt="" width="186" height="269" /></a>
                <p style="margin-top: 2.0em">ContentName: multifia/Batman.mp4</p>
                <p>ORCHID: 4754D4627C1E8A7BDFB1F67E</p>
                <p>Scheme: rtp</p>
                <label class="btn btn-warning btn-lg">
                    <input type="radio" name="options" id="batman" autocomplete="off" style="margin-left: -2.0em">Registration
                </label>
            </div>
            <script>
                jQuery(document).ready(function() {
                    $("#contents :input").change(function() {
                        var regit = this.id;
                        console.log(regit); // points to the clicked input button
                        if(regit == "spiderman") {
                            $.ajax({
                                url: "/registrar/registration",
                                data: {
                                    contextId: '292D05A61D8C335FA3411EBB5BAABE77',
                                    name: 'multifia/Spiderman.mp4',
                                    orchid: '5BEE0F5007E6915B32B4EF55',
                                    locator: '10.1.5.3:8100',
                                    scheme: 'rtp'
                                }
                            });
                            alert("해당 컨텐츠가 등록 되었습니다");
                        } else if(regit == "batman") {
                            $.ajax({
                                url: "http://localhost:8100/registrar/registration",
                                data: {
                                    contextId: '292D05A61D8C335FA3411EBB5BAABE77',
                                    name: 'multifia/Batman.mp4',
                                    orchid: '4754D4627C1E8A7BDFB1F67E',
                                    locator: '10.1.5.3:8100',
                                    scheme: 'rtp'
                                }
                            });
                            alert("해당 컨텐츠가 등록 되었습니다");
                        }
                        $.ajax({
                            url: "/registrar/registration",
                            data: {
                                contextId: '1AF52BA93BA24026CAF34D783DC12A09',
                                name: '10.1.5.3',
                                orchid: ${client_orchid},
                                locator: '10.1.5.3',
                                scheme: '*'
                            }
                        });
                    });
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>