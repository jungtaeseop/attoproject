
## POST 로그인계정 만들기

http://localhost:8081/api/auth/signup

- Body json

`{
"username":"관리자1",
"userId":"rabc1",
"role": ["admin"],
"password": "123"
}`


---
## POST 로그인
http://localhost:8081/api/auth/signin

- json

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
- Body json

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

- Body json

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
