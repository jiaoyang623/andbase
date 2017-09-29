package guru.ioio.alpha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import guru.ioio.base.adapter.AbsBindingListActivity;
import guru.ioio.base.adapter.RVBindingBaseAdapter;

/**
 * Created by daniel on 9/29/17.
 * for check permission
 */

public class TestPermissionActivity extends AbsBindingListActivity<TestPermissionActivity.PermissionBean> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RVBindingBaseAdapter<PermissionBean> getAdapter() {
        return new RVBindingBaseAdapter<>(R.layout.activity_permissions, guru.ioio.alpha.BR.data);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }


    public class PermissionBean {

    }
}
