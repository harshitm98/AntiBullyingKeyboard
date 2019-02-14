package com.example.fake_batman_.antibullyingkeyboard;

import android.app.Service;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private static final String TAG = "MyInputMethodService";
    public String typedText = "";

    public MyInputMethodService() {
    }

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.alphabets_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        if (inputConnection != null){
            switch (primaryCode){
                case Keyboard.KEYCODE_DELETE:
                    CharSequence selectedText = inputConnection.getSelectedText(0);
                    if(TextUtils.isEmpty(selectedText)){
                        inputConnection.deleteSurroundingText(1, 0);
                    }else{
                        inputConnection.commitText("", 1);
                        typedText.substring(0, typedText.length()-1);
                    }break;

                case Keyboard.KEYCODE_DONE:
                    inputConnection.commitText("\n".toString(), 1);
                    typedText.concat("\n");

                default:
                    char code = (char)primaryCode;
                    inputConnection.commitText(String.valueOf(code), 1);
                    typedText.concat(String.valueOf(code));

            }
        }
    }

    @Override
    public void onText(CharSequence text) {
        Log.d(TAG, "onText: " + text);
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
