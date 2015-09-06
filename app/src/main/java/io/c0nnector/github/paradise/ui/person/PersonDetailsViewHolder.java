package io.c0nnector.github.paradise.ui.person;

import android.view.View;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.least.BaseViewHolder;

/**
 * Person details viewholder
 */
public class PersonDetailsViewHolder extends BaseViewHolder {

    @Bind(R.id.vPersonView)
    public PersonDetailsView personDetailsView;

    public PersonDetailsViewHolder(View itemView) {
        super(itemView);
    }
}
