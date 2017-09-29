package guru.ioio.alpha.model;

import android.databinding.ObservableBoolean;

import guru.ioio.base.permission.PermissionsChecker;

/**
 * Created by daniel on 9/29/17.
 */
public class PermissionBean {
    public String name = "";
    public String permission = "";
    public ObservableBoolean isChecked = new ObservableBoolean(false);

    public PermissionBean(String permission) {
        this.permission = permission;
        name = permission.substring(permission.lastIndexOf('.') + 1);
        isChecked.set(!PermissionsChecker.lacksPermission(permission));
    }
}
