package ch.fhnw.graueenergie.entity;

public record QuestionEntity(
    String question,
    String[] answers,
    int correctAnswerId
) {

}
