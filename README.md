## Anti-S 스미싱 방지 어플

해당 프로젝트는 사용자들의 스미싱 피해 예방을 위한 안드로이드 기반의 어플리케이션입니다.

SMS를 수신하면 해당 메시지를 웹 서버에 전송하고 딥러닝 언어 모델이 메시지 내용을 분석해 스미싱 위험 여부를 사용자에게 알립니다.

이 레포지토리는 데모 어플로 저희의 아이디어를 간략하게 구현한 것입니다. 

**현재는 서버를 운용하고 있지 않으므로 서비스를 제공하지 않습니다. [코드만 open되어 있습니다]**

--------

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

  - github를 `clone`한 후에 아래의 명령어를 사용하면 됩니다.
    ```bash
    pip install -r requirements.txt
    ```

---------

### 의존성

- python 3.8.x
  - python library 설치는 위의 설치 방법을 따라하시면 됩니다.
  
- Andriod & API Level
  - Andriod version: `Andriod 11.0`
  - min sdk: `19`
  - target sdk:`32`

----

### 사용 방법

1. `.apk` 혹은 `.aab`파일을 이용해 어플을 설치할 수 있습니다.

2. 기본적으로 방해 금지 모드가 `ON`으로 설정되어 있습니다. 스미싱 위험 감지를 위해서는 해당 모드를 `OFF`해야 합니다.

3. 방해 금지 모드가 `OFF`라면 메시지를 수신함과 동시에 푸시 알림을 통해 해당 메시지에 스미싱 위험이 있는지 알려줍니다.

4. `report/info` 프레임에서 최신 스미싱 관련 정보를 볼 수 있으며 스미싱 신고 또한 가능합니다.

:warning: 해당 어플리케이션은 사용자에게 스미싱 위험 여부를 알려주지만 차단은 하지 않습니다. 따라서 스미싱 위험이 있는 메시지를 받았을 때에는 사용자의 주의가 필요합니다.

----------

### 사용 데이터

모델 생성에 사용된 데이터는 아래와 같습니다.

- **정상 대화 데이터(normal)**

  - 데이터 출처: [모두의 말뭉치](https://corpus.korean.go.kr/main.do)
  
  - 데이터명: 국립국어원 온라인 대화 말뭉치 2021(버전1.0)
  
- **스미싱 데이터(smishing + spam)**

  - 데이터 출처: 인피니그루

  - 데이터명: 스팸 문자
  
-------  

### 사용 모델

- 스미싱 위험 여부 예측을 위해 `klue-RoBERTa-small`모델을 사용하였습니다. 해당 모델은 [huggingface-hub](https://huggingface.co/klue/roberta-small)에 공개되어 있습니다.

- 모델은 Pretrain된 `klue-RoBERTa-small` 위에 2개의 Label을 가지는 `Linear Layer` 하나를 쌓은 구조입니다.

- 모델의 Pretrain에 관한 사항은 KLUE의 [공식 Paper](https://arxiv.org/pdf/2105.09680.pdf)를 참고해주세요.

- Fine-Tuning에 대한 Training Argument및 Train 방법은 본 레포지토리의 [model 폴더의 노트북 파일](https://github.com/IamJunhaHwang/Anti-S/blob/main/model/klue-roberta-small.ipynb)을 참고해주세요.

- 토크나이저의 경우 `klue-RoBERTa-small`의 Pretrain에 사용된 것을 사용하되 `SPECIAL_TOKEN`으로 `[URL]`라는 토큰을 추가하였습니다.
  - 입력의 전처리 과정에서 `url`을 정규표현식으로 추출한 후 `[URL]`로 바꾸어주게 됩니다.
  - 해당 토큰으로 인해 모델은 `url`이 등장했는지 안했는지를 추론의 근거로 사용할 수 있게 됩니다.


---------

### 사용 예시

<img src="https://user-images.githubusercontent.com/46083287/206853535-aa62aa68-f89f-4539-bf9e-f30e58453173.jpg" width="30%"></img>


-------

### 라이선스 

This project following Apache 2.0 License as written in LICENSE file

Copyright 2022 JunHa-Hwang & SeungDong-Lee, and klue contributors

Copyright (c) 2021 KLUE-baseline : [KLUE-baseline](https://github.com/KLUE-benchmark/KLUE-baseline)

### Author

- JunHa-Hwang, hwang_junha@naver.com, ChungBuk National Univ(Undergraduate).
- SeungDong-Lee, sdlee130@naver.com, ChungBuk National Univ(Undergraduate).
