# Проект - Pooh JMS

## О проекте

В данном проекте написан аналог асинхронной очереди. Приложение запускает Socket и ждет клиентов.
В качестве клиента использован [cURL](https://curl.se/download.html). В качестве протокола - HTTP.
Сервер представляет собой систему обмена сообщениями на базе потокобезопасных классов из пакета `java.util.concurrent`.

Клиенты могут как отправлять так и принимать сообщения. Система может работать в двух режимах:

- Queue - все клиенты записывают (отправляют сообщения) и читают (получают сообщения) из одной и той же очереди.
  В примере ниже weather произвольное название очереди и temperature=18 значение параметра.  
- Topic - у каждого клиента своя собственная очередь. Другими словами, каждый клиент читает из своей собственной очереди, но также может записывать (отправлять сообщения) в очереди всех остальных клиентов.