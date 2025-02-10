# Wefifty : 보고싶다 친구야.
## 🚦목차
[1. Overview](#️1-overview) <br>
[------ 서비스 개요](#-서비스-개요) <br>
[------ 목적](#-목적) <br>
[------ 개발 학습 목표](#-개발-학습-목표) <br>
[------ MVP 주요 기능](#-mvp-주요-기능) <br>
[2. Contributor](#2-contributor) <br>
[3. Stack & Tools](#3-stack--tools) <br>
[4. Architecture](#️4-architecture) <br>
[5. ERD](#5-erd) <br>
[------ user](#-user) <br>
[------ friend_request](#-friend_request) <br>
[------ user_school](#-user_school) <br>
[6. Troubleshooting](#6-troubleshooting) <br>
[7. Review](#7-review) <br>

## 🖥️**1. Overview**

### 📌 서비스 개요
**wefifty**는 50대 이상의 사용자들이 학창 시절 친구들을 다시 찾을 수 있도록 돕는 웹 서비스입니다. 서비스 이용자가 다녔던 학교 정보를 입력하면 비슷한 시기에 졸업한 다른 서비스 이용자를 추천해 줍니다. 추천된 유저가 본인의 친구라고 생각되면 연락처(전화번호 및 이메일)를 요청하여 잊고 지냈던 친구를 찾아보는 건 어떤가요?

####  [👉 사업기획서 살펴보기 👈](/business-plan.md)

### 💡 목적
> 이번 프로젝트의 목표는 서비스 오픈 전에 주요 이용 고객을 특정하고, 해당 타겟층의 수요를 분석하여 이에 맞는 서비스를 기획하는 것입니다. 이를 위해 시장 조사를 진행하고 얻은 인사이트를 반영하여 MVP를 제작하는 것입니다. 또한, Java Web 개발을 통해 서비스의 주요 기능을 구축함으로써 핵심 기술을 학습하고 실무 전반의 프로세스를 경험하고자 합니다.

### 🌼 개발 학습 목표
✅ **PR**을 활용한 **브랜치 전략을 경험** <br>
✅ **commit.template**를 활용한 **Commit 컨벤션 적용** <br>
✅ **JSP**와 **Servlet**을 활용한 동적 웹 페이지 구성 <br>
✅ **JDBC**를 이용한 데이터베이스 연동 및 **커넥션 풀 관리** <br>
✅ **JavaScript의 비동기 통신(AJAX) 구현**



### 🎯 MVP 주요 기능
| 기능 | 설명 |
| -- | -- |
| **로그인 및 회원가입** | 서비스를 이용하기 위해 기본적인 로그인과 회원가입을 할 수 있습니다. |
| **학교 기반 친구 추천** | 사용자가 입력한 학교 정보와 졸업 연도를 분석하여 적절한 사용자 추천합니다. |
| **친구 정보 요청** | 추천된 사용자의 연락처 정보를 요청할 수 있습니다. (요청 승인 시 공개)  |
| **친구 조회** | 요청을 승인한 사용자를 친구 목록 탭에서 확인할 수 있습니다. |
| **포인트 충전** | 사용자가 입력한 금액, 결제 방식을 이용해 친구 정보 요청 기능에서 사용되는 포인트를 충전할 수 있습니다. |


---

## 👊**2. Contributor**
<table>
  <tbody>
    <tr>
      <td align="center">
         <a href="https://github.com/danidana2">
          <img src="https://avatars.githubusercontent.com/u/150885774?v=4" width="150px;" alt=""/>
          <br /><sub><b> 최다영 </b></sub>
        </a>
        <br />
      </td>
      <td align="center">
          <a href="https://github.com/dyoun12">
          <img src="https://avatars.githubusercontent.com/u/107902336?v=4" width="150px;" alt=""/>
          <br /><sub><b> 김대연 </b></sub>
        </a>
        <br />
      </td>
      <td align="center">
        <a href="https://github.com/min-jp">
          <img src="https://avatars.githubusercontent.com/u/129049084?v=4" width="150px;" alt=""/>
          <br /><sub><b> 민정인 </b></sub>
        </a>
        <br />
      </td>
      <td align="center">
        <a href="https://github.com/EOTAEGYU">
          <img src="https://avatars.githubusercontent.com/u/123963462?v=4" width="150px;" alt=""/>
          <br /><sub><b> 어태규 </b></sub>
        </a>
        <br />
      </td>
    </tr>
  </tbody>
</table>

---

## 🔍**3. Stack & Tools**

| 분류 | 활용 기술 |
| -- | -- |
| FrontEnd | ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![JS](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) |
| Backend  | ![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white) ![JSP](https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white) ![Servlet](https://img.shields.io/badge/Servlet-007396?style=for-the-badge&logo=java&logoColor=white) ![JDBC](https://img.shields.io/badge/JDBC-4479A1?style=for-the-badge&logo=mysql&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white) |
| IDE      | ![Spring Tool Suite](https://img.shields.io/badge/STS-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white) |
| 시스템 환경 | ![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white) |
| 개발 도구 | ![DBeaver](https://img.shields.io/badge/DBeaver-372923?style=for-the-badge&logo=dbeaver&logoColor=white) |
| 협업 툴 | ![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)   ![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white) ![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white) |


--- 

## 🗺️**4. Architecture**
![architecture](/img/architecture.png)

| name | JDK | Tomcat | Mysql | Ubuntu |
|:--:|:--:|:--:|:--:|:--:|
| version | 17 | 10.1 | 8 | 24.01 |

---

## 📂**5. ERD**
![erd](/img/wefifty_erd.png)

### 😀 **user**

| Field           | Type         | Null |Key| Default | Extra          | desc |
| --------------- | ------------ | ---- |---| ------- | -------------- | ---- |
| user_id         | int          | NO   |PRI|         | auto_increment | |
| email           | varchar(255) | NO   |UNI|         |                | |
| pwd             | varchar(20)  | NO   |   |         |                | |
| nickname        | varchar(50)  | NO   |   |         |                | |
| bio             | text         | NO   |   |         |                | |
| name            | varchar(50)  | NO   |   |         |                | |
| gender          | char(1)      | NO   |   |         |                | |
| phone           | varchar(20)  | NO   |UNI|         |                | |
| birth           | date         | NO   |   |         |                | |
| profile_picture | varchar(255) | YES  |   |         |                | |
| point           | int          | NO   |   | 1000    |                | |

### 🎫 **friend_request**
| Field       | Type    | Null | Key | Default | Extra          | desc |
| ----------- | ------- | ---- | --- | ------- | -------------- | ---- |
| id          | int     | NO   | PRI |         | auto_increment | |
| sender_id   | int     | NO   | MUL |         |                | |
| receiver_id | int     | NO   | MUL |         |                | |
| status      | char(1) | NO   |     |         |                | |


### 🏫 **user_school**
| Field          | Type        | Null | Key | Default | Extra          | desc |
| -------------- | ----------- | ---- | --- | ------- | -------------- | ---- |
| user_school_id | int         | NO   | PRI |         | auto_increment | |
| user_id        | int         | NO   | MUL |         |                | |
| school_name    | varchar(50) | NO   |     |         |                | |
| grad_year      | year        | NO   |     |         |                | |
| school_type    | char(1)     | NO   |     |         |                | |


---
## 📒**5. Pages and Features** 

### 로그인
![Image](https://github.com/user-attachments/assets/cf682bb4-3f12-4f99-a35c-8d33d93ac6b2)

### 회원 가입
![Image](https://github.com/user-attachments/assets/cf4e16db-33d5-491a-9afd-cfad63f179c6)

### 친구 찾기
![Image](https://github.com/user-attachments/assets/e392d9eb-a139-4567-b1ec-de5fe64481c1)

### 친구 목록
![Image](https://github.com/user-attachments/assets/e31825c4-b99c-4983-9b34-d7916a205527)

### 친구 요청
![Image](https://github.com/user-attachments/assets/7baf8fd9-9f75-4b08-8218-4c9df5333e70)

### 포인트 충전
![Image](https://github.com/user-attachments/assets/95308da6-146f-4ee2-bb00-d8d7345053c5)

## 💣**6. Troubleshooting**
**문제 1 브렌치를 merge하지 않고 삭제 하는 문제 발생** <br>
> feature/login 브렌치에서 로그인 기능을 추가 하고 dev 브렌치에 merge가 성공적으로 된 줄 알고 로컬, 원격 브렌치를 모두 삭제하는 문제가 발생했다.

**문제 1 브렌치 복구 하기**<br>
1. 최근 작업 내용 확인
> 	`git reflog` : 최근 작업 내용을 확인하는 명령어
> 	![Image](https://github.com/user-attachments/assets/d3f2233e-91d3-4b0a-a2ed-eedde62543e1)
2. 해당 커밋을 기준으로 브렌치 복구
>	`git checkout -b feature/login d652904` : git checkout -b <branch 이름> <해당 커밋 해시>

<br>

**문제2. AJAX로 로드된 JSP의 Swiper 및 버튼 이벤트 미작동 문제** <br>
> AJAX를 사용하여 친구 추천 화면을 불러온 후, Swiper 네비게이션 버튼(이전/다음)이 보이지 않거나 작동하지 않고, "친구야 혹시 너니?" 버튼 클릭 이벤트도 동작하지 않는 현상이 있었습니다. <br><br>
> **오류 설명**
> -  AJAX로 JSP를 동적으로 불러왔을 때 JSP 내부에 포함된 스크립트들이 실행되지 않거나, 실행 타이밍이 맞지 않아 필요한 초기화가 이루어지지 않아 오류가 발생했습니다. <br>
>
> **원인**
> - JSP(친구 추천 화면) 내에 Swiper 초기화 및 버튼 이벤트 등록 스크립트가 포함되어 있었지만, AJAX로 innerHTML을 변경하면 브라우저는 새롭게 추가된 script 태그를 실행하지 않습니다.
> - DOMContentLoaded 이벤트는 페이지 최초 로딩 시에만 발생하므로, AJAX로 동적으로 삽입된 요소에서는 실행되지 않습니다.
> - DOMContentLoaded 이벤트 안에서 Swiper 초기화와 버튼 이벤트 등록이 실행되었기 때문에, AJAX로 불러온 후에는 DOMContentLoaded 이벤트가 다시 발생하지 않아 초기화가 되지 않습니다.
> - AJAX로 새로운 HTML을 삽입한 후 Swiper 초기화를 수행하지 않으면, 네비게이션 버튼(.swiper-button-next, .swiper-button-prev)이 정상적으로 표시되지 않습니다.
> - AJAX 요청으로 HTML을 교체하면 기존에 등록된 이벤트 리스너가 제거되므로, 새로운 요소에 대해 다시 이벤트 리스너를 등록해야 합니다. <br>

**문제 2 해결 방안** <br>
> **1.** Swiper 및 버튼 이벤트 등록을 별도 함수로 분리
> - AJAX 로드 성공 후 별도의 초기화 함수를 호출하여 Swiper와 버튼 이벤트를 재등록합니다.
> - Swiper 초기화 및 버튼 이벤트 등록을 별도의 함수(initRecommendationView())로 만들어 AJAX 요청 성공 후 직접 호출하도록 변경합니다.
> 
> **2.** AJAX 요청 성공 후 Swiper 및 버튼 이벤트 초기화 함수 호출
> - AJAX로 HTML을 불러온 후, initRecommendationView()를 호출하여 Swiper와 버튼 이벤트를 다시 등록하도록 변경합니다.
> **3.** 네비게이션 바의 친구 추천 버튼에서 AJAX 요청 시 loadFriendRecommendation() 함수를 호출하도록 통일합니다.

<br>

**문제 3. dev 브랜치에서 작업하여 push가 거부되는 문제** <br>
> 브랜치 전략 상 dev 브랜치는 타 브랜치에서 PR을 작성하여 merge 시키도록 설정해놓아서 dev 브랜치에서 작업한 내용을 push할 수 없는 문제가 발생했다.

**문제 3 해결 방안. commit한 내용을 타 브랜치로 옮기기.** <br>
#### 1. 타 브랜치로 가져갈 commit의 id를 복사
```bash
git logs
```

#### 2. 다른 브랜치로 이동
```bash
git checkout -b  <branch name>
```
#### 3. cherry-pick 실행
cherry-pick이란 현재 브랜치에서 특정 commit의 해당 변동사항을 적용할 수 있는 명령어이다.
```bash
git cherry-pick <commit-id>
```

#### 4. 변경사항 푸쉬
```bash
git push
```
<br>
<br>

---

## 😊**7. Review**
### 개선사항


### 배운점
