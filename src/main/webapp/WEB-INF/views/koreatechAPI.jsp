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
            <li><a href="#doc" class="button scrolly">REST 기반 Mapping API</a></li>
        </ul>
    </div>
</section>

<section id="doc" class="main style1 special">
    <header class="major">
        <h2>Mapping Registrar 및 REST 기반 API</h2>
    </header>
    <div class="container">
        <div align="left">
            그림 1은 Name, Identifier, Locator 간의 매핑을 위한 서로간의 관계 및 이에 관한 메시지 이름을 나타낸다.
            임의의 컨텐츠나 호스트의 Name은 하나의 유일한 Identifier로 매핑되며, 이러한 Identifier는 다양한 Locator를 가질수 있으므로 1:n 관계를 가질 수 있다.
        </div>
        <figure>
            <img src="../resources/images/name-id-loc_mapping_relation.png">
            <figcaption>그림 1. Name-Identifier-Locator 매핑 관계</figcaption>
        </figure><br/>
        <div align="left">
            End-Host에 있는 Name, Identifier, Locator는 네트워크에 등록 및 갱신되어져야 하고, 다른 End-Host에 의하여 검색되어야 한다.
            이를 위하여 그림 2처럼 새로운 네트워크 구조에 존재하게 될 Orchestration 모듈에 Identifier Registrar와 Locator Registrar Function을 마련해야 한다.
            예를 들어, 임의의 End-Host가 제공하고자 하는 콘텐츠가 있는 경우 해당 콘텐츠의 Name과 Context ID를 이용하여 Context-Typed Name와 Identifier를 생성하고
            그 콘텐츠를 접근할 수 있는 Locator 정보를 한꺼번에 Mapping Registrar에 등록한다.
        </div>
        <figure>
            <img src="../resources/images/name-id-loc_framework.png">
            <figcaption>그림 2. Name-Identifier-Locator 프레임워크</figcaption>
        </figure><br/>
        <div align="left">
            한편, 그 콘텐츠를 소비하려고 하는 End-Host에서는 해당 콘텐츠의 Identifier를 동일한 방식으로 생성하여 Registrars에 보내고,
            응답으로서 해당 Identifier에 매핑된 Locator를 찾아서 직접 콘텐츠 서비스와 통신 연결을 맺을 수 있다.
        </div>
        <br/><br/><br/>
        <header class="major">
            <h3>1. Identifier/Locator 등록</h3>
        </header>
        <div align="left">
            그림 3은 Tenanat에서 제공하는 서비스나 컨텐츠를 Orchestration 모듈에 위치한 Registrar에 해당 정보를 등록하는 것을 나타낸다.
            POST 방식을 통해 서버에서는 ORCHID와 TNID, Context ID, Name, Locator, Scheme를 등록한다. 이를 RESTful API를 통해 등록하며
            등록 이후 사용자가 원하는 시점에 Registrar에서 검색을 통해 해당 서비스의 위치를 알아 낼 수 있도록 한다.
        </div>
        <figure>
            <img src="../resources/images/identifier-locator_registration.png">
            <figcaption>그림 3. Identifier/Locator 등록 과정</figcaption>
        </figure><br/>
        <div align="left">다음의 표 1, 2, 3은 Tenant가 등록을 위해 사용하는 매핑 API의 정보 변수 출력 결과물을 보여준다.</div>
        <table>
            <caption>표 1. 매핑 등록 API 기본 정보</caption>
            <tr>
                <th><strong>역할</strong></th>
                <th><strong>메서드</strong></th>
                <th><strong>요청 URL</strong></th>
                <th><strong>출력 포맷</strong></th>
            </tr>
            <tr>
                <td>매핑 등록</td>
                <td>POST</td>
                <td>http://(Registrar 주소)/registrar/registration</td>
                <td>JSON</td>
            </tr>
        </table>
        <table>
            <caption>표 2. 매핑 등록 요청 변수</caption>
            <tr>
                <th><strong>요청 변수</strong></th>
                <th><strong>타입</strong></th>
                <th><strong>필수 여부</strong></th>
                <th><strong>설명</strong></th>
            </tr>
            <tr>
                <td>TNID</td>
                <td>String</td>
                <td>필수</td>
                <td>Tenant Network ID</td>
            </tr>
            <tr>
                <td>ORCHID</td>
                <td>String</td>
                <td>필수</td>
                <td>ORCHIDv2 프레임워크를 통해 생성된 IPv6형태의 식별자</td>
            </tr>
            <tr>
                <td>NAME</td>
                <td>String</td>
                <td>필수</td>
                <td>제공하는 서비스 혹은 컨텐츠의 이름</td>
            </tr>
            <tr>
                <td>CONTEXT_ID</td>
                <td>String</td>
                <td>필수</td>
                <td>네트워크 운영자가 각 Tenant에게 제공하는 서비스를 구별하기 위한 ID</td>
            </tr>
            <tr>
                <td>LOCATOR</td>
                <td>String</td>
                <td>필수</td>
                <td>서비스나 컨텐츠를 지니고 있는 Access Point의 주소. 가능한 모든 Access Point를 보냄</td>
            </tr>
            <tr>
                <td>SCHEME</td>
                <td>String</td>
                <td>선택</td>
                <td>해당 서비스를 이용하기 위한 프로토콜(ex. rtp). 기본 값은 모든 프로토콜</td>
            </tr>
        </table>
        <table>
            <caption>표 3. 매핑 등록 출력 결과</caption>
            <tr>
                <th><strong>필드</strong></th>
                <th><strong>타입</strong></th>
                <th><strong>설명</strong></th>
            </tr>
            <tr>
                <td>Response Code</td>
                <td>Int</td>
                <td>성공: 201 (자원 생성 성공)<br/>
                    실패: 409 (동일한 ORCHID가 이미 존재하는 경우)</td>
            </tr>
            <tr>
                <td>ORCHID</td>
                <td>String</td>
                <td>ORCHID를 킷값으로 등록한 결과 값이 일치한지 확인을 위한 값</td>
            </tr>
            <tr>
                <td>LOCATOR</td>
                <td>String</td>
                <td>자원 할당 등의 작업 이후에 최종 결정된 Access Point 정보</td>
            </tr>
        </table><br/><br/>
        <header class="major">
            <h3>2. Identifier/Locator 업데이트</h3>
        </header>
        <figure>
            <img src="../resources/images/locator_update.png">
            <figcaption>그림 4. Locator Update</figcaption>
        </figure><br/>
        <div align="left">
            그림 4에서 보이듯이 Tenant에서 제공하는 서비스나 컨텐츠를 저장하고 제공하는 서버의 Locator에 변화가 생겼을 시에 서버는 PUT 방식을 통해
            Registrar에 ORCHID를 킷값으로 하여 Locator의 정보를 업데이트 한다.<br/><br/>
            표 4, 5, 6은 Tenant가 업데이트를 위해 사용하는 매핑 API의 정보, 변수, 출력 결과를 보여준다.
        </div>
        <table>
            <caption>표 4. 매핑 업데이트 API 기본 정보</caption>
            <tr>
                <th><strong>역할</strong></th>
                <th><strong>메서드</strong></th>
                <th><strong>요청 URL</strong></th>
                <th><strong>출력 포맷</strong></th>
            </tr>
            <tr>
                <td>매핑 등록</td>
                <td>PUT</td>
                <td>http://(Registrar 주소)/registrar/update</td>
                <td>JSON</td>
            </tr>
        </table>
        <table>
            <caption>표 5. 매핑 업데이트 요청 변수</caption>
            <tr>
                <th><strong>요청 변수</strong></th>
                <th><strong>타입</strong></th>
                <th><strong>필수 여부</strong></th>
                <th><strong>설명</strong></th>
            </tr>
            <tr>
                <td>TNID</td>
                <td>String</td>
                <td>필수</td>
                <td>Tenant Network ID</td>
            </tr>
            <tr>
                <td>ORCHID</td>
                <td>String</td>
                <td>필수</td>
                <td>ORCHIDv2 프레임워크를 통해 생성된 IPv6형태의 식별자</td>
            </tr>
            <tr>
                <td>NAME</td>
                <td>String</td>
                <td>필수</td>
                <td>제공하는 서비스 혹은 컨텐츠의 이름</td>
            </tr>
            <tr>
                <td>CONTEXT_ID</td>
                <td>String</td>
                <td>필수</td>
                <td>네트워크 운영자가 각 Tenant에게 제공하는 서비스를 구별하기 위한 ID</td>
            </tr>
            <tr>
                <td>LOCATOR</td>
                <td>String</td>
                <td>필수</td>
                <td>서비스나 컨텐츠를 지니고 있는 Access Point의 주소. 가능한 모든 Access Point를 보냄</td>
            </tr>
            <tr>
                <td>SCHEME</td>
                <td>String</td>
                <td>선택</td>
                <td>해당 서비스를 이용하기 위한 프로토콜(ex. rtp). 기본 값은 모든 프로토콜</td>
            </tr>
        </table>
        <table>
            <caption>표 6. 매핑 업데이트 출력 결과</caption>
            <tr>
                <th><strong>필드</strong></th>
                <th><strong>타입</strong></th>
                <th><strong>설명</strong></th>
            </tr>
            <tr>
                <td>Response Code</td>
                <td>Int</td>
                <td>성공: 200 (자원 정보 업데이트 성공)<br/>
                    실패: 404 (업데이트 하려는 자원 존재하지 않음)</td>
            </tr>
            <tr>
                <td>ORCHID</td>
                <td>String</td>
                <td>ORCHID를 킷값으로 등록한 결과 값이 일치한지 확인을 위한 값</td>
            </tr>
            <tr>
                <td>LOCATOR</td>
                <td>String</td>
                <td>자원 할당 등의 작업 이후에 최종 결정된 Access Point 정보</td>
            </tr>
        </table><br/><br/>
        <header class="major">
            <h3>3. Identifier/Locator 검색</h3>
        </header>
        <div align="left">
            그림 5은 보이듯이 사용자가 서비스 받으려는 컨텐츠의 Name을 미리 알고 있으며 해당 컨텐츠를 접근하기 위한 Locator 정보를 알기 위해서는,
            해당 서비스의 Name을 통해 ORCHID를 생성하고 생성된 ORCHID를 통해 Registrar에 GET방식의 요청을 보내 Locator를 받는다.
        </div>
        <figure>
            <img src="../resources/images/locator_lookup.png">
            <figcaption>그림 5. Locator Lookup</figcaption>
        </figure><br/>
        <div align="left">
            표 7, 8, 9는 Tenant가 검색를 위해 사용하는 매핑 API의 정보, 변수, 출력 결과를 보여준다.
        </div>
        <table>
            <caption>표 7. 매핑 검색 API 기본 정보</caption>
            <tr>
                <th><strong>역할</strong></th>
                <th><strong>메서드</strong></th>
                <th><strong>요청 URL</strong></th>
                <th><strong>출력 포맷</strong></th>
            </tr>
            <tr>
                <td>매핑 등록</td>
                <td>GET</td>
                <td>http://(Registrar 주소)/registrar/lookup</td>
                <td>JSON</td>
            </tr>
        </table>
        <table>
            <caption>표 8. 매핑 검색 요청 변수</caption>
            <tr>
                <th><strong>요청 변수</strong></th>
                <th><strong>타입</strong></th>
                <th><strong>필수 여부</strong></th>
                <th><strong>설명</strong></th>
            </tr>
            <tr>
                <td>ORCHID</td>
                <td>String</td>
                <td>필수</td>
                <td>ORCHIDv2 프레임워크를 통해 생성된 IPv6형태의 식별자</td>
            </tr>
        </table>
        <table>
            <caption>표 9. 매핑 검색 출력 결과</caption>
            <tr>
                <th><strong>필드</strong></th>
                <th><strong>타입</strong></th>
                <th><strong>설명</strong></th>
            </tr>
            <tr>
                <td>Response Code</td>
                <td>Int</td>
                <td>성공: 200 (자원 정보 업데이트 성공)<br/>
                    실패: 404 (업데이트 하려는 자원 존재하지 않음)</td>
            </tr>
            <tr>
                <td>ORCHID</td>
                <td>String</td>
                <td>ORCHID를 킷값으로 등록한 결과 값이 일치한지 확인을 위한 값</td>
            </tr>
            <tr>
                <td>LOCATOR</td>
                <td>String</td>
                <td>자원 할당 등의 작업 이후에 최종 결정된 Access Point 정보</td>
            </tr>
            <tr>
                <td>SCHEME</td>
                <td>String</td>
                <td>서비스나 컨텐츠를 제공받기 위해 사용하는 프로토콜 (E.g. RTP, HTTP, FTP)</td>
            </tr>
        </table><br/><br/>
        <header class="major">
            <h3>4. ID-LOC 매핑을 이용한 소켓 통신</h3>
        </header>
        <div align="left">
            그림 6은 본 기술문서에서 제안한 Name-ID-Locater 메핑 프레임워크를 기반으로 응용단에서 ID 기반의 통신, 즉 ID 기반의 소켓을 생성하여
            클라이언트와 서버간에 통신을 하는 모습을 보여준다. 서버측 소켓은 우선 Name, ID, LOC 정보를 매핑 Registrars에게 등록하고, 클라이언트측
            소켓은 ID를 기반으로 통신하고자 하는 상대의 Locator 정보를 얻어와서 내부적으로는 일반적인 소켓 통신을 통해 데이터를 교환한다.
            이 때 클라이언트 측 Locator 유형과 서버 측 Locator 유형이 다를 경우 LOC Translation과 같은 기능이 네트워크 내부에서 요구된다.
        </div>
        <figure>
            <img src="../resources/images/ID-based%20communication.png">
            <figcaption>그림 6. Locator Lookup</figcaption>
        </figure><br/>
    </div>
</section>

<section id="footer">
    <ul class="copyright">
        <li>&copy; Copyright</li><li>IITP-multiFIA</li>
    </ul>
</section>
</body>
</html>
