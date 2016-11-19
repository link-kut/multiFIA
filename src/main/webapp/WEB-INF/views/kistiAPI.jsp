<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
            <li><a href="#doc" class="button scrolly">다양성 자원 API</a></li>
        </ul>
    </div>
</section>
<section id="doc" class="main style1 special">
    <div class="container">
        <header class="major">
            <h2>다양성 지원 네트워크 API 사용성</h2>
        </header>
        <table>
            <caption>표 1. openAIP의 평가모델에 기반한 1단계</caption>
            <th><strong>Level 1: All find (4)</strong></th>
            <tr>
                <td>From a single source (the portal), are all of the information or links to them available? </td>
                <td>yes</td>
            </tr>
            <tr>
                <td>Is updated documentation available?</td>
                <td>yes</td>
            </tr>
            <tr>
                <td>Are there examples of API requests seen as part of the documentation?</td>
                <td>yes</td>
            </tr>
            <tr>
                <td>Are there examples of the API request returned data?</td>
                <td>yes</td>
            </tr>
        </table>

        <table>
            <caption>표 2. openAPI의 평가모델에 기반한 2단계 중 일부</caption>
            <th><strong>Level 2: All use (5)</strong></th>
            <tr>
                <td>Does it support the use of JSON and/or XML?</td>
                <td>yes</td>
            </tr>
        </table>
        <br/><br/>
        <header class="major">
            <h2>NRC (네트워크 자원 제어)</h2>
        </header>
        <figure>
            <img src="../resources/images/nrc_structure.png">
            <figcaption>그림 1. NRC 구조도</figcaption>
        </figure>
        <p>NRC는 네트워크 자원을 제어하는 것으로, 네트워크 토폴로지를 구성하는 호스트, 네트워크 링크, 그리고 자원제어를 담당하는 3가지 API를 구성하고 있다.</p>

        <figure>
            <img src="../resources/images/NRC_API_abstract.png">
            <figcaption>그림 2. NRC 제공 API 요약</figcaption>
        </figure>
        <div align="left">
            <p>HOST API는 네트워크 토폴로지에서 호스트 정보를 전달하는 GET방식의 API로써, host MAC, host PORT, switch ID 정보를 포함하고 있다.</p>
            <p>LINK API는 네트워크 토폴로지에서 네트워크 링크 정보를 전달하는 GET방식의 API로써, 출발지 switch ID, 출발지 switch PORT, 목적지 switch ID, 목적지 switch PORT 정보를 포함하고 있다.</p>
            <p>POST API는 네트워크 토폴로지에서 출발지와 목적지 호스트간의 네트워크 링크를 할당하는 POST 방식의 API로써, 출발지 host 정보, 경유 링크 정보, 목적지 host 정보를 포함하고 있다.</p>
        </div>
        <table>
            <caption>표 3. NRC 제공 API spec</caption>
            <tr>
                <th><strong>Host_info API</strong></th>
                <th><strong>LINK_info API</strong></th>
                <th><strong>POST API</strong></th>
            </tr>
            <tr>
                <td>Source Host info.:<br/>SrcHost MAC<br/>SrcHost SW ID<br/>SrcPort</td>
                <td>Dest. Host Info.:<br/>DstHost MAC<br/>DstHost SW ID<br/>DstPort</td>
                <td>Complete Path Info.:<br/>SrcSwId<br/>SrcPort<br/>DstSwId<br/>DstPort</td>
            </tr>
        </table>
        <br/><br/>
        <header class="major">
            <h3>(1) LINK_info API</h3>
        </header>
        <figure>
            <img src="../resources/images/link_info_api.png">
            <figcaption>그림 3. LINK_info API</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/get%20link_info.png">
            <figcaption>그림 4. GET LINK_info via cur</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/get%20link_info_RESTAPI.png">
            <figcaption>그림 5. GET LINK_info via REST API</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/LINK_info_Requestmsg.png">
            <figcaption>그림 6. LINK_info 응답 메시지</figcaption>
        </figure>
        <br/><br/>
        <header class="major">
            <h3>(2) Host_info API</h3>
        </header>
        <figure>
            <img src="../resources/images/host_info_api.png">
            <figcaption>그림 7. HOST_info API</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/get%20host_info.png">
            <figcaption>그림 8. GET HOST_info via REST</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/get%20host_info_cur.png">
            <figcaption>그림 9. GET HOST_info via cur</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/host_info_reqmsg.png">
            <figcaption>그림 10. HOST_info 응답 메시지</figcaption>
        </figure>
        <br/><br/>
        <header class="major">
            <h3>(3) POST API</h3>
        </header>
        <figure>
            <img src="../resources/images/post_api.png">
            <figcaption>그림 11. POST API</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/post_curl.png">
            <figcaption>그림 12. POST via cur</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/post_example1.png">
            <figcaption>그림 13. 에지클라우드와 터미널 노드 간의 POST</figcaption>
        </figure>
        <figure>
            <img src="../resources/images/post_error_example.png">
            <figcaption>그림 14. POST에러 사용 예: 에러코드 400</figcaption>
        </figure>
        <br/><br/>
        <table>
            <caption>표 4. 예시 제시</caption>
            <tr>
                <th><strong>에지클라우드-터미널간의 할당예제</strong></th>
                <th><strong>클라우드-에지클라우드간의 할당예제</strong></th>
            </tr>
            <tr>
                <td>
                    <p>
                        From Edge to Terminal2:<br/>
                        {<br/>
                        &nbsp;"SrcHost":<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"SrcPort": "1",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcMac": "78:2B:CB:28:0E:32",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcSwId":"of:00000010189f19de"<br/>
                        &nbsp;&nbsp;},<br/>
                        &nbsp;&nbsp;"DstHost":<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"DstPort": "3",<br/>
                        &nbsp;&nbsp;&nbsp;"DstMac": "78:2B:CB:28:16:36",<br/>
                        &nbsp;&nbsp;&nbsp;"DstSwId":"of:0000001018a0de5a"<br/>
                        &nbsp;&nbsp;},<br/>
                        &nbsp;&nbsp;"PathInfo": {<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"SrcSwId": "of:00000010189f19de",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcPort": "3",<br/>
                        &nbsp;&nbsp;&nbsp;"DstSwId": "of:0000001018a0de5a",<br/>
                        &nbsp;&nbsp;&nbsp;"DstPort": "4"<br/>
                        &nbsp;&nbsp;}<br/>
                        &nbsp;}<br/>
                        }
                    </p>
                </td>
                <td>
                    <p>
                        From Cloud to Terminal2:<br/>
                        {<br/>
                        &nbsp;"SrcHost":<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"SrcPort": "1",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcMac": "78:2B:CB:28:0E:32",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcSwId":"of:00000010189f19de"<br/>
                        &nbsp;&nbsp;},<br/>
                        &nbsp;&nbsp;"DstHost":<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"DstPort": "3",<br/>
                        &nbsp;&nbsp;&nbsp;"DstMac": "78:2B:CB:28:0F:CF",<br/>
                        &nbsp;&nbsp;&nbsp;"DstSwId":"of:0000782bcb138204"<br/>
                        &nbsp;&nbsp;},<br/>
                        &nbsp;&nbsp;"PathInfo": {<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"SrcSwId": "of:00000010189f19de",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcPort": "3",<br/>
                        &nbsp;&nbsp;&nbsp;"DstSwId": "of:0000001018a0de5a",<br/>
                        &nbsp;&nbsp;&nbsp;"DstPort": "4"<br/>
                        &nbsp;&nbsp;}<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"SrcSwId": "of:0000001018a0de5a",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcPort": "2",<br/>
                        &nbsp;&nbsp;&nbsp;"DstSwId": "of:0000782bcb281078",<br/>
                        &nbsp;&nbsp;&nbsp;"DstPort": "3"<br/>
                        &nbsp;&nbsp;}<br/>
                        &nbsp;&nbsp;{<br/>
                        &nbsp;&nbsp;&nbsp;"SrcSwId": "of:0000782bcb281078",<br/>
                        &nbsp;&nbsp;&nbsp;"SrcPort": "1",<br/>
                        &nbsp;&nbsp;&nbsp;"DstSwId": "of:0000782bcb138204",<br/>
                        &nbsp;&nbsp;&nbsp;"DstPort": "2"<br/>
                        &nbsp;&nbsp;}<br/>
                        &nbsp;}<br/>
                        }
                    </p>
                </td>
            </tr>
        </table>
    </div>
</section>
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
</body>
</html>
