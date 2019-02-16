Приложение для прохождения теста из 14 вопросов по информационной безопасности для прохождения на выставке Info security 2018 Moscow.

Ссылка на google play https://play.google.com/store/apps/details?id=ru.alexlember.hello.

При повторном прохождении порядок вопросов, а также порядок вариантов ответов перемешивается местам.
В конце сообщается результат.

Сейчас приложение полностью работает offline, но в коде есть возможность быстро подключить отправку данных по email,
а также через http или https на сервер.

Приложение может служить неплохим примером первого приложения, на базе которого можно сделать любое простое тестирование.
В коде содержатся примеры:
-Верстки с различными layout
-Перехода между activity
-Переопределения нажатия кнопки "назад"
-Использования AsyncTask
-Использования yes-no dialog
-Использования drawable resourses (создание кастомных компонентов)
-OkHttpClient для отправки запросов
-Хранение примитивных параметров приложения в SharedPreferences
-Хороший пример формы с валидацией входных данных, введенных пользователем

Для переключения между режимами можно откатить коммит, который отменяет online передачу и изменить пару файлов:
1) Прописать в strings.xml нужные данные:
    <string name="serverProtocol"></string>
    <string name="serverHost"></string>
    <string name="serverPort"></string>
    <string name="httpPath"></string>
    <string name="clientPass"></string>
2) Подложить клиентский сертификат .bks в папку raw (название должно быть такое же как и в коде).


Для генерации .bks сертификата необходимо использовать команду:
keytool -importkeystore -srckeystore clnt.jks -srcstoretype JKS -srcstorepass pass -destkeystore clnt.bks -deststoretype BKS -deststorepass pass -provider org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath ./bcprov-jdk16-1.46.jar
Предполагая, что у нас есть clnt.jks с паролем pass, а мы хотим получить blbt.bks с паролем pass.
В директории запуска команды кроме .jks должны лежать файлы
bcprov-jdk16-1.46.jar
bouncycastle.jar

Для сборки новой версии необходимо:
1) Изменить номер версии (build.gradle)
2) build - Generate signed bundle / apk - apk - next - next - все галочки - finish