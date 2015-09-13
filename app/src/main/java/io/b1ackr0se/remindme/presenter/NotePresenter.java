package io.b1ackr0se.remindme.presenter;

import io.b1ackr0se.remindme.common.BasePresenter;
import io.b1ackr0se.remindme.view.NoteView;

public interface NotePresenter extends BasePresenter<NoteView> {
    void getNote();
}
