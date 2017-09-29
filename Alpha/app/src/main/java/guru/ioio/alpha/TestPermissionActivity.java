package guru.ioio.alpha;

import android.Manifest;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import guru.ioio.base.AbsBindingListActivity;
import guru.ioio.base.adapter.IDataLoader;
import guru.ioio.base.adapter.RVBindingBaseAdapter;

/**
 * Created by daniel on 9/29/17.
 * for check permission
 */

public class TestPermissionActivity extends AbsBindingListActivity<TestPermissionActivity.PermissionBean> {

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


    public class PermissionBean {
        public String name = "";
        public String permission = "";
        public ObservableBoolean isChecked = new ObservableBoolean(false);

        public PermissionBean(String permission) {
            this.permission = permission;
            name = permission.substring(permission.lastIndexOf('.'));
        }
    }

    private IDataLoader<PermissionBean> mLoader = new PermissionLoader();

    private class PermissionLoader implements IDataLoader<PermissionBean> {
        private IDataLoader.OnLoadListener listener;
        private String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.RECEIVE_MMS
        };

        private List<PermissionBean> beanList = new ArrayList<>();

        public PermissionLoader() {
            for (String p : permissions) {
                beanList.add(new PermissionBean(p));
            }
        }

        @Override
        public List<PermissionBean> getAll() {
            return new ArrayList<>(beanList);
        }

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public boolean hasMore() {
            return false;
        }

        @Override
        public void request() {
            if (listener != null) {
                listener.onLoad(this, beanList, 0);
            }
        }

        @Override
        public void more() {
            if (listener != null) {
                listener.onLoad(this, new ArrayList<>(), beanList.size());
            }
        }

        @Override
        public void setListener(OnLoadListener l) {
            listener = l;
        }
    }
}
