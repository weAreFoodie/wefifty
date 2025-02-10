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
| **친구 조회 기능** | 요청을 승인한 사용자를 친구 목록 탭에서 확인할 수 있습니다. |
| **포인트 충전 기능** | 사용자가 입력한 금액, 결제 방식을 이용해 친구 정보 요청 기능에서 사용되는 포인트를 충전할 수 있습니다. |


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

### 1. 로그인
![Image](https://github.com/user-attachments/assets/cf682bb4-3f12-4f99-a35c-8d33d93ac6b2)

### 회원 가입
![Image](https://github.com/user-attachments/assets/cf4e16db-33d5-491a-9afd-cfad63f179c6)

### 친구 찾기
![Image](https://github.com/user-attachments/assets/e392d9eb-a139-4567-b1ec-de5fe64481c1)

### 친구 목록

### 친구 요청

### 포인트 충전
![Image](https://github.com/user-attachments/assets/95308da6-146f-4ee2-bb00-d8d7345053c5)

## 💣**6. Troubleshooting**
**문제1. 브렌치를 merge하지 않고 삭제 하는 문제 발생** <br>
> feature/login 브렌치에서 로그인 기능을 추가 하고 dev 브렌치에 merge가 성공적으로 된 줄 알고 로컬, 원격 브렌치를 모두 삭제하는 문제가 발생했다.

**문제 1 브렌치 복구 하기**<br>
1. 최근 작업 내용 확인
> 	`git reflog` : 최근 작업 내용을 확인하는 명령어
> 	![Image](https://github.com/user-attachments/assets/d3f2233e-91d3-4b0a-a2ed-eedde62543e1)
2. 해당 커밋을 기준으로 브렌치 복구
>	`git checkout -b feature/login d652904` : git checkout -b <branch 이름> <해당 커밋 해시>


> **문제2. 문제 2 한줄 설명** <br>
> 문제 내용 요약 및 오류 설명
>
> **문제 2 해결 방안** <br>
> 문제 해결 방법

> **문제3. 문제 3 한줄 설명** <br>
> 문제 내용 요약 및 오류 설명
>
> **문제 3 해결 방안** <br>
> 문제 해결 방법

---

## 😊**7. Review**
### 개선사항


### 배운점
