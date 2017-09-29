package guru.ioio.alpha;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import guru.ioio.alpha.model.PermissionBean;
import guru.ioio.alpha.model.PermissionLoader;
import guru.ioio.base.AbsBindingListActivity;
import guru.ioio.base.adapter.IDataLoader;
import guru.ioio.base.adapter.RVBindingBaseAdapter;
import guru.ioio.base.permission.PermissionsActivity;

/**
 * Created by daniel on 9/29/17.
 * for check permission
 */

public class TestPermissionActivity extends AbsBindingListActivity<PermissionBean> {
    private static final int REQUEST_CODE = 0; // 请求码
    private IDataLoader<PermissionBean> mLoader = new PermissionLoader(this);

    @Override
    protected RVBindingBaseAdapter<PermissionBean> getAdapter() {
        RVBindingBaseAdapter<PermissionBean> adapter =
                new RVBindingBaseAdapter<>(R.layout.item_permission, guru.ioio.alpha.BR.data);
        adapter.addPresenter(guru.ioio.alpha.BR.presenter, this);
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
    }

    @Override
    public IDataLoader<PermissionBean> getLoader() {
        return mLoader;
    }

    private PermissionBean mCheckedBean = null;

    public boolean onItemClick(PermissionBean bean) {
        mCheckedBean = bean;
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, bean.permission);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (mCheckedBean == null) {
            return;
        }
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            Toast.makeText(getApplicationContext(), "拒绝 " + mCheckedBean.name, Toast.LENGTH_SHORT).show();
            mCheckedBean.isChecked.set(false);
        } else {
            mCheckedBean.isChecked.set(true);
            Toast.makeText(getApplicationContext(), "授权 " + mCheckedBean.name, Toast.LENGTH_SHORT).show();
        }
        mCheckedBean = null;
    }


}
