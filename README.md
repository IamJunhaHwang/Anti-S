## Anti-S 스미싱 방지

해당 프로젝트는 사용자들의 스미싱 피해 예방을 위한 안드로이드 기반의 어플리케이션입니다.

SMS를 수신하면 해당 메시지를 웹 서버에 전송하고 딥러닝 언어 모델이 메시지 내용을 분석해 스미싱 위험 여부를 사용자에게 알립니다.

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

1. `.apk` 혹은 `.aab`파일을 이용해 어플을 설치할 수 있습니다.

2. 기본적으로 방해 금지 모드가 `ON`으로 설정되어 있습니다. 스미싱 위험 감지를 위해서는 해당 모드를 `OFF`해야 합니다.

3. 방해 금지 모드가 `OFF`라면 메시지를 수신함과 동시에 푸시 알림을 통해 해당 메시지에 스미싱 위험이 있는지 알려줍니다.

4. `report/info` 프레임에서 최신 스미싱 관련 정보를 볼 수 있으며 스미싱 신고 또한 가능합니다.

:warning: 해당 어플리케이션은 사용자에게 스미싱 위험 여부를 알려주지만 차단은 하지 않습니다. 따라서 스미싱 위험이 있는 메시지를 받았을 때에는 사용자의 주의가 필요합니다.

### 사용 데이터

모델 생성에 사용된 데이터는 아래와 같습니다.

- **정상 대화 데이터(normal)**

  - 데이터 출처: [모두의 말뭉치](https://corpus.korean.go.kr/main.do)
  
  - 데이터명: 국립국어원 온라인 대화 말뭉치 2021(버전1.0)
  
- **스미싱 데이터(smishing + spam)**

  - 데이터 출처: 인피니그루

  - 데이터명: 스팸 문자
  
  
### 사용 예시

<img src="https://user-images.githubusercontent.com/46083287/206853535-aa62aa68-f89f-4539-bf9e-f30e58453173.jpg" width="30%"></img>


  
### 라이선스 

This project following Apache 2.0 License as written in LICENSE file

Copyright 2022 JunHa-Hwang & SeungDong-Lee, and klue contributors

Copyright (c) 2021 KLUE-baseline : [KLUE-baseline](https://github.com/KLUE-benchmark/KLUE-baseline)

### Author

- JunHa-Hwang, hwang_junha@naver.com, ChungBuk National Univ(Undergraduate).
- SeungDong-Lee, sdlee130@naver.com, ChungBuk National Univ(Undergraduate).
