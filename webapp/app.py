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


if __name__ == '__main__':
    app.run(debug=True)