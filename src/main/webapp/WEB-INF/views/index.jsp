<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Kyo
  Date: 2016. 6. 24.
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<!--
Photon by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>IITP-multiFIA</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <script src="../resources/assets/js/jquery.min.js"></script>
    <script src="../resources/assets/js/jquery.scrolly.min.js"></script>
    <!--[if lte IE 8]><script src="../resources/assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="../resources/assets/css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="../resources/assets/css/ie8.css" /><![endif]-->
    <!--[if lte IE 9]><link rel="stylesheet" href="../resources/assets/css/ie9.css" /><![endif]-->
    <script src="../resources/assets/js/skel.min.js"></script>
    <script src="../resources/assets/js/util.js"></script>
    <!--[if lte IE 8]><script src="../resources/assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="../resources/assets/js/main.js"></script>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body>

<section id="headerBar">
    <div class="inner">
        <%@ include file="headerBar.jsp" %>
    </div>
</section>

<!-- Header -->
<section id="header">
    <div class="inner">
        <span class="icon major fa-cloud"></span>
        <h1><strong>IITP-multiFIA</strong></h1>
        <h3><strong>Multi-dimensional Future Network System Architecture for Diversity of Services, Terminals and Networks</strong></h3>
        <p>네트워크 QoS 자원 매칭을 고려한 유형별 네트워킹 개체 ID 구조 및 확장성 있는 ID 디렉토리 서비스 개발<br />
            다양한 응용/서비스를 위한 추상화 및 QoS 표현기법 연구 및 구현<br />
            응용/서비스와 단말/네트워크 최적매칭을 위한 Orchestrating Control<br />
            응용/서비스에 특화된 인프라 제공을 위한 Software-Defined Infra 및 테스트베드 구축<br />
        </p>
        <ul class="actions">
            <sec:authorize access="isAnonymous()">
                <li><a href="#one" class="button scrolly">프로젝트 설명</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="#serviceCreation" class="button scrolly">서비스 생성</a></li>
            </sec:authorize>
        </ul>
    </div>
</section>

<!-- One -->
<section id="one" class="main style1">
    <div class="container">
        <div class="row 150%">
            <div class="6u 18u$(medium)">
                <header class="major">
                    <h3>다양한 네트워킹 개체 ID 처리</h3>
                </header>
                <p>
                    다양한 응용/서비스등을 처리하기 위한 여러가지 네트워킹 ID 유형 지원과 확장 가능한(Scalable) 네트워킹 개체 ID 검색 기법.
                    네트워크 QoS 자원 매칭을 고려한 유형별 네트워킹 개체 ID 구조 및 활용 연구를 통하여 확장성 있는 ID 디렉토리 서비스 개발
                </p>
                </br></br>
                <header class="major">
                    <h3>통합화된 QoS 추상화 표현</h3>
                </header>
                <p>
                    다양한 ID 유형 기반의 여러가지 응용/서비스 요구사항을 처리하기 위하여 통합되고 추상화된 표현기법과 응용/서비스 주도형 요구사항 표현
                    기법을 연구 및 구현
                </p>
                </br></br></br>
                <header class="major">
                    <h3>다양성 지원 네트워크 API 사용성</h3>
                </header>
                <a href="/kistiAPI" class="btn btn-warning" role="button" aria-pressed="true">API Information</a>
            </div>
            <div class="6u 18u$(medium)">
                <header class="major">
                    <h3>서비스별 최적화 Orchestration</h3>
                </header>
                <p>
                    응용/서비스별 최적화된 네트워크 및 클라우드 자원 할당을 처리하기 위한 최적의 Orchestration 기법과 엣지컴퓨팅 등을 이용한
                    유연하고 확장성 있는 최적화 처리 기법을 연구 및 구현
                </p>
                </br></br>
                <header class="major">
                    <h3>프로그래머블 인프라 기반 End-to-End Software-Defined Control 방식</h3>
                </header>
                <p>
                    Software-Defined Orchestration Control이 가능한 단말/네트워크/클라우드의 통합 자원 표현 기법, 소프트웨어 기반의
                    제어를 위한 API 기반의 프로그래머블 인프라 구현
                </p>
                </br></br>
                <header class="major">
                    <h3>Mapping Registrar 및 REST 기반 Mapping API</h3>
                </header>
                <a href="/koreatechAPI" class="btn btn-warning" role="button" aria-pressed="true">API Information</a>
            </div>
            <!--
            <div class="6u$ 12u$(medium) important(medium)">
                <span class="image fit"><img src="../resources/images/structure.png" alt="" /></span>
            </div>
            -->
        </div>
    </div>
