package account;

import uml.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MayorAccountData implements Serializable {

    private final List<Manager> allManagers = new ArrayList<>();
    private final List<Manager> managersToReview = new ArrayList<>();

    public void addManager(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("Manager is required!");

        if (!allManagers.contains(manager)) {
            allManagers.add(manager);
        }
    }

    public void addManagerToReview(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("Manager is required!");

        addManager(manager);

        if (!managersToReview.contains(manager)) {
            managersToReview.add(manager);
        }
    }

    public List<Manager> getAllManagers() {
        return List.copyOf(allManagers);
    }

    public List<Manager> getManagersToReview() {
        return List.copyOf(managersToReview);
    }

    public List<Manager> getSuspendedManagers() {
        List<Manager> result = new ArrayList<>();

        for (Manager manager : allManagers) {
            if (manager.isSuspended()) {
                result.add(manager);
            }
        }

        return List.copyOf(result);
    }

    public List<Manager> getActiveManagers() {
        List<Manager> result = new ArrayList<>();

        for (Manager manager : allManagers) {
            if (!manager.isSuspended()) {
                result.add(manager);
            }
        }

        return List.copyOf(result);
    }

    public void suspendManager(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("Manager is required!");

        manager.suspend();
        managersToReview.remove(manager);
        addManager(manager);
    }

    public void unfreezeManager(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("Manager is required!");

        manager.unfreeze();
        managersToReview.remove(manager);
        addManager(manager);
    }

    public void ignoreManager(Manager manager) {
        if (manager == null) throw new IllegalArgumentException("Manager is required!");

        managersToReview.remove(manager);
        addManager(manager);
    }
}