package com.mycv.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ApiUtil {

    public static String getAuthUserName() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return obj instanceof UserDetails ? ((UserDetails) obj).getUsername() :obj.toString();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
