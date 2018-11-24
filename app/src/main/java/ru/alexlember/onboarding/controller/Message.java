package ru.alexlember.onboarding.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Обертка для отправки на почту данных о человеке.
 */
@NoArgsConstructor
@Data
public class Message {

    private String name;
    private String company;
    private String position;
    private String email;
    private boolean isInterestedInDemoMaterials;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("ФИО: ").append(name)
                //.append("\nКомпания: ").append(company)
                .append("\nEmail: ").append(email);

        if (position != null) {
            builder.append("\nДолжность: ").append(position);
        }

//        if (isInterestedInDemoMaterials) {
//            builder.append("\nХочу получать материалы");
//        } else {
//            builder.append("\nНе хочу получать материалы");
//        }

        return builder.toString();
    }
}
