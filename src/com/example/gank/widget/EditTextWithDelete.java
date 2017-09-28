package com.example.gank.widget;

import com.example.gank.main.R;
import android.R.drawable;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class EditTextWithDelete extends EditText{
	private static final int DRAWABLE_LEFT = 0;
	private static final int DRAWABLE_TOP = 1;
	private static final int DRAWABLE_RIGHT = 2;
	private static final int DRAWABLE_BOTTOM = 3;
	private Drawable mDrawable;

	public EditTextWithDelete(Context context)
	{
		this(context, null);
	}
	
	public EditTextWithDelete(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	
	public EditTextWithDelete(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		setDeleteDrawableVisible(hasFocus() && text.length()>0);
	}
	
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		setDeleteDrawableVisible(focused && length()>0);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			float x = event.getX();
			Drawable drawable = getCompoundDrawables()[DRAWABLE_RIGHT];
			if(drawable != null && x<=(getWidth() - getPaddingRight())
					&& x>=(getWidth()-getPaddingRight()-drawable.getBounds().width()));
			setText("");
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	public void setDeleteDrawableVisible(boolean visibility)
	{
		setCompoundDrawablesWithIntrinsicBounds(
				getCompoundDrawables()[DRAWABLE_LEFT],
				getCompoundDrawables()[DRAWABLE_TOP], visibility ? mDrawable
						: null, getCompoundDrawables()[DRAWABLE_BOTTOM]);
	}

	
}
