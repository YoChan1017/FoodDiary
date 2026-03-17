# 스마트폰응용 기말고사 프로젝트
# 🍽️ FoodDiary

> **음식 메뉴 랜덤 추천 & 식사 기록 Android 앱**

[![Android](https://img.shields.io/badge/Platform-Android-green?logo=android)](https://developer.android.com/)
[![Java](https://img.shields.io/badge/Language-Java-orange?logo=java)](https://www.java.com/)
[![SQLite](https://img.shields.io/badge/Database-SQLite-blue?logo=sqlite)](https://www.sqlite.org/)
[![Android Studio](https://img.shields.io/badge/IDE-Android%20Studio-purple?logo=androidstudio)](https://developer.android.com/studio)

---

## 📌 프로젝트 소개

"오늘 뭐 먹지?" 라는 고민을 해결하는 **음식 메뉴 랜덤 추천 앱**입니다.  
카테고리별로 메뉴를 랜덤 추천받고, 먹은 음식을 날짜·사진과 함께 다이어리로 기록할 수 있습니다.

| 항목 | 내용 |
|------|------|
| **프로젝트명** | FoodDiary |
| **개발 기간** | 스마트폰응용 기말고사 프로젝트 |
| **개발 환경** | Android Studio |
| **사용 언어** | Java |
| **데이터베이스** | SQLite (Room) |
| **데이터 출처** | [공공데이터포털 - 음식 데이터셋](https://www.data.go.kr/data/15100070/standard.do) |

---

## 🛠️ 기술 스택

- **Frontend** : Android XML Layout, RecyclerView, ViewPager
- **Backend** : Java (Android SDK)
- **Database** : SQLite — 음식 정보 및 사용자 기록 저장
- **기타** : DatePicker, Image Picker (갤러리 연동)

---

## ✨ 주요 기능

| # | 기능 | 설명 |
|---|------|------|
| 1 | 🎲 **랜덤 메뉴 추천** | 카테고리 선택 후 메뉴를 랜덤으로 추천 |
| 2 | 🗂️ **카테고리 필터링** | 한식, 중식, 양식 등 원하는 카테고리로 필터링 |
| 3 | 🍜 **음식 정보 검색** | 음식명으로 영양 정보 검색 및 전체 목록 조회 |
| 4 | 📓 **식사 일기 기록** | 날짜·제목·내용·이미지 첨부로 식사 기록 저장 |
| 5 | 🗑️ **기록 관리** | 저장된 기록 확인 및 삭제 |
| 6 | 🗄️ **초기 데이터 자동 구축** | 앱 최초 실행 시 공공데이터 기반 음식 DB 자동 세팅 |

---

## 📱 화면 구성

### 1. 메인 화면
앱 진입 시 보이는 홈 화면으로, 각 기능으로 이동하는 네비게이션을 제공합니다.

<img width="360" height="640" alt="메인화면" src="https://github.com/user-attachments/assets/5d34f147-9a45-4e64-afe6-a94177ff338a" />

#### 1-1. 초기 데이터 구축
앱 최초 실행 시 공공데이터 기반의 음식 데이터를 SQLite DB에 자동으로 적재합니다.

<img width="360" height="640" alt="초기데이터1" src="https://github.com/user-attachments/assets/721ecf7b-231f-46aa-937f-ac33df665a4f" />
<img width="360" height="640" alt="초기데이터2" src="https://github.com/user-attachments/assets/7a1f09cd-226c-44f4-a9a9-b8a53c8b95f6" />

---

### 2. 선택 화면
랜덤 추천 / 음식 정보 / 나의 기록 중 원하는 기능을 선택하는 화면입니다.

<img width="360" height="640" alt="선택화면" src="https://github.com/user-attachments/assets/f87ea180-df10-4aec-b6ee-ed51fbe6e0d3" />

---

### 3. 카테고리 선택 & 랜덤 추천
카테고리를 선택하고 메뉴를 랜덤으로 추천받는 핵심 기능입니다.

<img width="360" height="640" alt="카테고리선택" src="https://github.com/user-attachments/assets/c35b4475-ef15-49fc-b13f-a3ae15f95b20" />

#### 3-1. 카테고리 선택 후 메뉴 뽑기
<img width="360" height="640" alt="메뉴뽑기" src="https://github.com/user-attachments/assets/ad569f01-8fec-4012-81df-01ee3da67cc6" />

#### 3-2. 메뉴 뽑기 예시
<img width="360" height="640" alt="메뉴결과" src="https://github.com/user-attachments/assets/1f41f2ec-6b55-47e8-8f21-cf23fbb56e86" />

#### 3-3. 뽑은 메뉴 기록 저장
추천받은 메뉴를 바로 다이어리에 저장할 수 있습니다.

<img width="360" height="640" alt="기록저장" src="https://github.com/user-attachments/assets/3aee9ee5-aa9a-4fa8-b153-2cc2518760df" />

#### 3-4. 기록 저장 시 날짜 변경
DatePicker를 통해 기록 날짜를 자유롭게 수정할 수 있습니다.

<img width="360" height="640" alt="날짜변경" src="https://github.com/user-attachments/assets/44d6ab1d-bebc-43a4-b49b-bd58dd7e901c" />

#### 3-5. 제목 입력
<img width="360" height="640" alt="제목입력" src="https://github.com/user-attachments/assets/563bf4f7-057d-4080-9b0a-7438af95b220" />

#### 3-6. 내용 작성 및 이미지 첨부
갤러리에서 이미지를 선택하여 기록에 첨부할 수 있습니다.

<img width="360" height="640" alt="이미지첨부" src="https://github.com/user-attachments/assets/47c752a4-2a4f-46bd-a5c4-910f672cabb4" />

---

### 4. 음식 정보
공공데이터 기반의 음식 영양 정보를 검색하고 조회하는 화면입니다.

<img width="360" height="640" alt="음식정보" src="https://github.com/user-attachments/assets/e7be68e7-be3d-419c-aadc-9f40ef2823da" />

#### 4-1. 정보 검색
<img width="360" height="640" alt="정보검색" src="https://github.com/user-attachments/assets/aadd3f3e-bcb8-466e-9bd0-b2331dc4c4f0" />

#### 4-2. 검색 결과 없을 시 전체 표시
검색어가 없거나 결과가 없으면 전체 음식 목록을 표시합니다.

<img width="360" height="640" alt="전체표시" src="https://github.com/user-attachments/assets/1caa7cad-4f85-456d-bbb9-6d5def38b545" />

---

### 5. 나의 기록
저장된 식사 일기를 모아보고 관리하는 화면입니다.

<img width="360" height="640" alt="기록목록" src="https://github.com/user-attachments/assets/bcb9f6fd-e8c7-4221-824b-3a18c6479bcb" />

#### 5-1. 기록 확인
<img width="360" height="640" alt="기록확인" src="https://github.com/user-attachments/assets/c2a384e8-e750-4e98-8b2d-dd802f13a86d" />

#### 5-2. 기록 삭제
<img width="360" height="640" alt="기록삭제1" src="https://github.com/user-attachments/assets/eca27ae5-a891-4b19-96fc-1339d73dfa60" />
<img width="360" height="640" alt="기록삭제2" src="https://github.com/user-attachments/assets/3197b15d-e6a4-4598-9362-a84c6687a247" />

---

## 🗄️ 데이터베이스 구조

공공데이터를 기반으로 음식 정보 테이블과 사용자 기록 테이블을 설계하였습니다.

<img width="1024" height="559" alt="데이터베이스 구조" src="https://github.com/user-attachments/assets/fd32b1bf-fb05-484a-a60c-9155c054ee0f" />

---

## 📂 데이터 출처

> **음식 데이터셋** : 공공데이터포털  
> 🔗 https://www.data.go.kr/data/15100070/standard.do

---

## 💡 배운 점 & 느낀 점

- Android Studio 환경에서 **SQLite DB 설계 및 CRUD** 구현 경험
- **공공데이터를 앱에 연동**하여 실제 데이터 기반 서비스 구성
- RecyclerView, DatePicker, Image Picker 등 **Android 주요 컴포넌트** 활용
- 사용자 흐름(UX)을 고려한 **화면 전환 및 네비게이션 설계** 경험