</section>

<!--공개 소프트웨어-->
<section id="two" class="main style2 special">
    <div class="container">
        <header class="major">
            <h2>multiFIA NRC/CSRC 개발 SW 공개</h2>
        </header>
        <ul class="actions uniform">
            <li>
                <a href=" https://github.com/syedasifraza/project-app" role="button" class="btn btn-lg btn-default" aria-pressed="true">
                <span class="glyphicon glyphicon-link"></span> LINK
                </a>
            </li>
        </ul>
    </div>
</section>


<!-- ServiceCreation -->
<sec:authorize access="isAuthenticated()">
    <section id="serviceCreation" class="main style2">
        <div class="container">
            <header class="major special">
                <h2>네트워크 서비스 생성</h2>
            </header>
            <script>
                var a = new Array();
                //a[0] = 0;
                a[0] = ${maxId + 1};
            </script>

            <ul class="tab">
                <li><a href="#type" class="tablinks" onclick="openCity(event, 'type')" id="defaultOpen">Service Type</a></li>
                <li><a href="#quality" class="tablinks" onclick="openCity(event, 'quality')">Quality</a></li>
                <li><a href="#plan" class="tablinks" onclick="openCity(event, 'plan')">Plan</a></li>
            </ul>

            <div id="type" class="tabcontent">
                <h3>서비스 유형 (네트워크에서 제공하려는 서비스 유형을 선택하세요.)</h3>
                <div class="btn-group" data-toggle="buttons" id="serviceType">
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="vod" autocomplete="off" checked> Video on Demand
                    </label>
                    &nbsp;
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="ft" autocomplete="off"> File Transfer
                    </label>
                </div>
                <br/><br/>
                <div align="right">
                    <button href="#quality" class="tablinks" onclick="openCity(event, 'quality')">Next</button>
                </div>

                <script>
                    jQuery(document).ready(function() {
                        $("#serviceType :input").change(function() {
                            var serviceType = this.id;
                            a[1] = serviceType;
                            console.log(a[1]); // points to the clicked input button
                        });
                    });
                </script>
            </div>

            <div id="quality" class="tabcontent">
                <h3>네트워크 품질 (제공할 네트워크 서비스의 품질을 선택하세요.)</h3>
                <div class="btn-group" data-toggle="buttons" id="qualityType">
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="high" autocomplete="off" checked> High
                    </label>
                    &nbsp;
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="medium" autocomplete="off"> Medium
                    </label>
                    &nbsp;
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="low" autocomplete="off"> Low
                    </label>
                </div>
                <br/>
                <br/>
                <div data-role="main" class="ui-content row">
                    <div class="col-md-6 align-left">
                        <button href="#type" class="tablinks" onclick="openCity(event, 'type')">Prev</button>
                    </div>
                    <div class="col-md-6 align-right">
                        <button href="#region" class="tablinks" onclick="openCity(event, 'plan')">Next</button>
                    </div>
                </div>
                <script>
                    jQuery(document).ready(function() {
                        $("#qualityType :input").change(function() {
                            var qualityType = this.id;
                            a[2] = qualityType;
                            console.log(a[2]); // points to the clicked input button
                        });
                    });
                </script>
            </div>

            <!--
            <div id="region" class="tabcontent">
                <h3>서비스 제공 지역 (서비스를 제공할 지역을 선택하세요.)</h3>
                <div class="btn-group" data-toggle="buttons" id="capacityType">
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="100" autocomplete="off" checked> 100
                    </label>
                    &nbsp;
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="500" autocomplete="off"> 500
                    </label>
                    &nbsp;
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="1000" autocomplete="off"> 1000
                    </label>
                    <label class="btn btn-success btn-lg">
                        <input type="radio" name="options" id="1111" autocomplete="off"> Over 1000
                    </label>
                </div>
                <div>
                    <iframe
                            width="600"
                            height="450"
                            frameborder="0" style="border:0"
                            src="https://www.google.com/maps/embed/v1/place?key=AIzaSyD_nPIsxP-46Qpm5FeDagCmTyq4PPAdQXo
                                &q=Space+Needle,Seattle+WA" allowfullscreen>
                    </iframe>
                </div>

                <br/><br/>
                <div data-role="main" class="ui-content row" align="right">
                    <div class="col-md-6 align-left">
                        <button href="#quality" class="tablinks" onclick="openCity(event, 'quality')">Prev</button>
                    </div>
                    <div class="col-md-6 align-right">
                        <button href="#plan" class="tablinks" onclick="openCity(event, 'plan')">Next</button>
                    </div>
                </div>
                <script>
                    jQuery(document).ready(function() {
                        $("#capacityType :input").change(function() {
                            var capacityType = this.id;
                            a[3] = capacityType;
                            console.log(a[3]); // points to the clicked input button
                        });
                    });
                </script>
            </div>
            -->

            <div id="plan" class="tabcontent">
                <h3>가입 정책 (네트워크 서비스에 가입할 정책을 선택하세요.)</h3>
                <div data-toggle="buttons" id="planType">
                    <div class="columns">
                        <ul class="price">
                            <li class="header" style="background-color:#333333">Basic</li>
                            <li class="grey" style="color:#000000">$ 499.99 / year</li>
                            <li>500GB Storage</li>
                            <li>-</li>
                            <li class="grey">
                                <label class="btn btn-success btn-lg align-center">
                                    <input type="radio" name="options" id="basic" autocomplete="off" style="margin-left: -2.0em">Select</input>
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="columns">
                        <ul class="price">
                            <li class="header" style="background-color:#4CAF50">Pro</li>
                            <li class="grey" style="color:#000000">$ 999.99 / year</li>
                            <li>1TB Storage</li>
                            <li>Edge Computing Support</li>
                            <li class="grey">
                                <label class="btn btn-success btn-lg">
                                    <input type="radio" name="options" id="pro" autocomplete="off" style="margin-left: -2.0em">Select</input>
                                </label>
                            </li>
                        </ul>
                    </div>
                    <div class="columns">
                        <ul class="price">
                            <li class="header" style="background-color:#aa0921">Premium</li>
                            <li class="grey" style="color:#000000">$ 1499.99 / year</li>
                            <li>4TB Storage</li>
                            <li>Edge Computing Support</li>
                            <li class="grey">
                                <label class="btn btn-success btn-lg">
                                    <input type="radio" name="options" id="premium" autocomplete="off" style="margin-left: -2.0em">Select</input>
                                </label>
                            </li>
                        </ul>
                    </div>
                    <script>
                        jQuery(document).ready(function() {
                            $("#planType :input").change(function() {
                                var planType = this.id;
                                a[3] = planType;
                                console.log(a[3]); // points to the clicked input button
                            });
                        });
                    </script>
                </div>
                <div data-role="main" class="ui-content" align="center" data-toggle="buttons" id="creation">
                    <label class="btn btn-primary btn-lg">
                        <input type="radio" name="options" id="create" autocomplete="off">Create
                    </label>
                </div>
                ${tenant_id}
                <script>
                    jQuery(document).ready(function() {
                        $("#creation :input").change(function() {
                            console.log(a); // points to the clicked input button
                            $.ajax({
                                url : "/networkService",
                                data : {userId : a[0], type : a[1], quality : a[2], plan : a[3]}
                            });
                            if(a[1] == "vod") {
                                alert("네트워크 서비스가 생성 되어습니다.\n" + "ServiceID: 292D05A61D8C335FA3411EBB5BAABE77");
                                //$("#contentsService").show();
                            }
                            else {
                                alert("네트워크 서비스가 생성 되어습니다.\n" + "ServiceID: 1AF52BA93BA24026CAF34D783DC12A09");
                                //$("#contentsService").show();
                            }
                        });
                    });

                    jQuery(document).ready(function() {
                        //$("#contentsService").hide();
                    });
                </script>
                <div data-role="main" class="ui-content" align="left">
                    <button href="#region" class="tablinks" onclick="openCity(event, 'quality')">Prev</button>
                </div>
            </div>

            <!--
            <div id="init" style="margin-top: 2.0em" data-toggle="buttons">
                <div class="col-md-6 align-center">
                    <a href="movie?title=Spiderman.mp4"><img src="../resources/images/Spiderman.jpeg" alt="" width="186" height="269" /></a>
                    <p style="margin-top: 2.0em">ContentName : multifia/Spiderman.mp4</p>
                    <p>ORCHIDv2 : 5BEE0F5007E6915B32B4EF55</p>
                    <p>ContextID : 292D05A61D8C335FA3411EBB5BAABE77</p>
                    <p>Scheme : rtp</p>
                    <label class="btn btn-warning btn-lg">
                        <input type="radio" name="options" id="spiderman" autocomplete="off" style="margin-left: -2.0em">Registration
                    </label>
                </div>
                <div class="col-md-6 align-center">
                    <a href="movie?title=Darkknight.mp4"><img src="../resources/images/darkknight.jpg" alt="" width="186" height="269" /></a>
                    <p style="margin-top: 2.0em">ContentName : multifia/Batman.mp4</p>
                    <p>ORCHIDv2 : 4754D4627C1E8A7BDFB1F67E</p>
                    <p>ContextID : 292D05A61D8C335FA3411EBB5BAABE77</p>
                    <p>Scheme : rtp</p>
                    <label class="btn btn-warning btn-lg">
                        <input type="radio" name="options" id="batman" autocomplete="off" style="margin-left: -2.0em">Registration
                    </label>
                </div>
                <script>
                    jQuery(document).ready(function() {
                        $("#init :input").change(function() {
                            var regit = this.id;
                            console.log(regit); // points to the clicked input button
                            $.ajax({
                                url : "/streaming/init"
                            });
                            alert("registration");
                        });
                    });
                </script>
            </div>
            -->
        </div>
    </section>
    <script>
        function openCity(evt, cityName) {
            // Declare all variables
            var i, tabcontent, tablinks;
            // Get all elements with class="tabcontent" and hide them
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            // Get all elements with class="tablinks" and remove the class "active"
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            // Show the current tab, and add an "active" class to the link that opened the tab
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " active";
        }
        document.getElementById("defaultOpen").click();
    </script>

    <section id="three" class="main style1">
        <div class="container">
            <header class="major special">
                <h2>응용 예제</h2>
            </header>
            <div class="row">
                <div class="col-md-6" align="center">
                    <h3>VoD 서비스 제공</h3>
                    <a href="/regit" class="btn btn-info btn-lg" role="button" aria-pressed="true">OPEN</a>
                </div>
                <div class="col-md-6" align="center">
                    <h3>VoD 서비스 이용</h3>
                    <a href="/stream" class="btn btn-info btn-lg" role="button" aria-pressed="true">OPEN</a>
                </div>
            </div>
        </div>
    </section>
