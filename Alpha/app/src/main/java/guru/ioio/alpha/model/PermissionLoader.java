package guru.ioio.alpha.model;

import java.util.ArrayList;
import java.util.List;

import guru.ioio.alpha.TestPermissionActivity;
import guru.ioio.base.adapter.IDataLoader;
import guru.ioio.base.permission.PermissionsChecker;

/**
 * Created by daniel on 9/29/17.
 */
public class PermissionLoader implements IDataLoader<PermissionBean> {
    private TestPermissionActivity testPermissionActivity;
    private OnLoadListener listener;

    private List<PermissionBean> beanList = new ArrayList<>();

    public PermissionLoader(TestPermissionActivity testPermissionActivity) {
        this.testPermissionActivity = testPermissionActivity;
        for (String p : PermissionsChecker.DANGER_PERMISSION) {
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
