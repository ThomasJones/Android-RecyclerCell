package com.tomatron.example.viewmodel;

/**
 * View model for presenting data to the footer cell.
 */
public class FooterViewModel {

    public interface ButtonListener {
        void onButtonPressed(FooterViewModel viewModel);
    }

    private final ButtonListener mButtonListener;
    private final String mTitle;
    private final String mDescription;

    public FooterViewModel(ButtonListener delegate, String title, String description) {
        mButtonListener = delegate;
        mTitle = title;
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getButtonLabel() {
        return "Button";
    }

    public void buttonClicked() {
        mButtonListener.onButtonPressed(this);
    }
}
