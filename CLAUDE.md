# 해동여객 관리 시스템

## Claude에게 - 협업 방식

이 프로젝트는 **학습을 목적으로 한다.**
사용자가 직접 코드를 작성하고 이해하는 것이 목표이므로, 아래 규칙을 반드시 따른다.

### 절대 하지 말 것
- 요청하지 않았는데 완성된 코드를 먼저 작성하지 않는다
- 여러 파일을 한꺼번에 만들어주지 않는다
- "이렇게 하면 됩니다" 하고 답만 주지 않는다

### 반드시 할 것
- 단계를 시작할 때 **왜 이게 필요한지** 먼저 설명한다
- 코드 작성 전에 **무엇을 만들려는지 구조를 설명**하고 사용자가 이해했는지 확인한다
- **코드는 Claude가 먼저 제안**하고, 사용자가 직접 입력하면서 이해하게 한다
- 사용자가 입력한 코드를 검토하고 **잘된 점 + 수정할 점**을 구체적으로 피드백한다
- 새로운 개념이 나오면 **짧은 예시로 원리**를 설명한다

### 피드백 방식
```
잘된 점: ...
수정 필요: ...
힌트: ...
```

---

## 프로젝트 개요

버스 회사(해동여객)의 인하우스 관리 시스템.
기존 Excel 관리 → 웹 시스템으로 전환.

---

## 기술 스택

| 영역 | 기술 |
|------|------|
| Backend | Java 17, Spring Boot 3.x, Spring Security, MyBatis |
| Database | PostgreSQL (AWS RDS) |
| Frontend | Vue 3, Vite, Pinia, Vue Router, Axios |
| Infra | Docker Desktop, Docker Compose, Nginx, AWS RDS |

---

## 구현 기능 (우선순위 순)

### 1단계
- [x] AWS RDS 연결 및 DB 스키마 설계
- [x] Docker Compose + Nginx 구성 (frontend / backend / nginx 3개 컨테이너)
- [x] JWT 로그인 / 권한 제어
- [x] 기사 관리 CRUD
- [x] 노선 관리 CRUD
- [x] 차량 관리 CRUD
- [x] 배차 현황
- [x] 운전직 전속현황 (차량별 전속기사 배정 + 대무자 자동 분류)

### 2단계 (추후)
- [ ] 기사 프로필 이미지 업로드
- [ ] 운행 스케줄
- [ ] 노선 경로 지도 연동 (카카오맵)
- [ ] 정비 이력

---

## 권한 구조

| 역할 | 권한 |
|------|------|
| ADMIN | 전체 접근 + 계정 관리 |
| MANAGER | 기사 / 차량 / 배차 / 노선 관리 |
| DRIVER | 본인 배차·스케줄 조회만 |

---

## DB 테이블

### 테이블 네이밍 규칙
- 모든 테이블은 `hd_` prefix를 사용한다
- 예: `hd_user`, `hd_driver`, `hd_bus_route`
- 모든 테이블과 컬럼에는 `COMMENT`를 반드시 작성한다

### 테이블 목록
- `hd_user` — 로그인 계정 (role 포함)
- `hd_driver` — 기사 정보
- `hd_bus_route` — 노선 (번호, 이름, 보유 대수)
- `hd_bus_vehicle` — 차량 (번호판, 노선, 상태)
- `hd_dispatch` — 배차 (기사 ↔ 차량)

---

## 패키지 구조 규칙

### 백엔드 도메인 기준 패키지
모든 파일은 도메인(업무) 단위 패키지 아래에 위치한다.
레이어(controller/service/mapper) 기준이 아닌 **도메인 기준**으로 묶는다.

```
com.haedong.backend/
    ├── auth/         ← Controller, Service, Mapper, DTO, Entity 전부 여기
    ├── driver/
    ├── route/
    ├── vehicle/
    ├── dispatch/
    └── common/       ← JwtUtil, JwtFilter, SecurityConfig 등 공통
```

### MyBatis XML 위치
mapper XML도 도메인 기준으로 분리한다.

```
resources/mapper/
    ├── auth/
    ├── driver/
    ├── route/
    ├── vehicle/
    └── dispatch/
```

---

## Git 구성

- 저장소 형태: **Monorepo (Public)**
- `.env` — Git 제외 (민감정보)
- `.env.example` — Git 포함 (형식만, 값 없음)
- `application-local.properties` — Git 제외 (로컬 DB 접속 정보)

---

## Spring Profile

| Profile | 용도 | DB 설정 방식 |
|---------|------|-------------|
| local | 로컬 개발 | application-local.properties에 직접 입력 |
| prod | Docker 운영 | 환경변수 (${DB_URL} 등) |

---

## Docker 구조

```
container/
  ├── .env                    ← 민감정보 (Git 제외)
  ├── .env.example            ← 형식 템플릿 (Git 포함)
  ├── docker-compose.yml
  ├── backend/Dockerfile
  ├── frontend/
  │   ├── Dockerfile          ← 멀티 스테이지 빌드 (Node → Nginx)
  │   └── nginx.conf          ← 3000 포트, Vue Router try_files
  └── nginx/
      ├── Dockerfile
      └── conf/
          ├── nginx.conf      ← 글로벌 설정
          └── default.conf    ← /api/* → backend, /* → frontend
```

### Docker 명령어
```bash
# 빌드 및 실행
docker compose up --build -d

# 특정 서비스만 재빌드
docker compose up --build -d backend

# 로그 확인
docker compose logs -f
```

---

## 현재 상태

- 1단계 전체 구현 완료
- Docker 로컬 테스트 완료
- 다음 예정: AWS Lightsail 배포, GitHub 업로드
