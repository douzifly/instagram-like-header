/**
 * @author douzifly
 * @date 2014-9-29
 * douzifly All rights reserved.
 */
package douzifly.android.instagramscrollheader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author douzifly
 *
 */
public class ScrollViewPlus extends ScrollView{

	OnScrollListener scrollListener;
	
	/**
	 * @param context
	 * @param attrs
	 */
	public ScrollViewPlus(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void computeScroll() {
		super.computeScroll();
		if (scrollListener != null) {
			scrollListener.onScroll(getScrollY());
		}
	}
	
	public static interface OnScrollListener {
		void onScroll(int scrollY);
	}

}
