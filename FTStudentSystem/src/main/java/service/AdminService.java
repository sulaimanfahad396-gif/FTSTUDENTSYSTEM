package service;

import java.util.HashMap;
import java.util.Map;

public class AdminService {
    private Map<String,String> admins = new HashMap<>();

    public AdminService(){
        admins.put("admin1","password");
        admins.put("admin2","password");
        admins.put("admin3","password");
        admins.put("admin4","password");
        admins.put("admin5","password");
        admins.put("admin6","password");
        admins.put("admin7","password");
        admins.put("admin8","password");
        admins.put("admin9","password");
        admins.put("admin10","password");
    }

    public boolean isValidAdmin(String username, String password){
        return admins.containsKey(username) && admins.get(username).equals(password);
    }
}