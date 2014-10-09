package douzifly.android.instagramscrollheader;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import douzifly.android.instagramscrollheader.ScrollViewPlus.OnScrollListener;

public class MainActivity extends Activity {

	View coverHeader;
	ScrollViewPlus scrollView;

	String text = "Our open and free Internet fuels some of the most incredible innovation in history. It provides new opportunities for billions of people to communicate and collaborate, contributes to economic growth across the world, supports a flourishing open source community, and changes the way we live our lives for the better."
+ "GitHub stands in solidarity with our Internet peers in urging all our US-based users, customers, and fans to call, write, or tweet at your local Senator or Congressperson to let them know you oppose the FCC's proposed changes to the net neutrality landscape."
+ "We believe a new Internet 'fast lane' that only privileged businesses can buy into threatens freedom of choice for users, and could ultimately harm the efforts of developers building and shipping both open source and commercial software. Without net neutrality, your users could have a very different experience of your software depending on how much Internet providers are paid."
+ "Congress has the power to take real action to ensure the Internet remains an open platform for speech and commerce. For example, when cable television called into question the traditional conflict between physical point-to-point telephone communication and airwave television broadcasts, Congress responded by adding Title VI to the Communications Act."
+ "GitHub believes that with encouragement and education from the broader Internet community, Congress can be motivated to take action once again. In May of this year, we indicated our support of net neutrality by co-signing a letter to the FCC, but we're not there yet."
+ "We think an open and free Internet is a better Internet, and today we’re asking you to join us by telling Congress you agree.";


	int headerHeight;
	int minHeaderHeight = 0;

	TextView txtHeaderTitle;
	float textViewSize = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scrollView = (ScrollViewPlus) findViewById(R.id.scrollView);
		coverHeader = findViewById(R.id.coverHeader);
		((TextView) findViewById(R.id.txtContent)).setText(text);;

		txtHeaderTitle = (TextView) findViewById(R.id.txtTitle);
		textViewSize = txtHeaderTitle.getTextSize();

		coverHeader.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				coverHeader.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				headerHeight = coverHeader.getHeight();

				findViewById(R.id.emptyView).getLayoutParams().height = headerHeight;
				coverHeader.requestLayout();
			}
		});

		scrollView.scrollListener = new OnScrollListener() {

			@Override
			public void onScroll(int scrollY) {
				int height = (int) (headerHeight - scrollY) + 1;
				if (height < minHeaderHeight) {
				    height = minHeaderHeight;
				}
				coverHeader.getLayoutParams().height = height;
				coverHeader.requestLayout();
				// use factor to play another animation
				float factor = (float) height / headerHeight;
				factor = factor > 1 ? 1 : factor;
				float size = factor * textViewSize;
				txtHeaderTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
			}
		};
	}
}
