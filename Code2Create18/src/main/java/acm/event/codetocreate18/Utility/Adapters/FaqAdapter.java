package acm.event.codetocreate18.Utility.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import acm.event.codetocreate18.Model.Data.FaqAnswerModel;
import acm.event.codetocreate18.Model.Data.FaqQuestionModel;
import acm.event.codetocreate18.Model.Holders.AnswerViewHolder;
import acm.event.codetocreate18.Model.Holders.QuestionViewHolder;
import acm.event.codetocreate18.R;

public class FaqAdapter extends ExpandableRecyclerViewAdapter<QuestionViewHolder, AnswerViewHolder> {

  public FaqAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public QuestionViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_faq_question, parent, false);
    return new QuestionViewHolder(view);
  }

  @Override
  public AnswerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_faq_answer, parent, false);
    return new AnswerViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(AnswerViewHolder holder, int flatPosition,
                                    ExpandableGroup group, int childIndex) {

    final FaqAnswerModel answer = ((FaqQuestionModel) group).getItems().get(childIndex);
    holder.setAnswer(answer.getName());
  }

  @Override
  public void onBindGroupViewHolder(QuestionViewHolder holder, int flatPosition,
                                    ExpandableGroup group) {
    holder.setQuestionTitle(group);
  }
}
