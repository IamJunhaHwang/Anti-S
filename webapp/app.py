from flask import Flask, jsonify, request
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


class klue_Dataset(torch.utils.data.Dataset):
    def __init__(self, dataset, label):  # 전처리된 데이터 셋이 들어옴
        self.dataset = dataset
        self.label = label

    def __getitem__(self, idx):
        # gradient 계산에 영향을 주지 않게 clone().detach() 실행

        item = {key: val[idx].clone().detach() for key, val in self.dataset.items()}
        item['label'] = torch.tensor(self.label[idx])

        return item

    def __len__(self):  # 샘플 수
        return len(self.label)

@app.route('/textcls', methods=['POST'])
def textcls():
    if request.method == 'POST':
        try:
            jsonf = request.get_json()
            text = jsonf["msg"]
            print(text, type(text))
            url_encoded = url_encode(text)
            preprocessed = preprocess(url_encoded, True)
            input_text = preprocessed.replace('윪', '[URL]')

            test_tok = klue_Dataset(tokenizer(input_text, return_tensors="pt",
                                              max_length=256,
                                              padding=True,
                                              truncation=True,
                                              add_special_tokens=True), [1])

            test_dataloader = DataLoader(test_tok, batch_size=128, shuffle=False)

            model.eval()
            for i, data in enumerate(test_dataloader):
                with torch.no_grad():
                    outputs = model(
                        input_ids=data['input_ids'].to(device),
                        attention_mask=data['attention_mask'].to(device),
                        token_type_ids=data['token_type_ids'].to(device)
                    )
                logits = outputs[0]
                logits = logits.detach().cpu().numpy()
                result = np.argmax(logits, axis=-1)
                answer = result.tolist()
                answer = answer[-1]
                answer = str(answer)

            rponse = {"result": answer}
            print(rponse)

            return jsonify(rponse), 200

        except Exception as e:
            return {'error': str(e)}

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=10025,debug=True)
