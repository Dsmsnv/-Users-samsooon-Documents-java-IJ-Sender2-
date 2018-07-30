# Sender
Программа отсылает письмо на Email с текстом из файла text заменяя &name% и &phone% на данные введенные пользователем

Что бы работало нужно заменить в файле mailProperties после '='

mail.smtps.host=                                // ваш smpt сервис (smpt.yandex.ru или smtp.googlemail.com) 

mail.smtps.user=<yourEmailAdress>             // ваш Email

outMail=<yourEmailAdress>                    // ваш Email

outMailPass=<yourEmailAdressPassword>     // ваш пароль

toMailAdress=<toEmailAddress>             // Email который хотите отправить

Работает с gmail.com и yandex.ru

Перед использованием необходимо поменять путь к файлам:

Строка 32 - путь к mailProperties

Строка 36 - путь к text

Используются библиотека Java Mail API 

скачать - https://www.oracle.com/technetwork/java/index-138643.html
