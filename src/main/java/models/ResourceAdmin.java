package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResourceAdmin {
    private ObservableList<AdminUser> ListUser;

    public ResourceAdmin() {
        this.ListUser = FXCollections.observableArrayList();

    }
    public void addAdmin(AdminUser adminUser){
        ListUser.add(adminUser);
    }
    public void remove(AdminUser adminUser){
        ListUser.remove(adminUser);
    }

}
