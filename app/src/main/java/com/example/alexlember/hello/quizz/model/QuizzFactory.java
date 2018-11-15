package com.example.alexlember.hello.quizz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizzFactory {

    public static List<Question> initQuestions() {

        List<Question> questions = new ArrayList<>();

        try {
            // 1
            String question = "Что является риском информационной безопасности?";
            List<String> answerOptions = Arrays.asList(
                    "Штраф по результатам проверки регулятора",
                    "Возможная кража рабочего ноутбука гендира в ходе посадки на самолет",
                    "Премия за найденную уязвимость (в рамках bugbounty)",
                    "Увольнение администратора за разглашение коммерческой тайны");
            String properAnswer = "Возможная кража рабочего ноутбука гендира в ходе посадки на самолет";


            questions.add(new Question(question, answerOptions, properAnswer));


            // 2
            question = "Что означает слово «контроль» на жаргоне безопасников-западников?";
            answerOptions = Arrays.asList(
                    "Такого слова нет в их жаргоне",
                    "Желание следить за всем",
                    "Противодействие интернет-троллям",
                    "Мера защиты информации");
            properAnswer = "Мера защиты информации";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 3
            question = "К какому типу контрмеры относится вывеска \"Осторожно, злая собака!\":";
            answerOptions = Arrays.asList(
                    "Детектирующая",
                    "Корректирующая",
                    "Превентивная",
                    "Восстанавливающая");
            properAnswer = "Превентивная";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 4
            question = "Тестирование на проникновение – это:";
            answerOptions = Arrays.asList(
                    "Что-то неприличное",
                    "Попытка взлома системы хакерами",
                    "Попытка взлома системы этичными хакерами",
                    "Сканирование уязвимостей");
            properAnswer = "Попытка взлома системы этичными хакерами";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 5
            question = "Что нельзя отнести к методам аудита ИБ?";
            answerOptions = Arrays.asList(
                    "Интервью",
                    "Разовый проход по процессу",
                    "Выборочное тестирование",
                    "Стресс-интервью сотрудниц");
            properAnswer = "Стресс-интервью сотрудниц";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 6
            question = "Таргетированная атака может включать в себя:";
            answerOptions = Arrays.asList(
                    "Метание флешки со зловредом в открытое окно офиса",
                    "Выход злоумышленников на администратора через сайт знакомств",
                    "Отвлечение внимания охранника аморальным поведением",
                    "Все варианты и многое другое");
            properAnswer = "Все варианты и многое другое";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 7
            question = "Сканер-ВС позволяет:";
            answerOptions = Arrays.asList(
                    "Проводить инвентаризацию узлов и сервисов",
                    "Сканировать на наличие уязвимостей",
                    "Подбирать пароли",
                    "Все варианты и многое другое");
            properAnswer = "Все варианты и многое другое";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 8
            question = "Уязвимость нулевого дня - это:";
            answerOptions = Arrays.asList(
                    "Уязвимость, обнаруженная в последний день предыдущего месяца",
                    "Никому неизвестная уязвимость",
                    "Незакрытая уязвимость, но уже кому-то известная",
                    "Мнимая уязвимость");
            properAnswer = "Незакрытая уязвимость, но уже кому-то известная";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 9
            question = "SIEM-система КОМРАД поддерживает:";
            answerOptions = Arrays.asList(
                    "Сбор и регистрацию событий безопасности с различных источников",
                    "Корреляцию событий и формирование инцидентов",
                    "Масштабирование в распределенных сетях",
                    "Все варианты и многое другое");
            properAnswer = "Все варианты и многое другое";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 10
            question = "Комплексы Рубикон:";
            answerOptions = Arrays.asList(
                    "Включает функционал МЭ, СОВ и маршрутизатора",
                    "Позволяют организовать однонаправленную передачу данных",
                    "Сертифицированы ФСТЭК России, Минобороны России",
                    "Все варианты и многое другое");
            properAnswer = "Все варианты и многое другое";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 11
            question = "Как лучше поступить для компании, если не хватает финансовых средств на закрытие всех уязвимостей:";
            answerOptions = Arrays.asList(
                    "Замолчать проблему до лучших времен",
                    "Организовать майнинг криптовалюты, чтобы подзаработать",
                    "Сосредоточиться на наиболее критичных уязвимостях",
                    "Начать искать новую работу");
            properAnswer = "Сосредоточиться на наиболее критичных уязвимостях";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 12
            question = "Во время посещения вашего любимого веб-сайта появилось всплывающее окно «Вы только что выиграли 100 000 рублей! " +
                    "Нажмите на эту ссылку, чтобы получить свой приз!». Ваши действия?";
            answerOptions = Arrays.asList(
                    "Проигнорирую",
                    "Позвоню в милицию",
                    "Перейду по ссылке ради приза",
                    "Перешлю ссылку коллеге");
            properAnswer = "Проигнорирую";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 13
            question = "Во время работы Рубикона у вас сработала СОВ. Ваши действия?";
            answerOptions = Arrays.asList(
                    "Проверю является ли инцидентом данное событие",
                    "Выключу СОВ, чтобы не отвлекал от работы",
                    "Напишу в техподдержку Эшелона, пускай разберутся с проблемой",
                    "Сделаю вид, что не заметил. Ведь на часах уже 18:56");
            properAnswer = "Проверю является ли инцидентом данное событие";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 14
            question = "Как расшифровывается цикл PDCA:";
            answerOptions = Arrays.asList(
                    "Plan-Do-Check-Act",
                    "Prevent-Disprove-Coordinate-Attack",
                    "Panic-Drink-Cry-Ask",
                    "Pay-Dream-Call-Agree");
            properAnswer = "Plan-Do-Check-Act";
            questions.add(new Question(question, answerOptions, properAnswer));
        } catch (QuestionModelException e) {
            System.out.println("Не удалось построить модель вопросов: " + e);
        }

        Collections.shuffle(questions);

        return Collections.unmodifiableList(questions);
    }
}
