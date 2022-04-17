# grip-assignment

---
### 개발환경
`SpringBoot 2.6.6`
`Jpa`
`Mysql`
`H2`
`Swagger-open-api`

### 이미지 경로
`프로젝트 경로/img/{createdDate}`

### db_schema.sql 경로
`classpath:/db_schema.sql`

### api 주소
1. create : 입력으로 스크랩할 이미지의 URL과 이미지에 대한 제목 및 설명을 받아서 DB에 저장하고 이미지를 다운로드 하여 로컬 파일시스템에 저장.
    - `POST` link : `/api/v1/download/files`
2. modify : 내가 생성한 스크랩 데이터의 제목 및 설명 수정
    - `PUT` link : `/api/v1/download/files/{id}`
3. remove : 내가 생성한 스크랩 데이터 삭제
    - `DELETE` link : `/api/v1/download/files/{id}`
4. list : 생성된 모든 사용자의 스크랩 데이터 목록. 목록은 Pagination을 고려. 목록에는 로컬 서버에 저장한 이미지의 썸네일 URL과 제목, 조회수를 응답
    - `GET` link : `/api/v1/download/files/list?page=0`
5. get : 생성한 스크랩 데이터 1개의 상세 정보. 로컬 서버에 저장한 이미지 URL과 제목, 설명을 응답하고 조회수를 증가
    - `GET` link : `/api/v1/download/files/{id}`
    
### 설정된 세팅 외에 추가 설명
다운로드된 파일을 게시하는 게시물(통칭 '게시물')의 삭제가 되었을 경우에는 delete쿼리를 날리지 않고 status를 100으로 만드는 형태로 가져갔습니다.
하지만 게시물의 파일 자체를 삭제하지는 않았습니다.
이는 유저가 게시물 자체의 복구를 원하거나, 사진만을 복구를 원할 경우가 있을 수 있기 때문입니다.

api 독의 경우 스웨거 open api를 사용하였으나, java doc도 가능합니다.