# HowAboutThisHouse
![image](https://user-images.githubusercontent.com/68576770/156894366-72a47ee6-c1c8-4b7a-9b4c-658232f9392d.png)\
### 소개
- 프로젝트명 : 이집어때?
- 주제 : 아파트 거래 정보, 주변 상가 정보 등을 조회하는 서비스를 제공하는 부동산 관련 웹 사이트
### 내가 맡은 기능
- 풀스택 개발 담당
- Front-end
  - 메인화면, Navbar, 회원 가입, 로그인, 공지사항/자유/QnA 게시판, 게시판 댓글, 관심지역 화면의 UI/UX를 HTML, CSS로 구현
  - Vue.js를 이용해 비동기 처리와 라우터를 사용
  - 로그인/로그아웃에 따라 보이는 Navbar 메뉴를 다르게 구성함
- Back-end
  - 회원관리, 게시판, 댓글, 관심지역 기능에 대한 CRUD 작성
  - MyBatis를 이용해 MySQL 데이터베이스와 매핑    
- Database
  - 자유게시판은 게시글과 댓글 테이블을 연결하고, 게시글 별 조회수, 댓글 수를 볼 수 있도록 함 
  - 공지사항은 관리자만 작성할 수 있도록 사용자 데이터의 권한을 User와 Admin로 구분함
  - QnA는 답글형 게시판으로 관리자가 답변을 등록하면 전체 목록에는 질문글 다음에 답변글이 오도록 계층형 쿼리로 설계함
- 부동산 중개업소 공공 데이터 API를 활용해 관심지역을 지도에 표시함
# 개요
### 배경
- 사용자가 이사를 하거나 집을 구하고자 하는 동네에서 원하는 주택을 편히 찾도록 하는 서비스 제공
### 목표
- 아파트 거래 정보, 주변 상가 정보 등을 조회하는 서비스를 제공하는 것
- 다른 사람들과 소통할 수 있는 게시판을 통해 최대 정보를 얻어갈 수 있도록 하자
# 서비스 자세히 보기
![image](https://user-images.githubusercontent.com/68576770/156894865-346d31c4-9503-43fe-80b1-67446045d34a.png)
![image](https://user-images.githubusercontent.com/68576770/156894874-ff0357f6-6a23-473f-a30f-487695e347bd.png)
![image](https://user-images.githubusercontent.com/68576770/156894882-bf9d2380-ab92-487c-95f1-4ae1c8e98633.png)
![image](https://user-images.githubusercontent.com/68576770/156894888-a4273d91-5e47-4b00-a854-6c5183495370.png)
![image](https://user-images.githubusercontent.com/68576770/156894897-d6b19c45-27c1-491c-a6f5-474bb311942d.png)
![image](https://user-images.githubusercontent.com/68576770/156894908-653ad5ca-aa85-48ef-9d2f-a7a6c913c037.png)
![image](https://user-images.githubusercontent.com/68576770/156894923-4403d5c7-260e-441b-a2cc-df17f3c905b6.png)
![image](https://user-images.githubusercontent.com/68576770/156894938-b72b0d05-1e0c-4ae6-ba13-c7a483f4d63e.png)
### 주요 특징
- 검색하는 주변 지역의 은행, 마트, 약국, 카페 등 편의시설 위치 제공
- 실거래가 가격과 면적을 필터를 이용하여 검색할 수 있는 기능 
- 관심 지역을 편리하게 설정할 수 있고, 설정한 관심지역 주변 정보를 API를 이용해 최신의 정보 제공
### 기술스택
    - frontend : vue.js
    - backend : Spring Boot, MySql
    - UI : HTML, CSS
### 자세한 기능
- 회원 가입
- 카카오 API를 이용해 실거래가 매물 위치 시각화 
    - 매물 상세 정보 조회
    - 은행, 마트 등 주변 시설 위치 시각화
- 관심 지역 간편 추가/삭제 가능
- 관심 지역 주변 부동산 정보 위치 시각화
- 회원 전용 자유 게시판 기능
    - 댓글과 조회수 기능 제공
- 회원 전용 QnA 게시판 기능
    - 관리자가 일대일로 답변 가능
- 공지사항으로 매물 관련 유용한 정보 제공
