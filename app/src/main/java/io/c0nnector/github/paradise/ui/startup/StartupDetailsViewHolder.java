package io.c0nnector.github.paradise.ui.startup;

import android.view.View;

import butterknife.Bind;
import io.c0nnector.github.paradise.R;
import io.c0nnector.github.least.BaseViewHolder;


public class StartupDetailsViewHolder extends BaseViewHolder {

    @Bind(R.id.vStartupView)
    public StartupDetailsView startupDetailsView;

    public StartupDetailsViewHolder(View itemView) {
        super(itemView);
    }
}