</sec:authorize>

<!-- Three -->
<section id="three" class="main style1">
    <div class="container">
        <header class="major">
            <h2>참여 연구원</h2>
        </header>
        <p>본 과제의 참여 연구원을 소개합니다.</p>
        <div class="row 150%">
            <div class="col-md-2">
                <span class="image fit"><img src="../resources/images/song_prof.png" alt="" /></span>
                <h5>Prof. Wang-Cheol Song</h5>
                <p>송왕철 교수, 제주대학교</p>
                <h5>참여 연구원</h5>
                <p>
                    아팍 무하마드 연구원</br>
                    김혜원 연구원
                </p>
                <!--
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
                -->
            </div>
            <div class="col-md-2">
                <span class="image fit"><img src="../resources/images/choi_prof.jpg" alt="" /></span>
                <h5>Prof. Deok-Jai Choi</h5>
                <p>최덕재 교수, 전남대학교</p>
                <h5>참여 연구원</h5>
                <p>
                    Nguyen Van Quyet </br>박사과정</br>
                    Nugraha I Gde Dharma </br>박사과정</br>
                    Vu Duc Tiep 석사과정</br>
                    Alvin Prayuda Juniarta </br>석사과정</br>
                    유시선 학사과정
                </p>
                <!--
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
                -->
            </div>
            <div class="col-md-2">
                <span class="image fit"><img src="../resources/images/seok_kisti.jpg" alt="" /></span>
                <h5>Dr. Woojin Seok</h5>
                <p>석우진 책임연구원 (박사), KISTI</p>
                <h5>참여 연구원</h5>
                <p>
                    문정훈 선임연구원</br>
                    곽재승 책임연구원</br>
                    홍원택 선임연구원</br>
                    김동균 책임연구원</br>
                    이민선 선임연구원
                </p>
                <!--
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
                -->
            </div>
            <div class="col-md-2">
                <span class="image fit"><img src="../resources/images/seok_prof.jpg" alt="" /></span>
                <h5>Prof. Seung-Joon Seok</h5>
                <p>석승준 교수, 경남대학교</p>
                <!--
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
                -->
            </div>
            <div class="col-md-2">
                <span class="image fit"><img src="../resources/images/kim_prof.jpg" alt="" /></span>
                <h5>Prof. Kyungbaek Kim</h5>
                <p>김경백 교수, 전남대학교</p>
                <!--
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
                -->
            </div>
            <div class="col-md-2">
                <span class="image fit"><img src="../resources/images/han_prof.jpg" alt="" /></span>
                <h5>Prof. Youn-Hee Han</h5>
                <p>한연희 교수,</br>한국기술교육대학교</p>
                <h5>참여 연구원</h5>
                <p>
                    임현교 석사과정</br>
                    김경한 석사과정
                </p>
                <!--
                <ul class="actions">
                    <li><a href="#" class="button">More</a></li>
                </ul>
                -->
            </div>
        </div>
    </div>
