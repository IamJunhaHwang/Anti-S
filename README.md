## Anti-S 스미싱 방지

### 설치 방법

- 어플 설치 방법
  - [Anti-S/android/app/app/release](https://github.com/IamJunhaHwang/Anti-S/tree/main/android/app/app/release) 경로의 `app-release.apk` 파일을 다운로드한 후에 안드로이드에 설치하면 됩니다.
  
  - 파일 다운로드는 아래와 같이 가능합니다.
    ```bash
    git clone https://github.com/IamJunhaHwang/Anti-S.git
    cd Anti-S/android/app/app/release
    ```
    
  - `.aab`파일로 설치를 하기 위해서는 추가 절차가 필요합니다. [Android Developer 공식 문서](https://developer.android.com/studio/command-line/bundletool?hl=ko)를 참고해주세요.

- 파이썬 라이브러리 설치 방법
  ```bash
  pip install -r requirements.txt
  ```

### 의존성

- python 3.8.x
  - python library 설치는 위의 설치 방법을 따라하시면 됩니다.
  
- Andriod & API Level
  - Andriod version: `Andriod 11.0`
  - min sdk: `19`
  - target sdk:`32`

### 사용 방법

TBD

### 사용 데이터

모델 생성에 사용된 데이터는 아래와 같습니다.

- **정상 대화 데이터(normal)**

  - 데이터 출처: [모두의 말뭉치](https://corpus.korean.go.kr/main.do)
  
  - 데이터명: 국립국어원 온라인 대화 말뭉치 2021(버전1.0)
  
- **스미싱 데이터(smishing + spam)**

  - 데이터 출처: 인피니그루

  - 데이터명: 스팸 문자
  
### 라이선스 

This project following Apache 2.0 License as written in LICENSE file

Copyright 2022 JunHa-Hwang & SeungDong-Lee, and klue contributors

Copyright (c) 2021 KLUE-baseline : [KLUE-baseline](https://github.com/KLUE-benchmark/KLUE-baseline)

### Author

- JunHa-Hwang, hwang_junha@naver.com, ChungBuk National Univ(Undergraduate).
- SeungDong-Lee, sdlee130@naver.com, ChungBuk National Univ(Undergraduate).
