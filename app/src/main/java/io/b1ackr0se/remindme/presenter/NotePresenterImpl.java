package io.b1ackr0se.remindme.presenter;

import io.b1ackr0se.remindme.view.NoteView;

public class NotePresenterImpl implements NotePresenter {

    private NoteView view;

    @Override
    public void getNote() {

    }

    @Override
    public void init(NoteView view) {
        this.view = view;
    }
}