</section>

<!-- Four -->
<!--
<section id="four" class="main style2 special">
    <div class="container">
        <header class="major">
            <h2>Ipsum feugiat consequat?</h2>
        </header>
        <p>Sed lacus nascetur ac ante amet sapien.</p>
        <ul class="actions uniform">
            <li><a href="#" class="button special">Sign Up</a></li>
            <li><a href="#" class="button">Learn More</a></li>
        </ul>
    </div>
</section>
-->
<!-- Five -->
<!--
    <section id="five" class="main style1">
        <div class="container">
            <header class="major special">
                <h2>Elements</h2>
            </header>

            <section>
                <h4>Text</h4>
                <p>This is <b>bold</b> and this is <strong>strong</strong>. This is <i>italic</i> and this is <em>emphasized</em>.
                This is <sup>superscript</sup> text and this is <sub>subscript</sub> text.
                This is <u>underlined</u> and this is code: <code>for (;;) { ... }</code>. Finally, <a href="#">this is a link</a>.</p>
                <hr />
                <header>
                    <h4>Heading with a Subtitle</h4>
                    <p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
                </header>
                <p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
                <header>
                    <h5>Heading with a Subtitle</h5>
                    <p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
                </header>
                <p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
                <hr />
                <h2>Heading Level 2</h2>
                <h3>Heading Level 3</h3>
                <h4>Heading Level 4</h4>
                <h5>Heading Level 5</h5>
                <h6>Heading Level 6</h6>
                <hr />
                <h5>Blockquote</h5>
                <blockquote>Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan faucibus. Vestibulum ante ipsum primis in faucibus lorem ipsum dolor sit amet nullam adipiscing eu felis.</blockquote>
                <h5>Preformatted</h5>
                <pre><code>i = 0;

