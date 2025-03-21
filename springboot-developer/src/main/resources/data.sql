-- 스프링 부트 실행시 데이터 베이스 초기화하는 파일(기본값 resources/data.sql, yml파일에서 name 설정 가능)
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목1', '내용1', NOW(), NOW())
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목2', '내용2', NOW(), NOW())
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목3', '내용3', NOW(), NOW())
