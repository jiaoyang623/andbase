package guru.ioio.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import guru.ioio.base.adapter.RVBindingBaseAdapter;
import guru.ioio.base.databinding.ActivityAbslistBinding;


/**
 * Created by daniel on 9/29/17.
 */

public abstract class AbsBindingListActivity<T> extends Activity {
    public RVBindingBaseAdapter<T> adapter;
    protected ActivityAbslistBinding binding;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_abslist);
        adapter = getAdapter();
        binding.refresh.setLayoutManager(getLayoutManager());
    }

    protected abstract RVBindingBaseAdapter<T> getAdapter();


    public abstract RecyclerView.LayoutManager getLayoutManager();
}
