/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author suare
 */
public class Session {
     private static user User=null;

    public static void start(user currentUser) {
        User = currentUser;
    }

    public static user getCurrentSession() {
        if (User != null) {
            return User;
        }
        return null;

    }
    
        public static void close() throws Exception {
        if (User != null) {
            User =null;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
}