while (!deck.isInOrder()) {
print 'Iteration ' + i;
deck.shuffle();
i++;
}

print 'It took ' + i + ' iterations to sort the deck.';</code></pre>
            </section>

            <section>
                <h4>Lists</h4>
                <div class="row">
                    <div class="6u 12u$(medium)">
                        <h5>Unordered</h5>
                        <ul>
                            <li>Dolor pulvinar etiam.</li>
                            <li>Sagittis adipiscing.</li>
                            <li>Felis enim feugiat.</li>
                        </ul>
                        <h5>Alternate</h5>
                        <ul class="alt">
                            <li>Dolor pulvinar etiam.</li>
                            <li>Sagittis adipiscing.</li>
                            <li>Felis enim feugiat.</li>
                        </ul>
                    </div>
                    <div class="6u$ 12u$(medium)">
                        <h5>Ordered</h5>
                        <ol>
                            <li>Dolor pulvinar etiam.</li>
                            <li>Etiam vel felis viverra.</li>
                            <li>Felis enim feugiat.</li>
                            <li>Dolor pulvinar etiam.</li>
                            <li>Etiam vel felis lorem.</li>
                            <li>Felis enim et feugiat.</li>
                        </ol>
                        <h5>Icons</h5>
                        <ul class="icons">
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
                        </ul>
                    </div>
                </div>
                <h5>Actions</h5>
                <div class="row">
                    <div class="6u 12u$(medium)">
                        <ul class="actions">
                            <li><a href="#" class="button special">Default</a></li>
                            <li><a href="#" class="button">Default</a></li>
                        </ul>
                        <ul class="actions small">
                            <li><a href="#" class="button special small">Small</a></li>
                            <li><a href="#" class="button small">Small</a></li>
                        </ul>
                        <ul class="actions vertical">
                            <li><a href="#" class="button special">Default</a></li>
                            <li><a href="#" class="button">Default</a></li>
                        </ul>
                        <ul class="actions vertical small">
                            <li><a href="#" class="button special small">Small</a></li>
                            <li><a href="#" class="button small">Small</a></li>
                        </ul>
                    </div>
                    <div class="6u 12u$(medium)">
                        <ul class="actions vertical">
                            <li><a href="#" class="button special fit">Default</a></li>
                            <li><a href="#" class="button fit">Default</a></li>
                        </ul>
                        <ul class="actions vertical small">
                            <li><a href="#" class="button special small fit">Small</a></li>
                            <li><a href="#" class="button small fit">Small</a></li>
                        </ul>
                    </div>
                </div>
            </section>

            <section>
                <h4>Table</h4>
                <h5>Default</h5>
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Item One</td>
                                <td>Ante turpis integer aliquet porttitor.</td>
                                <td>29.99</td>
                            </tr>
                            <tr>
                                <td>Item Two</td>
                                <td>Vis ac commodo adipiscing arcu aliquet.</td>
                                <td>19.99</td>
                            </tr>
                            <tr>
                                <td>Item Three</td>
                                <td> Morbi faucibus arcu accumsan lorem.</td>
                                <td>29.99</td>
                            </tr>
                            <tr>
                                <td>Item Four</td>
                                <td>Vitae integer tempus condimentum.</td>
                                <td>19.99</td>
                            </tr>
                            <tr>
                                <td>Item Five</td>
                                <td>Ante turpis integer aliquet porttitor.</td>
                                <td>29.99</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2"></td>
                                <td>100.00</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <h5>Alternate</h5>
                <div class="table-wrapper">
                    <table class="alt">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Item One</td>
                                <td>Ante turpis integer aliquet porttitor.</td>
                                <td>29.99</td>
                            </tr>
                            <tr>
                                <td>Item Two</td>
                                <td>Vis ac commodo adipiscing arcu aliquet.</td>
                                <td>19.99</td>
                            </tr>
                            <tr>
                                <td>Item Three</td>
                                <td> Morbi faucibus arcu accumsan lorem.</td>
                                <td>29.99</td>
                            </tr>
                            <tr>
                                <td>Item Four</td>
                                <td>Vitae integer tempus condimentum.</td>
                                <td>19.99</td>
                            </tr>
                            <tr>
                                <td>Item Five</td>
                                <td>Ante turpis integer aliquet porttitor.</td>
                                <td>29.99</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2"></td>
                                <td>100.00</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </section>

            <section>
                <h4>Buttons</h4>
                <ul class="actions">
                    <li><a href="#" class="button special">Special</a></li>
                    <li><a href="#" class="button">Default</a></li>
                </ul>
                <ul class="actions">
                    <li><a href="#" class="button big">Big</a></li>
                    <li><a href="#" class="button">Default</a></li>
                    <li><a href="#" class="button small">Small</a></li>
                </ul>
                <ul class="actions fit">
                    <li><a href="#" class="button fit">Fit</a></li>
                    <li><a href="#" class="button special fit">Fit</a></li>
                    <li><a href="#" class="button fit">Fit</a></li>
                </ul>
                <ul class="actions fit small">
                    <li><a href="#" class="button special fit small">Fit + Small</a></li>
                    <li><a href="#" class="button fit small">Fit + Small</a></li>
                    <li><a href="#" class="button special fit small">Fit + Small</a></li>
                </ul>
                <ul class="actions">
                    <li><a href="#" class="button special icon fa-download">Icon</a></li>
                    <li><a href="#" class="button icon fa-download">Icon</a></li>
                </ul>
                <ul class="actions">
                    <li><span class="button special disabled">Disabled</span></li>
                    <li><span class="button disabled">Disabled</span></li>
                </ul>
            </section>

            <section>
                <h4>Form</h4>
                <form method="post" action="#">
                    <div class="row uniform 50%">
                        <div class="6u 12u$(xsmall)">
                            <input type="text" name="demo-name" id="demo-name" value="" placeholder="Name" />
                        </div>
                        <div class="6u$ 12u$(xsmall)">
                            <input type="email" name="demo-email" id="demo-email" value="" placeholder="Email" />
                        </div>
                        <div class="12u$">
                            <div class="select-wrapper">
                                <select name="demo-category" id="demo-category">
                                    <option value="">- Category -</option>
                                    <option value="1">Manufacturing</option>
                                    <option value="1">Shipping</option>
                                    <option value="1">Administration</option>
                                    <option value="1">Human Resources</option>
                                </select>
                            </div>
                        </div>
                        <div class="4u 12u$(small)">
                            <input type="radio" id="demo-priority-low" name="demo-priority" checked>
                            <label for="demo-priority-low">Low</label>
                        </div>
                        <div class="4u 12u$(small)">
                            <input type="radio" id="demo-priority-normal" name="demo-priority">
                            <label for="demo-priority-normal">Normal</label>
                        </div>
                        <div class="4u$ 12u$(small)">
                            <input type="radio" id="demo-priority-high" name="demo-priority">
                            <label for="demo-priority-high">High</label>
                        </div>
                        <div class="6u 12u$(small)">
                            <input type="checkbox" id="demo-copy" name="demo-copy">
                            <label for="demo-copy">Email me a copy</label>
                        </div>
                        <div class="6u$ 12u$(small)">
                            <input type="checkbox" id="demo-human" name="demo-human" checked>
                            <label for="demo-human">Not a robot</label>
                        </div>
                        <div class="12u$">
                            <textarea name="demo-message" id="demo-message" placeholder="Enter your message" rows="6"></textarea>
                        </div>
                        <div class="12u$">
                            <ul class="actions">
                                <li><input type="submit" value="Send Message" class="special" /></li>
                                <li><input type="reset" value="Reset" /></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </section>

            <section>
                <h4>Image</h4>
                <h5>Fit</h5>
                <div class="box alt">
                    <div class="row uniform 50%">
                        <div class="12u"><span class="image fit"><img src="images/pic06.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic02.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic03.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic04.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic03.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic04.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic02.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic04.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic02.jpg" alt="" /></span></div>
                        <div class="4u"><span class="image fit"><img src="images/pic03.jpg" alt="" /></span></div>
                    </div>
                </div>
                <h5>Left &amp; Right</h5>
                <p><span class="image left"><img src="images/pic05.jpg" alt="" /></span>Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>
                <p><span class="image right"><img src="images/pic05.jpg" alt="" /></span>Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>
            </section>

        </div>
    </section>
-->

<!-- Footer -->
<section id="footer">
    <!--
    <ul class="icons">
        <li><a href="#" class="icon alt fa-twitter"><span class="label">Twitter</span></a></li>
        <li><a href="#" class="icon alt fa-facebook"><span class="label">Facebook</span></a></li>
        <li><a href="#" class="icon alt fa-instagram"><span class="label">Instagram</span></a></li>
        <li><a href="#" class="icon alt fa-github"><span class="label">GitHub</span></a></li>
        <li><a href="#" class="icon alt fa-envelope"><span class="label">Email</span></a></li>
    </ul>
    -->
    <ul class="copyright">
        <li>&copy; Copyright</li><li>IITP-multiFIA</li>
    </ul>
</section>

<!-- Scripts -->
</body>
</html>