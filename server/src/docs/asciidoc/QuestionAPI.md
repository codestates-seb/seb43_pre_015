# Question API 요약 (로컬 환경) 


+ `question`
    1. `POST` - `/questions/ask`: 질문 등록
    1. `GET` - `/questions?page=1&size=15`: 질문 목록 조회
    1. `GET` - `/questions/{question_id}`: 질문 상세 조회
    1. `PATCH` - `/questions/{question_id}`: 질문 수정
    1. `DELETE` - `/questions/{question_id}`: 질문 삭제

***
### 0️⃣ 질문 등록 
+ `Status Code`
  + `201: created`
  + `400: Bad request`
  + `500: Internal Server Error`


request-fields

| **Path** | **Type** | **Description** |
|:--------:|:--------:|:---------------:|
| memberId |  Number  |      회원 id      |
|  title   |  String  |      질문 제목      |
| content  |  String  |      질문 목록      |

request ex)
```ts
POST
http://localhost:8080/questions/ask

{
    "memberId": 1,
    "title": "제목",
    "content": "내용"
}
```
response

```ts
HTTP/1.1 201 Created
Location: /questions/1
```
***
### 1️⃣ 질문 목록 조회

+ `Status Code`
    + `200: ok`
    + `400: Bad request`
    + `500: Internal Server Error`

path-parameters

| **Parameter** |    **Description**    |
|:-------------:|:---------------------:|
|     page      |      현재 page 번호       |
|     size      |  Page 사이즈(15,30,45) |


request ex)

```ts
GET
http://localhost:8080/questions?page=1&size=15
```

response

```ts
HTTP/1.1 200 OK

{
    "questions": [
        {
            "questionId": 2,
            "memberId": 1,
            "title": "제목",
            "content": "내용",
            "displayName": "cm3222",
            "createdTime": "2023-04-20T21:06:04.407497",
            "modifiedTime": "2023-04-20T21:06:04.407497",
            "answerCount": 3
        },
        {
            "questionId": 1,
            "memberId": 1,
            "title": "sdf",
            "content": "sf",
            "displayName": "cm3222",
            "createdTime": "2023-04-20T20:58:31.503427",
            "modifiedTime": "2023-04-20T20:58:31.503427",
            "answerCount": 0
        }
    ],
    "pageInfo": {
        "page": 1,
        "size": 15,
        "totalElements": 2,
        "totalPages": 1
    }
}
```
***
### 2️⃣ 질문 상세 조회

+ `Status Code`
    + `200: ok`
    + `400: Bad request`
    + `500: Internal Server Error`

| **Parameter** |    **Description**    |
|:-------------:|:---------------------:|
|  question_id   |      질문 식별자       |

request ex)

```ts
GET
http://localhost:8080/questions/2
```

response

```ts
HTTP/1.1 200 OK

{
    "questionId": 2,
    "memberId": 1,
    "title": "제목",
    "content": "내용",
    "displayName": "cm3222",
    "createdTime": "2023-04-20T21:06:04.407497",
    "modifiedTime": "2023-04-20T21:06:04.407497",
    "answers": [
        {
            "answerId": 1,
            "content": "test",
            "like": 0,
            "selectionStatus": false,
            "memberId": null,
            "questionId": null
        },
        {
            "answerId": 2,
            "content": "test",
            "like": 0,
            "selectionStatus": false,
            "memberId": null,
            "questionId": null
        },
        {
            "answerId": 3,
            "content": "test",
            "like": 0,
            "selectionStatus": false,
            "memberId": null,
            "questionId": null
        }
    ]
}
```
***
### 3️⃣ 질문 수정
+ `Status Code`
    + `200: ok`
    + `400: Bad request`
    + `500: Internal Server Error`

| **Parameter** |    **Description**    |
|:-------------:|:---------------------:|
|  question_id   |      질문 식별자       |

request-fields

|  **Path**  | **Type** | **Description** |
|:----------:|:--------:|:---------------:|
|   title    |  String  |      질문 제목      |
|  content   |  String  |      질문 목록      |

request ex)

```ts
PATCH
http://localhost:8080/questions/1

{
    "title": "blah",
    "content": "blah blah"
}
```

response
```ts
HTTP/1.1 200 OK

{
    "questionId": 1,
    "memberId": 1,
    "title": "blah",
    "content": "blah blah",
    "displayName": "cm3222",
    "createdTime": "2023-04-20T20:58:31.503427",
    "modifiedTime": "2023-04-20T20:58:31.503427",
    "answers": []
}
```
***
### 4️⃣ 질문 삭제 

+ `Status Code`
    + `204: No Content`
    + `400: Bad request`
    + `500: Internal Server Error`


| **Parameter** |    **Description**    |
|:-------------:|:---------------------:|
|  question_id  |      질문 식별자       |

request ex)
```ts
DELETE
http://localhost:8080/questions/2
```

response
```ts
HTTP/1.1 204 No Content
```