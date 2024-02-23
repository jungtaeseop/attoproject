java 


POST
로그인계정 만들기
http://localhost:8081/api/auth/signup

Body
json
{
    "username":"관리자1",
    "userId":"rabc1",
    "role": ["admin"],
    "password": "123"
}

POST
로그인
http://localhost:8081/api/auth/signin

json
{
    "userId":"rabc1",
    "password":"123"
}

----
POST
로그아웃
http://localhost:8081/api/auth/logout

Authorization : Bearer {{token}}

----
POST
host 등록 관리자
http://localhost:8081/api/hosts
﻿

Request Headers
Authorization : Bearer {{token}}

Body
raw (json)
json
{
    "name":"네트워크1",
    "ip":"172.17.0.1"
}
