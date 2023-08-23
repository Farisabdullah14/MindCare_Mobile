package id.ac.polman.astra.kelompok2MI2B.mindcare.helper;


import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

public class LinkifiedTextView extends AppCompatTextView {

    public LinkifiedTextView(Context context) {
        super(context);
        init();
    }

    public LinkifiedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinkifiedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            CharSequence text = getText();
            SpannableString spannable = SpannableString.valueOf(text);
            if (Linkify.addLinks(spannable, Linkify.WEB_URLS)) {
                setText(spannable);
                result = true;
            }
        }
        return result;
    }
}

