# Проект - Pooh JMS

## О проекте

В данном проекте написан аналог асинхронной очереди. Приложение запускает Socket и ждет клиентов.
В качестве клиента использован [cURL](https://curl.se/download.html). В качестве протокола - HTTP.
Сервер представляет собой систему обмена сообщениями на базе потокобезопасных классов из пакета `java.util.concurrent`.

Клиенты могут как отправлять так и принимать сообщения. Система может работать в двух режимах:

- Queue - все клиенты записывают (отправляют сообщения) и читают (получают сообщения) из одной и той же очереди.
  В примере ниже weather произвольное название очереди и temperature=18 значение параметра.  
- Topic - у каждого клиента своя собственная очередь. Другими словами, каждый клиент читает из своей собственной очереди, но также может записывать (отправлять сообщения) в очереди всех остальных клиентов.

### Контакты:
[<img align="left" alt="telegram" width="18px" src="https://cdn.jsdelivr.net/npm/simple-icons@3.3.0/icons/telegram.svg" />][telegram]
[<img align="left" alt="gmail" width="18px" src="https://cdn.jsdelivr.net/npm/simple-icons@3.3.0/icons/gmail.svg" />][gmail]
[<img align="left" alt="LinkedIn" width="18px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/linkedin.svg" />][linkedin]


[telegram]: https://t.me/GrokDen
[gmail]: mailto:den.voiten@gmail.com
[linkedin]: https://www.linkedin.com/in/denis-voytenko-585488117/
