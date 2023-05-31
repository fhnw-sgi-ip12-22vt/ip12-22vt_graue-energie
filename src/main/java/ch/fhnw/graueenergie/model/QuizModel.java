package ch.fhnw.graueenergie.model;

import ch.fhnw.graueenergie.entity.EnergyActor;
import ch.fhnw.graueenergie.entity.QuestionEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizModel {

  private Map<String, List<QuestionEntity>> questionMap;
  private final Random random = new Random();

  public QuizModel() {
    try {
      Reader reader = Files.newBufferedReader(
          Paths.get(getClass().getResource("/json/questions.json").getPath()));

      questionMap = new Gson().fromJson(reader, Map.class);

      reader.close();
    } catch (Exception e) {
      questionMap = new HashMap<>();
    }
  }

  public QuestionEntity getQuestion(EnergyActor lastChange) {
    if (lastChange == null) {
      return null;
    }

    Type listType = new TypeToken<List<QuestionEntity>>() {
    }.getType();
    Gson gson = new Gson();
    String json = gson.toJson(questionMap.get(String.valueOf(lastChange.getId())), listType);
    List<QuestionEntity> questionList = gson.fromJson(json, listType);

    if (questionList == null || questionList.size() == 0) {
      return null;
    }

    return shuffleAnswers(questionList.get(random.nextInt(questionList.size())));
  }

  public static QuestionEntity shuffleAnswers(QuestionEntity questionEntity) {
    String correctAnswer = questionEntity.answers()[questionEntity.correctAnswerId()];

    List<String> answers = Arrays.asList(questionEntity.answers());
    Collections.shuffle(answers);

    int correctAnswerId = 0;
    for (int i = 0; i < questionEntity.answers().length; i++) {
      if (correctAnswer.equals(questionEntity.answers()[i])) {
        correctAnswerId = i;
      }
    }

    return new QuestionEntity(
        questionEntity.question(),
        answers.toArray(new String[0]),
        correctAnswerId
    );
  }
}
