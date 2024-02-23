## 실행 방법

이 프로젝트는 postgresql 데이터베이스를 사용합니다. 다음과 같은 순서로 실행해주세요.

1. DDL 폴더에 있는 ddlfile.sql 파일을 postgresql에서 실행합니다.
2. attoproject.jar 파일이 있는 폴더로 이동합니다.
3. attoproject.jar 파일을 실행합니다. 다음과 같은 명령어를 입력합니다. 이때, DB서버 ip, DB서버 port, DB명, DB 계정, 계정 패스워드는 자신의 환경에 맞게 변경해주세요.

- localhost:5432/attodb  == DB서버 ip : DB서버 port / DB명 
- atto == DB 계정 
- atto1234 == 계정 패스워드

`java -jar .\attoproject.jar -Dspring.datasource.url=jdbc:postgresql://localhost:5432/attodb -Dspring.datasource.username=atto -Dspring.datasource.password=atto1234`

## POST 새로운 사용자 계정 생성하는 API

http://localhost:8081/api/auth/signup

- Request Body: JSON
- "role": ["admin"], 또는 "role": ["user"]

`{
"username":"관리자1",
"userId":"rabc1",
"role": ["admin"],
"password": "123"
}`


---
## POST 로그인
http://localhost:8081/api/auth/signin

- Request Body: JSON

`{
    "userId":"rabc1",
    "password":"123"
}`

----
## POST 로그아웃

http://localhost:8081/api/auth/logout
- Request Headers
- Authorization : Bearer {{token}}

----
## POST host 등록 관리자

http://localhost:8081/api/hosts


- Request Headers
- Authorization : Bearer {{token}}


-  Request Body: JSON

`{
    "name":"네트워크1",
    "ip":"172.17.0.1"
}`

---
## GET hosts 전체조회
http://localhost:8081/api/hosts/list


- Request Headers
- Authorization : Bearer {{token}}

---
## GET host 단건 조회
http://localhost:8081/api/hosts/{host_id}

- Request Headers
- Authorization : Bearer {{token}}

---

## PUT host 수정
http://localhost:8081/api/hosts/{host_id}

- Request Headers
- Authorization : Bearer {{token}}


- Request Body: JSON

`{
"name":"server1",
"ip" : "1.1.1.4"
}`

---

## DELETE host 삭제
http://localhost:8081/api/hosts/{host_id}


- Request Headers
- Authorization : Bearer {{token}}

---

## GET hostStatus 단건 조회
http://localhost:8081/api/hosts/check/{host_id}

- Request Headers
- Authorization : Bearer {{token}}

---

## GET hostStatus 전체 조회
http://localhost:8081/api/hosts/check-all

- Request Headers
- Authorization : Bearer {{token}}

---

## GET auditLog 전체조회
http://localhost:8081/api/audit-log/list

- Request Headers
- Authorization : Bearer {{token}}
