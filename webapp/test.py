import requests

response = requests.post("http://localhost:10025/textcls",
                         data={"text": "[국외발신] 고객님 인증번호[7***5] 해외결제 695,000원 정상처리완료 문의:028268211"})
print(response.json())
