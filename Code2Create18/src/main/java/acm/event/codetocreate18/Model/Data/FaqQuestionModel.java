package acm.event.codetocreate18.Model.Data;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class FaqQuestionModel extends ExpandableGroup<FaqAnswerModel> {

  public FaqQuestionModel(String title, List<FaqAnswerModel> items) {
    super(title, items);
  }

}

