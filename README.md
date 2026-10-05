# 🍽️ FoodDiary

> **음식 메뉴 랜덤 추천 & 식사 기록 Android 애플리케이션**
>
> 스마트폰응용 기말고사 프로젝트  
> Java · Android · SQLite

![Android](https://img.shields.io/badge/Platform-Android-green?logo=android)
![Java](https://img.shields.io/badge/Language-Java-orange?logo=java)
![SQLite](https://img.shields.io/badge/Database-SQLite-blue?logo=sqlite)
![Android Studio](https://img.shields.io/badge/IDE-Android%20Studio-purple?logo=androidstudio)

---

## 📌 프로젝트 소개

**FoodDiary**는 사용자가 음식 카테고리를 선택하여 메뉴를 랜덤으로 추천받고, 실제로 먹은 음식을 날짜·내용·사진과 함께 기록할 수 있는 **Android 기반 식사 기록 애플리케이션**입니다.

공공데이터포털의 음식 데이터를 활용하여 음식 정보를 구성하고, SQLite 데이터베이스에 저장된 데이터를 기반으로 메뉴 추천과 음식 정보 검색 기능을 구현했습니다.

또한 추천받은 메뉴를 바로 식사 기록으로 저장할 수 있도록 기능을 연결하여 **메뉴 추천 → 식사 → 기록**의 사용자 흐름을 하나의 앱에서 구현했습니다.

---

## 🎯 프로젝트 목표

- Android Studio와 Java를 활용한 모바일 애플리케이션 개발
- SQLite 기반 로컬 데이터베이스 설계 및 CRUD 구현
- 공공데이터를 활용한 실제 데이터 기반 서비스 구현
- RecyclerView를 이용한 목록 데이터 표시
- DatePicker 및 이미지 선택 기능 구현
- 사용자의 흐름을 고려한 화면 전환 및 UI 구성

---

## 🛠️ 기술 스택

| 구분 | 기술 |
|---|---|
| Platform | Android |
| Language | Java |
| UI | Android XML Layout |
| List UI | RecyclerView |
| Navigation / UI | ViewPager |
| Database | SQLite |
| Date | DatePicker |
| Image | Android Gallery / Image Picker |
| Development | Android Studio |

---

## ✨ 주요 기능

### 1. 🎲 랜덤 메뉴 추천

사용자가 음식 카테고리를 선택하면 해당 카테고리에 포함된 메뉴 중 하나를 랜덤으로 추천합니다.

- 카테고리 선택
- 카테고리별 메뉴 필터링
- 랜덤 메뉴 선택
- 추천 결과 확인
- 추천 메뉴를 식사 기록으로 바로 저장

```text id="b4w6jz"
카테고리 선택
      ↓
음식 데이터 조회
      ↓
카테고리 필터링
      ↓
랜덤 메뉴 선택
      ↓
추천 결과 표시
```

---

### 2. 🗂️ 음식 카테고리 및 정보 검색

저장된 음식 데이터를 조회하고 음식명을 기준으로 원하는 정보를 검색할 수 있습니다.

- 한식 / 중식 / 양식 등 카테고리 구분
- 음식명 검색
- 전체 음식 목록 조회
- 검색 결과 표시
- 음식 영양 정보 확인

---

### 3. 📓 식사 일기 기록

사용자가 먹은 음식을 날짜와 함께 기록하고 추가적인 내용을 작성할 수 있도록 구현했습니다.

- 식사 날짜 지정
- 제목 입력
- 내용 작성
- 음식 이미지 첨부
- 식사 기록 저장
- 저장된 기록 조회
- 기록 삭제

---

### 4. 🗑️ 식사 기록 관리

저장된 식사 기록을 목록으로 확인하고 필요하지 않은 기록을 삭제할 수 있습니다.

```text id="qu4q9p"
식사 기록 저장
      ↓
기록 목록 조회
      ↓
상세 내용 확인
      ↓
필요한 경우 삭제
```

---

### 5. 🗄️ 초기 데이터 자동 구축

앱을 처음 실행하면 공공데이터 기반의 음식 데이터를 로컬 데이터베이스에 저장하여 별도의 수동 입력 없이 음식 추천 및 검색 기능을 사용할 수 있도록 구성했습니다.

```text id="yrsn41"
앱 최초 실행
      ↓
음식 데이터 확인
      ↓
SQLite DB 초기화
      ↓
음식 데이터 적재
      ↓
서비스 이용
```

---

## 🏗️ 애플리케이션 구조

```text id="g7lc8w"
┌──────────────────────────────┐
│        Android UI            │
│ XML Layout / RecyclerView    │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│       Java Application       │
│                              │
│ - 메뉴 추천                   │
│ - 음식 검색                   │
│ - 식사 기록                   │
│ - 날짜 / 이미지 처리          │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│        SQLite Database       │
│                              │
│ - 음식 정보                   │
│ - 식사 기록                   │
└──────────────────────────────┘
```

---

## 🔄 주요 데이터 흐름

### 메뉴 추천

```text id="b1gqmq"
사용자
 ↓
카테고리 선택
 ↓
SQLite 음식 데이터 조회
 ↓
카테고리 조건 필터링
 ↓
랜덤 메뉴 선택
 ↓
추천 결과 표시
```

### 식사 기록

```text id="or6j8e"
추천 메뉴 / 사용자 선택
        ↓
날짜 선택
        ↓
제목 / 내용 입력
        ↓
이미지 선택
        ↓
SQLite 저장
        ↓
나의 기록에서 조회
```

---

## 🗄️ 데이터베이스

애플리케이션 내부에 SQLite 데이터베이스를 구성하여 음식 정보와 식사 기록 데이터를 관리했습니다.

### 주요 데이터

```text id="9h8k0d"
Food Data
├── 음식명
├── 카테고리
├── 영양 정보
└── 기타 음식 관련 데이터

Food Diary
├── 날짜
├── 제목
├── 내용
├── 음식 정보
└── 이미지
```

음식 정보는 공공데이터를 기반으로 구성하고, 사용자 식사 기록은 앱에서 직접 생성·조회·삭제할 수 있도록 구현했습니다.

---

## 📂 데이터 출처

본 프로젝트에서는 공공데이터포털에서 제공하는 음식 관련 데이터를 활용했습니다.

**음식 데이터셋**

🔗 https://www.data.go.kr/data/15100070/standard.do

공공데이터를 애플리케이션의 초기 데이터로 활용하여 실제 데이터를 기반으로 한 음식 조회 및 추천 기능을 구현했습니다.

---

## 🔧 주요 구현 내용

### 1. RecyclerView를 활용한 음식 목록

음식 데이터를 목록 형태로 표시하기 위해 `RecyclerView`를 사용했습니다.

```text id="7owk4p"
SQLite
  ↓
Food Data
  ↓
RecyclerView Adapter
  ↓
Food List
```

이를 통해 음식 정보가 여러 개 존재하는 상황에서도 반복적인 목록 UI를 구성할 수 있도록 구현했습니다.

---

### 2. 카테고리 기반 랜덤 추천

사용자가 선택한 카테고리를 기준으로 음식 데이터를 필터링한 뒤, 조건에 맞는 음식 중 하나를 랜덤으로 선택하도록 구현했습니다.

```text id="b1w3ae"
전체 음식
    ↓
카테고리 필터
    ↓
추천 대상 목록
    ↓
Random Selection
    ↓
추천 음식
```

---

### 3. DatePicker를 이용한 기록 날짜 지정

식사 기록을 저장할 때 현재 날짜뿐만 아니라 사용자가 원하는 날짜를 직접 선택할 수 있도록 `DatePicker`를 적용했습니다.

이를 통해 실제 식사일과 기록 작성일이 다른 경우에도 원하는 날짜에 기록을 저장할 수 있도록 구성했습니다.

---

### 4. 갤러리 이미지 첨부

Android의 이미지 선택 기능을 연동하여 사용자가 갤러리에서 이미지를 선택하고 식사 기록에 첨부할 수 있도록 구현했습니다.

```text id="1fnrc7"
식사 기록 작성
      ↓
이미지 선택
      ↓
Gallery
      ↓
선택 이미지
      ↓
식사 기록
```

---

### 5. 식사 기록 CRUD

식사 기록을 저장하고 조회하거나 삭제할 수 있도록 로컬 데이터베이스를 활용해 데이터 관리 기능을 구현했습니다.

```text id="s89v1c"
Create
  ↓
Read
  ↓
Update / Delete
```

이를 통해 Android 애플리케이션에서 로컬 DB를 이용한 기본적인 데이터 관리 흐름을 경험했습니다.

---

## 📱 화면 구성

### 1. 메인 화면

앱의 주요 기능으로 이동할 수 있는 홈 화면입니다.

<img width="360" height="640" alt="메인화면" src="https://github.com/user-attachments/assets/5d34f147-9a45-4e64-afe6-a94177ff338a" />

### 초기 데이터 구축

앱 최초 실행 시 음식 데이터를 SQLite 데이터베이스에 구성합니다.

<img width="360" height="640" alt="초기데이터1" src="https://github.com/user-attachments/assets/721ecf7b-231f-46aa-937f-ac33df665a4f" />

<img width="360" height="640" alt="초기데이터2" src="https://github.com/user-attachments/assets/7a1f09cd-226c-44f4-a9a9-b8a53c8b95f6" />

---

### 2. 기능 선택 화면

랜덤 추천, 음식 정보 조회, 나의 기록 기능 중 원하는 기능을 선택할 수 있습니다.

<img width="360" height="640" alt="선택화면" src="https://github.com/user-attachments/assets/f87ea180-df10-4aec-b6ee-ed51fbe6e0d3" />

---

### 3. 카테고리 선택 및 랜덤 추천

음식 카테고리를 선택하고 메뉴를 랜덤으로 추천받을 수 있습니다.

<img width="360" height="640" alt="카테고리선택" src="https://github.com/user-attachments/assets/c35b4475-ef15-49fc-b13f-a3ae15f95b20" />

#### 메뉴 뽑기

<img width="360" height="640" alt="메뉴뽑기" src="https://github.com/user-attachments/assets/ad569f01-8fec-4012-81df-01ee3da67cc6" />

#### 추천 결과

<img width="360" height="640" alt="메뉴결과" src="https://github.com/user-attachments/assets/1f41f2ec-6b55-47e8-8f21-cf23fbb56e86" />

#### 추천 메뉴 기록 저장

추천받은 메뉴를 식사 기록으로 바로 저장할 수 있습니다.

<img width="360" height="640" alt="기록저장" src="https://github.com/user-attachments/assets/3aee9ee5-aa9a-4fa8-b153-2cc2518760df" />

#### 기록 날짜 변경

`DatePicker`를 이용하여 식사 기록 날짜를 직접 선택할 수 있습니다.

<img width="360" height="640" alt="날짜변경" src="https://github.com/user-attachments/assets/44d6ab1d-bebc-43a4-b49b-bd58dd7e901c" />

#### 제목 입력

<img width="360" height="640" alt="제목입력" src="https://github.com/user-attachments/assets/563bf4f7-057d-4080-9b0a-7438af95b220" />

#### 내용 및 이미지 첨부

<img width="360" height="640" alt="이미지첨부" src="https://github.com/user-attachments/assets/47c752a4-2a4f-46bd-a5c4-910f672cabb4" />

---

### 4. 음식 정보

공공데이터 기반의 음식 정보를 조회하고 검색할 수 있습니다.

<img width="360" height="640" alt="음식정보" src="https://github.com/user-attachments/assets/e7be68e7-be3d-419c-aadc-9f40ef2823da" />

#### 정보 검색

<img width="360" height="640" alt="정보검색" src="https://github.com/user-attachments/assets/aadd3f3e-bcb8-466e-9bd0-b2331dc4c4f0" />

#### 전체 목록 표시

검색어가 없거나 검색 결과가 없는 경우 전체 음식 목록을 표시합니다.

<img width="360" height="640" alt="전체표시" src="https://github.com/user-attachments/assets/1caa7cad-4f85-456d-bbb9-6d5def38b545" />

---

### 5. 나의 기록

저장한 식사 기록을 한곳에서 확인하고 관리할 수 있습니다.

<img width="360" height="640" alt="기록목록" src="https://github.com/user-attachments/assets/bcb9f6fd-e8c7-4221-824b-3a18c6479bcb" />

#### 기록 상세 확인

<img width="360" height="640" alt="기록확인" src="https://github.com/user-attachments/assets/c2a384e8-e750-4e98-8b2d-dd802f13a86d" />

#### 기록 삭제

<img width="360" height="640" alt="기록삭제1" src="https://github.com/user-attachments/assets/eca27ae5-a891-4b19-96fc-1339d73dfa60" />

<img width="360" height="640" alt="기록삭제2" src="https://github.com/user-attachments/assets/3197b15d-e6a4-4598-9362-a84c6687a247" />

---

## 🗺️ 데이터베이스 구조

프로젝트에서 사용하는 음식 정보 및 식사 기록 데이터를 SQLite로 관리하도록 구성했습니다.

<img width="1024" height="559" alt="데이터베이스 구조" src="https://github.com/user-attachments/assets/fd32b1bf-fb05-484a-a60c-9155c054ee0f" />

---

## 💡 프로젝트에서 얻은 경험

### Android 애플리케이션 개발

Java와 Android SDK를 이용하여 여러 화면을 구성하고, 사용자의 입력에 따라 화면과 데이터를 처리하는 모바일 애플리케이션의 기본적인 구조를 경험했습니다.

### 로컬 데이터베이스 활용

SQLite를 이용하여 음식 데이터와 사용자 식사 기록을 저장하고 조회·삭제하는 데이터 처리 흐름을 구현했습니다.

### 공공데이터 활용

공공데이터포털에서 제공하는 실제 음식 데이터를 애플리케이션 초기 데이터로 활용하여 **외부 데이터를 서비스에 적용하는 경험**을 쌓았습니다.

### 사용자 경험을 고려한 기능 연결

단순히 메뉴를 추천하는 것에서 끝나지 않고, 추천 결과를 바로 식사 기록으로 저장할 수 있도록 기능을 연결하여 사용자 흐름을 구성했습니다.

```text id="j5i3s8"
메뉴 추천
   ↓
추천 결과
   ↓
기록 저장
   ↓
나의 기록
```

---

## 📚 프로젝트를 통해 학습한 내용

- Android Studio 기반 애플리케이션 개발
- Java 기반 Android 프로그래밍
- XML Layout을 이용한 UI 구성
- RecyclerView를 이용한 목록 처리
- SQLite 데이터베이스 활용
- 로컬 데이터 CRUD
- DatePicker를 이용한 날짜 선택
- Gallery / Image Picker 연동
- 공공데이터를 활용한 초기 데이터 구성
- 화면 전환 및 사용자 흐름 설계

---

## 🔮 개선 방향

- 음식 추천 알고리즘 고도화
- 음식별 상세 영양 정보 확대
- 식사 기록 수정 기능 추가
- 날짜별 / 카테고리별 기록 검색
- 식사 통계 및 차트 기능 추가
- 데이터 백업 및 복원 기능 추가
- UI/UX 개선 및 접근성 강화

---

<div align="center">

### 🍽️ FoodDiary

**Random Food Recommendation & Meal Diary Android App**

Educational Project · Java · Android · SQLite

</div>
