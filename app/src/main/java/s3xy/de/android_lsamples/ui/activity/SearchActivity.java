package s3xy.de.android_lsamples.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import s3xy.de.android_lsamples.R;

/**
 * Noodlesoup
 * <p/>
 * Created by arueggeberg on 28.10.14.
 */
public class SearchActivity extends AppCompatActivity {


    public static final String EXTRA_IMAGE = "DetailActivity.EXTRA_IMAGE";
    public static final String EXTRA_CAPTION = "DetailActivity.EXTRA_CAPTION";

    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.text)
    TextView mText;
    @Bind(R.id.my_awesome_toolbar)
    Toolbar mMyAwesomeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mMyAwesomeToolbar);


        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(mImage);
        mText.setText(getIntent().getStringExtra(EXTRA_CAPTION));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
