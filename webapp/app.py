from flask import Flask
from flask_restful import Resource, Api
import numpy as np
import torch
import re
from torch.utils.data import DataLoader

from transformers import AutoModelForSequenceClassification, AutoTokenizer, AutoModel

app = Flask(__name__)
api = Api(app)

MODEL_NAME = '/home/egg2018037024/lab_pycharm/klue_best_model'
TOK_NAME = '/home/egg2018037024/lab_pycharm/klue_best_tok'
device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')

tokenizer = AutoTokenizer.from_pretrained(TOK_NAME)
model = AutoModelForSequenceClassification.from_pretrained(MODEL_NAME)
model.to(device)


def preprocess(text: str, only_kor: bool = True):
    """한국어 문장을 옵션에 맞게 전처리"""
    # 한국어 모음과 특수 문자, 숫자 및 영어 제거
    if only_kor:
        text = re.sub(f"[^가-힣| |]+", "", text)
    else:
        text = re.sub(f"[^가-힣|ㄱ-ㅎ|0-9|]+", "", text)  # f-문자열을 적용시켜 SPECIALS를 넣어줌.

    # 연속 공백 제거
    text = re.sub(" +", " ", text)

    # 좌우 불필요한 공백 제거
    return text.strip()


def url_encode(text: str):
    "메시지 상에 존재하는 url을 [url] 토큰으로 만들기"

    # URL 추출할 정규표현식 생성
    url_regex = r"(https?:\/\/)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&\/\/=]*)"

    reg = re.compile(url_regex)

    res = reg.search(text)

    if res == None:
        return text

    else:
        indexes = res.span()

        url_txt = text[indexes[0]:indexes[1]]

        return text.replace(url_txt, " 윪 ")





if __name__ == '__main__':
    app.run(debug=True)